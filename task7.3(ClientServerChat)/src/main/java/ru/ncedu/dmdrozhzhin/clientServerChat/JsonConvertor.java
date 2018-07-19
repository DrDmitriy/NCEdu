package ru.ncedu.dmdrozhzhin.clientServerChat;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonConvertor {
    static ObjectMapper mapper = new ObjectMapper();


  /*  public static byte[] convert(Message message) {

        byte[] messageInByte = new byte[0];
        try {
            messageInByte = mapper.writeValueAsBytes(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return messageInByte;
    }*/

    public static <T> byte[] convert(T obj) {

        byte[] objIByte = new byte[0];
        try {
            objIByte = mapper.writeValueAsBytes(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return objIByte;
    }


    // Написать метод обратного конвертирования
    public static  <T>  T reversConvert(byte[] bytes, Class<T> claz){
        ObjectMapper mapper = new ObjectMapper();
      //  Class reverseClass = claz.getClass();
        T tObj = null;
        try {
           tObj  = (T)mapper.readValue(bytes,claz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tObj;
    }
    public static  <T>  T reversConvert(String s, Class<T> claz){
        //return reversConvert(s.getBytes(),claz);
        ObjectMapper mapper = new ObjectMapper();
        //  Class reverseClass = claz.getClass();
        T tObj = null;
        try {
            tObj  = mapper.readValue(s,claz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tObj;
    }

  //Testing
    public static void main(String[] args){
        Message message = reversConvert(convert(new Message("123","123","")),Message.class);
        System.out.println(message.getLoginFrom() + " " + message.getMessage());
    }


}