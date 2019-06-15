package org.test7;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

public class MyClientHandler extends SimpleChannelInboundHandler<Message.DataInfo> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message.DataInfo msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        int i = new Random().nextInt()%3;
        Message.DataInfo dataInfo = null;
        if(i == 0){
            dataInfo = Message.DataInfo.newBuilder()
                    .setData(Message.DataInfo.DataType.PersonType)
                    .setPerson(Message.Person.newBuilder()
                    .setAge(21)
                    .setUserName("jack")
                    .setAddress("street1").build())
                    .build();
        }else if(i == 1) {
            dataInfo = Message.DataInfo.newBuilder()
                    .setData(Message.DataInfo.DataType.DogType)
                    .setDog(Message.Dog.newBuilder()
                            .setName("jack")
                            .setAddress("street1").build())
                    .build();
        }else{
            dataInfo = Message.DataInfo.newBuilder()
                    .setData(Message.DataInfo.DataType.CatType)
                    .setCat(Message.Cat.newBuilder()
                            .setName("jack")
                            .setCity("street1").build())
                    .build();
        }
        ctx.channel().writeAndFlush(dataInfo);
    }
}
