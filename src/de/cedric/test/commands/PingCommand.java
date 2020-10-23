package de.cedric.test.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.cedric.test.main.Main;


public class PingCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("ping")){

		
		if(args.length == 0) {
			p.sendMessage(Main.prefix +"�aYour ping�7:�e " + getPing(p) +"ms�7.");
			p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 3, 2);
		}
			
		else if(args.length == 1) {
			
			Player target = Bukkit.getPlayer(args[0]);
			if(target != null){
			p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 3, 2);
			p.sendMessage(Main.prefix +"�aPing of �e"+ target.getDisplayName() +"�a:�e " + getPing(target) +"ms�7.");
			}else{
			p.sendMessage(Main.prefix +"�The player �e"+ args[0] + "�c is not online!");
			p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 3, 2);
			}
			}
		}
		
		
		return false;
	}
	
	public int getPing(Player p) {
		org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer pingc = (org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer) p;
		net.minecraft.server.v1_16_R2.EntityPlayer pinge = pingc.getHandle();
		return pinge.ping;
	}


}

//p.sendMessage("§aDein Ping:§e " + getPing(p) +"ms§7.");

