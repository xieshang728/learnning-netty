package org.test7;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyServerHandler extends SimpleChannelInboundHandler<Message.DataInfo> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message.DataInfo msg) throws Exception {
        Message.DataInfo.DataType type = msg.getData();
        if(type.equals(Message.DataInfo.DataType.PersonType)){
            System.out.println("-------person---------");
            Message.Person person = msg.getPerson();
            System.out.println(person);
        }else if(type.equals(Message.DataInfo.DataType.DogType)){
            System.out.println("-------dog------------");
            Message.Dog dog = msg.getDog();
            System.out.println(dog);
        }else {
            System.out.println("--------cat------------");
            Message.Cat cat = msg.getCat();
            System.out.println(cat);
        }
    }
}
