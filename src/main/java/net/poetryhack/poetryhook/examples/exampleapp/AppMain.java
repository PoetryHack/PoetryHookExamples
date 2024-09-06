/**
 * Created: 09.06.2024
 */

package net.poetryhack.poetryhook.examples.exampleapp;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

public class AppMain {
    public static void main(String[] args)
            throws AgentLoadException, IOException, AttachNotSupportedException, AgentInitializationException
    {
        System.out.println("--- running all tests before hooking");
        HookMe.runAllMethods();

        System.out.println("--- hooking methods");
        loadAgent();

        System.out.println("--- running all tests after hooking");
        HookMe.runAllMethods();

        // unhooking should be done by the agent
    }

    private static void loadAgent()
            throws IOException, AgentLoadException, AgentInitializationException, AttachNotSupportedException
    {
        String agentPath = System.getenv("POETRYHOOK_EXAMPLE_AGENT_PATH");
        if (agentPath == null) {
            throw new RuntimeException("POETRYHOOK_EXAMPLE_AGENT_PATH environmental variable is undefined. " +
                    "Try setting to 'target/PoetryHookExampleAgent-1.0.0.jar'"
            );
        }

        VirtualMachine vm;
        try {
            vm = VirtualMachine.attach(String.valueOf(ProcessHandle.current().pid()));
        } catch (IOException e) {
            throw new IOException("make sure you are launching with jdk.attach.allowAttachSelf=true", e);
        }
        try {
            vm.loadAgent(agentPath);
        } finally {
            vm.detach();
        }
    }
}
