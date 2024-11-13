package com.rilurise.emergencystop.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.rilurise.emergencystop.EmergencyStop;
import java.io.File;

public class ModConfig {
    public static Configuration config;
    
    public static boolean stopWalkingInertia = true;
    public static boolean stopFlyingInertia = true;

    public static void init(File configFile) {
        if (config == null) {
            config = new Configuration(configFile);
            loadConfig();
        }
    }

    public static void loadConfig() {
        stopWalkingInertia = config.getBoolean("Stop Walking Inertia", Configuration.CATEGORY_GENERAL,
                true, "停止行走惯性");
        
        stopFlyingInertia = config.getBoolean("Stop Flying Inertia", Configuration.CATEGORY_GENERAL,
                true, "停止飞行惯性");

        if (config.hasChanged()) {
            config.save();
        }
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(EmergencyStop.MODID)) {
            loadConfig();
        }
    }
} 