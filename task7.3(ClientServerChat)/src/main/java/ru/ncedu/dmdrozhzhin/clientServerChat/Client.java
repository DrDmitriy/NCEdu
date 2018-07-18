package ru.ncedu.dmdrozhzhin.clientServerChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
    private String login;
    SocketChannel socketChannel;
    boolean readRunnig = true;

    public Client() {
        regestryLogin();
        new Thread(new MessageOutput()).start();
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.getConnection();
        client.read();
    }

    private void getConnection() {
        int port = 9000;
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            socketChannel = SocketChannel.open(new InetSocketAddress(inetAddress, port));
            socketChannel.configureBlocking(false);
            socketChannel.write(ByteBuffer.wrap(JsonConvertor.convert(new Message(login," "," "))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read() {
        int num = 0;
        StringBuffer sb = new StringBuffer();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //SocketChannel socketChannel2 =  socketChannel.socket().getChannel();
        while (readRunnig) {
            buffer.clear();
            try {
                while ((num = socketChannel.read(buffer)) > 0) {
                    sb.append(new String(buffer.array(), 0, num));
                    buffer.clear();
                    //System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
                }
                if (sb.length() > 0) {
                    //System.out.println();
                    System.out.println("<<< " + sb.toString());
                    sb.delete(0, sb.length());
                }
                buffer.flip();
            } catch (IOException ex) {
            }
        }
    }

    class MessageOutput implements Runnable {
        boolean running = true;
        public void close() {
            running = false;
        }

        @Override
        public void run() {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (running) {
                // System.out.print (">>> ");
                try {
                    String str = reader.readLine();
                    Message message = new Message("ClientName1",str,"ToAll");

                    socketChannel.write(ByteBuffer.wrap(JsonConvertor.convert(message)));
                    //socketChannel.write(ByteBuffer.wrap(str.getBytes()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private void regestryLogin(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите логин");
        try {
            login = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}