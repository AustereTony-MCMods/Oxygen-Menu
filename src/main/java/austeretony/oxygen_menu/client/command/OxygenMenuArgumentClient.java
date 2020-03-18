package austeretony.oxygen_menu.client.command;

import java.util.concurrent.TimeUnit;

import austeretony.oxygen_core.client.api.ClientReference;
import austeretony.oxygen_core.client.api.OxygenHelperClient;
import austeretony.oxygen_core.common.command.ArgumentExecutor;
import austeretony.oxygen_menu.client.gui.menu.OxygenMenuScreen;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class OxygenMenuArgumentClient implements ArgumentExecutor {

    @Override
    public String getName() {
        return "menu";
    }

    @Override
    public void process(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 1)
            OxygenHelperClient.scheduleTask(this::openOxygenMenuDelegated, 100L, TimeUnit.MILLISECONDS);
    }

    private void openOxygenMenuDelegated() {
        ClientReference.delegateToClientThread(()->ClientReference.displayGuiScreen(new OxygenMenuScreen()));
    }
}
