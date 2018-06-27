package ru.ncedu.dmdrozhzhin.decodeString;

import java.util.ArrayList;
import java.util.List;

public class StringDecoder {
    private String string;
    private char[] charAray;
    private int maxNestedLevel;
    private int maxNestedLevelIndex;
    //Хранит коды по таблице ASCII
    private List charList;


    public StringDecoder(String string) {
        this.charAray = string.toCharArray();

        this.charList = new ArrayList();

        for (int i = 0; i < charAray.length; i++){
            charList.add((int)charAray[i]);
        }
        countMaxNesting();
        System.out.println(maxNestedLevel);
        System.out.println(maxNestedLevelIndex);



    }

    private void countMaxNesting(){
        int nestedLevel= 0;
        int indexOpenBracket = 0;
        for (int i = 0 ; i<charList.size();i++){
            Integer a = (Integer) charList.get(i);
            if(a.equals(91)) {
                nestedLevel++;
                if(nestedLevel>maxNestedLevel){
                    maxNestedLevel=nestedLevel;
                    maxNestedLevelIndex = i;
                }
            }
            if(a.equals(93)){
                nestedLevel--;
            }
        }
    }



}
