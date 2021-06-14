package com.alan.java.debug.sample.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

public class NettyClient {

    private static final int PORT = 8000;

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap =
                    new Bootstrap()
                            .group(group)
                            .channel(NioSocketChannel.class)
                            .handler(new ChannelInitializer<SocketChannel>() {
                                @Override
                                protected void initChannel(SocketChannel ch) throws Exception {
                                    ChannelPipeline pipeline = ch.pipeline();
//                                    pipeline.addLast(new LoggingHandler());
                                    pipeline.addLast(new StringEncoder());
                                    pipeline.addLast(new StringDecoder());
                                    pipeline.addLast(new EchoClientHandler());
                                }
                            });

            ChannelFuture future = bootstrap.connect("localhost", PORT).sync();

            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String content = scanner.nextLine();
                future.channel().writeAndFlush(content);
            }

        } finally {
            group.shutdownGracefully();
        }
    }
}
