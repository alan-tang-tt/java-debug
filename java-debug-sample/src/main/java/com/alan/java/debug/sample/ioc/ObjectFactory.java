package com.alan.java.debug.sample.ioc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectFactory {

    private static final Map<Class<?>, Object> OBJECT_CONTAINER = new HashMap<>();

    public void init() {
        // 获取哪些类要实例化到容器中
        List<Class<?>> clzList = new ArrayList<>();
        clzList.add(UserController.class);
        clzList.add(UserService.class);

        // 实例化这些对象
        for (Class<?> clz : clzList) {
            instantiate(clz);
        }
    }

    private <V> V instantiate(Class<V> clz) {
        if (OBJECT_CONTAINER.containsKey(clz)) {
            return (V) OBJECT_CONTAINER.get(clz);
        }

        if (clz == UserService.class) {
            UserService userService = new UserService();
            OBJECT_CONTAINER.put(clz, userService);
            return (V) userService;
        } else if (clz == UserController.class) {
            UserController userController = new UserController();
            OBJECT_CONTAINER.put(clz, userController);

            // 注入属性
            UserService userService = getObject(UserService.class);
            if (userService == null) {
                userService = instantiate(UserService.class);
            }
            userController.setUserService(userService);

            return (V) userController;
        }
        throw new UnsupportedOperationException();
    }

    public <V> V getObject(Class<V> clz) {
        return (V) OBJECT_CONTAINER.get(clz);
    }

}
