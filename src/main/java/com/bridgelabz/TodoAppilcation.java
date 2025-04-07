package com.bridgelabz;
import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Todo {
    String task();
    String assignedTo();
    String priority() default "MEDIUM";
}

public class TodoAppilcation {
    @Todo(task = "Implement user authentication", assignedTo = "Alice", priority = "HIGH")
    public void loginFeature() {
        System.out.println("Login feature pending...");
    }

    @Todo(task = "Optimize database queries", assignedTo = "Bob")
    public void fetchData() {
        System.out.println("Fetching data...");
    }

    public void helperMethod() {
        System.out.println("Helper method.");
    }
}

class MainForTodo{
    public static void main(String[] args) {
        Class<?> todo = TodoAppilcation.class;
        Method[] method = todo.getMethods();
        for(Method m: method) {
            Todo td = m.getAnnotation(Todo.class);
            if(td != null) {
                System.out.println(td.task());
                System.out.println(td.assignedTo());
                System.out.println(td.priority());
                System.out.println("=========");
            }
        }
    }
}