package IO.demo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

/**
 * Description
 *
 * @author tyw
 * @since 2022/3/23
 */
public class NIO {
    public static void main(String[] args) throws IOException,InterruptedException{
        Selector selector = Selector.open();
        ServerSocketChannel serverChannel = ServerSocketChannel.open();;
        serverChannel.configureBlocking(false);//设置为非阻塞模式
        serverChannel.socket().bind(new InetSocketAddress(1111), 1024);//绑定监听的端口地址
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);//将ServerSocketChannel注册到Selector，交给Selector监听
        System.out.println("start listen thread!");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        System.out.println(new Date().getTime()+" start select!"+"当前线程:"+Thread.currentThread().getName()+Thread.currentThread().getId());
                        selector.select();
                        System.out.println(new Date().getTime()+"select complete!"+"当前线程:"+Thread.currentThread().getName()+Thread.currentThread().getId());
                        Set<SelectionKey> selectionKeys = selector.selectedKeys();
                        for (SelectionKey selectionKey : selectionKeys) {
                            try {
                                if(selectionKey.isValid()){//判断key是否有效
                                    if (selectionKey.isAcceptable()) {
                                        ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                                        SocketChannel client = server.accept();//获取到客户端的通道
                                        client.configureBlocking(true);//设置成阻塞
                                        ByteBuffer receivebuffer = ByteBuffer.allocate(1024);
                                        //读取客户端请求消息到缓冲区
                                        int count = client.read(receivebuffer);   //非阻塞
                                        if (count > 0) {
                                            receivebuffer.flip();
                                            byte[] bytes = new byte[receivebuffer.remaining()];
                                            receivebuffer.get(bytes);
                                            String body = new String(bytes, "UTF-8");
                                            System.out.println(new Date().getTime()+"receive message:"+body+"当前线程:"+Thread.currentThread().getName()+Thread.currentThread().getId());
                                        }else{
                                            client.close();
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                if(selectionKey!=null){
                                    selectionKey.cancel();
                                    if(selectionKey.channel()!=null){
                                        selectionKey.channel().close();
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        },"服务器线程-").start();
        Thread.sleep(5000);
        SocketChannel socketChannel = SocketChannel.open();
        new Thread(new Runnable() {//新起线程代表客户端发送数据
            @Override
            public void run() {
                try {
                    boolean connect = socketChannel.connect(new InetSocketAddress("127.0.0.1", 1111));
                    if(connect){
                        Thread.sleep(5000);//隔5秒钟在写
                        ByteBuffer sendbuffer = ByteBuffer.allocate(1024);
                        sendbuffer.put("hello!".getBytes());
                        sendbuffer.flip();
                        socketChannel.write(sendbuffer);
                        if(!sendbuffer.hasRemaining()){
                            System.out.println(new Date().getTime()+"Send hello to server succeed."+"当前线程:"+Thread.currentThread().getName()+Thread.currentThread().getId());
                        }else{
                            System.out.println("has remaining!");
                        }
                    }else{
                        System.out.println("connect error!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"客户端线程1-").start();
        SocketChannel socketChannel2 = SocketChannel.open();
        new Thread(new Runnable() {//新起线程代表客户端发送数据
            @Override
            public void run() {
                try {
                    boolean connect = socketChannel2.connect(new InetSocketAddress("127.0.0.1", 1111));
                    if(connect){
                        Thread.sleep(5000);//隔5秒钟在写
                        ByteBuffer sendbuffer = ByteBuffer.allocate(1024);
                        sendbuffer.put("hello!".getBytes());
                        sendbuffer.flip();
                        socketChannel2.write(sendbuffer);
                        if(!sendbuffer.hasRemaining()){
                            System.out.println(new Date().getTime()+"Send hello to server succeed."+"当前线程:"+Thread.currentThread().getName()+Thread.currentThread().getId());
                        }else{
                            System.out.println("has remaining!");
                        }
                    }else{
                        System.out.println("connect error!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"客户端线程2-").start();
    }
}

