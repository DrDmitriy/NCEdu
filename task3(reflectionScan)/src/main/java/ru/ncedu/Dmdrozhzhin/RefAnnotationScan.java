package ru.ncedu.Dmdrozhzhin;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public  class RefAnnotationScan {
    //Map<Integer, ArrayList<? extends Annotation>> paramAnnotationMap;


    public static Map<Integer,ArrayList<? extends Annotation>> getParametrAnn(Method method) {
        Map<Integer, ArrayList<? extends Annotation>> paramAnnotationMap = new HashMap<>();

        Annotation[][] annotations = method.getParameterAnnotations();
        for (int i = 0; i < annotations.length; i++) {
            ArrayList list = new ArrayList();
            for (int j = 0; j < annotations[i].length; j++) {

                list.add(annotations[i][j]);
                //System.out.println(annotations[i][j].annotationType());
            }
            paramAnnotationMap.put(i, list);
        }
        return paramAnnotationMap;
    }
}