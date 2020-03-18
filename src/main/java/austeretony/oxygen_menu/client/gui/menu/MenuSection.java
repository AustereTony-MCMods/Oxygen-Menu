package austeretony.oxygen_menu.client.gui.menu;

import austeretony.alternateui.screen.core.AbstractGUISection;
import austeretony.alternateui.screen.core.GUIBaseElement;
import austeretony.oxygen_core.client.api.ClientReference;
import austeretony.oxygen_core.client.api.EnumBaseGUISetting;
import austeretony.oxygen_core.client.api.OxygenMenuHelper;
import austeretony.oxygen_core.client.gui.elements.OxygenScrollablePanel;
import austeretony.oxygen_core.client.gui.menu.OxygenMenuEntry;
import austeretony.oxygen_menu.common.config.OxygenMenuConfig;

public class MenuSection extends AbstractGUISection {

    private OxygenMenuScreen screen;

    private OxygenScrollablePanel entriesPanel;

    public MenuSection(OxygenMenuScreen screen) {
        super(screen);
        this.screen = screen;
    }

    @Override
    public void init() {
        this.addElement(new MenuBackgroundFiller(0, 0, this.getWidth(), this.getHeight(), this.screen.getMenuAlignment()));

        int amount = OxygenMenuScreen.getEntriesAmount();
        this.addElement(this.entriesPanel = new OxygenScrollablePanel(this.screen, 0, 0, 100, 18, 1, amount, amount, EnumBaseGUISetting.TEXT_SCALE.get().asFloat(), false));
        for (OxygenMenuEntry entry : OxygenMenuHelper.getOxygenMenuEntries())
            if (entry.isValid())
                this.entriesPanel.addEntry(new MenuPanelEntry(this.screen.getMenuAlignment(), entry));

        this.entriesPanel.<MenuPanelEntry>setElementClickListener((previous, clicked, mouseX, mouseY, mouseButton)->{
            if (mouseButton == 0) {
                this.screen.close();
                ClientReference.delegateToClientThread(clicked.getWrapped()::open);                
            }
        });
    }

    @Override
    public void handleElementClick(AbstractGUISection section, GUIBaseElement element, int mouseButton) {}

    @Override
    public boolean keyTyped(char typedChar, int keyCode) {   
        if (keyCode == OxygenMenuConfig.OXYGEN_MENU_KEY.asInt())
            this.screen.close();
        for (OxygenMenuEntry entry : OxygenMenuHelper.getOxygenMenuEntries()) {
            if (entry.isValid()) {
                if (keyCode == entry.getKeyCode()) {
                    this.screen.close();
                    ClientReference.delegateToClientThread(entry::open);                
                }
            }
        }
        return super.keyTyped(typedChar, keyCode); 
    }
}
