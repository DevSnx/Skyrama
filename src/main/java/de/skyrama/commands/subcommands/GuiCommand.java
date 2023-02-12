package de.skyrama.commands.subcommands;

import de.skyrama.interfaces.ISubCommand;
import org.bukkit.entity.Player;

public class GuiCommand implements ISubCommand {
    @Override
    public String getName() {
        return "GUI";
    }

    @Override
    public String getDescription() {
        return "open a Island Gui";
    }

    @Override
    public String getSyntax() {
        return "/island gui";
    }

    @Override
    public void perform(Player player, String[] args) {







    }
}
