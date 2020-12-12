package me.hsgamer.bettergui.targetmenu;

import me.hsgamer.bettergui.api.menu.Menu;
import me.hsgamer.bettergui.hook.PlaceholderAPIHook;
import me.hsgamer.bettergui.lib.core.variable.VariableManager;
import me.hsgamer.bettergui.manager.PluginVariableManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

class TargetManager {

    private static final String PAPI = "papi_";
    private final Map<UUID, UUID> targetPlayers = new HashMap<>();

    public TargetManager(Menu menu) {
        PluginVariableManager.register("menu_" + menu.getName() + "_target_", (s, uuid) -> {
            s = s.trim();
            if (targetPlayers.containsKey(uuid)) {
                OfflinePlayer target = Bukkit.getOfflinePlayer(targetPlayers.get(uuid));
                if (s.toLowerCase().startsWith(PAPI)) {
                    return PlaceholderAPIHook.hasValidPlugin() ? PlaceholderAPIHook.setPlaceholders("%" + s.substring(PAPI.length()) + "%", target) : null;
                } else {
                    String variable = "{" + s + "}";
                    return VariableManager.setVariables(variable, target.getUniqueId());
                }
            }
            return null;
        });
    }

    @SuppressWarnings("deprecated")
    public void storeTarget(Player player, String target) {
        OfflinePlayer targetPlayer = Bukkit.getOfflinePlayer(target);
        targetPlayers.put(player.getUniqueId(), targetPlayer.getUniqueId());
    }
}
