package com.bridgelabz;

import java.lang.annotation.*;
import java.lang.reflect.Method;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface BugReports{
    BugReport[] value();
}

@Repeatable(BugReports.class)
@Retention(RetentionPolicy.RUNTIME)
@interface BugReport{
    String description();
}


public class BugTracker {
    @BugReport(description = "no data available")
    @BugReport(description = "connection String not found")
    public void processData() {
        System.out.println("Processing data...");
    }
}

class MainForBugTracker{
    public static void main(String[] args) throws NoSuchMethodException {
        BugTracker bg = new BugTracker();
        Method m = bg.getClass().getMethod("processData");
//        System.out.println(m.getAnnotations());
        BugReport[] bgreport = m.getAnnotationsByType(BugReport.class);
        for(BugReport bgrep: bgreport) {
            System.out.println(bgrep.description());
        }
    }
}
