package ru.ncedu.dmdrozhzhin.reflectionScan;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

/**
 * Getting information about the class through reflection
 */
public class ReflectionScan {
    private ReflectionModifier reflectionModifier = new ReflectionModifier();

    public String getReturnTypeString(Method method) {
        return method.getReturnType().getSimpleName() + " ";
    }

    private String getParentName(Class clazz) {
        String parentName = clazz.getSuperclass().getSimpleName();
        if (parentName.equals("Object")) {
            return "";
        } else {
            return " extends " + parentName + " ";
        }
    }

    private String getImplInterfece(Class clazz) {
        Class[] masInterface = clazz.getInterfaces();
        StringBuilder stringBuilder = new StringBuilder("implements ");
        if (masInterface.length > 0) {
            for (Class interfClass : masInterface) {
                stringBuilder.append(interfClass.getSimpleName() + ", ");
            }
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
            return stringBuilder.toString();
        } else {
            return "";
        }
    }

    public String getDescriptClass(Class clazz) {
        int intModifiers = clazz.getModifiers();
        return reflectionModifier.getModAccess(intModifiers)
                + reflectionModifier.isStaticString(intModifiers)
                + reflectionModifier.isAbstarctString(intModifiers)
                + "class "
                + clazz.getSimpleName()
                + getParentName(clazz)
                + getImplInterfece(clazz);

    }

    public String getAnnotation(Annotation annotation) {
        return "@" + annotation.annotationType().getSimpleName();
    }

    public String getPackage(Class clazz) {
        return clazz.getPackage().getName();
    }

    public String getDecriptField(Field field) {
        int modif = field.getModifiers();
        return reflectionModifier.getModAccess(modif)
                + reflectionModifier.isStaticString(modif)
                + reflectionModifier.isFinalString(modif)
                + field.getType().getSimpleName()
                + " " + field.getName();
    }

    public String getDecriptConst(Constructor constructor, Class clazz) {
        Class[] paramsClass = constructor.getParameterTypes();
        StringBuilder stringBuilder = new StringBuilder();
        int modif = constructor.getModifiers();
        for (int i = 0; i < paramsClass.length; i++) {
            stringBuilder.append(paramsClass[i].getSimpleName() + " param" + (i + 1) + ", ");
        }
        if (paramsClass.length > 0) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }

        return reflectionModifier.getModAccess(modif) + clazz.getSimpleName() + " (" + stringBuilder.toString() + ")";
    }

    /**
     * make description method. Modification + mathod name + parametr's annotations + parametrs
     *
     * @param method method for parsing
     * @return description method in string representation
     */
    public String getDecriptMethod(Method method) {
        int numSpase = 1;
        int modif = method.getModifiers();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(
                reflectionModifier.getModAccess(modif)
                        + reflectionModifier.isStaticString(modif)
                        + getReturnTypeString(method)
                        + method.getName() + "( "
        );

        Class[] paramsMethod = method.getParameterTypes();
        Map<Integer, ArrayList<? extends Annotation>> paramAnnotationMap = RefAnnotationScan.getParametrAnn(method);

        for (int i = 0; i < paramsMethod.length; i++) {
            numSpase = 2;
            for (Annotation annotation : paramAnnotationMap.get(i)) {
                stringBuilder.append("@" + annotation.annotationType().getSimpleName() + " ");
            }
            stringBuilder.append(paramsMethod[i].getSimpleName() + " param" + (i + 1) + ", ");
        }
        stringBuilder.delete(stringBuilder.length() - numSpase, stringBuilder.length());
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
