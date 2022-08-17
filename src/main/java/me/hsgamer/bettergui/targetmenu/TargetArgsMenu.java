package me.hsgamer.bettergui.targetmenu;

import me.hsgamer.bettergui.menu.ArgumentMenu;
import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import me.hsgamer.hscore.config.Config;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class TargetArgsMenu extends ArgumentMenu {

    private final TargetManager targetManager;
    private final Main main;

    public TargetArgsMenu(Main main, Config config) {
        super(config);
        this.main = main;
        targetManager = new TargetManager(this);
    }


    @Override
    public boolean create(Player player, String[] args, boolean bypass) {
        if (args.length < 1) {
            MessageUtils.sendMessage(player, main.getExtraMessageConfig().targetRequired);
            return false;
        }
        targetManager.storeTarget(player, args[0]);
        return super.create(player, Arrays.copyOfRange(args, 1, args.length), bypass);
    }
}
