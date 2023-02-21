package me.hsgamer.bettergui.targetmenu;

import me.hsgamer.bettergui.api.argument.ArgumentProcessor;
import me.hsgamer.bettergui.api.menu.Menu;
import me.hsgamer.bettergui.util.StringReplacerApplier;
import me.hsgamer.hscore.bukkit.utils.BukkitUtils;
import me.hsgamer.hscore.bukkit.utils.MessageUtils;
import me.hsgamer.hscore.common.Pair;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.*;
import java.util.stream.Collectors;

public class TargetArgumentProcessor implements ArgumentProcessor {
    private static final String PAPI = "papi_";
    private final Map<UUID, UUID> targetPlayers = new HashMap<>();
    private final Main main;
    private final Menu menu;

    public TargetArgumentProcessor(Main main, Menu menu) {
        this.main = main;
        this.menu = menu;
        menu.getVariableManager().register("target_", (s, uuid) -> {
            s = s.trim();
            if (targetPlayers.containsKey(uuid)) {
                OfflinePlayer target = Bukkit.getOfflinePlayer(targetPlayers.get(uuid));
                String variable;
                if (s.toLowerCase().startsWith(PAPI)) {
                    variable = "%" + s.substring(PAPI.length()) + "%";
                } else {
                    variable = "{" + s + "}";
                }
                return StringReplacerApplier.replace(variable, target.getUniqueId(), menu);
            }
            return null;
        });
    }

    @SuppressWarnings("deprecation")
    private void storeTarget(UUID uuid, String target) {
        OfflinePlayer targetPlayer = Bukkit.getOfflinePlayer(target);
        targetPlayers.put(uuid, targetPlayer.getUniqueId());
    }

    @Override
    public Optional<String[]> process(UUID uuid, String[] args) {
        if (args.length < 1) {
            MessageUtils.sendMessage(uuid, main.getExtraMessageConfig().targetRequired);
            return Optional.empty();
        }
        storeTarget(uuid, args[0]);
        return Optional.of(Arrays.copyOfRange(args, 1, args.length));
    }

    @Override
    public Pair<Optional<List<String>>, String[]> tabComplete(UUID uuid, String[] args) {
        if (args.length == 0) {
            return Pair.of(Optional.of(Collections.emptyList()), new String[0]);
        }
        if (args.length == 1) {
            String arg = args[0].toLowerCase();
            List<String> list = BukkitUtils.getAllPlayerNames().stream()
                    .filter(name -> arg.isEmpty() || name.toLowerCase().startsWith(arg))
                    .collect(Collectors.toList());
            return Pair.of(Optional.of(list), new String[0]);
        }
        return Pair.of(Optional.empty(), Arrays.copyOfRange(args, 1, args.length));
    }

    @Override
    public Menu getMenu() {
        return menu;
    }
}
