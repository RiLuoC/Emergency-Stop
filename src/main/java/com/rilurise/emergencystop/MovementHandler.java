package com.rilurise.emergencystop;

import com.rilurise.emergencystop.config.ModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MovementHandler {
    
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null) return;
        
        EntityPlayerSP player = mc.player;
        
        // 处理行走惯性
        if (ModConfig.stopWalkingInertia && !player.capabilities.isFlying) {
            if (!mc.gameSettings.keyBindForward.isKeyDown() &&
                !mc.gameSettings.keyBindBack.isKeyDown() &&
                !mc.gameSettings.keyBindLeft.isKeyDown() &&
                !mc.gameSettings.keyBindRight.isKeyDown()) {
                player.motionX = 0;
                player.motionZ = 0;
            }
        }
        
        // 处理飞行惯性
        if (ModConfig.stopFlyingInertia && player.capabilities.isFlying) {
            if (!mc.gameSettings.keyBindForward.isKeyDown() &&
                !mc.gameSettings.keyBindBack.isKeyDown() &&
                !mc.gameSettings.keyBindLeft.isKeyDown() &&
                !mc.gameSettings.keyBindRight.isKeyDown()) {
                player.motionX = 0;
                player.motionZ = 0;
            }
            
            // 处理垂直方向的飞行惯性
            if (!mc.gameSettings.keyBindJump.isKeyDown() &&
                !mc.gameSettings.keyBindSneak.isKeyDown()) {
                player.motionY = 0;
            }
        }
    }
} 