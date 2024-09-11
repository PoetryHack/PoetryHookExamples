/**
 * Created: 09.06.2024
 */

package net.poetryhack.poetryhook.examples.exampleapp;

import java.util.Scanner;

public class AppMain {
    public static void main(String[] args) {
        long pid = ProcessHandle.current().pid();
        System.out.println("PID: " + pid);

        System.out.println("--- start first run");
        HookMe.runAllMethods();
        System.out.println("--- end first run");

        Scanner scanner = new Scanner(System.in);

        boolean keepRunning = true;
        while (keepRunning) {
            System.out.print("enter \"run\" to run the tests, \"pid\" to print pid, and anything else to exit: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "run" -> {
                    System.out.println("---");
                    HookMe.runAllMethods();
                    System.out.println("---");
                }
                case "pid" -> {
                    System.out.println("---");
                    System.out.println(pid);
                    System.out.println("---");
                }
                default -> keepRunning = false;
            }
        }
        scanner.close();
    }
}
