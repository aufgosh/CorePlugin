package de.cedric.test.commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import de.cedric.test.main.Main;



public class shop {
	
	private static HashMap<Player, BukkitTask> countdown;
	
	static {
		countdown = new HashMap<>();
	}
	
	private Player p;
	private int count;
	
	public shop(Player p)
	{
		this.p = p;
		this.count = 5;
	}
	

	public void start() {
		
		if(countdown.containsKey(p)) {
			return;
		}
		
		BukkitTask task = Bukkit.getScheduler().runTaskTimer(Main.getPlugin(), new Runnable() {
			public void run() {
				
				if(count == 5) {
					p.sendMessage(Main.prefix+"�cTeleportprocess started, don't move!");
				}
				
				if(count != 0) {
				
				p.sendMessage(Main.prefix +"�aYou'll be teleported in�c " + count + " �aseconds!");
				p.playSound(p.getLocation(), Sound.BLOCK_WOODEN_TRAPDOOR_OPEN, 3, 2);
				}
				if(count == 0) {
					

					FileConfiguration cfg = Main.getPlugin().getConfig();
					World world = Bukkit.getWorld(cfg.getString("shop.World"));
					double x = cfg.getDouble("shop.X");
					double y = cfg.getDouble("shop.Y");
					double z = cfg.getDouble("shop.Z");
					float yaw = (float) cfg.getDouble("shop.Yaw");
					float pitch = (float) cfg.getDouble("shop.Pitch");
					
					Location loc = new Location(world, x, y, z, yaw, pitch);
					

					p.teleport(loc);
					p.sendMessage(Main.prefix +"�aYour teleport was successfull!");
					p.playSound(p.getLocation(),Sound.ENTITY_ENDERMAN_TELEPORT, 3, 2);
					
					countdown.get(p).cancel();
					countdown.remove(p);
					return;
					
				} count--;
				
				
			}
			}, 0, 20);
		
		
		countdown.put(this.p, task);
		
	}
	
	public void stop() {
		if(!countdown.containsKey(p)) {
			return;
		}
		
		countdown.get(this.p).cancel();
		p.sendMessage(Main.prefix +"�cTeleportprocess canceled!");
		p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 3, 2);
		countdown.remove(this.p);
	}
	
		
	

}
