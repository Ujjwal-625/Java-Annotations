package com.bridgelabz;

import java.lang.annotation.*;
import java.lang.reflect.Field;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}

public class User {

    @MaxLength(10)
    String username;

    public User(String username) {
        this.username = username;

        // Check length using reflection
        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(MaxLength.class)) {
                field.setAccessible(true);
                MaxLength maxLength = field.getAnnotation(MaxLength.class);
                try {
                    String value = (String) field.get(this);
                    if (value.length() > maxLength.value()) {
                        throw new IllegalArgumentException("Username too long. Max allowed: " + maxLength.value());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class MainForUser {
    public static void main(String[] args) {
        // Valid case
        new User("ABC");

        // Invalid case
        new User("VeryLongUsername");
    }
}
