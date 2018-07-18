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

    }

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }
    public void start(){
        getConnection();
        regestryLogin();
        new Thread(new MessageOutput()).start();
        read();
    }

    private void getConnection() {
        int port = 9000;
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            socketChannel = SocketChannel.open(new InetSocketAddress(inetAddress, port));
            socketChannel.configureBlocking(false);

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
                    System.out.println("Введите получателя [ToAll or User Login]");
                    String recipient = reader.readLine();
                    System.out.println("Введите сообщение");
                    String str = reader.readLine();
                    Message message = new Message(login,str,recipient);

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
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        StringBuffer sb = new StringBuffer();
        try {
            login = reader.readLine();
            socketChannel.write(ByteBuffer.wrap(JsonConvertor.convert(new Message(login," "," "))));

            int num = 0;
           // buffer.flip();
            Thread.sleep(1000);
//while (sb.toString().equals("")){

        while ((num = socketChannel.read(buffer)) > 0) {
            sb.append(new String(buffer.array(), 0, num));
            buffer.clear();
        }
//}
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (sb.toString().equals("true")){
            System.out.println(login+ " успешно зарегистрирован");
        }
        else {
            System.out.println(sb.toString()+ " Такой логие уже используется");
            regestryLogin();
        }

    }
}