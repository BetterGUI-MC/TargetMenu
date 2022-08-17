package me.hsgamer.bettergui.targetmenu;

import me.hsgamer.hscore.config.Config;
import me.hsgamer.hscore.config.annotated.AnnotatedConfig;
import me.hsgamer.hscore.config.annotation.ConfigPath;

public class ExtraMessageConfig extends AnnotatedConfig {
    public final @ConfigPath("target-player-required") String targetRequired;

    public ExtraMessageConfig(Config config) {
        super(config);
        targetRequired = "&cYou need to specify a target player";
    }
}
