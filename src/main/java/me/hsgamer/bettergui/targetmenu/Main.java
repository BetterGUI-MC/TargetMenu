package me.hsgamer.bettergui.targetmenu;

import me.hsgamer.bettergui.builder.MenuBuilder;
import me.hsgamer.hscore.bukkit.addon.PluginAddon;
import me.hsgamer.hscore.bukkit.config.BukkitConfig;

import java.io.File;

public final class Main extends PluginAddon {
    private final ExtraMessageConfig extraMessageConfig = new ExtraMessageConfig(new BukkitConfig(new File(getDataFolder(), "messages.yml")));

    @Override
    public boolean onLoad() {
        extraMessageConfig.setup();
        return true;
    }

    @Override
    public void onEnable() {
        MenuBuilder.INSTANCE.register(config -> new TargetMenu(this, config), "target");
        MenuBuilder.INSTANCE.register(config -> new TargetArgsMenu(this, config), "target-args");
    }

    @Override
    public void onReload() {
        extraMessageConfig.reload();
    }

    public ExtraMessageConfig getExtraMessageConfig() {
        return extraMessageConfig;
    }
}
