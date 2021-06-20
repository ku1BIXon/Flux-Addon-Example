package cn.margele.fluxaddon.example.modules;

import today.flux.api.api.AddonEntityClientPlayer;
import today.flux.api.api.AddonWorldClient;
import today.flux.api.api.entities.AddonEntity;
import today.flux.api.module.AddonCategory;
import today.flux.api.module.AddonModule;
import today.flux.api.packet.AddonPacket;
import today.flux.api.packet.client.PacketUseEntity;

/*
 * This is a simple module for flux.
 * It can make you can not hit any entity.
 * */

public class NoHits extends AddonModule {
    public NoHits() {
        super("NoHits", AddonCategory.Combat);
    }

    // Override onPacket to handle packets.
    @Override
    public void onPacket(AddonPacket packet) {
        // Test if this packet is Use Entity Packet.
        if (packet instanceof PacketUseEntity) {
            PacketUseEntity useEntityPacket = (PacketUseEntity) packet;
            // If this packet's action is attack.
            if (useEntityPacket.getAction() == PacketUseEntity.Action.ATTACK) {
                // Cancel this packet.
                packet.setCancelled(true);

                // Get attacking entity.
                AddonEntity attackingEntity = AddonWorldClient.getWorldClient().getEntityByID(useEntityPacket.getEntityId());
                // Get entity's name.
                String name = attackingEntity.getName();

                // Tell player the entity.
                AddonEntityClientPlayer.getClientPlayer().addChatMessage(String.format("Player tried to attack %s!", name));
            }
        }
    }
}
