/**
 * Created: 09.06.2024
 */

package net.poetryhack.poetryhook.examples.exampleagent;

import java.lang.instrument.Instrumentation;

import static net.poetryhack.poetryhook.PoetryHookInjector.ejectMixins;
import static net.poetryhack.poetryhook.PoetryHookInjector.injectMixins;

public class MixinMain {
    public static void registerHooks(Instrumentation inst) {
        // the ejection will make it so that when retransformAllRelevantClasses is called, the classes will go back to normal
        ejectMixins(inst, injectMixins(inst, new HookMeMixin()));
    }
}
