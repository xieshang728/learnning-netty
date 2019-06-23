package org.test9;

import io.netty.buffer.ByteBuf;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest4 {
    public static void main(String[] args) throws Exception{
        FileInputStream fis = new FileInputStream("in.txt");
        FileOutputStream fos = new FileOutputStream("out.txt");

        FileChannel fc1 = fis.getChannel();
        FileChannel fc2 = fos.getChannel();

        ByteBuffer bb = ByteBuffer.allocate(512);

        while(true){
            bb.clear();
            int read = fc1.read(bb);
            System.out.println("read:"+read);
            if(read == -1){
                break;
            }
            bb.flip();
            fc2.write(bb);
        }


    }
}
