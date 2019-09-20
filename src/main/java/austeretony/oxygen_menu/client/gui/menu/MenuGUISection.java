package austeretony.oxygen_menu.client.gui.menu;

import austeretony.alternateui.screen.core.AbstractGUISection;
import austeretony.alternateui.screen.core.GUIBaseElement;
import austeretony.alternateui.screen.panel.GUIButtonPanel;
import austeretony.alternateui.util.EnumGUIOrientation;
import austeretony.oxygen_core.client.gui.IndexedGUIButton;
import austeretony.oxygen_core.client.gui.menu.OxygenMenuEntry;
import austeretony.oxygen_core.client.gui.menu.OxygenMenuManager;
import austeretony.oxygen_core.client.gui.settings.GUISettings;
import austeretony.oxygen_menu.client.input.OxygenMenuKeyHandler;

public class MenuGUISection extends AbstractGUISection {

    public MenuGUISection(OxygenMenuGUIScreen screen) {
        super(screen);
    }

    @Override
    public void init() {
        this.addElement(new MenuGUIFiller(0, 0, this.getWidth(), this.getHeight()));
        GUIButtonPanel panel;
        this.addElement(panel = new GUIButtonPanel(EnumGUIOrientation.VERTICAL, 0, 0, 120, 18).setButtonsOffset(1).setTextScale(GUISettings.get().getTextScale()).enableTextShadow());
        for (OxygenMenuEntry entry : OxygenMenuManager.getOxygenMenuEntries())
            panel.addButton(new MenuGUIButton(entry));
    }

    @Override
    public void handleElementClick(AbstractGUISection section, GUIBaseElement element, int mouseButton) {
        if (mouseButton == 0 && element instanceof IndexedGUIButton)
            ((IndexedGUIButton<OxygenMenuEntry>) element).index.open();
    }

    @Override
    public boolean keyTyped(char typedChar, int keyCode) {   
        if (keyCode == OxygenMenuKeyHandler.OXYGEN_MENU.getKeyCode())
            this.screen.close();
        for (OxygenMenuEntry entry : OxygenMenuManager.getOxygenMenuEntries())
            if (keyCode == entry.getIndex() + 2)
                entry.open();
        return super.keyTyped(typedChar, keyCode); 
    }
}
