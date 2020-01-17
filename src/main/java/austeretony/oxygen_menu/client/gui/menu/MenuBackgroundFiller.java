package austeretony.oxygen_menu.client.gui.menu;

import austeretony.alternateui.screen.core.GUISimpleElement;
import austeretony.alternateui.util.EnumGUIAlignment;
import austeretony.oxygen_core.client.api.EnumBaseGUISetting;
import austeretony.oxygen_core.client.gui.OxygenGUIUtils;
import austeretony.oxygen_core.client.gui.elements.OxygenBackgroundFiller;
import net.minecraft.client.renderer.GlStateManager;

public class MenuBackgroundFiller extends GUISimpleElement<OxygenBackgroundFiller> {

    private final boolean gradient;

    public MenuBackgroundFiller(int xPosition, int yPosition, int width, int height) {             
        this.setPosition(xPosition, yPosition);         
        this.setSize(width, height);
        this.setDynamicBackgroundColor(EnumBaseGUISetting.BACKGROUND_BASE_COLOR.get().asInt(), 0, 0);
        this.gradient = EnumBaseGUISetting.VERTICAL_GRADIENT.get().asBoolean();
    }

    @Override
    public void draw(int mouseX, int mouseY) {  
        GlStateManager.pushMatrix();            
        GlStateManager.translate(this.getX(), this.getY(), 0.0F);            
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);   

        //main background  
        if (this.gradient) {
            OxygenGUIUtils.drawGradientRect(0.0D, - this.screen.guiTop, this.getWidth(), 0.0D, 0x00000000, this.getEnabledBackgroundColor(), EnumGUIAlignment.RIGHT);
            OxygenGUIUtils.drawGradientRect(0.0D, this.getHeight(), this.getWidth(), this.getHeight() + this.screen.guiTop, 0x00000000, this.getEnabledBackgroundColor(), EnumGUIAlignment.RIGHT);
        }

        OxygenGUIUtils.drawGradientRect(0.0D, 0.0D, this.getWidth(), this.getHeight(), 0x00000000, this.getEnabledBackgroundColor(), EnumGUIAlignment.RIGHT);

        GlStateManager.popMatrix();            
    }
}
