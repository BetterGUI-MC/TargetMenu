package me.hsgamer.bettergui.targetmenu;

import me.hsgamer.bettergui.lib.core.bukkit.utils.MessageUtils;
import me.hsgamer.bettergui.menu.SimpleMenu;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class TargetMenu extends SimpleMenu {

    private final TargetManager targetManager;

    public TargetMenu(String name) {
        super(name);
        this.targetManager = new TargetManager(this);
    }

    @Override
    public boolean createInventory(Player player, String[] args, boolean bypass) {
        if (args.length < 1) {
            MessageUtils.sendMessage(player, Main.TARGET_REQUIRED.getValue());
            return false;
        }
        targetManager.storeTarget(player, args[0]);
        return super.createInventory(player, Arrays.copyOfRange(args, 1, args.length), bypass);
    }
}
