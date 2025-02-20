package io.github.gaming32.worldhost.gui.widget;

import io.github.gaming32.worldhost.versions.Components;
import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class EnumButton<E extends Enum<E> & StringRepresentable> extends CustomCycleButton<E, EnumButton<E>> {
    private final Component[] translations;

    public EnumButton(
        int x, int y,
        int width, int height,
        String translationBase,
        @Nullable Component title,
        Class<E> clazz, Consumer<EnumButton<E>> onToggle
    ) {
        super(x, y, width, height, title, onToggle, clazz.getEnumConstants());
        translations = getTranslations(translationBase);
    }

    private Component[] getTranslations(String translationBase) {
        final E[] constants = getValues();
        final Component[] result = new Component[constants.length];
        for (int i = 0; i < constants.length; i++) {
            result[i] = Components.translatable(translationBase + '.' + constants[i].getSerializedName());
        }
        return result;
    }

    public void setValue(E value) {
        setValueIndex(value.ordinal());
    }

    @NotNull
    @Override
    public Component getValueMessage() {
        return translations[getValueIndex()];
    }
}
