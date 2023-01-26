package me.hsgamer.bettergui.targetmenu;

import me.hsgamer.bettergui.api.argument.ArgumentProcessor;
import me.hsgamer.bettergui.api.menu.Menu;
import me.hsgamer.hscore.bukkit.utils.BukkitUtils;
import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import me.hsgamer.hscore.common.Pair;

import java.util.*;

public class TargetArgumentProcessor implements ArgumentProcessor {
    private final Main main;
    private final Menu menu;
    private final TargetManager targetManager;

    public TargetArgumentProcessor(Main main, Menu menu) {
        this.main = main;
        this.menu = menu;
        this.targetManager = new TargetManager(menu);
    }

    @Override
    public Optional<String[]> process(UUID uuid, String[] args) {
        if (args.length < 1) {
            MessageUtils.sendMessage(uuid, main.getExtraMessageConfig().targetRequired);
            return Optional.empty();
        }
        targetManager.storeTarget(uuid, args[0]);
        return Optional.of(Arrays.copyOfRange(args, 1, args.length));
    }

    @Override
    public Pair<Optional<List<String>>, String[]> tabComplete(UUID uuid, String[] args) {
        if (args.length == 0) {
            return Pair.of(Optional.of(Collections.emptyList()), new String[0]);
        }
        if (args.length == 1) {
            return Pair.of(Optional.of(BukkitUtils.getAllPlayerNames()), new String[0]);
        }
        return Pair.of(Optional.empty(), Arrays.copyOfRange(args, 1, args.length));
    }

    @Override
    public Menu getMenu() {
        return menu;
    }
}
