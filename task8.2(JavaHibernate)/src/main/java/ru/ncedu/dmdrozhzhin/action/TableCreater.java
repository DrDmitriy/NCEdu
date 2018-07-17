package ru.ncedu.dmdrozhzhin.action;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;


import java.io.File;
import java.util.EnumSet;

public class TableCreater {
    public static final String SCRIPT_FILE = "exportScript.sql";

    private static SchemaExport getSchemaExport() {
        SchemaExport export = new SchemaExport();
        File outputFile = new File(SCRIPT_FILE);
        String outputFilePath = outputFile.getAbsolutePath();

        export.setDelimiter(";");
        export.setOutputFile(outputFilePath);
        export.setHaltOnError(false);

        return export;
    }

    public static void createDataBase(SchemaExport export, Metadata metadata) {
        // TargetType.DATABASE - Execute on Databse
        // TargetType.SCRIPT - Write Script file.
        // TargetType.STDOUT - Write log to Console.
        EnumSet<TargetType>  targetTypes = EnumSet.of
                (TargetType.DATABASE, TargetType.SCRIPT, TargetType.STDOUT);
        SchemaExport.Action action = SchemaExport.Action.CREATE;
        export.execute(targetTypes, action, metadata);
        System.out.println("Export OK");
    }
    public static void main(String[] args){
        String configFileName = "hibernate.cfg.xml";

        ServiceRegistry serviceRegistry =
                new StandardServiceRegistryBuilder().configure(configFileName).build();

        Metadata metadata =
                new MetadataSources(serviceRegistry).getMetadataBuilder().build();

        SchemaExport export = getSchemaExport();
        System.out.println("Create Database...");
        createDataBase(export,metadata);
    }
}
