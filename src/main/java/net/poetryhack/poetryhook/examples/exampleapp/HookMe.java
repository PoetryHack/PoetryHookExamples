/**
 * Created: 09.06.2024
 */

package net.poetryhack.poetryhook.examples.exampleapp;

public class HookMe {

    public static void runAllMethods() {
        preAndPostHooks();

        accessingArgument("foobar");

        System.out.println("setReturnValue() returned `" + setReturnValue() + "`");
    }

    // todo examples with non-static methods

    private static void preAndPostHooks() {
        System.out.println("normal print statement");
    }

    private static int setReturnValue() {
        return 1;
    }

    private static void accessingArgument(@SuppressWarnings("SameParameterValue") String argument) {
        System.out.println("accessingArgument(String) called");
    }
}
