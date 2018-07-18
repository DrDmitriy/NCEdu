package ru.ncedu.dmdrozhzhin.clientServerChat;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;

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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Server {
    Map<String,SelectionKey> keyMap;
    static  int staticNum = 0;
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
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            keyMap = new HashMap<>();


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
                if (num > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();

                        if (key.isAcceptable()){
                            connect(key);
                        } else if (key.isReadable()) {
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
        socketChannel.register(selector, SelectionKey.OP_READ);
        System.out.println("Successful connection");
        staticNum++;

    }
    private void read(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        //System.out.print(socketChannel.socket().getRemoteSocketAddress() + " ");
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //System.out.println("!!!! "+ new String(buffer.array(),0));
        buffer.clear();


        StringBuffer stringBuffer = new StringBuffer();
        int len = 0;

        while ((len = socketChannel.read(buffer)) > 0) {
            buffer.flip();
            stringBuffer.append(new String(buffer.array(), 0, len));
        }
//        if(key.attachment()== null){
//            key.attach("Client " + staticNum);
//        }

        if (stringBuffer.length() > 0) {
            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println(socketChannel.socket().getRemoteSocketAddress() + ">>  " + stringBuffer.toString());
            Message message = objectMapper.readValue(stringBuffer.toString(),Message.class);
            String messageString = "From " + message.getLoginFrom()+" :" + message.getMessage();

            //Если сообщение пустое  --> Регистрация -> пробуем добавить в базу
            if (message.getMessage().equals(" ")){
                if(keyMap.get(message.getLoginFrom())== null) {
                    keyMap.put(message.getLoginFrom(),key);
                    System.out.println(message.getLoginFrom()+" успешно зарегистрирован");
                }
                else {
                    write(key,"Такой логин уже используется");
                    //
                }
            }

           else if (message.getLoginTo().equals("ToAll")){
                //broadCast


                for (SelectionKey k : selector.selectedKeys()){
                    write(k,messageString);
                }


            }
            else  {
                SelectionKey recipientKey = keyMap.get(message.getLoginTo());

                write(recipientKey,messageString);
            }


            //write(socketChannel,stringBuffer);
            //key.interestOps(SelectionKey.OP_WRITE);
            //write(key, stringBuffer);
            key.interestOps(SelectionKey.OP_READ);
        }
        //selector.wakeup();
    }

    private void write(SelectionKey key, String s) {
        try {
            //System.out.println(key.attachment());
            SocketChannel socketChannel = (SocketChannel) key.channel();
            socketChannel.write(ByteBuffer.wrap(s.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


