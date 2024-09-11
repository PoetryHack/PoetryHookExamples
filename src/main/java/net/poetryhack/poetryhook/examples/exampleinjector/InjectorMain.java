/**
 * Created: 09.10.2024
 */

package net.poetryhack.poetryhook.examples.exampleinjector;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

public class InjectorMain {
    public static void main(String[] args)
            throws AgentLoadException, IOException, AttachNotSupportedException, AgentInitializationException
    {
        boolean eject = false;
        if (args.length > 1) {
            eject = true;
        }
        loadAgent(args[0], eject);
    }

    private static void loadAgent(String pid, boolean eject)
            throws IOException, AgentLoadException, AgentInitializationException, AttachNotSupportedException
    {
        String args = null;
        if (eject) {
            args = "somethingtomakeargsnotnull";  // todo make this more robust
        }

        String agentPath = System.getenv("POETRYHOOK_EXAMPLE_AGENT_PATH");
        if (agentPath == null) {
            throw new RuntimeException("POETRYHOOK_EXAMPLE_AGENT_PATH environmental variable is undefined. " +
                    "Try setting to 'target/PoetryHookExampleAgent-1.0.0.jar'"
            );
        }

        VirtualMachine vm = VirtualMachine.attach(pid);
        try {
            vm.loadAgent(agentPath, args);
        } finally {
            vm.detach();
        }
    }
}
