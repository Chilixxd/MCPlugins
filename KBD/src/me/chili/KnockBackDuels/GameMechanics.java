package me.chili.KnockBackDuels;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.chili.KnockBackDuels.PlayerData.PlayerManager;

public class GameMechanics implements Listener {

	
	private Main plugin = Main.getPlugin(Main.class);
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {

		if (plugin.gameManager.isStarted() == false) {
			Player player = event.getPlayer();
			UUID uuid = player.getUniqueId();
			if (plugin.playersLeftGame.contains(player)) {
				// TODO bungee push back
			}
			event.setJoinMessage("");
			plugin.playermanager.put(uuid, new PlayerManager(uuid, false, false));
			plugin.playersInGame.add(player);
			plugin.gameManager.lobbyWait(player);
		} else {
			// TODO bungee push back
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
	}


	
}