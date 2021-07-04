package com.study.server;

import com.study.constant.NettyConstant;
import com.study.discovery.ServiceDiscovery;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务端
 *
 * @author me-ht
 * @date 2021-07-01
 */
public class NettyServer {

    private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);

    public void run() {
        logger.info("ServiceServer启动");
        //注册服务
        register();
        //启动netty
        EventLoopGroup bossGroup = new NioEventLoopGroup(2);
        EventLoopGroup workerGroup = new NioEventLoopGroup(16);
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_BACKLOG, 1024);
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                            ChannelPipeline channelPipeline = nioSocketChannel.pipeline();
                            channelPipeline.addLast(new NettyServerInitializer());
                        }
                    });
            ChannelFuture channelFuture = b.bind(NettyConstant.NETTY_HOST, NettyConstant.NETTY_PORT).sync();
            logger.info("开启netty http服务器，监听地址和端口为 http://127.0.0.1:[{}]", NettyConstant.NETTY_PORT);
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            logger.error("NettyServer occur error", e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private void register() {
        logger.info("开始注册ZK服务");
        //服务发现
        ServiceDiscovery serverDiscovery = new ServiceDiscovery();
        try {
            serverDiscovery.register();
        } catch (Exception e) {
            logger.error("服务注册失败", e);
            throw new RuntimeException(e);
        }
    }
}
