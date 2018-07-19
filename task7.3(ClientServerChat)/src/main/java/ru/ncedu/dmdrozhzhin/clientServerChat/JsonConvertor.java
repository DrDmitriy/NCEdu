package ru.ncedu.dmdrozhzhin.clientServerChat;

import org.codehaus.jackson.map.ObjectMapper;
import java.io.IOException;

public class JsonConvertor {
    static ObjectMapper mapper = new ObjectMapper();
    public static <T> byte[] convert(T obj) {
        byte[] objIByte = new byte[0];
        try {
            objIByte = mapper.writeValueAsBytes(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objIByte;
    }

    public static  <T>  T reversConvert(byte[] bytes, Class<T> claz){
        ObjectMapper mapper = new ObjectMapper();
        T tObj = null;
        try {
           tObj  = (T)mapper.readValue(bytes,claz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tObj;
    }
    public static  <T>  T reversConvert(String s, Class<T> claz){
        ObjectMapper mapper = new ObjectMapper();
        T tObj = null;
        try {
            tObj  = mapper.readValue(s,claz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tObj;
    }

  //Testing
 /*   public static void main(String[] args){
        Message message = reversConvert(convert(new Message("123","123","")),Message.class);
        System.out.println(message.getLoginFrom() + " " + message.getMessage());
    }
*/

}