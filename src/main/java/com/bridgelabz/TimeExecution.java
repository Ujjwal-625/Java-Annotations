package com.bridgelabz;
import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface LogExecutionTime {
}

 class TimeTest {

    @LogExecutionTime
    public void fastMethod() {
        System.out.println("Running fast method...");
        for (int i = 0; i < 1_000_000; i++);  // Small loop
    }

    @LogExecutionTime
    public void slowMethod() {
        System.out.println("Running slow method...");
        for (int i = 0; i < 100_000_000; i++);  // Bigger loop
    }

    public void normalMethod() {
        System.out.println("Running normal method (no log)...");
    }
}

 class MainForLogExecution {
    public static void main(String[] args) throws Exception {
        TimeTest obj = new TimeTest();
        Method[] methods = TimeTest.class.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(LogExecutionTime.class)) {
                long start = System.nanoTime();
                method.invoke(obj);  // Call the method
                long end = System.nanoTime();
                long duration = end - start;
                System.out.println("Execution time for " + method.getName() + ": " + duration + " nanoseconds");
                System.out.println("--------");
            } else {
                // Optionally invoke non-annotated methods
                method.invoke(obj);
            }
        }
    }
}


