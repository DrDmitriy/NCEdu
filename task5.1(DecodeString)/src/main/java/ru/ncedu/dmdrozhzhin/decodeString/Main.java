package ru.ncedu.dmdrozhzhin.decodeString;

public class Main {
    public static void main(String[] args) {
    /*    char a = '[';
        char b = ']';
        int c = (int) a;
        int d = (int) b;
        System.out.println(c);
        System.out.println(d);*/

        String test = "2[1[2[dsdzf]]2[fdsf]]";
        //System.out.println(new CheckString().isValid(test));
        new StringDecoder(test);

    }


}
