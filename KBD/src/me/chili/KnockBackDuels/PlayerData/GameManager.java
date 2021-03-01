package me.chili.KnockBackDuels.PlayerData;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import de.bg.derh4nnes.TitleActionBarAPI;
import me.chili.KnockBackDuels.Main;
import net.md_5.bungee.api.ChatColor;

public class GameManager implements Listener {

	private Main plugin = Main.getPlugin(Main.class);
	
	private int aftergameCountdown = 10;
	private int lobbyCountdown = 5;
	private int playerNeeded = 2;
	private boolean isStarted;
	
	public void setupGame() {
		for(Player online : Bukkit.getOnlinePlayers()) {
			Location lobby = new Location (Bukkit.getWorld("WatingArea"), -161, 5, -142);
			online.teleport(lobby);
			plugin.playersInGame.add(online);
			plugin.playermanager.put(online.getUniqueId(), new PlayerManager(online.getUniqueId(), false,false));
			
			lobbyWait(online);
			online.setFoodLevel(20);
			online.setHealth(20);
			
		}
	}
	

	public void lobbyWait(Player player) {
		int online = Bukkit.getOnlinePlayers().size();
		Bukkit.broadcastMessage(ChatColor.YELLOW + "There are currently (" + online + "/2)" + " players online!");
		playerCheck(online);
	}


	public void gameStart() {
		for (Player ingamePlayer : plugin.playersInGame) {
			if (plugin.blue.size() == 0) {
				plugin.blue.add(ingamePlayer);
			} else if(plugin.red.size() == 0) {
				plugin.red.add(ingamePlayer);
			}
		}
		
		for (Player p1 : plugin.red) {
		    Location redSpawn = new Location(Bukkit.getWorld("knockbackduels"), 19, 101, 1, 90, 0);
			p1.teleport(redSpawn);
			p1.sendMessage(ChatColor.YELLOW + "You are on the " + ChatColor.RED + "" + ChatColor.BOLD + "RED " + ChatColor.YELLOW + "team!");
			p1.getInventory().clear();
			redstartKit(p1);
 		}
		
		for (Player p2 : plugin.blue) {
		    Location blueSpawn = new Location(Bukkit.getWorld("knockbackduels"), -19, 101, 1, 0, 90);
		    p2.teleport(blueSpawn);
			p2.sendMessage(ChatColor.YELLOW + "You are on the " + ChatColor.AQUA + "" + ChatColor.BOLD + "BLUE " + ChatColor.YELLOW + "team!");
			p2.getInventory().clear();
			bluestartKit(p2);
		}
	}

	public void bluestartKit(Player player) {
		ItemStack kbdStick = new ItemStack(Material.STICK);
		ItemStack concrete1 = new ItemStack(Material.CYAN_CONCRETE, 64);
		ItemStack concrete2 = new ItemStack(Material.CYAN_CONCRETE, 64);
		ItemStack gapple = new ItemStack(Material.DIAMOND_PICKAXE);
		
		ItemMeta kbd = kbdStick.getItemMeta();
		
		kbd.addEnchant(Enchantment.KNOCKBACK, 3, true);
		kbdStick.setItemMeta(kbd);
		
		player.getInventory().setItem(0, kbdStick);
		player.getInventory().setItem(3, concrete1);
		player.getInventory().setItem(4, concrete2);
		player.getInventory().setItem(1, gapple);
	}
	
	public void redstartKit(Player player) {
		
		
		ItemStack kbdStick = new ItemStack(Material.STICK);
		ItemStack concrete1 = new ItemStack(Material.RED_CONCRETE, 64);
		ItemStack concrete2 = new ItemStack(Material.RED_CONCRETE, 64);
		ItemStack gapple = new ItemStack(Material.DIAMOND_PICKAXE);
		
		ItemMeta kbd = kbdStick.getItemMeta();
		
		kbd.addEnchant(Enchantment.KNOCKBACK, 3, true);
		kbdStick.setItemMeta(kbd);
		
		player.getInventory().setItem(0, kbdStick);
		player.getInventory().setItem(3, concrete1);
		player.getInventory().setItem(4, concrete2);
		player.getInventory().setItem(1, gapple);

	}
	
	public void gameStop() {
		new BukkitRunnable() {
			@Override
			public void run() {
				
				if (aftergameCountdown > 0) {
					aftergameCountdown = aftergameCountdown -1;
				} else {
					for (Player player : Bukkit.getOnlinePlayers()) {
						Bukkit.broadcastMessage(ChatColor.YELLOW + "Teleporting you to the Hub!");
			            plugin.sendToServer(player, plugin.getConfig().getString("Settings.Hub"));
						plugin.red.clear();
						plugin.blue.clear();
						plugin.playermanager.clear();
						plugin.playersInGame.clear();
						plugin.playersLeftGame.clear();
					}
				}
				
			}
		}.runTaskTimer(plugin, 0, 20l);
		
		//clear inven
	}
	public void playerCheck(int online) {
		if (online == playerNeeded) {
			lobbyCountdown();
			setStarted(true);
		}
	}



	public void lobbyCountdown() {
		new BukkitRunnable() {
			@Override
			public void run() {
				if (lobbyCountdown > 0) {
					Bukkit.broadcastMessage(ChatColor.YELLOW + "Game starting in " + ChatColor.RED + lobbyCountdown + ChatColor.YELLOW + " seconds!");
				} else {
					gameStart();
					this.cancel();
				}
				lobbyCountdown = lobbyCountdown -1;
			}
		}.runTaskTimer(plugin, 0, 20l);
	}
	

	public boolean isStarted() {
		return isStarted;
	}

	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}

}