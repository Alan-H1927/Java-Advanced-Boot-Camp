package com.study.javacourse.week3.handler;

import com.study.javacourse.week3.bean.Response;
import com.study.javacourse.week3.filter.HeaderHttpRequestFilter;
import com.study.javacourse.week3.filter.HeaderHttpResponseFilter;
import com.study.javacourse.week3.filter.HttpRequestFilter;
import com.study.javacourse.week3.filter.HttpResponseFilter;
import com.study.javacourse.week3.util.JsonUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.util.ReferenceCountUtil;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-05-23
 */
public class DefaultHandler extends AbstractHandler implements Handler {

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            logger.info("channelRead流量接口请求开始，时间为{}", timestamp);
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            requestFilter.filter(fullRequest, ctx);
            handle(fullRequest, ctx);
        } catch (Exception e) {
            logger.error("channelRead exception", e);
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    public void handle(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        System.out.println("使用DefaultHandler");
        FullHttpResponse response = null;
        try {
            String value = JsonUtil.beanToString(buildResponse());
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(value.getBytes(StandardCharsets.UTF_8)));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", response.content().readableBytes());
            responseFilter.filter(response);
        } catch (Exception e) {
            System.out.println("处理出错:" + e.getMessage());
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }
            }
        }
    }

    private Response buildResponse() {
        Response response = new Response();
        response.setCode("200");
        response.setMessage("DefaultHandler");
        return response;
    }

}
