package de.cedric.test.main;



import java.util.HashMap;

import org.bukkit.Bukkit;


import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.cedric.test.commands.Belohnungen;
import de.cedric.test.commands.Enderchest;
import de.cedric.test.commands.Freischalten;
import de.cedric.test.commands.FreischaltenConsole;
import de.cedric.test.commands.GmCommand;
import de.cedric.test.commands.HealCommand;
import de.cedric.test.commands.HomeCommand;
import de.cedric.test.commands.Key;
import de.cedric.test.commands.MuteCommand;
import de.cedric.test.commands.PingCommand;
import de.cedric.test.commands.SetHomeCommand;
import de.cedric.test.commands.SetSpawnCommand;
import de.cedric.test.commands.Testobject;
import de.cedric.test.commands.TpaCommand;
import de.cedric.test.commands.Tpaccept;
import de.cedric.test.commands.TrashCommand;
import de.cedric.test.commands.Tutorial;
import de.cedric.test.commands.Tutorialtp;
import de.cedric.test.commands.armorCommand;
import de.cedric.test.commands.banCommand;
import de.cedric.test.commands.bootsCommand;
import de.cedric.test.commands.ccCommand;
import de.cedric.test.commands.newSpawnCommand;
import de.cedric.test.commands.newshop;
import de.cedric.test.commands.opCommand;
import de.cedric.test.commands.rtpCommand;
import de.cedric.test.commands.setshop;
import de.cedric.test.commands.unbanCommand;
import listener.JoinListener;

public class Main extends JavaPlugin implements Listener {
	
	
	public static String prefix = "§cServer§7 >> ";
	public static String syntax = "§cwrong Syntax!";
	public static String error = "§cServer§7 >> §cThis Command doesen't exist!";
	private static Main plugin;
	
	
	
	public static HashMap<Player, Player> tpa = new HashMap<Player, Player>();
	

	
	public void onEnable() {

	    getConfig().set("invite", null);
		
		getConfig().options().copyDefaults(true);
		saveConfig();
		
		
		plugin = this;
		//new Recipes().registerRecipes();
		getCommand("key1").setExecutor(new Key());
		getCommand("aosdohjkouppiahopdsbnFREElasdouasd").setExecutor(new FreischaltenConsole());
		getCommand("freischalten").setExecutor(new Freischalten());
		getCommand("tutorial").setExecutor(new Tutorialtp());
		getCommand("settut").setExecutor(new Tutorial());
		getCommand("key").setExecutor(new Testobject());
		getCommand("Belohnung").setExecutor(new Belohnungen());
		getCommand("ping").setExecutor(new PingCommand());
		getCommand("heal").setExecutor(new HealCommand());
		getCommand("trash").setExecutor(new TrashCommand());
		getCommand("setspawn").setExecutor(new SetSpawnCommand());
		getCommand("cc").setExecutor(new ccCommand());
		getCommand("tpa").setExecutor(new TpaCommand());
		getCommand("tpaccept").setExecutor(new Tpaccept());
		
		getCommand("enderchest").setExecutor(new Enderchest());
		
        getCommand("spawn").setExecutor(new newSpawnCommand());
		
		getCommand("gm").setExecutor(new GmCommand());
		getCommand("mute").setExecutor(new MuteCommand());
		
		getCommand("home").setExecutor(new HomeCommand());
		getCommand("sethome").setExecutor(new SetHomeCommand());
		getCommand("ban").setExecutor(new banCommand());
		getCommand("unban").setExecutor(new unbanCommand());
		getCommand("op").setExecutor(new opCommand());
		getCommand("aasdooqppaspdyymncxasdjaslde").setExecutor(new rtpCommand());
		getCommand("boots").setExecutor(new bootsCommand());
		getCommand("armor").setExecutor(new armorCommand());
		getCommand("shop").setExecutor(new newshop());
		getCommand("setshop").setExecutor(new setshop());
		
		
		
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new JoinListener(), this);
		pm.registerEvents(new MuteCommand(), this);
		pm.registerEvents(this, this);
	}
	
	public void writeText(String text) {
		System.out.println("");
	}
	
	public static Main getPlugin() {
		return plugin;
	}
	
	public static void Respawn(final Player p, int Time) {
		Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
			
			 @Override
			 public void run() {
				 ((org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer)p).getHandle().playerConnection.a(new net.minecraft.server.v1_16_R2.PacketPlayInClientCommand(net.minecraft.server.v1_16_R2.PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN));
			 }
			 
			
			
		}, Time);
	}
	
	
	
	

}
