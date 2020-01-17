package austeretony.oxygen_menu.client.gui.menu;

import austeretony.alternateui.screen.core.AbstractGUIScreen;
import austeretony.alternateui.screen.core.AbstractGUISection;
import austeretony.alternateui.screen.core.GUIBaseElement;
import austeretony.alternateui.screen.core.GUIWorkspace;
import austeretony.alternateui.util.EnumGUIAlignment;
import austeretony.oxygen_core.client.api.OxygenMenuHelper;
import austeretony.oxygen_core.client.gui.menu.OxygenMenuEntry;

public class OxygenMenuScreen extends AbstractGUIScreen {

    private AbstractGUISection menuSection;

    @Override
    protected GUIWorkspace initWorkspace() {
        int amount = getEntriesAmount();
        return new GUIWorkspace(this, 100, amount * 18 + (amount - 1)).setAlignment(EnumGUIAlignment.RIGHT, 0, 0);
    }

    @Override
    protected void initSections() {
        this.menuSection = this.getWorkspace().initSection(new MenuSection(this));        
    }

    @Override
    protected AbstractGUISection getDefaultSection() {
        return this.menuSection;
    }

    @Override
    public void handleElementClick(AbstractGUISection section, GUIBaseElement element) {}

    @Override
    protected boolean doesGUIPauseGame() {
        return false;
    }

    public static int getEntriesAmount() {
        int i = 0;
        for (OxygenMenuEntry entry : OxygenMenuHelper.getOxygenMenuEntries())
            if (entry.isValid())
                i++;
        return i;
    }
}
