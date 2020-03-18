package austeretony.oxygen_menu.common.config;

import java.util.List;

import austeretony.oxygen_core.common.api.CommonReference;
import austeretony.oxygen_core.common.api.config.AbstractConfig;
import austeretony.oxygen_core.common.config.ConfigValue;
import austeretony.oxygen_core.common.config.ConfigValueUtils;
import austeretony.oxygen_menu.common.main.OxygenMenuMain;

public class OxygenMenuConfig extends AbstractConfig {

    public static final ConfigValue
    ENABLE_OXYGEN_MENU_KEY = ConfigValueUtils.getValue("client", "enable_oxygen_menu_key", true),
    OXYGEN_MENU_KEY = ConfigValueUtils.getValue("client", "oxygen_menu_key", 15);

    @Override
    public String getDomain() {
        return OxygenMenuMain.MODID;
    }

    @Override
    public String getVersion() {
        return OxygenMenuMain.VERSION_CUSTOM;
    }

    @Override
    public String getExternalPath() {
        return CommonReference.getGameFolder() + "/config/oxygen/menu.json";
    }

    @Override
    public void getValues(List<ConfigValue> values) {
        values.add(ENABLE_OXYGEN_MENU_KEY);
        values.add(OXYGEN_MENU_KEY);
    }
}
