package austeretony.oxygen_menu.client.gui.menu;

import austeretony.alternateui.util.EnumGUIAlignment;
import austeretony.oxygen_core.client.api.ClientReference;
import austeretony.oxygen_core.client.api.EnumBaseClientSetting;
import austeretony.oxygen_core.client.api.EnumBaseGUISetting;
import austeretony.oxygen_core.client.gui.OxygenGUIUtils;
import austeretony.oxygen_core.client.gui.elements.OxygenIndexedPanelEntry;
import austeretony.oxygen_core.client.gui.menu.OxygenMenuEntry;
import austeretony.oxygen_core.common.sound.OxygenSoundEffects;
import net.minecraft.client.renderer.GlStateManager;

public class MenuPanelEntry extends OxygenIndexedPanelEntry<OxygenMenuEntry> {

    private final String entryKeyStr;

    public MenuPanelEntry(OxygenMenuEntry entry) {
        super(entry);
        this.entryKeyStr = "[" + ClientReference.getGameSettings().getKeyDisplayString(entry.getKeyCode()) + "]";
        this.setDisplayText(entry.getLocalizedName());
        this.setDynamicBackgroundColor(EnumBaseGUISetting.ELEMENT_ENABLED_COLOR.get().asInt(), EnumBaseGUISetting.ELEMENT_DISABLED_COLOR.get().asInt(), EnumBaseGUISetting.ELEMENT_HOVERED_COLOR.get().asInt());
        this.setTextDynamicColor(EnumBaseGUISetting.TEXT_ENABLED_COLOR.get().asInt(), EnumBaseGUISetting.TEXT_DISABLED_COLOR.get().asInt(), EnumBaseGUISetting.TEXT_HOVERED_COLOR.get().asInt());
        if (EnumBaseClientSetting.ENABLE_SOUND_EFFECTS.get().asBoolean())
            this.setSound(OxygenSoundEffects.BUTTON_CLICK.soundEvent);
        this.enableFull();
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        if (this.isVisible()) {
            GlStateManager.pushMatrix();           
            GlStateManager.translate(this.getX(), this.getY(), 0.0F);   
            GlStateManager.scale(this.getScale(), this.getScale(), 0.0F); 
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            int 
            color = this.getEnabledBackgroundColor(), 
            textColor = this.getEnabledTextColor();                      
            if (!this.isEnabled()) {                 
                color = this.getDisabledBackgroundColor();
                textColor = this.getDisabledTextColor(); 
            } else if (this.isHovered() || this.isToggled()) {                 
                color = this.getHoveredBackgroundColor();
                textColor = this.getHoveredTextColor();
            }

            OxygenGUIUtils.drawGradientRect(0.0D, 0.0D, this.getWidth(), this.getHeight(), 0x00000000, color, EnumGUIAlignment.RIGHT);

            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            GlStateManager.pushMatrix();           
            GlStateManager.translate(this.getWidth() - this.textWidth(this.getDisplayText(), this.getTextScale()) - 6.0F, (this.getHeight() - this.textHeight(this.getTextScale())) / 2.0F, 0.0F); 
            GlStateManager.scale(this.getTextScale(), this.getTextScale(), 0.0F); 
            this.mc.fontRenderer.drawString(this.getDisplayText(), 0, 0, textColor, this.isTextShadowEnabled());
            if (this.isHovered())
                this.mc.fontRenderer.drawString(this.entryKeyStr, - this.textWidth(this.entryKeyStr, this.getTextScale()) - 10, 0, this.getDisabledTextColor(), this.isTextShadowEnabled());
            GlStateManager.popMatrix();

            GlStateManager.popMatrix();
        }
    }
}
