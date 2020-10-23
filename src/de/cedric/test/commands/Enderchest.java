package de.cedric.test.commands;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.cedric.test.main.Main;

public class Enderchest implements CommandExecutor {
	
	public static ArrayList<UUID> enderchest = new ArrayList<UUID>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player p = (Player) sender;
		if(p.hasPermission("main.user")) {
		if(args.length == 0) {
			p.openInventory(p.getEnderChest());
			p.sendMessage("�7*�aYou are opening your enderchest�7*");
		} else if (args.length == 1) {
			if(!p.hasPermission("main.admin")) {
				p.openInventory(p.getEnderChest());
				p.sendMessage("�7*�aYou are opening your enderchest�7*");
				return true;
			}
			Player player = Bukkit.getPlayer(args[0]);
			if(player == null) {
				p.sendMessage("�cThis player is not online!");
				return true;
			}
			p.openInventory(player.getEnderChest());
			p.sendMessage("�7*�aYou are opening�c " +player.getName() + "'s �aenderchest�7*");
			enderchest.contains(p.getUniqueId());
		}
		} else {
			p.sendMessage(Main.prefix + "�cYou need to do the Tutorial first!");
			p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 3, 2);
		}
		return false;
	}

}
