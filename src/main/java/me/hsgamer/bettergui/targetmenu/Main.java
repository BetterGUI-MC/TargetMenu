package me.hsgamer.bettergui.targetmenu;

import me.hsgamer.bettergui.builder.MenuBuilder;
import me.hsgamer.bettergui.config.ConfigPath;
import me.hsgamer.bettergui.object.addon.Addon;

public final class Main extends Addon {

  public static final ConfigPath<String> TARGET_REQUIRED = new ConfigPath<>(String.class,
      "target-player-required", "&cYou need to specify a target player");

  @Override
  public boolean onLoad() {
    TARGET_REQUIRED.setConfig(getPlugin().getMessageConfig());
    getPlugin().getMessageConfig().saveConfig();
    return true;
  }

  @Override
  public void onEnable() {
    MenuBuilder.register("target", TargetMenu.class);
    MenuBuilder.register("target-args", TargetArgsMenu.class);
  }
}
