package ru.ncedu.dmdrozhzhin.reflectionScan;

public class Main {
    public static void main(String[] args) {
        printClass(TestClass.class);
    }

    public static void printClass(Class clazz) {

        PrintReflectionClass printer = new PrintReflectionClass(clazz);
        printer.printPackage();
        System.out.println("");
        printer.printImportedClass(".\\src\\main\\java\\ru\\ncedu\\dmdrozhzhin\\reflectionScan\\TestClass.java");
        System.out.println();
        printer.printClassAnnotation();
        printer.printClassName();
        System.out.println("");
        printer.printConstr();
        System.out.println();
        printer.printFieldsClass();
        System.out.println();
        printer.printMethod();
    }
}
