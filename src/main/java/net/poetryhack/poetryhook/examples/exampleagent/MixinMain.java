/**
 * Created: 09.06.2024
 */

package net.poetryhack.poetryhook.examples.exampleagent;

import java.lang.instrument.Instrumentation;
import java.util.ArrayList;

import static net.poetryhack.poetryhook.PoetryHookInjector.injectMixins;
import static net.poetryhack.poetryhook.PoetryHookInjector.retransformAllRelevantClasses;

public class MixinMain {
    public static void registerHooks(Instrumentation inst) {
        injectMixins(inst, true, new HookMeMixin());
    }

    public static void ejectHooks(Instrumentation inst) throws ClassNotFoundException {
        // real projects will have better ways to eject by using the return of injectMixins instead of hardcoding the hooked classes

        ArrayList<Class<?>> clazzes = new ArrayList<>();
        clazzes.add(ClassLoader.getSystemClassLoader().loadClass("net.poetryhack.poetryhook.examples.exampleapp.HookMe"));
        retransformAllRelevantClasses(inst, clazzes);
    }
}
