package com.windpvp.bedrock;

import org.bukkit.plugin.java.JavaPlugin;

import com.windpvp.bedrock.pvp.SwordBlockInterceptor;

public class BedrockCompat extends JavaPlugin {

    @Override
    public void onEnable() {
        SwordBlockInterceptor.register(this);
        getLogger().info("AdminTools enabled (1.8.8 ProtocolLib)");
    }

    @Override
    public void onDisable() {

    }
}
