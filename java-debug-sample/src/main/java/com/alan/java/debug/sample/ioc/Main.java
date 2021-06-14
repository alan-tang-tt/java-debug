package com.alan.java.debug.sample.ioc;

public class Main {

    private static final ObjectFactory OBJECT_FACTORY = new ObjectFactory();

    public static void main(String[] args) {
        OBJECT_FACTORY.init();

        UserController userController = OBJECT_FACTORY.getObject(UserController.class);

        System.out.println(userController.getUserName(1L));
        System.out.println(userController.getUserName(2L));
        System.out.println(userController.getUserName(3L));
        System.out.println(userController.getUserName(4L));

    }
}
