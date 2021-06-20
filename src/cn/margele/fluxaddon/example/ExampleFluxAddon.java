package cn.margele.fluxaddon.example;

import cn.margele.fluxaddon.example.commands.TestCommand;
import cn.margele.fluxaddon.example.modules.BigJump;
import cn.margele.fluxaddon.example.modules.NoHits;
import cn.margele.fluxaddon.example.modules.ScaffoldInfoDisplay;
import cn.margele.fluxaddon.example.modules.TargetDisplay;
import today.flux.api.FluxAddon;
import today.flux.api.FluxAPI;
import today.flux.api.command.AddonCommand;
import today.flux.api.module.AddonModule;

import java.util.ArrayList;
import java.util.List;

/*
*  This is a simple Flux Addon Instance.
* */
public class ExampleFluxAddon extends FluxAddon {
    // Save Flux API Instance.
    public static FluxAPI API;

    // Create two list for save modules and commands you want to add.
    List<AddonModule> modules = new ArrayList<>();
    List<AddonCommand> commands = new ArrayList<>();

    @Override
    public String getAPIName() {
        return "Example API 2";
    }

    // Return your version here.
    @Override
    public float getVersion() {
        return 1.0f;
    }

    // Return your Author here.
    @Override
    public String getAuthor() {
        return "Margele";
    }

    // This method will be invoked when Flux is loading this addon.
    // (Whether it has been enabled or not)
    @Override
    public void initAPI(FluxAPI api) {
        // Save API Instance.
        API = api;

        // I recommend you can load modules and commands here.
        modules.add(new BigJump());
        modules.add(new NoHits());
        modules.add(new ScaffoldInfoDisplay());
        modules.add(new TargetDisplay());

        commands.add(new TestCommand());
    }

    // Return AddonModules here
    @Override
    public List<AddonModule> getModules() {
        return modules;
    }

    // Return AddonCommands here
    @Override
    public List<AddonCommand> getCommands() {
        return commands;
    }
}
