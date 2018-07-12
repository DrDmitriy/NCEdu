package ru.ncedu.dmdrozhzhin.clientServerChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server {
    Selector selector;
    ServerSocketChannel serverSocketChannel;
    int port = 9000;

    public Server() {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);

            InetSocketAddress inetSocketAddress = new InetSocketAddress(inetAddress, port);
            serverSocketChannel.bind(inetSocketAddress);
            serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static void main(String[] args) {
        new Server().start();
    }

    private void start() {
        while (true) {
            try {
                int num = selector.select();
                if(num > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();

                        if(key.isAcceptable()){
                            connect(key);
                        }
                        else if(key.isReadable()){
                            read(key);
                        }
                       /* else if (key.isValid()&&key.isWritable()){
                            write(key);
                        }*/
                        else {
                            System.out.println("key is not valid");
                        }
                    }

                }
               // Thread.yield();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }





    private void connect(SelectionKey key) throws IOException {
        ServerSocketChannel connectChanel = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = connectChanel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector,SelectionKey.OP_READ);
        System.out.println("Successful connection");
    }
    private void read(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
       //System.out.print(socketChannel.socket().getRemoteSocketAddress() + " ");
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //System.out.println("!!!! "+ new String(buffer.array(),0));
        buffer.clear();

        StringBuffer stringBuffer = new StringBuffer();
        int len =0 ;
        while ((len = socketChannel.read(buffer)) > 0) {
            buffer.flip();
            stringBuffer.append(new String(buffer.array(), 0 ,len));
        }
        if (stringBuffer.length()>0){
            System.out.println(socketChannel.socket().getRemoteSocketAddress() + ">>  " + stringBuffer.toString());
            //write(socketChannel,stringBuffer);

            //key.interestOps(SelectionKey.OP_WRITE);
            write(socketChannel, stringBuffer);
            key.interestOps(SelectionKey.OP_READ);
        }
        //selector.wakeup();

    }

    private void write (SocketChannel chanel , StringBuffer sb) {
        try {
            chanel.write(ByteBuffer.wrap(sb.toString().toUpperCase().getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  /*  private void write(SelectionKey key) {
        SocketChannel channel = (SocketChannel) key.channel();

        try {
            System.out.println("Записываю в поток");
            channel.write(ByteBuffer.wrap(sb.toString().toUpperCase().getBytes()));
            //channel.write(ByteBuffer.wrap("SERVER ANSWER".getBytes()));
            key.interestOps(SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }*/

        //try {


            //ServerSocket serverSocket = serverSocketChannel.socket();


            //serverSocket.bind(inetSocketAddress);//bind the ServerSoket

            //serverSocketChannel.configureBlocking(false);

           // int validOps = serverSocketChannel.validOps();
            // serverSocketChannel.register(selector, validOps, SelectionKey.OP_READ);
            //SelectionKey selectKy = serverSocketChannel.register(selector, validOps, null);

         /*   while (true) {
                selector.select();

                Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                //System.out.println(selectionKeySet.size());
                Iterator<SelectionKey> i = selectionKeySet.iterator();

                while (i.hasNext()) {
                    SelectionKey key = i.next();

                    if (key.isAcceptable()) {
                        System.out.println("Acceptable block");
                        SocketChannel soketChannelCleint = serverSocketChannel.accept();
                        soketChannelCleint.configureBlocking(false);
                        soketChannelCleint.register(selector, SelectionKey.OP_READ);
                    } else if ((key.isReadable())) {
                        //System.out.println("Readable block");
                        SocketChannel myClient = (SocketChannel) key.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        StringBuilder sb = new StringBuilder();
                        int num =0;
                        while ((num = myClient.read(byteBuffer))>0) {
                            sb.append((new String(byteBuffer.array())));
                        }
                        System.out.println(sb.toString());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        key.interestOps(SelectionKey.OP_WRITE);
                    }
                    else if (key.isValid()&&key.isWritable()){
                        System.out.println("block write");
                        SocketChannel sc = (SocketChannel) key.channel();

                        sc.write(ByteBuffer.wrap("server answer".getBytes()));
                        Thread.sleep(3000);

                    }

                    i.remove();

                }
            }


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

    }


