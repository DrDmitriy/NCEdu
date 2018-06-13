package ru.ncedu.Dmdrozhzhin;
import java.lang.reflect.Modifier;

public class ReflectionModifier {

    private String getModAccess(int modif){
        if (Modifier.isPublic(modif)) {
            return "public ";
        }
        else if (Modifier.isProtected(modif)) {
            return "ptotected ";
        }
        else if (Modifier.isPrivate(modif)) {
            return "private ";
        }
        else {
            return "";
        }
    }

    private String isStaticString(int modif) {
        if(Modifier.isStatic(modif)) {
            return "static ";
        }
        else {
            return "";
        }
    }
    private String isFinalString(int modif) {
        if(Modifier.isFinal(modif)) {
            return "final ";
        }
        else {
            return "";
        }
    }

    private String isAbstarctString(int modif) {
        if(Modifier.isAbstract(modif)) {
            return "abstract ";
        }
        else return "";
    }

}
