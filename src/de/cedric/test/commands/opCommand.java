package de.cedric.test.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class opCommand implements CommandExecutor {

	@Override
	public boolean onCommand( CommandSender sender,  Command cmd,  String label,
			 String[] args) {
		

		if (!(sender instanceof Player)) {
			Player p = Bukkit.getPlayer(args[0]);
	        p.setOp(true);
	} else {
		Player p = (Player) sender;
		p.sendMessage("§7[Server: Made " + p.getName() + " a server operator]");
	}
		
		
		return false;
	}

}
