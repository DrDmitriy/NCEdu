package ru.ncedu.dmdrozhzhin.decodeString;

public class CheckString {

    public static boolean isValid(String str) {
        int deep = 0;
        int index = 1;
        int asciiNum;
        int preAsciiNum;
        final int OPEN_BRACKET_ASCII = 91;
        final int CLOSE_BRACKET_ASCII = 93;
        final int FIRST_DIGITAL = 48;
        final int LAST_DIGITAL = 57;

        char[] charArr = str.toCharArray();
        if ((int) charArr[0] == OPEN_BRACKET_ASCII || (int) charArr[0] == CLOSE_BRACKET_ASCII) {
            System.out.println("String is invalid. Check a bracket on first position");
            return false;
        }

        while (index < str.length()) {

            asciiNum = (int) charArr[index];
            preAsciiNum = (int) charArr[index - 1];
            if (asciiNum == OPEN_BRACKET_ASCII) {
                if (preAsciiNum >= FIRST_DIGITAL && preAsciiNum <= LAST_DIGITAL) {
                    deep++;
                } else {
                    System.out.println("String is invalid. Check the numbers before the brackets");
                    return false;
                }
            }
            if (asciiNum == CLOSE_BRACKET_ASCII) {
                deep--;
                if (deep < 0) {
                    System.out.println("String is invalid. Check the number of brackets");
                    return false;
                }
            }
            if (index < 0) {
                System.out.println("String is invalid. index < 0)");
                return false;
            }
            index++;
        }
        if (deep != 0) {
            System.out.println("String is invalid. Check the number of brackets");
            return false;
        }
        return true;
    }

}
