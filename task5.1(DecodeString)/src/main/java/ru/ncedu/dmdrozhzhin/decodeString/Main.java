package ru.ncedu.dmdrozhzhin.decodeString;

public class Main {
    public static void main(String[] args) {
        String test = "2[U3[]2[T]]";
        StringDecoder stringDecoder = new StringDecoder(test);
        stringDecoder.decodeString();
    }
}
