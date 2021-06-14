package com.alan.java.debug.sample.content;

/**
 * 断点类型有哪些？
 */
public class BreakpointType {

    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String test(String name) {
        if (getName() == null) {
            setName(name);
        }
        return getName();
    }

    public static void main(String[] args) {
        System.out.println(new BreakpointType().test("breakpoint"));
    }
}
