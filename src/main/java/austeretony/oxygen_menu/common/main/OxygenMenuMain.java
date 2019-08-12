package austeretony.oxygen_menu.common.main;

import austeretony.oxygen.client.gui.OxygenMenuManager;
import austeretony.oxygen.common.core.api.CommonReference;
import austeretony.oxygen_menu.client.input.OxygenMenuKeyHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod(
        modid = OxygenMenuMain.MODID, 
        name = OxygenMenuMain.NAME, 
        version = OxygenMenuMain.VERSION,
        clientSideOnly = true,
        dependencies = "required-after:oxygen@[0.8.2,);",//TODO Always check required Oxygen version before build
        certificateFingerprint = "@FINGERPRINT@",
        updateJSON = OxygenMenuMain.VERSIONS_FORGE_URL)
public class OxygenMenuMain {

    public static final String 
    MODID = "oxygen_menu",    
    NAME = "Oxygen: Menu",
    VERSION = "0.8.0",
    VERSION_CUSTOM = VERSION + ":beta:0",
    GAME_VERSION = "1.12.2",
    VERSIONS_FORGE_URL = "https://raw.githubusercontent.com/AustereTony-MCMods/Oxygen-Menu/info/mod_versions_forge.json";

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        if (event.getSide() == Side.CLIENT)
            OxygenMenuManager.enableOxygenMenu();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        if (event.getSide() == Side.CLIENT)
            CommonReference.registerEvent(new OxygenMenuKeyHandler());
    }
}