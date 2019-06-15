package org.test6;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerFrameHandler extends SimpleChannelInboundHandler<MessageData.RequestUser> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageData.RequestUser msg) throws Exception {
        Channel ch = ctx.channel();
        System.out.println("name: "+msg.getUserName());
        System.out.println("age: "+msg.getAge());
        System.out.println("password: "+msg.getPassword());

        MessageData.ResponseUser bank = MessageData.ResponseUser.newBuilder()
                .setAge(21)
                .setUserName("jack")
                .setPassword("123456")
                .build();

        ch.writeAndFlush(bank);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerAdded: "+ctx.channel().id().asLongText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved: "+ctx.channel().id().asLongText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
