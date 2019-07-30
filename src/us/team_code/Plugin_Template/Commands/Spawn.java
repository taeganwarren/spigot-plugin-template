package us.team_code.Plugin_Template.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.team_code.Plugin_Template.Main;

public class Spawn implements CommandExecutor {

    static Main plugin;

    public Spawn(Main main) {
        plugin = main;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage("This is the spawn command!");
            return true;
        }
        return false;
    }

}
