package ru.ncedu.dmdrozhzhin.decodeString;

public class Main {
    public static void main(String[] args) {
        String test = "1[T]";
        System.out.println(
        CheckString.isValid(test));
        StringDecoder stringDecoder = new StringDecoder(test);
        stringDecoder.decodeString();
    }
}
