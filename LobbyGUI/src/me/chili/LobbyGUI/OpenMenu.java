package me.chili.LobbyGUI;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.chili.LobbyGUI.menuSystem.menus.MainMenu;

public class OpenMenu implements Listener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if(e.getItem() != null && e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
			if(e.getItem().getType().equals(Material.COMPASS)) {
				Player player = (Player) e.getPlayer();
				new MainMenu(MainGUI.getPlayerMenuUtility(player)).open();
			}
		}
	}
}
