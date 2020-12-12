package me.hsgamer.bettergui.targetmenu;

import me.hsgamer.bettergui.api.addon.BetterGUIAddon;
import me.hsgamer.bettergui.builder.MenuBuilder;
import me.hsgamer.bettergui.lib.core.config.path.StringConfigPath;

import static me.hsgamer.bettergui.BetterGUI.getInstance;

public final class Main extends BetterGUIAddon {

    public static final StringConfigPath TARGET_REQUIRED = new StringConfigPath("target-player-required", "&cYou need to specify a target player");

    @Override
    public boolean onLoad() {
        TARGET_REQUIRED.setConfig(getInstance().getMessageConfig());
        getInstance().getMessageConfig().saveConfig();
        return true;
    }

    @Override
    public void onEnable() {
        MenuBuilder.INSTANCE.register(TargetMenu::new, "target");
        MenuBuilder.INSTANCE.register(TargetArgsMenu::new, "target-args");
    }
}
