package org.test9;

import java.nio.IntBuffer;
import java.security.SecureRandom;

public class NioTest {
    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(10);
        for(int i = 0; i < 5; i++){
            int randomNumber = new SecureRandom().nextInt(20);
            buffer.put(randomNumber);
        }

        System.out.println("before limit: "+buffer.limit());
        buffer.flip();
        System.out.println("after limit: "+buffer.limit());
        System.out.println("-----------------------------------------");
        while(buffer.hasRemaining()){
            System.out.println("position: "+buffer.position());
            System.out.println("limit: "+buffer.limit());
            System.out.println("capacity: "+buffer.capacity());
            System.out.println("data: "+buffer.get());
        }
    }
}
