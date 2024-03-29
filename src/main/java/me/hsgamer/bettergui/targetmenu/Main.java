package me.hsgamer.bettergui.targetmenu;

import me.hsgamer.bettergui.builder.ArgumentProcessorBuilder;
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
        ArgumentProcessorBuilder.INSTANCE.register(menu -> new TargetArgumentProcessor(this, menu), "target");
    }

    @Override
    public void onReload() {
        extraMessageConfig.reload();
    }

    public ExtraMessageConfig getExtraMessageConfig() {
        return extraMessageConfig;
    }
}
