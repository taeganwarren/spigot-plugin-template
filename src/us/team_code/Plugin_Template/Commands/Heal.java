package us.team_code.Plugin_Template.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.team_code.Plugin_Template.Main;

public class Heal implements CommandExecutor {

    static Main plugin;

    public Heal(Main main) {
        plugin = main;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("heal.use") && args.length == 0 && sender instanceof Player) {
            Player p = (Player) sender;
            if (p.getHealth() == 20.0) {
                p.sendMessage("You already have full health.");
                return true;
            } else {
                p.setHealth(20.0);
                p.sendMessage("You have been healed.");
            }
        }
        else if (args.length >= 1) {
            if (Bukkit.getPlayerExact(args[0]) != null) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target.getHealth() == 20.0) {
                    sender.sendMessage(target.getName() + " already has full health.");
                } else {
                    target.setHealth(20.0);
                    target.sendMessage("You have been healed.");
                    sender.sendMessage("You healed " + target.getName());
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
