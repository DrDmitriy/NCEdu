package ru.ncedu.dmdrozhzhin.reflectionScan;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Class with methods for print to console
 */
public class PrintReflectionClass {
    ReflectionScan scan = new ReflectionScan();
    Class clazz;

    /**
     * @param clazz class for parsing
     */
    public PrintReflectionClass(Class clazz) {
        this.clazz = clazz;
    }

    /**
     * print imported class
     *
     * @param sourcePath path to java class
     */
    public void printImportedClass(String sourcePath) {
        for (Object string : DefineImport.getImportsSet(sourcePath)) {
            System.out.println("import " + (String) string);
        }
    }

    public void printMethod() {
        for (Method method : clazz.getDeclaredMethods()) {
            printMethodAnnotation(method);
            System.out.println(scan.getDecriptMethod(method));
            System.out.println();
        }
    }

    public void printClassAnnotation() {
        for (Annotation annotation : clazz.getDeclaredAnnotations()) {
            System.out.println(scan.getAnnotation(annotation));
        }
    }

    private void printFieldAnnotation(Field field) {
        Annotation[] annotations = field.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(scan.getAnnotation(annotation));
        }
    }

    private void printMethodAnnotation(Method method) {
        Annotation[] annotations = method.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(scan.getAnnotation(annotation));
        }
    }

    public void printConstr() {
        for (Constructor constr : clazz.getDeclaredConstructors()) {
            System.out.println(scan.getDecriptConst(constr, clazz));
        }
    }

    public void printFieldsClass() {
        for (Field field : clazz.getDeclaredFields()) {
            printFieldAnnotation(field);
            System.out.println(scan.getDecriptField(field));
        }
    }

    public void printPackage() {
        System.out.println("package " + scan.getPackage(clazz));
    }

    public void printClassName() {
        System.out.println(scan.getDescriptClass(clazz));
    }
}
