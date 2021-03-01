package me.chili.Manhunt;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import me.chili.PlayerData.PlayerManager;
import net.md_5.bungee.api.ChatColor;

public class GameManager implements Listener {

	private Main plugin = Main.getPlugin(Main.class);

	private int lobbyCountdown = 20;
	private int gracePeriod = 90;
	private int playersNeeded = 2;
	private int compassAim = 3600;

	public void setupGame() {
		for (Player online : Bukkit.getOnlinePlayers()) {
			plugin.playersInGame.add(online);
			plugin.playermanager.put(online.getUniqueId(), new PlayerManager(online.getUniqueId(), false,false));

			online.setFoodLevel(20);
			online.setHealth(20);

		}

	}
	
	public void gameStop() {
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
	}
	
	public void gameStart() {
		for (Player ingamePlayer : plugin.playersInGame) {
			if (plugin.hunters.size() == 0) {
				plugin.hunters.add(ingamePlayer);
			} else if(plugin.runners.size() == 0) {
				plugin.runners.add(ingamePlayer);
			}

			ingamePlayer.setGameMode(GameMode.SURVIVAL);

			for (Player player : plugin.hunters) {
				player.sendMessage(ChatColor.YELLOW + "You are a " + ChatColor.RED + "" + ChatColor.BOLD + "HUNTER");
				ItemStack compass = new ItemStack(Material.COMPASS);
				ItemMeta meta1 = compass.getItemMeta();
				meta1.setDisplayName(ChatColor.RED + "Tracker");

				compass.setItemMeta(meta1);
				player.getInventory().setItem(8, compass);
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
						System.out.println("set location on player (hopefully)");
					}
					compassAim = compassAim -1;
				}
			}
		}.runTaskTimer(plugin, 0, 20l);
	}

	public void lobbyWait(Player player) {
		int online = Bukkit.getOnlinePlayers().size();
		Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + "There are (" + online + "/2) players online");
		playerCheck(online);
	}

	public void playerCheck(int online) {
		if (online == playersNeeded) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				Location WatingArea = new Location(Bukkit.getWorld("WatingArea"), 287, 5, -280, -90, 0);
				player.teleport(WatingArea);
			}
			lobbyCountdown(null);
			setStarted1(true);
		}
	}

	public void setStarted(boolean isStarted) {
	}

	public void lobbyCountdown(String s) {
		new BukkitRunnable() {
			@Override
			public void run() {
				if (lobbyCountdown == 20) {
					Bukkit.broadcastMessage(ChatColor.YELLOW + "Starting game in 20 seconds!");
					generateWorld();
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
					System.out.println(s);
					for (Player player : Bukkit.getOnlinePlayers()) {
						Location manhuntSpawn = new Location(Bukkit.getWorld(s), 100, 100, 100, 90, 0);
						gracePeriod();
						player.teleport(manhuntSpawn);
					}

					this.cancel();
				}
				lobbyCountdown = lobbyCountdown -1;

			}
		}.runTaskTimer(plugin, 0 ,20l); 
	}


	public void gracePeriod() {
		new BukkitRunnable() {
			@Override
			public void run() {

				if (gracePeriod > 0) {
					for(Player player : Bukkit.getOnlinePlayers()) {

						player.setGameMode(GameMode.ADVENTURE);
					}
				} else {
					this.cancel();
				}
				if (gracePeriod == 90 || gracePeriod == 89 ||gracePeriod == 88|| gracePeriod == 87|| gracePeriod == 86|| gracePeriod == 85|| gracePeriod == 84|| gracePeriod == 83|| gracePeriod == 82|| gracePeriod == 81|| gracePeriod == 80) {
					gameStart();
					for(Player player : Bukkit.getOnlinePlayers()) {
						player.addPotionEffect(new org.bukkit.potion.PotionEffect(PotionEffectType.SLOW_FALLING, 10, 1));
					}
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

	public boolean isStarted() {
		return false;
	}

	public void setStarted1(boolean isStarted) {
	}

	public void generateWorld() {
		String randomString = generateString();
		lobbyCountdown(randomString);
		System.out.println("new world generating! " + randomString);
		Bukkit.createWorld(WorldCreator.name(randomString));
		Bukkit.createWorld(WorldCreator.name(randomString + "_nether").environment(Environment.NETHER));
		Bukkit.createWorld(WorldCreator.name(randomString + "_the_end").environment(Environment.THE_END));

		setupGame();
	}

	public String generateString() {

		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		StringBuilder sb = new StringBuilder();

		Random random = new Random();

		int length = 7;

		for(int i = 0; i < length; i++) {

			int index = random.nextInt(alphabet.length());

			char randomChar = alphabet.charAt(index);

			sb.append(randomChar);
		}
		String randomString = sb.toString();

		return randomString;
	}

}