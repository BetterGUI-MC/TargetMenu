package me.hsgamer.bettergui.targetmenu;

import me.hsgamer.bettergui.api.menu.Menu;
import me.hsgamer.bettergui.util.StringReplacerApplier;
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
    public void storeTarget(Player player, String target) {
        OfflinePlayer targetPlayer = Bukkit.getOfflinePlayer(target);
        targetPlayers.put(player.getUniqueId(), targetPlayer.getUniqueId());
    }
}
