/**
 * Created: 09.06.2024
 */

package net.poetryhack.poetryhook.examples.exampleapp;

public class StaticHookMe {
    public static void runAllMethods() {
        preAndPostHooks();

        accessingArgument("foobar");

        System.out.println("returnEarly(2): " + returnEarly(2));
        System.out.println("returnEarly(42): " + returnEarly(42));
    }

    private static void preAndPostHooks() {
        System.out.println("preAndPostHooks normal print statement");
    }

    private static void accessingArgument(@SuppressWarnings("SameParameterValue") String argument) {
        System.out.println("accessingArgument called from normal application");
    }

    public static int returnEarly(int in) {
        return in;
    }
}
