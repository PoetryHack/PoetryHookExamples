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
        boolean eject;
        if (args[1].equals("eject")) {
            eject = true;
        } else if (args[1].equals("inject")) {
            eject = false;
        } else {
            throw new RuntimeException("invalid command passed");
        }
        loadAgent(args[0], args[2], eject);
    }

    private static void loadAgent(String agentPath, String pid, boolean eject)
            throws IOException, AgentLoadException, AgentInitializationException, AttachNotSupportedException
    {
        String args = null;
        if (eject) {
            args = "foobar";  // todo make this more robust
        }

        VirtualMachine vm = VirtualMachine.attach(pid);
        try {
            vm.loadAgent(agentPath, args);
        } finally {
            vm.detach();
        }
    }
}
