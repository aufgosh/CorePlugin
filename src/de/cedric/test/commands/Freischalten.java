package de.cedric.test.commands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;


public class Freischalten implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player p = (Player) sender;
		

		

		
		TextComponent message = new TextComponent( "HERE" );
		message.setColor( ChatColor.GREEN );
		message.setBold( true );
		message.setUnderlined( true );
		message.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/aosdohjkouppiahopdsbnFREElasdouasd" ) );
		message.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( "§aConfirm" ).create() ) );
		
		TextComponent world = new TextComponent( "§cServer §7>>§a To unlock yourself, click " );

		p.spigot().sendMessage(world, message);
		
		return false;
	}

}
