package de.cedric.test.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.cedric.test.main.Main;

public class MuteCommand implements CommandExecutor, Listener {
	
	private static ArrayList<Player> mutedPlayers = new ArrayList<>();
	
	@EventHandler
	public void handlePlayerChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		if(mutedPlayers.contains(p)) {
			p.sendMessage("§cServer §7>> §cYou are muted!");
			p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 3, 2);
			e.setCancelled(true);
			
		}
		
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("main.mod")){
				if(args.length == 1) {
					
					Player target = Bukkit.getPlayer(args[0]);
					if(target != null) {
						if(!mutedPlayers.contains(target)) {
							mutedPlayers.add(target);
							p.sendMessage("§aYou muted the player §e" + target.getName() + "§a succesfully. §aTo unmute the player again, type in §e/mute§a.");
							target.sendMessage("§cYou were muted!");
							p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 3, 2);
							target.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 3, 2);
							
						}else {
							mutedPlayers.remove(target);
							p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 3, 2);
							p.sendMessage("§aYou have unmuted" + target.getName() + "§7.");
							target.sendMessage("§aYou were unmuted!");
							target.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 3, 2);
						}
						
						
					} else
						p.sendMessage("§cThis player is not online!");
					p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 3, 2);
				}else
					p.sendMessage("§cUse /mute (Player name)");
				p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 3, 2);
				
				
			} else
				p.sendMessage(Main.error);
			p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 3, 2);
			
		}
		
		
		
		return false;
	}
	

}
