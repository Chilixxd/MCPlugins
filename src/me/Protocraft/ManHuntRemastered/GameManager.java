package me.Protocraft.ManHuntRemastered;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.WorldCreator;
import org.bukkit.World.Environment;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.FileUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.inventory.ItemStack;

public class GameManager implements Listener {

	private ManHuntCore plugin = ManHuntCore.getPlugin(ManHuntCore.class);
	
	private int lobbyCountdown = 20;
	private int gracePeriod = 90;
	private int playersNeeded = 2;
	private int compassAim = 1800; //30 min
	
	
	public void lobbyWait(Player player) {
		int online = Bukkit.getOnlinePlayers().size();
		Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + "There are (" + online + "/2) players online");
		playerCheck(online);
	}

	public void playerCheck(int online) {
		if (online == playersNeeded) {
			lobbyCountdown();
			setStarted1(true);
		}
	}
	
	public void gameStart() {
		for (Player ingamePlayer : plugin.playersInGame) {
			if (plugin.hunters.size() == 0) {
				plugin.hunters.add(ingamePlayer);
			} else if (plugin.runners.size() == 0) {
				plugin.runners.add(ingamePlayer);
			}

			ingamePlayer.setGameMode(GameMode.SURVIVAL);

			for (Player player : plugin.hunters) {
				player.sendMessage(ChatColor.YELLOW + "You are a " + ChatColor.RED + "" + ChatColor.BOLD + "HUNTER");
				
				ItemStack Compass = new ItemStack(Material.COMPASS);
				ItemMeta meta1 = Compass.getItemMeta();
				meta1.setDisplayName(ChatColor.RED + "Tracker");

				Compass.setItemMeta(meta1);
				player.getInventory().setItem(8, Compass);
			}
			for (Player player : plugin.runners) {
				player.sendMessage(ChatColor.YELLOW + "You are a " + ChatColor.GREEN + "" + ChatColor.BOLD + "RUNNER");
			}
		}

		compassAim();
	}
	
	public void compassAim() {
		new BukkitRunnable() {
			@Override
			public void run() {
				if (compassAim > 0) {
					for (Player player : plugin.hunters) {
						Player runner = plugin.runners.get(0);
						Location loc = runner.getLocation();
						
						if (loc == null) {
							return;
						}
						
						player.setCompassTarget(loc);
					}
					compassAim = compassAim -1;
				}
			}
		}.runTaskTimer(plugin, 0, 20l);
	}
	
	public void lobbyCountdown() {
		new BukkitRunnable() {
			@Override
			public void run() {
				if (lobbyCountdown == 20) {
					Bukkit.broadcastMessage(ChatColor.YELLOW + "Starting game in 20 seconds!");
				}
				if (lobbyCountdown == 5) {
					Bukkit.broadcastMessage(ChatColor.YELLOW + "Game starting in " + ChatColor.RED +  lobbyCountdown + ChatColor.YELLOW + " seconds!");
				}
				if (lobbyCountdown == 4) {
					Bukkit.broadcastMessage(ChatColor.YELLOW + "Game starting in " + ChatColor.RED +  lobbyCountdown + ChatColor.YELLOW + " seconds!");
				}
				if (lobbyCountdown == 3) {
					Bukkit.broadcastMessage(ChatColor.YELLOW + "Game starting in " + ChatColor.RED +  lobbyCountdown + ChatColor.YELLOW + " seconds!");
				}
				if (lobbyCountdown == 2) {
					Bukkit.broadcastMessage(ChatColor.YELLOW + "Game starting in " + ChatColor.RED +  lobbyCountdown + ChatColor.YELLOW + " seconds!");
				}
				if (lobbyCountdown == 1) {
					Bukkit.broadcastMessage(ChatColor.YELLOW + "Game starting in " + ChatColor.RED +  lobbyCountdown + ChatColor.YELLOW + " second!");
				}
				if (lobbyCountdown == 0) {
					for (Player player : Bukkit.getOnlinePlayers()) {
						Location manhuntSpawn = new Location(Bukkit.getWorld("ManHunt"), 100, 100, 100, 90, 0);
						
						player.teleport(manhuntSpawn);
						gracePeriod();
					}
					
					this.cancel();
				}
				lobbyCountdown = lobbyCountdown -1;

			}
		}.runTaskTimer(plugin, 0 ,20l); 
	}
	
	public boolean isStarted() {
		return false;
	}

	public void setStarted1(boolean isStarted) {
	}

	public void gracePeriod() {
		new BukkitRunnable() {
			@Override
			public void run() {

				if (gracePeriod > 0) {
					for(Player player : Bukkit.getOnlinePlayers()) {
						player.addPotionEffect(new org.bukkit.potion.PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 90, 8));
						player.setGameMode(GameMode.SURVIVAL);
					}
				} else {
					this.cancel();
				}
				if (gracePeriod == 90) {
					gameStart();
					Bukkit.broadcastMessage(ChatColor.YELLOW + "Grace period ending in 90 seconds!");
				}
				if (gracePeriod == 60) {
					Bukkit.broadcastMessage(ChatColor.YELLOW + "Grace period ending in 60 seconds!");
				}
				if (gracePeriod == 30) {
					Bukkit.broadcastMessage(ChatColor.YELLOW + "Grace period ending in 30 seconds!");
				}
				if (gracePeriod == 10) {
					Bukkit.broadcastMessage(ChatColor.YELLOW + "Grace period ending in 10 seconds!");
				}
				if (gracePeriod == 0) {
					Bukkit.broadcastMessage(ChatColor.YELLOW + "Grace period"  + ChatColor.RED + "" + ChatColor.BOLD + " ENDING NOW");
					for (Player player : Bukkit.getOnlinePlayers()) {
						player.setGameMode(GameMode.SURVIVAL);
					}
					
				}
				
				gracePeriod = gracePeriod -1;
			}
		}.runTaskTimer(plugin, 0, 20l);
	}
	
	public void GameEnd() throws IOException {	
		for (Player player : Bukkit.getOnlinePlayers()) {
			plugin.runners.clear();
			plugin.hunters.clear();
			plugin.playermanager.clear();
			plugin.playersInGame.clear();
			plugin.playersLeftGame.clear();
			player.getInventory().clear();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}   
            plugin.sendToServer(player, plugin.getConfig().getString("Settings.Hub"));
		}
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}   
		
		Bukkit.getServer().unloadWorld("ManHunt", false);
		Bukkit.getServer().unloadWorld("ManHunt_nether", false);
		Bukkit.getServer().unloadWorld("ManHunt_the_end", false);
		
		FileUtils.deleteDirectory(new File("ManHunt"));
		FileUtils.deleteDirectory(new File("ManHunt_nether"));
		FileUtils.deleteDirectory(new File("ManHunt_the_end"));

		Bukkit.createWorld(WorldCreator.name("ManHunt"));
		Bukkit.createWorld(WorldCreator.name("ManHunt" + "_nether").environment(Environment.NETHER));
		Bukkit.createWorld(WorldCreator.name("ManHunt" + "_the_end").environment(Environment.THE_END));
	}
	
	//The world you die in dosen't get deleted / renewed
	
}
