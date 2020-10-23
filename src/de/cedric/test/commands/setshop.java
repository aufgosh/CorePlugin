package de.cedric.test.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.cedric.test.main.Main;

public class setshop implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("main.setspawn")) {
				if(args.length == 0) {
					
					FileConfiguration cfg = Main.getPlugin().getConfig();
					Location loc = p.getLocation();
					
					cfg.set("shop.World", loc.getWorld().getName());
					cfg.set("shop.X", loc.getX());
					cfg.set("shop.Y", loc.getY());
					cfg.set("shop.Z", loc.getZ());
					cfg.set("shop.Yaw", loc.getYaw());
					cfg.set("shop.Pitch", loc.getPitch());
					Main.getPlugin().saveConfig();
					
					p.sendMessage("§Shop successfully set!");
					
				} else
					p.sendMessage("§aPlease use /setshop !");
				
			} else
				p.sendMessage(Main.error);
		}
		return false;
	}

}
