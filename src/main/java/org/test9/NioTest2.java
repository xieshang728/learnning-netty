package org.test9;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

public class NioTest2 {

    public static void main(String[] args) throws Exception{
        FileInputStream fileInputStream = new FileInputStream("nio.txt");
        FileChannel fileChannel = fileInputStream.getChannel();

        ByteBuffer charBuffer = ByteBuffer.allocate(512);
        fileChannel.read(charBuffer);

        charBuffer.flip();
        while(charBuffer.hasRemaining()){
            byte b = charBuffer.get();
            System.out.println(b);
        }
    }
}
