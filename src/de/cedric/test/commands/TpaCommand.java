package de.cedric.test.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import de.cedric.test.main.Main;

public class TpaCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args	) {
		
		Player p = (Player) sender;
		if(p.hasPermission("main.user")) {
		
		if(args.length == 1) {
			
			try {
				
			Player p2 = Bukkit.getPlayer(args[0]);
			Main.tpa.put(p2, p);
			
			p2.sendMessage(Main.prefix + p.getName() + "§a requested to teleport to you§7. §aYou have 60 seconds to accept the request with §e/tpaccept§a !");
			
			p.sendMessage(Main.prefix + "§aYou sent the player §e" + p2.getName() + "§a a teleport request! The request is active for 60 seconds.");
			
	        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
	            public void run() {
	            	if(Main.tpa.containsKey(p2)) {
	            		if(Main.tpa.containsKey(p)) {

	                p2.sendMessage(Main.prefix + "§cThe teleport request from§e "+ p.getName() +"§c has expired!");
	                p.sendMessage(Main.prefix + "§cYour teleport reuqest has expired!");
	            	Main.tpa.remove(p2, p);
	            		}
	            	}
	                
	             }
	            
	            
	            }, 1200);
			
			
			}
			catch(Exception ex) {
				p.sendMessage("§cThe player§7 " + args[0] + "§c is not online!");
				p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 3, 2);
			}
			
		} else {
			p.sendMessage(Main.prefix + "§cPlease use /tpa (playername)");
			p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 3, 2);
		}
	    } else {
			p.sendMessage(Main.prefix + "§cYou need to do the Tutorial first!");
			p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 3, 2);
		 }
		
		
		return false;
	}

}
