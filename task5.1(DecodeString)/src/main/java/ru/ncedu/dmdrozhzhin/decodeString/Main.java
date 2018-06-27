package ru.ncedu.dmdrozhzhin.decodeString;

public class Main {
    public static void main(String[] args) {
        String test = "2[TU5[R1[2J]]]";
        StringDecoder stringDecoder = new StringDecoder(test);
        stringDecoder.decodeString();
    }
}
