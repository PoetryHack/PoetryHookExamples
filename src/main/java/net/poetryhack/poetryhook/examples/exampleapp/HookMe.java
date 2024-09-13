/**
 * Created: 09.12.2024
 */

package net.poetryhack.poetryhook.examples.exampleapp;

public class HookMe {
    @SuppressWarnings({"FieldMayBeFinal"})
    private String stringToChange;
    private int counter;

    public HookMe(int counter, String stringToChange) {
        this.counter = counter;
        this.stringToChange = stringToChange;
    }

    public void runAllMethods() {
        System.out.println("the non-static field stringToChange is: \"" + printStringToChange() + "\"");
        System.out.println("counter is equal to " + getCounterMethod());
    }

    private String printStringToChange() {
        return stringToChange;
    }

    private int getCounterMethod() {
        return counter++;
    }
}
