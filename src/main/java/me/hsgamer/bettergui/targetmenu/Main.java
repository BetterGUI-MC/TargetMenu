package me.hsgamer.bettergui.targetmenu;

import static me.hsgamer.bettergui.BetterGUI.getInstance;

import me.hsgamer.bettergui.builder.MenuBuilder;
import me.hsgamer.bettergui.object.addon.Addon;
import me.hsgamer.bettergui.util.config.path.StringConfigPath;

public final class Main extends Addon {

  public static final StringConfigPath TARGET_REQUIRED = new StringConfigPath(
      "target-player-required", "&cYou need to specify a target player");

  @Override
  public boolean onLoad() {
    TARGET_REQUIRED.setConfig(getInstance().getMessageConfig());
    getInstance().getMessageConfig().saveConfig();
    return true;
  }

  @Override
  public void onEnable() {
    MenuBuilder.register(TargetMenu::new, "target");
    MenuBuilder.register(TargetArgsMenu::new, "target-args");
  }
}
