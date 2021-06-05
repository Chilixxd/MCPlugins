package me.Protocraft.ManHuntRemastered;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
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
	public void PortalCheckOverWorldNether(PlayerPortalEvent e) {
		Player player = e.getPlayer();
		
		if (player.getWorld().getName().equals("ManHunt")) {
			if (e.getCause() == TeleportCause.NETHER_PORTAL) {
				
				plugin.LastLoc.put(player.getUniqueId(), player.getLocation());
				e.setTo(Bukkit.getWorld("ManHunt_nether").getSpawnLocation());
			}
		}		
	}
	
	@EventHandler
	public void PortalCheckNetherOverWorld(PlayerPortalEvent e) {
		Player player = e.getPlayer();
		
		if (player.getWorld().getName() == "ManHunt_nether") {
			if (e.getCause() == TeleportCause.NETHER_PORTAL) {
				e.setTo(plugin.LastLoc.get(player.getUniqueId()));
				plugin.LastLoc.clear();
			}
		}
	}
	
	@EventHandler
	public void PortalCheckOverWorldEnd(PlayerPortalEvent e) {
		Player player = e.getPlayer();
		
		if (player.getWorld().getName().equals("ManHunt")) {
			if (e.getCause() == TeleportCause.END_PORTAL) {
				e.setTo(Bukkit.getWorld("ManHunt_the_end").getSpawnLocation());
			}
		}
	}
	
	@EventHandler
	public void onEnderDragonDeath(EntityDeathEvent e) throws IOException {
		
		if (e.getEntity() instanceof EnderDragon) {
			Bukkit.broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "RUNNERS" + ChatColor.YELLOW + " won!");
			for (Player player : Bukkit.getOnlinePlayers()) {
				player.setHealth(20);
				player.setGameMode(GameMode.SPECTATOR);

			}
			plugin.gameManager.GameEnd();
		}
	}
	//If player location is ManHunt teleport to ManHunt_nether X
	//If player location is ManHunt_nether teleport to ManHunt X
	//If player location is ManHunt teleport to ManHunt_the_end X
	
	//If player leaves in waitingarea then abort back to waiting for players
	//If player leaves in any manhunt then finish game and the player that's left in the game wins and gets sent back to the lobby
	//If entity ender_dragon dies end game and let runners win. X
}
