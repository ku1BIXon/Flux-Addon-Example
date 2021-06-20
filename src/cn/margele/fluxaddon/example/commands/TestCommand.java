package cn.margele.fluxaddon.example.commands;

import today.flux.api.api.AddonEntityClientPlayer;
import today.flux.api.command.AddonCommand;

/*
*  This is a simple command.
* */
public class TestCommand extends AddonCommand {
    public TestCommand() {
        // Name, Description, Args
        super("test", "A simple test command", "so", "cool");
    }

    @Override
    public void onExecute(String[] strings) {
        if (strings.length == 1) { // Confirm there are only one arg.

            if (strings[0].equalsIgnoreCase("so")) { // Command: ".test so"
                AddonEntityClientPlayer.getClientPlayer().addChatMessage(strings[0]);

            } else if (strings[0].equalsIgnoreCase("cool")) { // Command: ".test cool"
                AddonEntityClientPlayer.getClientPlayer().addChatMessage(strings[0] + "!");
            } else { // Other commands but only one arg.
                AddonEntityClientPlayer.getClientPlayer().addChatMessage("???");
            }
        }
    }
}
