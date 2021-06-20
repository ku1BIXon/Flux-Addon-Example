package cn.margele.fluxaddon.example.modules;

import cn.margele.fluxaddon.example.ExampleFluxAddon;
import today.flux.api.event.events.Event2DRender;
import today.flux.api.module.AddonCategory;
import today.flux.api.module.AddonModule;
import today.flux.api.value.AddonValue;

/*
 * This is a simple module for flux.
 * It can help you to confirm Scaffold mode and display stage.
 * */

public class ScaffoldInfoDisplay extends AddonModule {

    public ScaffoldInfoDisplay() {
        super("ScaffoldInfoDisplay", AddonCategory.Misc);
    }

    // Override onPacket to handle packets.
    @Override
    public void on2DRender(Event2DRender event) {
        // Get other module.
        AddonModule scaffold = ExampleFluxAddon.API.getModuleManager().getModule("Scaffold");

        // Get other value.
        AddonValue mode = new AddonValue("Scaffold", "Mode");
        // Get value stage.
        String modeString = mode.getModeValue();

        // Set render name.
        this.setRenderName(this.getName() + String.format(" \247fScaffold:%s Mode:%s", scaffold.getStage() ? "On" : "Off", modeString));
    }
}
