package de.cedric.test.commands;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.cedric.test.main.Main;

public class unbanCommand implements CommandExecutor {
	
	public static File file = banCommand.file;
	public static FileConfiguration bancfg = banCommand.bancfg;
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,  String label,
			 String[] args) {
		
		
		if(sender.hasPermission("main.ban")) {
			OfflinePlayer t = Bukkit.getOfflinePlayer(args[0]);
			
			if(!(args.length >= 1)) {
				if(sender instanceof Player) {
					Player p = (Player) sender;
				
				p.sendMessage(Main.prefix + "§cplease use /unban (player)");
				p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 3, 2);
				}
			} else {
				unbanPlayer(t);
				if(sender instanceof Player) {
					Player p = (Player) sender;
				p.sendMessage(Main.prefix + "§cYou successfully unbanned player §7: §e" + t);
				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 3, 2);
				} else {
					sender.sendMessage("Player " + t + " unbanned.");
				}
			}
		} else {
			if(sender instanceof Player) {
				Player p = (Player) sender;
			
			p.sendMessage(Main.error);
			p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 3, 2);
			}
		}
		
		return false;
	}

	public void unbanPlayer(OfflinePlayer target) {
		
		try {
			bancfg.load(file);
		} catch (IOException | InvalidConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		boolean banned = false;
		
		bancfg.set(target.getName().substring(0, 1).toUpperCase() + target.getName().substring(1) + ".BANNED:", banned);
		try {
			bancfg.save(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

