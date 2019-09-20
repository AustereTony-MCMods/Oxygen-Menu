package austeretony.oxygen_menu.client.gui.menu;

import austeretony.alternateui.util.EnumGUIAlignment;
import austeretony.oxygen_core.client.api.ClientReference;
import austeretony.oxygen_core.client.gui.IndexedGUIButton;
import austeretony.oxygen_core.client.gui.elements.CustomRectUtils;
import austeretony.oxygen_core.client.gui.menu.OxygenMenuEntry;
import austeretony.oxygen_core.client.gui.settings.GUISettings;
import austeretony.oxygen_core.common.sound.OxygenSoundEffects;
import net.minecraft.client.renderer.GlStateManager;

public class MenuGUIButton extends IndexedGUIButton<OxygenMenuEntry> {

    private final String key;

    public MenuGUIButton(OxygenMenuEntry entry) {
        super(entry);
        this.key = "[" + String.valueOf(entry.getIndex() + 1) + "]";
        this.setDisplayText(ClientReference.localize(entry.getName()));
        this.enableDynamicBackground(GUISettings.get().getEnabledElementColor(), GUISettings.get().getEnabledElementColor(), GUISettings.get().getHoveredElementColor());
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
            textColor = this.getEnabledTextColor(), 
            textY = (this.getHeight() - this.textHeight(this.getTextScale())) / 2,
            iconU = 0;                      
            if (!this.isEnabled()) {                 
                color = this.getDisabledBackgroundColor();
                textColor = this.getDisabledTextColor(); 
                iconU = 14;                      
            } else if (this.isHovered() || this.isToggled()) {                 
                color = this.getHoveredBackgroundColor();
                textColor = this.getHoveredTextColor();
                iconU = 28;                      
            }

            CustomRectUtils.drawGradientRect(0.0D, 0.0D, this.getWidth(), this.getHeight(), 0x00000000, color, EnumGUIAlignment.RIGHT);

            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            GlStateManager.pushMatrix();           
            GlStateManager.translate(this.getWidth() - this.textWidth(this.getDisplayText(), this.getTextScale()) - 6.0F, textY, 0.0F); 
            GlStateManager.scale(this.getTextScale(), this.getTextScale(), 0.0F); 
            this.mc.fontRenderer.drawString(this.getDisplayText(), 0, 0, textColor, this.isTextShadowEnabled());
            if (this.isHovered())
                this.mc.fontRenderer.drawString(this.key, - this.textWidth(this.key, this.getTextScale()) - 10, 0, this.getDisabledTextColor(), this.isTextShadowEnabled());
            GlStateManager.popMatrix();

            GlStateManager.popMatrix();
        }
    }
}
