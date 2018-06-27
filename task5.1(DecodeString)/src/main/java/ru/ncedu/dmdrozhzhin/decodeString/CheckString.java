package ru.ncedu.dmdrozhzhin.decodeString;

public class CheckString {

    public boolean isValid(String str) {
        int deep = 0;
        int index = 1;
        int asciiNum;
        int preAsciiNum;
        final int OPEN_BRACKET_ASCII = 91;
        final int CLOSE_BRACKET_ASCII = 93;
        final int FIRST_DIGITAL = 48;
        final int LAST_DIGITAL = 57;

        char[] charArr = str.toCharArray();

        while (index < str.length()) {

            asciiNum = (int) charArr[index];
            preAsciiNum = (int) charArr[index - 1];
            if (asciiNum == OPEN_BRACKET_ASCII) {
                if (preAsciiNum >= FIRST_DIGITAL && preAsciiNum <= LAST_DIGITAL) {
                    deep++;
                }
                else return false;
            }
            if (asciiNum == CLOSE_BRACKET_ASCII) {
                deep--;
                if (deep < 0) {
                    return false;
                }
            }
            if (index < 0) {
                return false;
            }
            index++;

        }
        if (deep != 0) {
            return false;
        }
        return true;
    }


}
