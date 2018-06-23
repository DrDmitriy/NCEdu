public class Main {
    public static void main(String[] args){
        char a = '[';
        char b = ']';
        int c = (int) a;
        int d = (int) b;
        System.out.println(c);
        System.out.println(d);

        String test = "2[[dsdzf][fdsf][fdfd[dfsd]]][";
        System.out.println(new Main().isValid(test));

    }

    public boolean isValid(String str){
        int deep = 0;
        int index = 0;
        int asciiNum;
        final int OPEN_BRACKET_ASCII = 91;
        final int CLOSE_BRACKET_ASCII = 93;

        char[] charArr = str.toCharArray();

        while (index<str.length()){
            asciiNum = (int)charArr[index];

            if(asciiNum == OPEN_BRACKET_ASCII) { deep++; }
            if(asciiNum == CLOSE_BRACKET_ASCII) {
                deep--;
                if(deep  < 0){
                    return false;
                }
            }
            if (index<0){return false;}
            index++;

        }
        if (deep != 0 ){
            return false;
        }
        return true;
    }


}
