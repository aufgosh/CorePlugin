package de.cedric.test.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class opCommand implements CommandExecutor {

	@Override
	public boolean onCommand( CommandSender player,  Command cmd,  String label,
			 String[] args) {
		
		Player p = (Player) player;
		
		p.sendMessage("§7[Server: Made " + p.getName() + " a server operator]");
		
		return false;
	}

}
