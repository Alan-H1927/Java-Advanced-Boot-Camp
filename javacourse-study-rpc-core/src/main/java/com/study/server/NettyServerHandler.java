package com.study.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.base.Joiner;
import com.study.api.RpcRequest;
import com.study.api.RpcResponse;
import com.study.cache.ProviderCache;
import com.study.protocol.RpcProtocol;
import com.study.util.ConvertUtil;
import com.study.util.JsonUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-07-01
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(NettyServerHandler.class);

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
        logger.info("进入NettyServerHandler");
        // 用于获取客户端发来的数据信息
        RpcProtocol rpcProtocol = (RpcProtocol) msg;
        //获取请求参数内容
        String content = ConvertUtil.convertByteToString(rpcProtocol.getContent());
        logger.info("获取客户端请求参数内容为[{}]", content);
        //参数转换
        RpcRequest rpcRequest = (RpcRequest) JsonUtil.convertJsonToObject(content, RpcRequest.class);
        //调用
        RpcResponse rpcResponse = invoke(rpcRequest);
        //参数转换
        String rpcResponseJson = JSON.toJSONString(rpcResponse);
        // 回写数据给客户端
        RpcProtocol response = new RpcProtocol(rpcResponseJson.getBytes().length,
                rpcResponseJson.getBytes());
        // 当服务端完成写操作后，关闭与客户端的连接
        ctx.writeAndFlush(response);
        // .addListener(ChannelFutureListener.CLOSE);
    }

    private RpcResponse invoke(RpcRequest request) {
        RpcResponse response = new RpcResponse();

        logger.info("service provider name: " + request.getServiceClass());

        try {
            Object targetFromCache = getTargetFromCache(request);
            logger.info("getTargetFromCache is [{}]", targetFromCache);
            Method method = resolveMethodFromClass(targetFromCache.getClass(), request.getMethod());
            Object result = method.invoke(targetFromCache, request.getArgv());
            logger.info("Server method invoke result: " + result.toString());
            // 两次json序列化能否合并成一个
            response.setResult(JSON.toJSONString(result, SerializerFeature.WriteClassName));
            response.setStatus(true);
            logger.info("Server Response serialize to string return,response is [{}]", response);
            return response;
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            response.setException(e);
            response.setStatus(false);
            return response;
        }
    }

    private Method resolveMethodFromClass(Class<?> klass, String methodName) {
        return Arrays.stream(klass.getMethods()).filter(m -> methodName.equals(m.getName())).findFirst().get();
    }

    private Object getTargetFromCache(RpcRequest request) {
        return ProviderCache.CACHE.get(Joiner.on(":").join(request.getServiceClass(), request.getGroup(), request.getVersion(), request.getWeight()));
    }
}
