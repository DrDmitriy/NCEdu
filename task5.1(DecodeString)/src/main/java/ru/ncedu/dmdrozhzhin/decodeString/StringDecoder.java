package ru.ncedu.dmdrozhzhin.decodeString;

import java.util.ArrayList;
import java.util.List;

public class StringDecoder {
    private StringBuilder stringBuilder;
    private char[] charAray;
    private int maxNestedLevel;
    private int maxNestedLevelIndex;
    private int maxNestedLevelIndexCloseBracket;
    private int countDigitalInKoef;

    final int OPEN_BRACKET_ASCII = 91;
    final int CLOSE_BRACKET_ASCII = 93;
    final int FIRST_DIGITAL = 48;
    final int LAST_DIGITAL = 57;
    final int FIRST_UPPER_CASE_LETTER = 65;
    final int LAST_UPPER_CASE_LETTER = 90;
    final int FIRST_LOWER_CASE_LETTER = 97;
    final int LAST_LOWER_CASE_LETTER = 122;
    //Хранит коды по таблице ASCII
    private List charList;


    public StringDecoder(String string) {
        this.stringBuilder = new StringBuilder(string);
        this.charAray = this.stringBuilder.toString().toCharArray();

        this.charList = new ArrayList();

        for (int i = 0; i < charAray.length; i++) {
            charList.add((int) charAray[i]);
        }
        countMaxNesting();
        System.out.println(maxNestedLevel);
        System.out.println(maxNestedLevelIndex);


    }
    public void decodeString(){
       while (maxNestedLevel>0){
            //countMaxNesting();
            System.out.println("maxNestedLevelIndex = " + maxNestedLevelIndex);

                char charKoef = (char) determDigital(maxNestedLevelIndex);
                Integer koef = Integer.valueOf(String.valueOf(charKoef));
                //System.out.println(koef);
                String openBracket = openBracket(koef);
           System.out.println("oldString " + stringBuilder);
                stringBuilder.replace(maxNestedLevelIndex-countDigitalInKoef,maxNestedLevelIndexCloseBracket+1,openBracket);

           /*
                stringBuilder.delete(maxNestedLevelIndex-1, maxNestedLevelIndexCloseBracket + 1);
           System.out.println("After delete " + stringBuilder);
                stringBuilder.insert(maxNestedLevelIndex, openBracket);*/
                System.out.println("newString " + stringBuilder);



        this.charAray = this.stringBuilder.toString().toCharArray();


           this.charList = new ArrayList();
        for (int i = 0; i < charAray.length; i++) {
            charList.add((int) charAray[i]);
        }

            countMaxNesting();






        }
    }

    public String openBracket(Integer koef) {
        StringBuilder stringBuilder = new StringBuilder();
        int index = maxNestedLevelIndex;
        char charCode;
        index++;
        int asciiCode = (Integer)charList.get(index);
        System.out.println("!!! "+ asciiCode);
        while (asciiCode != CLOSE_BRACKET_ASCII) {
            charCode = (char)asciiCode;
            stringBuilder.append(String.valueOf(charCode));

            asciiCode =(Integer)charList.get(++index);
        }
        maxNestedLevelIndexCloseBracket = index;
        StringBuilder witoutBracket = new StringBuilder();
        for (int i = 0; i< koef; i++){
            witoutBracket.append(stringBuilder);
        }
       // System.out.println(witoutBracket);

     /*   if(asciiCode>= FIRST_UPPER_CASE_LETTER  && asciiCode<= LAST_UPPER_CASE_LETTER  || asciiCode>=FIRST_LOWER_CASE_LETTER && asciiCode <= LAST_LOWER_CASE_LETTER){

        }
        else{
            System.out.println("Такой ситуации не должно произойти");
        }*/

     return witoutBracket.toString();
    }

    private void countMaxNesting() {
        int nestedLevel = 0;
        maxNestedLevelIndex =0;
        maxNestedLevel = 0;
        int indexOpenBracket = 0;
        for (int i = 0; i < charList.size(); i++) {
            //System.out.println("countMaxNesting block FOR");
            Integer a = (Integer) charList.get(i);
            if (a.equals(OPEN_BRACKET_ASCII)) {
                //System.out.println("countMaxNesting block FIRST IF");
                nestedLevel++;
                if (nestedLevel > maxNestedLevel) {
                    //System.out.println("countMaxNesting block SECOND IF");
                    maxNestedLevel = nestedLevel;
                    maxNestedLevelIndex = i;
                }
            }
            if (a.equals(93)) {
                nestedLevel--;
            }
        }
    }

    public int determDigital(Integer bracketIndex) {
        countDigitalInKoef = 0;
        bracketIndex--;


        StringBuilder digitalString = new StringBuilder();
        Integer asciiCode = (Integer) charList.get(bracketIndex);

        while (asciiCode >= FIRST_DIGITAL && asciiCode <= LAST_DIGITAL && bracketIndex >= 0) {
            countDigitalInKoef++;
            digitalString.insert(0, asciiCode);
            //System.out.println("!!!" + digitalString.toString());
            if (bracketIndex.equals(0)) { return Integer.valueOf(digitalString.toString()); }

            asciiCode = (Integer) charList.get(--bracketIndex);


        }
        return Integer.valueOf(digitalString.toString());

    }


}
