package us.team_code.Plugin_Template.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.team_code.Plugin_Template.Main;

public class Feed implements CommandExecutor {

    static Main plugin;

    public Feed(Main main) {
        plugin = main;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("feed.use") && args.length == 0 && sender instanceof Player) {
            Player p = (Player) sender;
            if (p.getFoodLevel() == 20) {
                p.sendMessage("You already have full food.");
                return true;
            } else {
                p.setFoodLevel(20);
                p.sendMessage("You have been fed.");
            }
        }
        else if (args.length >= 1) {
            if (Bukkit.getPlayerExact(args[0]) != null) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target.getFoodLevel() == 20) {
                    sender.sendMessage(target.getName() + " already has full food.");
                } else {
                    target.setFoodLevel(20);
                    target.sendMessage("You have been fed.");
                    sender.sendMessage("You fed " + target.getName());
                }
                return true;
            } else {
                sender.sendMessage("Player not found.");
                return true;
            }
        }
        return true;
    }

}
