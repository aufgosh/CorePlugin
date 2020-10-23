package de.cedric.test.commands;


import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;

import de.cedric.test.main.Main;



public class newSpawnCommand implements CommandExecutor{
	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		if(sender instanceof Player) {
			if(p.hasPermission("main.user")) {
			if(args.length == 0) {
				new Spawn(p).start();
		
		
	}}

			else {
				p.sendMessage(Main.prefix + "�cYou need to do the Tutorial first!");
				p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 3, 2);
			 }}
		return true;}
}

