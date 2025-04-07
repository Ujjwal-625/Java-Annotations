package com.bridgelabz;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface TaskInfo{
    String priority();
    String assignedTo();
}

public class TaskManager {

    @TaskInfo(priority = "High", assignedTo = "ABC")
    public void completeProject() {
        System.out.println("Completing the project...");
    }

}
    class MainForTaskManager{
        public static void main(String[] args) throws NoSuchMethodException {
            TaskManager t = new TaskManager();
            Annotation c = t.getClass().getMethod("completeProject").getAnnotation(TaskInfo.class);
            System.out.println(((TaskInfo) c).priority());
            System.out.println(((TaskInfo) c).assignedTo());
        }
    }

