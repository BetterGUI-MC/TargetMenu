package me.hsgamer.bettergui.targetmenu;

import java.util.Arrays;
import me.hsgamer.bettergui.object.menu.SimpleMenu;
import me.hsgamer.bettergui.util.MessageUtils;
import org.bukkit.entity.Player;

public class TargetMenu extends SimpleMenu {

  private final TargetManager targetManager = new TargetManager(this);

  public TargetMenu(String name) {
    super(name);
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
