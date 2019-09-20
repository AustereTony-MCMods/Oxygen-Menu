package austeretony.oxygen_menu.client.gui.menu;

import austeretony.alternateui.screen.core.AbstractGUIScreen;
import austeretony.alternateui.screen.core.AbstractGUISection;
import austeretony.alternateui.screen.core.GUIBaseElement;
import austeretony.alternateui.screen.core.GUIWorkspace;
import austeretony.alternateui.util.EnumGUIAlignment;
import austeretony.oxygen_core.client.gui.menu.OxygenMenuManager;

public class OxygenMenuGUIScreen extends AbstractGUIScreen {

    private AbstractGUISection menuSection;

    @Override
    protected GUIWorkspace initWorkspace() {
        int amount = OxygenMenuManager.getOxygenMenuEntries().size();
        return new GUIWorkspace(this, 120, amount * 18 + (amount - 1)).setAlignment(EnumGUIAlignment.RIGHT, - 10, 0);
    }

    @Override
    protected void initSections() {
        this.menuSection = this.getWorkspace().initSection(new MenuGUISection(this));        
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
}
