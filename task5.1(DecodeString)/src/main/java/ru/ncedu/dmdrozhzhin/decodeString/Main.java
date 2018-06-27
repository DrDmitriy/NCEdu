package ru.ncedu.dmdrozhzhin.decodeString;

public class Main {
    public static void main(String[] args) {
    /*    char a = '[';
        char b = ']';
        int c = (int) a;
        int d = (int) b;
        System.out.println(c);
        System.out.println(d);*/

        //String test = "2[1[2[dsdz[[3[test_]]]f]]2[fdsf]]";
        String test = "2[xyz3[R]]";
        //System.out.println(new CheckString().isValid(test));
        StringDecoder stringDecoder = new StringDecoder(test);
        stringDecoder.decodeString();
        //System.out.println(stringDecoder.determDigital(5));

    }


}
