package me.Protocraft.ManHuntRemastered;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

public class DeathEvent implements Listener {
	private ManHuntCore plugin = ManHuntCore.getPlugin(ManHuntCore.class);
	
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event) throws IOException {
		Player killed = event.getEntity();
		
		if(plugin.hunters.contains(killed)) {
			if (killed.isDead()) {
				killed.setHealth(20);
			}
			Bukkit.broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "RUNNERS" + ChatColor.YELLOW + " won!");
			killed.setGameMode(GameMode.SPECTATOR);	    
			plugin.gameManager.GameEnd();
		}
		if(plugin.runners.contains(killed)) {
			if (killed.isDead()) {
				killed.setHealth(20);
			}
			Bukkit.broadcastMessage(ChatColor.GREEN + "" + ChatColor.RED + "" + ChatColor.BOLD + "HUNTERS" + ChatColor.YELLOW + " won!");
			killed.setGameMode(GameMode.SPECTATOR);
			plugin.gameManager.GameEnd();

		}
	}
	
	@EventHandler
	public void PortalCheck(PlayerPortalEvent e) {
		Player player = e.getPlayer();
		
		if (player.getWorld().getName() == "ManHunt") {
			if (e.getCause() == TeleportCause.NETHER_PORTAL) {
				e.setTo(Bukkit.getWorld("ManHunt_nether").getSpawnLocation());
			}
		}
		
		if (player.getWorld().getName() == "ManHunt_nether") {
			if (e.getCause() == TeleportCause.NETHER_PORTAL) {
				e.setTo(Bukkit.getWorld("ManHunt").getSpawnLocation());
				//dosen't work has to find last location of where the player entered into the nether portal
			}
		}
		
		if (player.getWorld().getName() == "ManHunt") {
			if (e.getCause() == TeleportCause.END_PORTAL) {
				e.setTo(Bukkit.getWorld("ManHunt_the_end").getSpawnLocation());
			}
		}
	}
	
	//If player location is ManHunt teleport to ManHunt_nether X
	//If player location is ManHunt_nether teleport to ManHunt X
	//If player location is ManHunt teleport to ManHunt_the_end X
}
