package ru.ncedu.dmdrozhzhin.clientServerChat;

import ru.ncedu.dmdrozhzhin.clientServerChat.DataBase.Account;
import ru.ncedu.dmdrozhzhin.clientServerChat.DataBase.UserDaoImpl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Server {
    Map<String, SelectionKey> keyMap;
    Map<SelectionKey, String> mapKeyLogin;
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
                        iterator.remove();

                        if (key.isAcceptable()) {
                            connect(key);
                        } else if (key.isReadable()) {
                            read(key);
                        } else {
                            System.out.println("key is not valid");
                        }
                    }
                }
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
        String messageFromChanel = readFromKey(key);
        if (messageFromChanel.length() > 0) { //Если что-то получил от клиента
            if (mapKeyLogin.get(key) != null) {
                System.out.println("Начинаю общение с " + mapKeyLogin.get(key));
                System.out.println(messageFromChanel);
                Message message = JsonConvertor.reversConvert(messageFromChanel, Message.class);
                String messageString = "From " + message.getLoginFrom() + ": " + message.getMessage();
                if (message.getLoginTo().equals("ToAll")) {
                    for (Map.Entry<SelectionKey, String> entry : mapKeyLogin.entrySet()) {
                        SelectionKey keyTo = entry.getKey();
                        if (!keyTo.equals(key)) {
                            writeToKey(keyTo, "To All " + messageString);
                        }
                    }
                } else {
                    SelectionKey recipientKey = keyMap.get(message.getLoginTo());
                    if (recipientKey == null) {
                        if (userDao.loginIsExsist(message.getLoginTo())) {
                            writeToKey(key, message.getLoginTo() + " is offline");
                        } else {
                            writeToKey(key, "Контакт \"" + message.getLoginTo() + "\" не зарегистрирован");
                        }
                    } else {
                        writeToKey(recipientKey, messageString);
                    }
                }
            } else {
                UserAccount userAccount = JsonConvertor.reversConvert(messageFromChanel, UserAccount.class);
                if (userAccount.getLogin().length() > 0 && userAccount.getPassword().length() > 0) {
                    //Регистрация/Вход

                    if ((userAccount.isRegistry()).equals(Boolean.TRUE)) {
                        System.out.println("Answer is registry");
                        System.out.println(userAccount);
                        if (!userDao.loginIsExsist(userAccount.getLogin())) {
                            //Регистрация в БД
                            userDao.addUser(new Account(userAccount.getLogin(), userAccount.getPassword(), userAccount.getEmail()));
                            keyMap.put(userAccount.getLogin(), key);
                            mapKeyLogin.put(key, userAccount.getLogin());
                            writeToKey(key, "registryTrue");
                        } else {
                            writeToKey(key, "registryFalse");
                            //Логин уже занят
                        }
                    } else {
                        System.out.println("Answer is sign in");
                        System.out.println(messageFromChanel);
                        //Проверка в БД  -> Если ок autentifMap.put(key,true);
                        if (userDao.isValidPasword(userAccount.getLogin(), userAccount.getPassword())) {
                            //Добавление в мапу
                            keyMap.put(userAccount.getLogin(), key);
                            mapKeyLogin.put(key, userAccount.getLogin());
                            writeToKey(key, "signInTrue");
                        } else {
                            writeToKey(key, "signInFalse");
                        }
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


