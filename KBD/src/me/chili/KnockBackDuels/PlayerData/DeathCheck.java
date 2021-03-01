package me.chili.KnockBackDuels.PlayerData;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.chili.KnockBackDuels.Main;
import net.md_5.bungee.api.ChatColor;

public class DeathCheck implements Listener {

	
	private Main plugin = Main.getPlugin(Main.class);
	
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player killed = event.getEntity();
		
		if(plugin.blue.contains(killed)) {
			if (killed.isDead()) {
				killed.setHealth(20);
			}
			Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + "RED" + ChatColor.YELLOW + " won!");
			killed.setGameMode(GameMode.SPECTATOR);
		    Location specLocation = new Location(Bukkit.getWorld("knockbackduels"), 0, 110, 0);
		    killed.teleport(specLocation);
		    
			plugin.gameManager.gameStop();
		}
		if(plugin.red.contains(killed)) {
			if (killed.isDead()) {
				killed.setHealth(20);
			}
			Bukkit.broadcastMessage(ChatColor.AQUA + "" + ChatColor.BOLD + "BLUE" + ChatColor.YELLOW + " won!");
			killed.setGameMode(GameMode.SPECTATOR);
		    Location specLocation = new Location(Bukkit.getWorld("knockbackduels"), 0, 110, 0);
		    killed.teleport(specLocation);

			plugin.gameManager.gameStop();

		}
	}

}
