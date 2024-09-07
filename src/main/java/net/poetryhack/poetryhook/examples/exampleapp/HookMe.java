/**
 * Created: 09.06.2024
 */

package net.poetryhack.poetryhook.examples.exampleapp;

public class HookMe {

    public static void runAllMethods() {
        preAndPostHooks();

        accessingArgument("foobar");

//        WIP
//        System.out.println("hookReturnValue returned " + hookReturnValue());
    }

    // todo examples with non-static methods

    private static void preAndPostHooks() {
        System.out.println("preAndPostHooks normal print statement");
    }

    private static void accessingArgument(@SuppressWarnings("SameParameterValue") String argument) {
        System.out.println("accessingArgument called from normal application");
    }

//    private static int hookReturnValue() {
//        return 1;
//    }
}
