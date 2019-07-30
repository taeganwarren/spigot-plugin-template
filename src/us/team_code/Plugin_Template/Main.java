package us.team_code.Plugin_Template;

import org.bukkit.plugin.java.JavaPlugin;
import us.team_code.Plugin_Template.Commands.*;

public class Main extends JavaPlugin {

    @Override
    public void onEnable(){
        registerCmds();
    }

    @Override
    public void onDisable(){
        //Fired when the server stops and disables all plugins
    }

    public void registerCmds() {
        this.getCommand("feed").setExecutor(new Feed(this));
        this.getCommand("heal").setExecutor(new Heal(this));
        this.getCommand("spawn").setExecutor(new Spawn(this));
        this.getCommand("setspawn").setExecutor(new SetSpawn(this));
        this.getCommand("fly").setExecutor(new Fly(this));
    }

}
