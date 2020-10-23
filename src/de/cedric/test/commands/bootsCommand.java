package de.cedric.test.commands;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.cedric.test.main.Main;



public class bootsCommand implements CommandExecutor {

	@Override
	public boolean onCommand( CommandSender sender, Command cmd,  String label,
			 String[] args) {
		
		
		Player p = (Player) sender;
		if(p.hasPermission("main.admin")) {

		if(args.length == 1) {
			
			switch(args[0]) {
			
			case "love":
				ItemStack love =  new ItemStack(Material.DIAMOND_BOOTS);
				ItemMeta lovemeta =  love.getItemMeta();
				lovemeta.setDisplayName("§4Boots of Love");
				ArrayList<String> loveLore = new ArrayList<String>();
				loveLore.add("§8xl̴͕̺̬͋̑͗͜ỹ̴̖͕̎̀̅̉");
				loveLore.add("§8s̴̯̙̲̬̥̠͙̈s̵̞̓̉̆̿̽̓̕͘n̵͎̭̙͓̱̙̰̮̿̚͜ṫ̶̪̮̙");
				lovemeta.setLore(loveLore);
				love.setItemMeta(lovemeta);	
				p.getInventory().addItem(love);
				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 3, 2);
				break;
			case "torment":
				//boots
				ItemStack angry = new ItemStack(Material.DIAMOND_BOOTS);
				ItemMeta angrymeta =   angry.getItemMeta();
				angrymeta.setDisplayName("§cBoots of Torment");
				ArrayList<String> angryLore = new ArrayList<String>();
				angryLore.add("§aPart of the §cTorment itemset§8.");
				angryLore.add("§a");
				angryLore.add("§aGrants you +2 Strength§8.");
				angrymeta.setLore(angryLore);
				angry.setItemMeta(angrymeta);
				
				
				p.getInventory().addItem(angry);
				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 3, 2);
				break;
			default:
				
				p.sendMessage(Main.prefix +"§c/armor (torment/love)");
				p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 3, 2);
				break;
				
			
			}
			
		} else {
			p.sendMessage(Main.prefix +"§c/boots (torment/love)");
			p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 3, 2);
		}
			
		} else {
			
			p.sendMessage(Main.prefix + Main.error);
			p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 3, 2);
		}
		
		
		
		return false;
		
	}

}
