/**
 * Created: 09.12.2024
 */

package net.poetryhack.poetryhook.examples.exampleagent;

import net.poetryhack.poetryhook.MixinBase;
import net.poetryhack.poetryhook.annotations.Inject;
import net.poetryhack.poetryhook.annotations.ObjectWrapper;
import net.poetryhack.poetryhook.annotations.StringMixin;

import java.lang.reflect.Field;
import java.util.Objects;

@SuppressWarnings("unused")
@StringMixin("net.poetryhack.poetryhook.examples.exampleapp.HookMe")
public class HookMeMixin implements MixinBase {

    @Inject(
            value = "printStringToChange"
    )
    public static void printStringToChangeHook(
            @ObjectWrapper("net.poetryhack.poetryhook.examples.exampleapp.HookMe") Object thisthis
    ) throws NoSuchFieldException, IllegalAccessException {
        Class<?> clazz = thisthis.getClass();
        Field f = clazz.getDeclaredField("stringToChange");
        f.setAccessible(true);
        if (Objects.equals(f.get(thisthis), "unchanged hookMeObj1 string")) {
            f.set(thisthis, "hookMeObj1 string changed by hook while injected");
        }
    }

    @Inject(
            value = "getCounterMethod"
    )
    public static void getCounterMethodHook(
            @ObjectWrapper("net.poetryhack.poetryhook.examples.exampleapp.HookMe") Object thisthis
    ) throws NoSuchFieldException, IllegalAccessException {
        Class<?> clazz = thisthis.getClass();
        Field f = clazz.getDeclaredField("counter");
        f.setAccessible(true);
        int val = (int) f.get(thisthis);
        f.set(thisthis, (val + 12) * 3);
    }
}
