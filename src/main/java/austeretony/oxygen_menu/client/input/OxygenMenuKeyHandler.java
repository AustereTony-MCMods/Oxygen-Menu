package austeretony.oxygen_menu.client.input;

import austeretony.oxygen_core.client.api.ClientReference;
import austeretony.oxygen_menu.client.gui.menu.OxygenMenuScreen;
import austeretony.oxygen_menu.common.config.OxygenMenuConfig;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class OxygenMenuKeyHandler {

    private KeyBinding oxygenMenuKeybinding;

    public OxygenMenuKeyHandler() {
        if (OxygenMenuConfig.ENABLE_OXYGEN_MENU_KEY.asBoolean())
            ClientReference.registerKeyBinding(this.oxygenMenuKeybinding = new KeyBinding("key.oxygen_menu.openMenu", OxygenMenuConfig.OXYGEN_MENU_KEY.asInt(), "Oxygen"));

    }

    @SubscribeEvent
    public void onKeyInput(KeyInputEvent event) {        
        if (this.oxygenMenuKeybinding != null && this.oxygenMenuKeybinding.isPressed())
            ClientReference.displayGuiScreen(new OxygenMenuScreen());
    }
}
