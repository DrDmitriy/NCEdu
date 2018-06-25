package ru.ncedu.dmdrozhzhin.reflectionScan;

import java.lang.reflect.Modifier;

/**
 * check different modificator and return string representation
 */
public class ReflectionModifier {

    public String getModAccess(int modif) {
        if (Modifier.isPublic(modif)) {
            return "public ";
        } else if (Modifier.isProtected(modif)) {
            return "ptotected ";
        } else if (Modifier.isPrivate(modif)) {
            return "private ";
        } else {
            return "";
        }
    }

    public String isStaticString(int modif) {
        if (Modifier.isStatic(modif)) {
            return "static ";
        } else {
            return "";
        }
    }

    public String isFinalString(int modif) {
        if (Modifier.isFinal(modif)) {
            return "final ";
        } else {
            return "";
        }
    }

    public String isAbstarctString(int modif) {
        if (Modifier.isAbstract(modif)) {
            return "abstract ";
        } else return "";
    }

    public String isTransient(int modif) {
        if (Modifier.isTransient(modif)) {
            return "transient";
        } else {
            return "";
        }
    }

    public String isVolatile(int modif) {
        if (Modifier.isVolatile(modif)) {
            return "volatile";
        } else {
            return "";
        }
    }

    public String isNative(int modif) {
        if (Modifier.isNative(modif)) {
            return "native";
        } else {
            return "";
        }
    }
}
