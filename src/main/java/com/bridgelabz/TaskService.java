package com.bridgelabz;

import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface ImportantMethod {
    String level() default "HIGH";
}

public class TaskService {

    @ImportantMethod(level = "PENDING")
    public void processPayments() {
        System.out.println("Processing payments...");
    }

    @ImportantMethod
    public void generateReports() {
        System.out.println("Generating reports...");
    }

    public void helperMethod() {
        System.out.println("Just a helper.");
    }
}

 class MainForImportantMethod {
    public static void main(String[] args) {
        Class<?> clazz = TaskService.class;

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ImportantMethod.class)) {
                ImportantMethod annotation = method.getAnnotation(ImportantMethod.class);
                System.out.println("Method: " + method.getName() + " | Level: " + annotation.level());
            }
        }
    }
}
