package austeretony.oxygen_menu.client.gui.menu;

import austeretony.alternateui.screen.core.AbstractGUISection;
import austeretony.alternateui.screen.core.GUIBaseElement;
import austeretony.alternateui.screen.panel.GUIButtonPanel;
import austeretony.alternateui.screen.text.GUITextLabel;
import austeretony.alternateui.util.EnumGUIOrientation;
import austeretony.oxygen.client.core.api.ClientReference;
import austeretony.oxygen.client.gui.AbstractMenuEntry;
import austeretony.oxygen.client.gui.IOxygenMenuEntry;
import austeretony.oxygen.client.gui.IndexedGUIButton;
import austeretony.oxygen.client.gui.OxygenMenuManager;
import austeretony.oxygen.client.gui.settings.GUISettings;
import austeretony.oxygen_menu.client.input.OxygenMenuKeyHandler;

public class MenuGUISection extends AbstractGUISection {

    public MenuGUISection(OxygenMenuGUIScreen screen) {
        super(screen);
    }

    @Override
    public void init() {
        this.addElement(new MenuGUIFiller(0, 0, this.getWidth(), this.getHeight()));
        String title = ClientReference.localize("oxygen_menu.gui.menu.title");
        this.addElement(new GUITextLabel(this.getWidth() - this.textWidth(title, GUISettings.instance().getTitleScale()) - 2, 2).setDisplayText(title, true, GUISettings.instance().getTitleScale()));
        GUIButtonPanel panel;
        this.addElement(panel = new GUIButtonPanel(EnumGUIOrientation.VERTICAL, 0, 12, 120, 18).setButtonsOffset(1).setTextScale(GUISettings.instance().getTextScale()).enableTextShadow());
        for (AbstractMenuEntry entry : OxygenMenuManager.getOxygenMenuEntries())
            panel.addButton(new MenuGUIButton(entry));
    }

    @Override
    public void handleElementClick(AbstractGUISection section, GUIBaseElement element, int mouseButton) {
        if (element instanceof IndexedGUIButton)
            ((IndexedGUIButton<IOxygenMenuEntry>) element).index.open();
    }

    @Override
    public boolean keyTyped(char typedChar, int keyCode) {   
        if (keyCode == OxygenMenuKeyHandler.OXYGEN_MENU.getKeyCode())
            this.screen.close();
        for (AbstractMenuEntry entry : OxygenMenuManager.getOxygenMenuEntries())
            if (keyCode == entry.index + 2)
                entry.open();
        return super.keyTyped(typedChar, keyCode); 
    }
}