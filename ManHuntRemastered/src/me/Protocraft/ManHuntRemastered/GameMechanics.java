package me.Protocraft.ManHuntRemastered;

import java.util.UUID;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.Protocraft.PlayerData.PlayerManager;

public class GameMechanics implements Listener {
	private ManHuntCore plugin = ManHuntCore.getPlugin(ManHuntCore.class);
	GameManager c = new GameManager();
	
	
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
	
}
