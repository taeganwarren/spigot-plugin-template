package us.team_code.Plugin_Template.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.team_code.Plugin_Template.Main;

public class Fly implements CommandExecutor {

    static Main plugin;

    public Fly(Main main) {
        plugin = main;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("fly.use") && args.length == 0 && sender instanceof Player) {
            Player p = (Player) sender;
            if (!p.getAllowFlight()) {
                p.setAllowFlight(true);
                p.sendMessage("Flying enabled.");
                return true;
            } else {
                p.setAllowFlight(false);
                p.sendMessage("Flying disabled.");
                return true;
            }
        }
        return true;
    }

}
