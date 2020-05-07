package me.hsgamer.bettergui.targetmenu;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import me.hsgamer.bettergui.BetterGUI;
import me.hsgamer.bettergui.config.impl.MessageConfig.DefaultMessage;
import me.hsgamer.bettergui.hook.PlaceholderAPIHook;
import me.hsgamer.bettergui.object.LocalVariable;
import me.hsgamer.bettergui.object.LocalVariableManager;
import me.hsgamer.bettergui.object.menu.ArgsMenu;
import me.hsgamer.bettergui.util.CommonUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class TargetArgsMenu extends ArgsMenu {

  private static final String PAPI = "papi_";
  private final Map<UUID, UUID> targetPlayers = new HashMap<>();

  public TargetArgsMenu(String name) {
    super(name);
  }

  @Override
  public void setFromFile(FileConfiguration file) {
    super.setFromFile(file);
    registerVariable("target_", new LocalVariable() {
      @Override
      public String getIdentifier() {
        return "target_";
      }

      @Override
      public LocalVariableManager<?> getInvolved() {
        return getParent();
      }

      @Override
      public String getReplacement(Player player, String s) {
        UUID uuid = player.getUniqueId();
        s = s.trim();
        if (targetPlayers.containsKey(uuid)) {
          Player target = Bukkit.getPlayer(targetPlayers.get(uuid));
          if (s.toLowerCase().startsWith(PAPI)) {
            return PlaceholderAPIHook.hasValidPlugin() ? PlaceholderAPIHook
                .setPlaceholders("%" + s.substring(PAPI.length()) + "%", target) : null;
          } else {
            String variable = "{" + s + "}";
            return hasVariables(target, variable) ? setVariables(variable, target) : null;
          }
        }
        return null;
      }
    });
  }

  @Override
  public boolean createInventory(Player player, String[] args, boolean bypass) {
    if (args.length < 1) {
      CommonUtils.sendMessage(player, BetterGUI.getInstance().getMessageConfig().getConfig()
          .getString("target-player-required"));
      return false;
    }
    Player target = Bukkit.getPlayer(args[0]);
    if (target == null) {
      CommonUtils.sendMessage(player,
          BetterGUI.getInstance().getMessageConfig().get(DefaultMessage.PLAYER_NOT_FOUND));
      return false;
    }
    targetPlayers.put(player.getUniqueId(), target.getUniqueId());
    return super.createInventory(player, Arrays.copyOfRange(args, 1, args.length), bypass);
  }
}
