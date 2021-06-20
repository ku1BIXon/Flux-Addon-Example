package cn.margele.fluxaddon.example.modules;

import today.flux.api.api.AddonEntityClientPlayer;
import today.flux.api.event.events.EventPreUpdate;
import today.flux.api.module.AddonCategory;
import today.flux.api.module.AddonModule;
import today.flux.api.utils.Motion;

/*
 * This is a simple module for flux.
 * It can make you jump higher.
 * */

public class BigJump extends AddonModule {

    public BigJump() {
        super("BigJump", AddonCategory.Movement);
    }

    // Override onPreUpdate to handle event.
    @Override
    public void onPreUpdate(EventPreUpdate event) {
        // Get player entity.
        AddonEntityClientPlayer player = AddonEntityClientPlayer.getClientPlayer();

        // Confirm player is on ground and player is moving.
        if (player.isOnGround() && player.isMoving()) {
            // Make Player jump.
            player.jump();

            // Modify motionY = 1.
            Motion motion = player.getMotion(); // Get player motion.
            motion.y = 1; // Set motionY to 1.
            player.setMotion(motion); // Set back motion to player.
        }
    }
}
