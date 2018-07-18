package ru.ncedu.dmdrozhzhin.clientServerChat;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonConvertor {
    static ObjectMapper mapper = new ObjectMapper();


    public static byte[] convert(Message message) {

        byte[] messageInByte = new byte[0];
        try {
            messageInByte = mapper.writeValueAsBytes(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return messageInByte;
    }


}