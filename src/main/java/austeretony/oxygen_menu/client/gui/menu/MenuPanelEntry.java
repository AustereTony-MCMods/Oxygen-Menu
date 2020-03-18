package austeretony.oxygen_menu.client.gui.menu;

import austeretony.alternateui.util.EnumGUIAlignment;
import austeretony.oxygen_core.client.api.ClientReference;
import austeretony.oxygen_core.client.api.EnumBaseClientSetting;
import austeretony.oxygen_core.client.api.EnumBaseGUISetting;
import austeretony.oxygen_core.client.gui.OxygenGUIUtils;
import austeretony.oxygen_core.client.gui.elements.OxygenWrapperPanelEntry;
import austeretony.oxygen_core.client.gui.menu.OxygenMenuEntry;
import austeretony.oxygen_core.common.sound.OxygenSoundEffects;
import net.minecraft.client.renderer.GlStateManager;

public class MenuPanelEntry extends OxygenWrapperPanelEntry<OxygenMenuEntry> {

    private final String entryKeyStr;

    private final EnumGUIAlignment menuAlignment;

    public MenuPanelEntry(EnumGUIAlignment menuAlignment, OxygenMenuEntry entry) {
        super(entry);
        this.menuAlignment = menuAlignment;
        this.entryKeyStr = String.format("[%s]", ClientReference.getGameSettings().getKeyDisplayString(entry.getKeyCode()));
        this.setDisplayText(entry.getLocalizedName());
        this.setDynamicBackgroundColor(EnumBaseGUISetting.ELEMENT_ENABLED_COLOR.get().asInt(), EnumBaseGUISetting.ELEMENT_DISABLED_COLOR.get().asInt(), EnumBaseGUISetting.ELEMENT_HOVERED_COLOR.get().asInt());
        this.setTextDynamicColor(EnumBaseGUISetting.TEXT_ENABLED_COLOR.get().asInt(), EnumBaseGUISetting.TEXT_DISABLED_COLOR.get().asInt(), EnumBaseGUISetting.TEXT_HOVERED_COLOR.get().asInt());
        if (EnumBaseClientSetting.ENABLE_SOUND_EFFECTS.get().asBoolean())
            this.setSound(OxygenSoundEffects.BUTTON_CLICK.getSoundEvent());
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

            if (this.menuAlignment == EnumGUIAlignment.RIGHT || this.menuAlignment == EnumGUIAlignment.LEFT)
                OxygenGUIUtils.drawGradientRect(0.0D, 0.0D, this.getWidth(), this.getHeight(), 0x00000000, color, this.menuAlignment);
            else {
                int third = this.getWidth() / 3;
                OxygenGUIUtils.drawGradientRect(0.0D, 0.0D, third, this.getHeight(), 0x00000000, color, EnumGUIAlignment.RIGHT);
                drawRect(third, 0, this.getWidth() - third, this.getHeight(), color);
                OxygenGUIUtils.drawGradientRect(this.getWidth() - third, 0.0D, this.getWidth(), this.getHeight(), 0x00000000, color, EnumGUIAlignment.LEFT);
            }

            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            GlStateManager.pushMatrix();           
            GlStateManager.translate(this.menuAlignment == EnumGUIAlignment.RIGHT ? this.getWidth() - this.textWidth(this.getDisplayText(), this.getTextScale()) - 6.0F : 6.0F, (this.getHeight() - this.textHeight(this.getTextScale())) / 2.0F, 0.0F); 
            GlStateManager.scale(this.getTextScale(), this.getTextScale(), 0.0F); 
            this.mc.fontRenderer.drawString(this.getDisplayText(), 0, 0, textColor, false);
            if (this.isHovered())
                this.mc.fontRenderer.drawString(this.entryKeyStr, this.menuAlignment == EnumGUIAlignment.RIGHT ? - this.textWidth(this.entryKeyStr, 1.0F) - 4 : this.textWidth(this.getDisplayText(), 1.0F) + 4, 0, this.getDisabledTextColor(), false);
            GlStateManager.popMatrix();

            GlStateManager.popMatrix();
        }
    }
}
