package de.cedric.test.commands;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class rtpCommand implements CommandExecutor {

	@Override
	public boolean onCommand( CommandSender sender,  Command cmd, String label,
			 String[] args) {
		
		Player p = (Player) sender;
		
		sender.getServer().dispatchCommand(sender.getServer().getConsoleSender(), "spreadplayers ~ ~ 5000 5000 false " + p.getName());
		
		return false;
	}

}
