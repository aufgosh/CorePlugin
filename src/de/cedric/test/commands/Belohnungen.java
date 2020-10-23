package de.cedric.test.commands;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.cedric.test.main.Main;

public class Belohnungen implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player p = (Player) sender;
		Inventory inv = Bukkit.createInventory(null, 27, "§cRewards");
		
		
		
		ItemStack Glass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);;
		ItemMeta GlassMeta = Glass.getItemMeta();
		Glass.setAmount(1);
	    GlassMeta.setDisplayName(" ");
		Glass.setItemMeta(GlassMeta);
		
		ItemStack daily = new ItemStack(Material.ENDER_CHEST);
		ItemMeta dailyMeta = daily.getItemMeta();
		dailyMeta.setDisplayName("§dCommon Chest");
		ArrayList<String> dailyLore = new ArrayList<String>();
		dailyLore.add("§6You may open this common chest");
		dailyLore.add("§6with a key,");
		dailyLore.add("§6which u receive from §c/vote");
		dailyMeta.setLore(dailyLore);
		daily.setItemMeta(dailyMeta);
		
		ItemStack rare = new ItemStack(Material.ENDER_CHEST);
		ItemMeta rareMeta = rare.getItemMeta();
		rareMeta.setDisplayName("§dRare Chest");
		ArrayList<String> rareLore = new ArrayList<String>();
	    rareLore.add("§6You may open this rare chest");
		rareLore.add("§6with a key,");
		rareLore.add("§6which u receive from §c/vote");
		rareMeta.setLore(rareLore);
		rare.setItemMeta(rareMeta);
		
		ItemStack epic = new ItemStack(Material.ENDER_CHEST);
		ItemMeta epicMeta = epic.getItemMeta();
		epicMeta.setDisplayName("§dEpic Chest");
		ArrayList<String> epicLore = new ArrayList<String>();
	    epicLore.add("§6You may open this epic chest");
		epicLore.add("§6with a key,");
		epicLore.add("§6which u receive from §c/vote");
		epicMeta.setLore(epicLore);
		epic.setItemMeta(epicMeta);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		inv.setItem(0, Glass);
		inv.setItem(1, Glass);
		inv.setItem(2, Glass);
		inv.setItem(3, Glass);
		inv.setItem(4, Glass);
		inv.setItem(5, Glass);
		inv.setItem(6, Glass);
		inv.setItem(7, Glass);
		inv.setItem(8, Glass);
		inv.setItem(9, Glass);
		inv.setItem(10, Glass);
		inv.setItem(11, daily);
		inv.setItem(12, Glass);
		inv.setItem(13, rare);
		inv.setItem(14, Glass);
		inv.setItem(15, epic);
		inv.setItem(16, Glass);
		inv.setItem(17, Glass);
		inv.setItem(18, Glass);
		inv.setItem(19, Glass);
		inv.setItem(20, Glass); 	
		inv.setItem(21, Glass);
		inv.setItem(22, Glass);
		inv.setItem(23, Glass);
		inv.setItem(24, Glass);
		inv.setItem(25, Glass);
		inv.setItem(26, Glass);
		if(p.hasPermission("main.user")) {
		p.playSound(p.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 3, 2);
		p.openInventory(inv);
		} else {
			p.sendMessage(Main.prefix + "§cYou need to do the Tutorial first!");
			p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 3, 2);
		}
		return false;
	}

}
