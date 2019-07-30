package us.team_code.Plugin_Template.Commands;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import us.team_code.Plugin_Template.Main;

public class SetSpawn implements CommandExecutor {

    static Main plugin;

    public SetSpawn(Main main) {
        plugin = main;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0 && sender instanceof Player) {
            Player p = (Player) sender;
            World world = p.getWorld();
            Location loc = p.getLocation();
            world.setSpawnLocation(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ());
            p.sendMessage("You have set the spawn point.");
            return true;
        } else {
            sender.sendMessage("You are unable to use this command.");
            return true;
        }
    }

}
