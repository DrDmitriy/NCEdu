package ru.ncedu.Dmdrozhzhin;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RefAnnotationScan {
    /**
     * parsing annotations on method parameters, if any Ex:(public void method(@myAnn @Deprecated String[] params))
     *
     * @param method method for parsing
     * @return Map where key is a parametr number, value is ArrayList with annotation
     * if there are no annotation - return empty HashMap
     */
    public static Map<Integer, ArrayList<? extends Annotation>> getParametrAnn(Method method) {
        Map<Integer, ArrayList<? extends Annotation>> paramAnnotationMap = new HashMap<>();

        Annotation[][] annotations = method.getParameterAnnotations();
        for (int i = 0; i < annotations.length; i++) {
            ArrayList list = new ArrayList();
            for (int j = 0; j < annotations[i].length; j++) {
                list.add(annotations[i][j]);
            }
            paramAnnotationMap.put(i, list);
        }
        return paramAnnotationMap;
    }
}