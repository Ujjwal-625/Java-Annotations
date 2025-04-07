package com.bridgelabz;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface RoleAllowed{
    String Role();
}

public class LimitedAccess{
    @RoleAllowed(Role="ADMIN")
    private void getAdminName(){
        System.out.println("Admin name is ABC");
    }

    //method with admin role

    @RoleAllowed(Role="USER")
    private void getAdminName1(){
        System.out.println("Admin name is ABC");
    }

}

class codeRunner{
    public static void main(String[] args) throws Exception {
        LimitedAccess la=new LimitedAccess();

        Method [] methods=la.getClass().getDeclaredMethods();

        for(Method m:methods){
            if(m.getAnnotation(RoleAllowed.class).Role().equals("ADMIN")){
                m.setAccessible(true);
                m.invoke(la);
            }
            else{
                System.out.println(m.getName()+" this method does not have Admin role in the annotation");
            }
        }
    }
}
