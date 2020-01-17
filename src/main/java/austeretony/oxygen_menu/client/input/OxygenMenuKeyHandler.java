package austeretony.oxygen_menu.client.input;

import org.lwjgl.input.Keyboard;

import austeretony.oxygen_core.client.api.ClientReference;
import austeretony.oxygen_menu.client.gui.menu.OxygenMenuScreen;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class OxygenMenuKeyHandler {

    public static final KeyBinding OXYGEN_MENU = new KeyBinding("key.oxygen_menu.openMenu", Keyboard.KEY_TAB, "Oxygen");

    public OxygenMenuKeyHandler() {
        ClientReference.registerKeyBinding(OXYGEN_MENU);
    }

    @SubscribeEvent
    public void onKeyInput(KeyInputEvent event) {        
        if (OXYGEN_MENU.isPressed())
            ClientReference.displayGuiScreen(new OxygenMenuScreen());
    }
}
