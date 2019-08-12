package austeretony.oxygen_menu.client.gui.menu;

import austeretony.oxygen.client.core.api.ClientReference;
import austeretony.oxygen.client.gui.AbstractMenuEntry;
import austeretony.oxygen.client.gui.IndexedGUIButton;
import austeretony.oxygen.client.gui.settings.GUISettings;
import austeretony.oxygen.common.main.OxygenSoundEffects;
import net.minecraft.client.renderer.GlStateManager;

public class MenuGUIButton extends IndexedGUIButton<AbstractMenuEntry> {

    private final String key;

    public MenuGUIButton(AbstractMenuEntry entry) {
        super(entry);
        this.key = "[" + String.valueOf(entry.index + 1) + "]";
        this.setTexture(entry.getIcon());
        this.setDisplayText(ClientReference.localize(entry.getName()));
        this.enableDynamicBackground(GUISettings.instance().getEnabledElementColor(), GUISettings.instance().getEnabledElementColor(), GUISettings.instance().getHoveredElementColor());
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

            MenuGUIFiller.drawGradient(0, 0, this.getWidth(), this.getHeight(), 0x00000000, color);

            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

            GlStateManager.pushMatrix();           
            GlStateManager.translate(this.getWidth() - this.textWidth(this.getDisplayText(), this.getTextScale()) - 6.0F/*18.0F*/, textY, 0.0F); 
            GlStateManager.scale(this.getTextScale(), this.getTextScale(), 0.0F); 
            this.mc.fontRenderer.drawString(this.getDisplayText(), 0, 0, textColor, this.isTextShadowEnabled());
            if (this.isHovered())
                this.mc.fontRenderer.drawString(this.key, - this.textWidth(this.key, this.getTextScale()) - 10, 0, this.getDisabledTextColor(), this.isTextShadowEnabled());
            GlStateManager.popMatrix();

            //TODO Setup textures first
            /*GlStateManager.enableBlend(); 
            this.mc.getTextureManager().bindTexture(this.getTexture());                        
            drawCustomSizedTexturedRect(this.getWidth() - 16, 3, iconU, 0, 14, 14, 42, 14);
            GlStateManager.disableBlend();*/

            GlStateManager.popMatrix();
        }
    }
}
