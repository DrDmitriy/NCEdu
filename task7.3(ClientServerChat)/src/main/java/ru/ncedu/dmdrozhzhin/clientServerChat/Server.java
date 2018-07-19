package ru.ncedu.dmdrozhzhin.clientServerChat;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;
import ru.ncedu.dmdrozhzhin.clientServerChat.DataBase.Account;
import ru.ncedu.dmdrozhzhin.clientServerChat.DataBase.UserDaoImpl;

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
    Map<SelectionKey, String> mapKeyLogin;
    static int staticNum = 0;
    Selector selector;
    ServerSocketChannel serverSocketChannel;
    UserDaoImpl userDao;

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
            userDao = new UserDaoImpl();
            mapKeyLogin = new HashMap<>();


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
                        //System.out.println("PPPPPPPPPPPPPPPPPPP" + key.isReadable());
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
        System.out.println("Successful connection");
    }

    private void read(SelectionKey key) throws IOException {
        if (autentifMap.get(key) == null) {
            autentifMap.put(key, false);
        }

        String messageFromChanel = readFromKey(key);
        if(messageFromChanel.length()>0) { //Если что-то получил от клиента
            System.out.println("**********************");
          /*  if (autentifMap.get(key).equals(Boolean.TRUE)) {

                System.out.println("Начинаю общение с "+ mapKeyLogin.get(key));
                //Общение
            }*/

            if(mapKeyLogin.get(key) != null){
                System.out.println("Начинаю общение с "+ mapKeyLogin.get(key));
                System.out.println(messageFromChanel);
                Message message = JsonConvertor.reversConvert(messageFromChanel,Message.class);
                //System.out.println("&&&&&" + message.getLoginFrom() + message.getMessage() + message.getLoginTo());
                String messageString = "From "+ message.getLoginFrom()+": " + message.getMessage();
                if (message.getLoginTo().equals("ToAll")){
                    for(Map.Entry<SelectionKey,String> entry: mapKeyLogin.entrySet()){
                       SelectionKey keyTo = entry.getKey();
                        if(!keyTo.equals(key)){
                            writeToKey(keyTo,"To All "+ messageString);
                        }

                    }
                }
                else {
                    SelectionKey recipientKey = keyMap.get(message.getLoginTo());
                    if(recipientKey == null) {
                        if(userDao.loginIsExsist(message.getLoginTo())){
                            writeToKey(key,message.getLoginTo() + " is offline");
                        }
                        else {
                            writeToKey(key,"Контакт \""+ message.getLoginTo()+ "\" не зарегистрирован");
                        }
                    }
                    else {
                        writeToKey(recipientKey,messageString);
                    }
                }
            }




            else {
                UserAccount userAccount = JsonConvertor.reversConvert(messageFromChanel,UserAccount.class);
                if (userAccount.getLogin().length() > 0 && userAccount.getPassword().length() > 0) {
                    //Регистрация/Вход

                    if ((userAccount.isRegistry()).equals(Boolean.TRUE)) {
                        System.out.println("Answer is registry");
                        System.out.println(userAccount);
                        if(!userDao.loginIsExsist(userAccount.getLogin())){
                            //Регистрация в БД
                            userDao.addUser(new Account(userAccount.getLogin(),userAccount.getPassword(),userAccount.getEmail()));
                           // autentifMap.put(key,true);
                            keyMap.put(userAccount.getLogin(),key);
                            mapKeyLogin.put(key,userAccount.getLogin());
                            writeToKey(key, "registryTrue");
                        }
                        else {
                            writeToKey(key, "registryFalse");
                            //Логин уже занят
                        }
                    } else {
                        System.out.println("Answer is sign in");
                        System.out.println(messageFromChanel);
                        //Проверка в БД  -> Если ок autentifMap.put(key,true);
                        if(userDao.isValidPasword(userAccount.getLogin(),userAccount.getPassword())){
                            //autentifMap.put(key,true);
                            //Добавление в мапу
                            keyMap.put(userAccount.getLogin(),key);
                            mapKeyLogin.put(key,userAccount.getLogin());
                            writeToKey(key, "signInTrue");
                            }
                        else {
                            writeToKey(key, "signInFalse");

                        }
                       //writeToKey(key,"signInFalse");

                    }


                } else {
                    writeToKey(key, "Не корректные данные");
                }
            }
        }


        //--------------------------------------------------------------
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

    private String readFromKey(SelectionKey key) {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        StringBuffer sb = new StringBuffer("");
        int len = 0;
        try {
            while ((len = socketChannel.read(buffer)) > 0) {
                sb.append(new String(buffer.array(), 0, len));
                //buffer.clear();
            }
        } catch (IOException e) {
            //e.printStackTrace();
        }
        buffer.clear();
        return sb.toString();
    }

    private void writeToKey(SelectionKey key, String messageToKey) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        socketChannel.write(ByteBuffer.wrap(messageToKey.getBytes()));
    }


}


