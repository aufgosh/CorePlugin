package de.cedric.test.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import de.cedric.test.main.Main;

public class GmCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args.length == 0 || args.length ==1) {
			if(sender instanceof ConsoleCommandSender) {
				return false;	
			}
		} 		
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(args.length == 1) {
			if(p.hasPermission("main.gm")) {
				
				if(args[0].equalsIgnoreCase("0")){
					p.setGameMode(GameMode.SURVIVAL);
					p.setAllowFlight(false);
					p.setFlying(false);
					p.sendMessage("§aYour gamemode is now set to§7: §e§nSurvival§r.");
				}else if(args[0].equalsIgnoreCase("1")){
					p.setAllowFlight(true);
					p.setFlying(false);
					p.setGameMode(GameMode.CREATIVE);
					p.sendMessage("§aYour gamemode is now set to§7: §e§nCreative§r.");
					
				}else if(args[0].equalsIgnoreCase("2")){
					p.setAllowFlight(false);
					p.setFlying(false);
					p.setGameMode(GameMode.ADVENTURE);
					p.sendMessage("§aYour gamemode is now set to§7: §e§nAdventure§r.");
					
				}else if(args[0].equalsIgnoreCase("3")){
					p.setGameMode(GameMode.SPECTATOR);
					p.sendMessage("§aYour gamemode is now set to§7: §e§nSpectator§r.");
				}
			
					
			} else {
				
				p.sendMessage(Main.error);
			}
			} else if(args.length == 2) {
				Player target = Bukkit.getPlayer(args[1]);
				if(target == null) {
					p.sendMessage("§cThis player is not online§7!");	
				}else {
					if(args[0].equalsIgnoreCase("0")){
						target.setGameMode(GameMode.SURVIVAL);
						target.setAllowFlight(false);
						target.setFlying(false);
						target.sendMessage("§aYour game mode is now set to§7: §e§nSurvival§r.");
						p.sendMessage("§aYou set the gamemode of§7: §e" + target.getName() + "§a to §e§nSurvival§r.");
					}else if(args[0].equalsIgnoreCase("1")){
						target.setAllowFlight(true);
						target.setFlying(false);
						target.setGameMode(GameMode.CREATIVE);
						target.sendMessage("§aYour game mode was set to§7: §e§nCreativeÂ§r.");
						p.sendMessage("§aYou set the gamemode of§7: §e" + target.getName() + "§a to §e§nCreativeÂ§r.");
						
					}else if(args[0].equalsIgnoreCase("2")){
						target.setGameMode(GameMode.ADVENTURE);
						target.setAllowFlight(false);
						target.setFlying(false);
						target.sendMessage("§aYour game mode was set to§7: §e§nAdventure§r.");
						p.sendMessage("§aYou set the gamemode of§7: §e" + target.getName() + "§a to §e§nAdventure§r.");
						
					}else if(args[0].equalsIgnoreCase("3")){
						target.setGameMode(GameMode.SPECTATOR);
						target.sendMessage("§aYour game mode was set to§7: §e§nSpectatorÂ§r.");
						p.sendMessage("§aYou set the gamemode of§7: §e" + target.getName() + "§a to §e§nSpectator§r.");
					}
					
				}
			}
		}
		
		return false;
	}

} 
	

