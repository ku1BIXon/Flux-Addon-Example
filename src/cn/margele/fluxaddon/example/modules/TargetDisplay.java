package cn.margele.fluxaddon.example.modules;

import cn.margele.fluxaddon.example.utils.Timer;
import today.flux.api.api.AddonRenderUtils;
import today.flux.api.api.AddonWorldClient;
import today.flux.api.api.entities.AddonEntity;
import today.flux.api.api.entities.AddonEntityLivingBase;
import today.flux.api.event.events.Event2DRender;
import today.flux.api.module.AddonCategory;
import today.flux.api.module.AddonModule;
import today.flux.api.packet.AddonPacket;
import today.flux.api.packet.client.PacketUseEntity;
import today.flux.api.value.AddonValue;

import java.awt.*;

/*
 * This is a simple module for flux.
 * It can display the target and health left on you screen.
 * */

public class TargetDisplay extends AddonModule {
    // Create a value in module.
    AddonValue renderBackground = new AddonValue("TargetDisplay", "Render Background", false);

    // To save string need to render.
    String targetInfo = "";

    // Create a timer util.
    Timer timer = new Timer();

    public TargetDisplay() {
        super("TargetDisplay", AddonCategory.Render);
    }

    @Override
    public void onPacket(AddonPacket packet) {
        // Test if this packet is Use Entity Packet.
        if (packet instanceof PacketUseEntity) {
            PacketUseEntity useEntity = ((PacketUseEntity) packet);

            // If this packet's action is attack.
            if (useEntity.getAction() == PacketUseEntity.Action.ATTACK) {
                // Get attacking entity.
                AddonEntity entity = AddonWorldClient.getWorldClient().getEntityByID(useEntity.getEntityId());

                // Confirm the entity is existing.
                if (entity != null) {
                    if (entity instanceof AddonEntityLivingBase) {
                        // If this entity is Living Base, get the health.
                        targetInfo = entity.getName() + " HP: " + ((AddonEntityLivingBase) entity).getHealth();
                    } else {
                        // Or just get the name.
                        targetInfo = entity.getName();
                    }
                }
                // Reset timer.
                timer.reset();
            }
        }
    }

    @Override
    public void on2DRender(Event2DRender event) {
        // Get Render Util.
        AddonRenderUtils util = AddonRenderUtils.getInstance();

        // Get window size.
        int[] windowSize = util.getWindowSize();

        // Check timer.
        if (!timer.delay(5000)) {
            // Get string length.
            int width = util.getStringWidth(targetInfo);

            // Check render background value stage.
            if (renderBackground.getBooleanValue())
                // Render Black Background
                util.drawRect((windowSize[0] - width) / 2f - 5, windowSize[1] / 2f - 15f, (windowSize[0] + width) / 2f + 5, windowSize[1] / 2f + 5f, new Color(0, 0, 0).getRGB());

            // Render string.
            util.drawString(targetInfo, (windowSize[0] - width) / 2f, windowSize[1] / 2f - 10f, new Color(255, 255, 255).getRGB(), false);
        }
    }
}
