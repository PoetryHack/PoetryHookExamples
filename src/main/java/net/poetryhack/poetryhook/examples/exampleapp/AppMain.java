/**
 * Created: 09.06.2024
 */

package net.poetryhack.poetryhook.examples.exampleapp;

import java.util.Scanner;

public class AppMain {
    static HookMe hookMeObj0;
    static HookMe hookMeObj1;
    static int runCount = 2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long pid = ProcessHandle.current().pid();
        System.out.println("PID: " + pid);

        System.out.println();
        System.out.println();

        System.out.println("------ start first run");
        hookMeObj0 = new HookMe(0, "unchanged hookMeObj0 string");
        hookMeObj1 = new HookMe(1000, "unchanged hookMeObj1 string"); // todo in hook if it is this one change it but not the other one
        printTests();
        System.out.println("------ end first run");

        System.out.println();

        boolean keepRunning = true;
        while (keepRunning) {

            System.out.println();

            System.out.print("enter \"run\" to run the tests, \"pid\" to print pid, and anything else to exit: ");
            String choice = scanner.nextLine();

            System.out.println();

            switch (choice) {
                case "run" -> {
                    System.out.println();
                    System.out.println("------ start run " + runCount);

                    printTests();

                    System.out.println("------ end run " + runCount);
                    System.out.println();

                    runCount++;
                }
                case "pid" -> {
                    System.out.println();
                    System.out.println("------ start print pid");

                    System.out.println(pid);

                    System.out.println("------ end print pid");
                    System.out.println();
                }
                default -> keepRunning = false;
            }
        }
        scanner.close();
    }

    private static void printTests() {
        System.out.println();

        System.out.println("--- start hookMeObj0");
        hookMeObj0.runAllMethods();
        System.out.println("--- end hookMeObj0");

        System.out.println();

        System.out.println("--- start hookMeObj1");
        hookMeObj1.runAllMethods();
        System.out.println("--- end hookMeObj1");

        System.out.println();

        System.out.println("--- start StaticHookMe");
        StaticHookMe.runAllMethods();
        System.out.println("--- end StaticHookMe");

        System.out.println();
    }
}
