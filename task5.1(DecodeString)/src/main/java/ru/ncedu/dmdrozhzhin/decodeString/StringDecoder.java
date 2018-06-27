package ru.ncedu.dmdrozhzhin.decodeString;

import java.util.ArrayList;
import java.util.List;

public class StringDecoder {
    private StringBuilder stringBuilder;
    private char[] charAray;
    private int maxNestedLevel;
    private int maxNestedLevelIndexOpenBracket;
    private int maxNestedLevelIndexCloseBracket;
    private int countDigitalInKoef;
    final int OPEN_BRACKET_ASCII = 91;
    final int CLOSE_BRACKET_ASCII = 93;
    final int FIRST_DIGITAL = 48;
    final int LAST_DIGITAL = 57;
    //Хранит коды по таблице ASCII
    private List charList;


    public StringDecoder(String string) {
        if (CheckString.isValid(string)) {
            this.stringBuilder = new StringBuilder(string);
            this.charList = new ArrayList();
            refreshData();
        }
    }

    private void refreshData() {
        this.charAray = this.stringBuilder.toString().toCharArray();
        this.charList.clear();
        for (int i = 0; i < charAray.length; i++) {
            charList.add((int) charAray[i]);
        }
        countMaxNesting();
    }

    private void countMaxNesting() {
        int nestedLevel = 0;
        maxNestedLevelIndexOpenBracket = 0;
        maxNestedLevel = 0;
        for (int i = 0; i < charList.size(); i++) {
            Integer asciiCode = (Integer) charList.get(i);
            if (asciiCode.equals(OPEN_BRACKET_ASCII)) {
                nestedLevel++;
                if (nestedLevel > maxNestedLevel) {
                    maxNestedLevel = nestedLevel;
                    maxNestedLevelIndexOpenBracket = i;
                }
            }
            if (asciiCode.equals(93)) {
                nestedLevel--;
            }
        }
    }

    public void decodeString() {
        while (maxNestedLevel > 0) {
            int digitalKoef = determDigital(maxNestedLevelIndexOpenBracket);
            String stringAfterOpenBracket = openBracket(digitalKoef);
            stringBuilder.replace(maxNestedLevelIndexOpenBracket - countDigitalInKoef, maxNestedLevelIndexCloseBracket + 1, stringAfterOpenBracket);

            System.out.println("newString " + stringBuilder);
            refreshData();
        }
    }

    private String openBracket(Integer koef) {
        StringBuilder stringBuilder = new StringBuilder();
        int index = maxNestedLevelIndexOpenBracket;
        char charCode;
        index++;
        int asciiCode = (Integer) charList.get(index);
        while (asciiCode != CLOSE_BRACKET_ASCII) {
            charCode = (char) asciiCode;
            stringBuilder.append(String.valueOf(charCode));
            asciiCode = (Integer) charList.get(++index);
        }
        maxNestedLevelIndexCloseBracket = index;
        StringBuilder witoutBracket = new StringBuilder();
        for (int i = 0; i < koef; i++) {
            witoutBracket.append(stringBuilder);
        }
        return witoutBracket.toString();
    }


    private int determDigital(Integer bracketIndex) {
        countDigitalInKoef = 0;
        bracketIndex--;
        StringBuilder digitalString = new StringBuilder();
        Integer asciiCode = (Integer) charList.get(bracketIndex);

        while (asciiCode >= FIRST_DIGITAL && asciiCode <= LAST_DIGITAL && bracketIndex >= 0) {
            countDigitalInKoef++;
            digitalString.insert(0, String.valueOf((char) (int) asciiCode));
            if (bracketIndex.equals(0)) {
                return Integer.valueOf(digitalString.toString());
            }
            asciiCode = (Integer) charList.get(--bracketIndex);
        }
        return Integer.valueOf(digitalString.toString());
    }
}
