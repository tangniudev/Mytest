package IO.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Description
 *
 * @author tyw
 * @since 2022/3/23
 */
public class AIO {
    public static void main(String[] args) throws InterruptedException{
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open().bind(
                            new InetSocketAddress(1111));
                    server.accept(null,new CompletionHandler<AsynchronousSocketChannel, Object>() {//获取客户端连接方法
                        @Override
                        public void completed(AsynchronousSocketChannel acceptChannel, Object attachment) {
                            System.out.println(new Date().getTime()+"accept");
                            ByteBuffer buffer = ByteBuffer.allocate(6);
                            acceptChannel.read(buffer, 10L, TimeUnit.SECONDS, null, new CompletionHandler<Integer, Object>() {//读取数据方法
                                @Override
                                public void completed(Integer result, Object attachment) {
                                    if (result != -1) {
                                        buffer.flip();
                                        System.out.println(new Date().getTime()+"received message:" + Charset.forName("UTF-8").decode(buffer));
                                        buffer.clear();
                                    }else{
                                        try {
                                            acceptChannel.close();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                                @Override
                                public void failed(Throwable exc, Object attachment) {
                                    exc.printStackTrace();
                                }
                            });
                        }
                        @Override
                        public void failed(Throwable exc, Object attachment) {
                            exc.printStackTrace();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        },"服务器线程-").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
                    System.out.println(new Date().getTime()+"connect");
                    client.connect(new InetSocketAddress("127.0.0.1", 1111)).get();
                    Thread.sleep(5000);
                    System.out.println(new Date().getTime()+"send message:hello");
                    client.write(ByteBuffer.wrap("hello".getBytes()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"客户端线程1-").start();
        Thread.sleep(50000);
    }
}

