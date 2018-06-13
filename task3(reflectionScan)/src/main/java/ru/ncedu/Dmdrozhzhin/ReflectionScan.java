package ru.ncedu.Dmdrozhzhin;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReflectionScan {
    TestClass testClass = new TestClass();
    Class clazz = testClass.getClass();
    Map<Integer,ArrayList<? extends  Annotation>> paramAnnotationMap = null;
    public  static void main(String[] args){
        ReflectionScan reflectionScan = new ReflectionScan();
        reflectionScan.printPackage();

       // reflectionScan.printAnnotation();
        reflectionScan.printClassName();
        System.out.println("");
        reflectionScan.printFieldsClass();
        System.out.println();
        reflectionScan.printConstr();


         reflectionScan.printMethod();



    }

    public void getParametrAnn(Method method){
        paramAnnotationMap = new HashMap<>();

            Annotation[][] annotations = method.getParameterAnnotations();
            for (int i = 0; i < annotations.length; i++) {
                ArrayList list = new ArrayList();
                for (int j = 0; j < annotations[i].length; j++) {

                    list.add(annotations[i][j]);
                    //System.out.println(annotations[i][j].annotationType());
                }
                paramAnnotationMap.put(i, list);
            }



    }
    public String getReturnTypeString(Method method) {
        return method.getReturnType().getSimpleName()+" ";
    }

    public void printMethod() {
        Method[] methods = clazz.getDeclaredMethods();

        for(Method method: methods) {

            int numSpase = 1;
            int modif = method.getModifiers();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(
                    getModAccess(modif)
                    +isStaticString(modif)
                    + getReturnTypeString(method)
                    + method.getName()+"( ");

            Class[] paramsMethod = method.getParameterTypes();
            getParametrAnn(method);


            for (int i = 0; i < paramsMethod.length; i++) {

                numSpase =2 ;
                if (!(paramAnnotationMap.get(i).isEmpty()) &&paramAnnotationMap.get(i).size() > 0) {
                    for (Annotation annotation : paramAnnotationMap.get(i)) {
                        stringBuilder.append("@" + annotation.annotationType().getSimpleName() + " ");
                    }
                    stringBuilder.append( paramsMethod[i].getSimpleName() + " param" + (i + 1) + ", ");
                }
            }
            stringBuilder.delete(stringBuilder.length() - numSpase, stringBuilder.length());
            stringBuilder.append(")");
            System.out.println(stringBuilder);
        }

    }






    public void printAnnotation (Object object) {

        if(object.getClass().equals(Class.class)){
            Annotation[] annotations = ((Class)object).getDeclaredAnnotations();
            System.out.println(annotations.length);
            for(Annotation annotation:annotations){
                System.out.println("@"+annotation.annotationType().getSimpleName());
            }

        }

        if(object.getClass().equals(Method.class)){
            Annotation[] annotations = ((Method)object).getDeclaredAnnotations();
            for(Annotation annotation:annotations){
                System.out.println("@"+annotation.annotationType().getSimpleName());
            }
        }

        if(object.getClass().equals(Field.class)) {
            Annotation[] annotations = ((Field)object).getDeclaredAnnotations();
            for(Annotation annotation:annotations){
                System.out.println("@"+annotation.annotationType().getSimpleName());
            }
        }
    }

    public void printConstr() {
        Constructor[] constructors = clazz.getDeclaredConstructors();
        for(Constructor constr: constructors){
            int modif = constr.getModifiers();
            Class[] paramsClass= constr.getParameterTypes();
            StringBuilder stringBuilder = new StringBuilder();
            for(int i = 0; i<paramsClass.length;i++) {
                stringBuilder.append(paramsClass[i].getSimpleName()+" param"+(i+1)+", ");
            }
            if(paramsClass.length>0) {
                stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
            }
            System.out.println(getModAccess(modif)+clazz.getSimpleName()+" ("+stringBuilder+")");
        }
    }

    public void printFieldsClass() {
        Field[] fields = clazz.getDeclaredFields();
        for (Field a : fields) {
            int mod = a.getModifiers();
            System.out.println(getModAccess(mod) + isStaticString(mod) + isFinalString(mod) + a.getType().getSimpleName() + " " + a.getName());
        }

    }




    public void printClassParent(){
        System.out.println(getParentName());
    }

    private String getParentName() {
        String parentName = clazz.getSuperclass().getSimpleName();
        if(parentName.equals("Object")) {
            return "";
        }
        else {
            return " extends "+parentName+" ";
        }
    }

    private String getImplInterfece() {
        Class[] masInterface = clazz.getInterfaces();
        StringBuilder stringBuilder = new StringBuilder("implements ");
        if (masInterface.length > 0) {
            for (Class interfClass : masInterface) {
                stringBuilder.append(interfClass.getSimpleName() + ", ");
            }
            //removes the last comma
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
            return stringBuilder.toString();
        }
        else {
            return "";
        }
    }

    public void printClassName(){
        int intModifiers = clazz.getModifiers();
        printAnnotation(clazz);
        System.out.println(getModAccess(intModifiers)+ isStaticString(intModifiers)+isFinalString(intModifiers)+isAbstarctString(intModifiers)+"class "
                + clazz.getSimpleName()+getParentName()+getImplInterfece());

        //System.out.println(clazz.getName());

    }
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
    public void printPackage() {
        System.out.println("package "+clazz.getPackage().getName());
    }


}
