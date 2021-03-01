package me.chili.Manhunt;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.PortalType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import me.chili.PlayerData.PlayerManager;
import net.md_5.bungee.api.ChatColor;

public class GameMechanics implements Listener {
	
	private Main plugin = Main.getPlugin(Main.class);

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {

		if (plugin.gameManager.isStarted() == false) {
			Player player = event.getPlayer();
			player.setGameMode(GameMode.ADVENTURE);
			UUID uuid = player.getUniqueId();
			if (plugin.playersLeftGame.contains(player)) {
	            plugin.sendToServer(player, plugin.getConfig().getString("Settings.Hub"));
			}
			event.setJoinMessage("");
			plugin.playermanager.put(uuid, new PlayerManager(uuid, false,false));
			plugin.playersInGame.add(player);
			plugin.gameManager.lobbyWait(player);
		} else {
			Player player = event.getPlayer();
            plugin.sendToServer(player, plugin.getConfig().getString("Settings.Hub"));

		}
	}

	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		UUID uuid = player.getUniqueId();

		event.setQuitMessage("");
		plugin.playermanager.remove(uuid);
		plugin.playersInGame.remove(player);
		plugin.playersLeftGame.add(player);
		plugin.runners.clear();
		plugin.hunters.clear();
		plugin.playermanager.clear();
		plugin.playersInGame.clear();
		plugin.playersLeftGame.clear();
	}
	
	@EventHandler
	public void enderPortalEvent(PlayerTeleportEvent e) {
		if (e.getCause() == TeleportCause.END_PORTAL) {
			Bukkit.broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "RUNNERS" + ChatColor.YELLOW + " won!");
			plugin.gameManager.gameStop();
		}
	}
	
}
