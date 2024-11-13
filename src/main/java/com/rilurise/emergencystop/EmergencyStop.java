package com.rilurise.emergencystop;

import com.rilurise.emergencystop.config.ModConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = EmergencyStop.MODID, name = EmergencyStop.NAME, version = EmergencyStop.VERSION, clientSideOnly = true, guiFactory = "com.rilurise.emergencystop.config.GuiFactory")
public class EmergencyStop {
    public static final String MODID = "emergencystop";
    public static final String NAME = "Emergency Stop";
    public static final String VERSION = "1.0";

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModConfig.init(event.getSuggestedConfigurationFile());
        MinecraftForge.EVENT_BUS.register(new ModConfig());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new MovementHandler());
    }
} 