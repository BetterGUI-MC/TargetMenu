package me.hsgamer.bettergui.targetmenu;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import me.hsgamer.bettergui.hook.PlaceholderAPIHook;
import me.hsgamer.bettergui.object.Menu;
import me.hsgamer.bettergui.object.variable.LocalVariable;
import me.hsgamer.bettergui.object.variable.LocalVariableManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

class TargetManager {

  private static final String PAPI = "papi_";
  private final Map<UUID, UUID> targetPlayers = new HashMap<>();

  public TargetManager(Menu<?> menu) {
    menu.registerVariable("target_", new LocalVariable() {
      @Override
      public String getIdentifier() {
        return "target_";
      }

      @Override
      public LocalVariableManager<?> getInvolved() {
        return menu;
      }

      @Override
      public String getReplacement(OfflinePlayer player, String s) {
        UUID uuid = player.getUniqueId();
        s = s.trim();
        if (targetPlayers.containsKey(uuid)) {
          OfflinePlayer target = Bukkit.getOfflinePlayer(targetPlayers.get(uuid));
          if (s.toLowerCase().startsWith(PAPI)) {
            return PlaceholderAPIHook.hasValidPlugin() ? PlaceholderAPIHook
                .setPlaceholders("%" + s.substring(PAPI.length()) + "%", target) : null;
          } else {
            String variable = "{" + s + "}";
            return getInvolved().hasVariables(target, variable) ? getInvolved()
                .setVariables(variable, target) : null;
          }
        }
        return null;
      }
    });
  }

  @SuppressWarnings("deprecated")
  public void storeTarget(Player player, String target) {
    OfflinePlayer targetPlayer = Bukkit.getOfflinePlayer(target);
    targetPlayers.put(player.getUniqueId(), targetPlayer.getUniqueId());
  }
}
