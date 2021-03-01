package me.chili.Manhunt;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import net.md_5.bungee.api.ChatColor;

public class DeathEvent implements Listener {

	private Main plugin = Main.getPlugin(Main.class);
	
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player killed = event.getEntity();
		
		if(plugin.hunters.contains(killed)) {
			if (killed.isDead()) {
				killed.setHealth(20);
			}
			Bukkit.broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "RUNNERS" + ChatColor.YELLOW + " won!");
			killed.setGameMode(GameMode.SPECTATOR);	    
			plugin.gameManager.gameStop();
		}
		if(plugin.runners.contains(killed)) {
			if (killed.isDead()) {
				killed.setHealth(20);
			}
			Bukkit.broadcastMessage(ChatColor.GREEN + "" + ChatColor.RED + "" + ChatColor.BOLD + "HUNTERS" + ChatColor.YELLOW + " won!");
			killed.setGameMode(GameMode.SPECTATOR);
			plugin.gameManager.gameStop();

		}
	}
	
}
