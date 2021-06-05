package me.chili.LobbyGUI;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class JoinListener implements Listener {

	private MainGUI plugin = MainGUI.getPlugin(MainGUI.class);

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();

		if(p.getInventory() != null) {

			if(!p.getInventory().contains(Material.COMPASS)) {

				ItemStack menuBlock = new ItemStack(Material.COMPASS);
				ItemMeta meta1 = menuBlock.getItemMeta();
				meta1.setDisplayName(ChatColor.AQUA +"" +ChatColor.BOLD+ "Main menu");
				menuBlock.setItemMeta(meta1);
				p.getInventory().setItem(4, menuBlock);
				p.sendMessage(ChatColor.YELLOW + "Welcome back!");
			}
		}}

	@EventHandler
	public void onClickSlot(InventoryClickEvent e) {
		if(e.getSlot() == 4) {
			e.setCancelled(true);
		}
	}


	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent e) {
		Player player = e.getPlayer();
		Item item = e.getItemDrop();
		if (item.getItemStack().getType().equals(Material.COMPASS)) {
			player.sendMessage(ChatColor.RED + "No can do, pal!");
			e.setCancelled(true);
		}
	}


}
