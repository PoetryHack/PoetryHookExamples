/**
 * Created: 09.06.2024
 */

package net.poetryhack.poetryhook.examples.exampleagent;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class AgentMain {
    public static void agentmain(String agentArgs, Instrumentation inst)
            throws IOException, NoSuchMethodException, IllegalAccessException, URISyntaxException, ClassNotFoundException, InvocationTargetException
    {
        @SuppressWarnings("resource") JarFile jarFile = new JarFile(new File(AgentMain.class.getProtectionDomain().getCodeSource().getLocation().toURI()));

        ClassLoader classLoader = null;

        for (Class<?> clazz : inst.getAllLoadedClasses()) {
            if (clazz.getName().startsWith("net.poetryhack.poetryhook.examples.exampleapp")) {
                classLoader = clazz.getClassLoader();
                break;
            }
        }
        assert classLoader != null;

        {
            ArrayList<JarEntry> entries = new ArrayList<>();
            for (JarEntry file : jarFile.stream().toList()) {
                if (
                        file.getName().endsWith(".class") && file.getName().startsWith("net.poetryhack.poetryhook")
                                && !(
                                file.getName().contains("exampleapp")
                                        || file.getName().contains("exampleinjector")
                        )  // needed because the package name of the example app, example agent, and poetryhook library are all similar
                ) {
                    entries.add(file);
                }
            }

            while (!entries.isEmpty()) {
                ArrayList<JarEntry> failed = new ArrayList<>();
                Exception linkError = null;
                for (JarEntry file : entries) {
                    InputStream is = jarFile.getInputStream(file);
                    byte[] classBytes = new byte[is.available()];
                    //noinspection ResultOfMethodCallIgnored
                    is.read(classBytes);
                    String name = file.getName().replace(".class", "").replace("/", ".");

                    Method m = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, int.class, int.class);  // reflection is used here because ClassLoader#defineClass is protected
                    m.setAccessible(true);
                    // todo maybe remove this error handling?
                    try {
                        m.invoke(classLoader, name, classBytes, 0, classBytes.length);
                    } catch (InvocationTargetException e) {
                        if (e.getCause() instanceof LinkageError) {
                            failed.add(file);
                            linkError = e;
                        }
                    }
                }

                if (failed.size() == entries.size()) {
                    throw new RuntimeException("Failed to load any classes, last error:", linkError);
                } else {
                    entries = failed;
                }
            }
        }

        boolean eject = agentArgs != null;
        if (eject) {
            classLoader.loadClass("net.poetryhack.poetryhook.examples.exampleagent.MixinMain").getMethod("ejectHooks", Instrumentation.class).invoke(null, inst);
        } else {
            classLoader.loadClass("net.poetryhack.poetryhook.examples.exampleagent.MixinMain").getMethod("registerHooks", Instrumentation.class).invoke(null, inst);
        }
    }
}
