package listener;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.help.HelpTopic;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import de.cedric.test.commands.Spawn;

import de.cedric.test.commands.TpaAcceptCommand;
import de.cedric.test.commands.TutorialSpawn;
import de.cedric.test.commands.Tutorialexec;
import de.cedric.test.commands.banCommand;
import de.cedric.test.commands.newHome;
import de.cedric.test.main.Main;
import utilities.checkForBan;
import utilities.utilitiesFunctions;





public class JoinListener implements Listener {
	
	File file = new File("plugins/Test", "bans.yml");
	FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	
	
	
	@EventHandler
	public void command(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		
		String msg = e.getMessage();

		switch(msg) {
		
		case "/bukkit:ver":
			e.setCancelled(true);
			utilitiesFunctions.sendError(p);
			break;
		case "/bukkit:version":
			e.setCancelled(true);
			utilitiesFunctions.sendError(p);
			break;
		case "/bukkit:plugins":
			e.setCancelled(true);
			utilitiesFunctions.sendError(p);
			break;
		case "/bukkit:pl":
			e.setCancelled(true);
			utilitiesFunctions.sendError(p);
			break;
		case "/pl":
			e.setCancelled(true);
			utilitiesFunctions.sendError(p);
			break;
		case "/plugins":
			e.setCancelled(true);
			utilitiesFunctions.sendError(p);
			break;
		case "/ver":
			e.setCancelled(true);
			utilitiesFunctions.sendError(p);
			break;
		case "/version":
			e.setCancelled(true);
			utilitiesFunctions.sendError(p);
			break;
		case "/?":
			e.setCancelled(true);
			utilitiesFunctions.sendHelpMessage(p);
			break;
		case "/help":
			e.setCancelled(true);
			utilitiesFunctions.sendHelpMessage(p);
			break;
		case "/minecraft:help":
			e.setCancelled(true);
			utilitiesFunctions.sendHelpMessage(p);
			break;
		case "/bukkit:help":
			e.setCancelled(true);
			utilitiesFunctions.sendHelpMessage(p);
			break;
		case "/bukkit:?":
			e.setCancelled(true);
			utilitiesFunctions.sendHelpMessage(p);
			break;
		default:
			break;
		
		}
		}
		

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();
		
		checkForBan.banCheck(p);
		
		e.setJoinMessage("§8[§a+§8] " + p.getName());

		utilitiesFunctions.clearPlayerChat(p);
		utilitiesFunctions.sendHelpMessage(p);
		p.sendTitle("§aWelcome!", "", 15,15,15);
	

		if(!p.hasPlayedBefore()) {
			new TutorialSpawn(p).start();
		    ItemStack food = new ItemStack(Material.BREAD);
			ItemMeta testMeta = food.getItemMeta();
			food.setAmount(16);
			
			ItemStack shovel = new ItemStack(Material.GOLDEN_SHOVEL);
			ItemMeta shovelmeta = shovel.getItemMeta();
			
			food.setItemMeta(testMeta);
			p.getPlayer().getInventory().addItem(food, shovel);
		}
		

		
	}
	
	@EventHandler
	public void onMoveBoots(PlayerMoveEvent e) {
		
		Player p = (Player) e.getPlayer();
		
		if(p.getInventory().getBoots() != null) {
		
		switch(p.getInventory().getBoots().getItemMeta().getDisplayName()) {
		
		case "§cBoots of Torment":
			p.spawnParticle(Particle.VILLAGER_ANGRY, p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), 1);
			p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40, 1));
			break;
		case "§4Boots of Love":
			p.spawnParticle(Particle.HEART, p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), 1);
			p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 40, 1));
			break;
		case "":
			break;
		default:
			break;
		}
		
		}
		
	}
	
	@EventHandler
	public void on(PlayerRespawnEvent e) {
		
			FileConfiguration cfg = Main.getPlugin().getConfig();
			World world = Bukkit.getWorld(cfg.getString("Spawn.World"));
			double x = cfg.getDouble("Spawn.X");
			double y = cfg.getDouble("Spawn.Y");
			double z = cfg.getDouble("Spawn.Z");
			float yaw = (float) cfg.getDouble("Spawn.Yaw");
			float pitch = (float) cfg.getDouble("Spawn.Pitch");
			
			Location loc = new Location(world, x, y, z, yaw, pitch);
			
			if(!e.isBedSpawn()) {
			
				e.setRespawnLocation(loc);
				
			}
		
		}
	
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if(!banCommand.bancfg.getBoolean(p.getName() + ".BANNED:", true)) {
			e.setQuitMessage("§8[§c-§8] " + p.getName());
			new newHome(p).stop();
			new Spawn(p).stop();
			new TpaAcceptCommand(p).stop();
			return;
		} else {
			e.setQuitMessage("");
		}
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		e.setDeathMessage("");
	}

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if(e.getFrom().getBlockX() !=e.getTo().getBlockX() || e.getFrom().getBlockY() != e.getTo().getBlockY()|| e.getFrom().getBlockZ() !=e.getTo().getBlockZ()) {
			
			new Spawn(e.getPlayer()).stop();
			new newHome(e.getPlayer()).stop();
			new TpaAcceptCommand(e.getPlayer()).stop();
  
	        }

			
		}
	
	@EventHandler
	public void onUnknown(PlayerCommandPreprocessEvent e) {
		if(!(e.isCancelled())) {
		Player p = e.getPlayer();
		String msg = e.getMessage().split(" ") [0];
		HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(msg);
		if(topic == null) {
			p.sendMessage(Main.prefix + "§cThis Command doesen't exist!");
			p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 3, 2);
			e.setCancelled(true);
		}
		}
		}
		
	
	
	@EventHandler
	public void onDmg(EntityDamageByEntityEvent e) {
		/*
		if(e.getEntity() instanceof Player) { 
		
		Player p = (Player) e.getDamager();
		
		if(!(e.getDamager() instanceof Player)) {
		
		if(p.getInventory().getBoots() != null && p.getInventory().getChestplate() != null && p.getInventory().getLeggings() != null && p.getInventory().getHelmet() !=null) {

			if((p.getInventory().getBoots().getItemMeta().getDisplayName() == "§cBoots of Torment") && 
					(p.getInventory().getChestplate().getItemMeta().getDisplayName() == "§cCuirass of Torment") && 
					(p.getInventory().getLeggings().getItemMeta().getDisplayName() == "§cChaps of Torment") && 
					(p.getInventory().getHelmet().getItemMeta().getDisplayName() == "§cTiara of Torment")) {
				e.setDamage(e.getDamage() * 20);
				p.sendMessage("Damage done:" + (e.getDamage() * 20));
			} else {
				p.sendMessage("Damage done != :" + (e.getDamage()));
			}
		}
			
		}
		}
		*/
		if(e.getEntityType() == EntityType.PLAYER) {
			new Spawn((Player) e.getEntity()).stop();
			new newHome((Player) e.getEntity()).stop();
			new Tutorialexec((Player)e.getEntity()).stop();
			new TpaAcceptCommand((Player) e.getEntity()).stop();
		}
	
		}
	
	
	
	@EventHandler
	public void on(InventoryClickEvent e) {
		
		Inventory open = e.getClickedInventory();
		ItemStack item = e.getCurrentItem();
		Player p = (Player) e.getWhoClicked();
		
		if(e.getView().getTitle().contains("§cRewards")) {
			e.setCancelled(true);
			
			if (open == null) {
				return;
			}
			
			if (item == null || !item.hasItemMeta()) {
				return;
			}
			
                if(item.getItemMeta().getDisplayName().equals("§dCommon Chest")) {
				
				
			    ItemStack test = new ItemStack(Material.TRIPWIRE_HOOK);
			    ArrayList<String> testLore = new ArrayList<String>();
				ItemMeta testMeta = test.getItemMeta();
				testMeta.setDisplayName("§dGewöhnlicher Schlüssel");
				testLore.add("§6Ein Schlüssel womit du eine");
				testLore.add("§6gewöhnliche Belohnungskiste öffnen kannst!");
				testLore.add("§6Gebe dazu §c/Belohnung§6 ein.");
				testMeta.setLore(testLore);
				test.setAmount(1);
				
				test.setItemMeta(testMeta);
				
				ItemStack diamond = new ItemStack(Material.DIAMOND);
				ItemMeta diamondMeta = diamond.getItemMeta();
				diamond.setAmount(3);
				diamond.setItemMeta(diamondMeta);
				
				ItemStack Iron = new ItemStack(Material.IRON_INGOT);
				ItemMeta IronMeta = Iron.getItemMeta();
				Iron.setAmount(10);
				Iron.setItemMeta(IronMeta);
				
				ItemStack xp = new ItemStack(Material.EXPERIENCE_BOTTLE);
				ItemMeta xpMeta = xp.getItemMeta();
				xp.setAmount(16);
				xp.setItemMeta(xpMeta);
				
				if (p.getInventory().containsAtLeast(test, 1)) {
				p.getInventory().removeItem(test);
				p.getInventory().addItem(diamond);
				p.getInventory().addItem(Iron);
				p.getInventory().addItem(xp);
				p.playSound(p.getLocation(),Sound.ENTITY_PLAYER_LEVELUP, 3, 2);
			      }else {
				     p.sendMessage(Main.prefix + "§cDu hast keinen passenden Schlüssel!");
				     p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 3, 2);
			         }
			         }
                
                if(item.getItemMeta().getDisplayName().equals("§dRare Chest")) {
    				
    			    ItemStack test = new ItemStack(Material.TRIPWIRE_HOOK);
    			    ArrayList<String> testLore = new ArrayList<String>();
    				ItemMeta testMeta = test.getItemMeta();
    				testMeta.setDisplayName("§dSeltener Schlüssel");
    				testLore.add("§6Ein Schlüssel womit du eine");
    				testLore.add("§6seltene Belohnungskiste öffnen kannst!");
    				testLore.add("§6Gebe dazu §c/Belohnung§6 ein.");
    				testMeta.setLore(testLore);
    				test.setAmount(1);
    				
    				test.setItemMeta(testMeta);
    				
    				ItemStack diamond = new ItemStack(Material.DIAMOND);
    				ItemMeta diamondMeta = diamond.getItemMeta();
    				diamond.setAmount(6);
    				diamond.setItemMeta(diamondMeta);
    				
    				ItemStack Iron = new ItemStack(Material.IRON_INGOT);
    				ItemMeta IronMeta = Iron.getItemMeta();
    				Iron.setAmount(32);
    				Iron.setItemMeta(IronMeta);
    				
    				ItemStack xp = new ItemStack(Material.EXPERIENCE_BOTTLE);
    				ItemMeta xpMeta = xp.getItemMeta();
    				xp.setAmount(40);
    				xp.setItemMeta(xpMeta);

    				if (p.getInventory().containsAtLeast(test, 1)) {
    				p.getInventory().removeItem(test);
    				p.getInventory().addItem(diamond);
    				p.getInventory().addItem(Iron);
    				p.getInventory().addItem(xp);
    				p.playSound(p.getLocation(),Sound.ENTITY_PLAYER_LEVELUP, 3, 2);
    			}else {
    				p.sendMessage(Main.prefix + "§cDu hast keinen passenden Schlüssel!");
    				p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 3, 2);
    			}
    			}
                
                if(item.getItemMeta().getDisplayName().equals("§dEpic Chest")) {
    				
    				
    			    ItemStack test = new ItemStack(Material.TRIPWIRE_HOOK);
    			    ArrayList<String> testLore = new ArrayList<String>();
    				ItemMeta testMeta = test.getItemMeta();
    				testMeta.setDisplayName("§dEpischer Schlüssel");
    				testLore.add("§6Ein Schlüssel womit du eine");
    				testLore.add("§6epische Belohnungskiste öffnen kannst!");
    				testLore.add("§6Gebe dazu §c/Belohnung§6 ein.");
    				testMeta.setLore(testLore);
    				test.setAmount(1);
    				
    				test.setItemMeta(testMeta);
    				
    				ItemStack diamond = new ItemStack(Material.DIAMOND_BLOCK);
    				ItemMeta diamondMeta = diamond.getItemMeta();
    				diamond.setAmount(1);
    				diamond.setItemMeta(diamondMeta);
    				
    				ItemStack Iron = new ItemStack(Material.IRON_BLOCK);
    				ItemMeta IronMeta = Iron.getItemMeta();
    				Iron.setAmount(7);
    				Iron.setItemMeta(IronMeta);
    				
    				ItemStack xp = new ItemStack(Material.EXPERIENCE_BOTTLE);
    				ItemMeta xpMeta = xp.getItemMeta();
    				xp.setAmount(64);
    				xp.setItemMeta(xpMeta);

    				if (p.getInventory().containsAtLeast(test, 1)) {
    				p.getInventory().removeItem(test);
    				p.getInventory().addItem(diamond);
    				p.getInventory().addItem(Iron);
    				p.getInventory().addItem(xp);
    				p.playSound(p.getLocation(),Sound.ENTITY_PLAYER_LEVELUP, 3, 2);
    			}else {
    				p.sendMessage(Main.prefix + "§cDu hast keinen passenden Schlüssel!");
    				p.playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 3, 2);
    			}
    			}

		}
		
	}
	

	
	@EventHandler
	public void onPlayerTab(PlayerCommandSendEvent e) {
		List<String> blockedCommands = new ArrayList<>();
		
		Player p = e.getPlayer();
		
		if((!p.hasPermission("main.admin"))) {
		
		blockedCommands.add("gamemode");
		blockedCommands.add("pl");
		blockedCommands.add("plugins");
		blockedCommands.add("boots");
		blockedCommands.add("belohnung");
		blockedCommands.add("freischalten");
		blockedCommands.add("aasdooqppaspdyymncxasdjaslde");
		blockedCommands.add("aosdohjkouppiahopdsbnfreelasdouasd");
		e.getCommands().removeAll(blockedCommands);
	}
	}

	
	
	    

	

	

}

