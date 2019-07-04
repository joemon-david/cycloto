package com.cycloides.qa.auto.framework.utils;

import com.cycloides.qa.auto.framework.constants.FilePaths;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReportUtility {




    public static void generateReport()
    {

        String path= "target";
//        Collection<File> jsonFiles= FileUtils.listFiles(new File(path),new String[]{"json"},true);
        List<String>jsonPaths= new ArrayList(1);
//        jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
        jsonPaths.add(FilePaths.userDir+"/target/cucumber.json");
        Configuration config= new Configuration(new File("target"), "Test Automation");
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths,config);
        reportBuilder.generateReports();
    }

    public static void main(String[] args) {
        generateReport();
    }
}
