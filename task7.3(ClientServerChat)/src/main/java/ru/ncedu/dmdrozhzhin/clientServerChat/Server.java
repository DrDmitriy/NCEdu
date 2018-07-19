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
    Map<String, SelectionKey> keyMap;
    Map<SelectionKey, Boolean> autentifMap;
    static int staticNum = 0;
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
            autentifMap = new HashMap<>();


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

                        if (key.isAcceptable()) {
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
        //При первом соединение клиент не авторизирован -> Делаю метку в attachment

        System.out.println("Successful connection");
    }

    private void read(SelectionKey key) throws IOException {
        if(autentifMap.get(key) == null ){
            autentifMap.put(key,false);
        }

        String messageFromChanel = readFromKey(key);
        if(messageFromChanel.length()>0) { //Если что-то получил от клиента
            if (autentifMap.get(key).equals(Boolean.TRUE)) {
                //Общение
            } else { //Регистрация/Вход
                if (messageFromChanel.equals("registry")){
                    writeToKey(key,"registryOk");
                    System.out.println("Answer is registry");
                }
                else if(messageFromChanel.equals("sign in")){
                    writeToKey(key,"signInOk");
                    System.out.println("Answer is sign in");
                }
                else {
                    writeToKey(key,"Не верная команада");
                }


            }
        }
       /*

        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.clear();
        StringBuffer stringBuffer = new StringBuffer();
        int len = 0;

        while ((len = socketChannel.read(buffer)) > 0) {
            buffer.flip();
            stringBuffer.append(new String(buffer.array(), 0, len));
        }

        if (stringBuffer.length() > 0) {


            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println(socketChannel.socket().getRemoteSocketAddress() + ">>  " + stringBuffer.toString());
            Message message = objectMapper.readValue(stringBuffer.toString(), Message.class);
            String messageString = "From " + message.getLoginFrom() + " :" + message.getMessage();

            //Если сообщение пустое  --> Регистрация -> пробуем добавить в базу
            if (message.getMessage().equals(" ")) {
                if (keyMap.get(message.getLoginFrom()) == null) {
                    keyMap.put(message.getLoginFrom(), key);
                    System.out.println(message.getLoginFrom() + " успешно зарегистрирован");
                    write(key, "true");
                } else {
                    write(key, "false");
                    //
                }
            } else if (message.getLoginTo().equals("ToAll")) {
                //broadCast


                for (Map.Entry<String, SelectionKey> entry : keyMap.entrySet()) {

                    SelectionKey rKey = entry.getValue();
                    if (!rKey.equals(key)) {
                        write(entry.getValue(), "To All " + messageString);
                    }
                }


            } else {
                SelectionKey recipientKey = keyMap.get(message.getLoginTo());
                if (recipientKey == null) {
                    write(key, "Такой контакт не зарегистрирован");
                } else {

                    write(recipientKey, messageString);

            }


            key.interestOps(SelectionKey.OP_READ);
        }
           }*/
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

   /* private boolean authentication(SocketChannel socketChannel) throws IOException {
        writeToKey(socketChannel, "Регистрация/Вход?");
        boolean waitAnswer = true;
        String answer = "";
        answer = readFromKey(socketChannel);
        System.out.println("Try read In  authen answer is " + answer);
        //Если ответ пустой, жду ответ. 2 варината ответа(вход/регистрация)
      *//*  if (!answer.equals("")) {
            waitAnswer = false;
        }*//*
        System.out.println("Answer is " + answer);
        //Регистрация

        if (answer.equals("registry")) {
            System.out.println("Регистрация");
            return true;
        }

        if (answer.equals("sign in")) {
            System.out.println("Вход");
            return true;
        }
        return false;


    }*/

    private String readFromKey(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        StringBuffer sb = new StringBuffer("");
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.clear();
            int len = 0;
            while ((len = socketChannel.read(buffer)) > 0) {
                sb.append(new String(buffer.array(), 0, len));
            }
        return sb.toString();
    }

    private void writeToKey(SelectionKey key, String messageToKey) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        socketChannel.write(ByteBuffer.wrap(messageToKey.getBytes()));
    }
}


