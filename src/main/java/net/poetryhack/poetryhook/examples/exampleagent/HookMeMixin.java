/**
 * Created: 09.06.2024
 */

package net.poetryhack.poetryhook.examples.exampleagent;

import net.poetryhack.poetryhook.MixinBase;
import net.poetryhack.poetryhook.annotations.Inject;
import net.poetryhack.poetryhook.annotations.StringMixin;
import net.poetryhack.poetryhook.util.InjectLocation;

@StringMixin("net.poetryhack.poetryhook.examples.exampleapp.HookMe")
public class HookMeMixin implements MixinBase {

    @Inject(
            value = "preAndPostHooks"
    )
    public static void preAndPostHooksPreHook() {
        System.out.println("Before preAndPostHooks from hook");
    }

    @Inject(
            value = "preAndPostHooks",
            injectLocation = InjectLocation.TAIL
    )
    public static void preAndPostHooksPostHook() {
        System.out.println("After preAndPostHooks from hook");
    }

    @Inject(
            value = "accessingArgument",
            injectLocation = InjectLocation.HEAD
    )
    public static void accessingArgumentHook(String argument) {
        System.out.println("printing accessingArgument(" + argument + ") from hook");
    }
}
