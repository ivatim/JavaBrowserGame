package main;

import sun.net.www.ApplicationLaunchException;

import java.util.HashMap;
import java.util.Map;

public class Context{
    private Map<Class<?>, Object> context = new HashMap<>();

    public Object get(Class<?> clazz) {
        Object obj = context.get(clazz);
        if (obj == null) {
            throw new Error("No such service in context");
        }
        return obj;
    }

    public void add(Object obj) throws ApplicationLaunchException  {
        Class<?> clazz = obj.getClass();
        if (context.containsKey(clazz)) {
            throw new ApplicationLaunchException("Such service already exists");
        }
        context.put(clazz, obj);
    }
}
