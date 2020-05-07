package me.hsgamer.bettergui.targetmenu;

import me.hsgamer.bettergui.builder.MenuBuilder;
import me.hsgamer.bettergui.object.addon.Addon;

public final class Main extends Addon {

  @Override
  public boolean onLoad() {
    getPlugin().getMessageConfig().getConfig()
        .addDefault("target-player-required", "&cYou need to specify a target player");
    getPlugin().getMessageConfig().saveConfig();
    return true;
  }

  @Override
  public void onEnable() {
    MenuBuilder.register("target", TargetMenu.class);
    MenuBuilder.register("target-args", TargetArgsMenu.class);
  }
}
