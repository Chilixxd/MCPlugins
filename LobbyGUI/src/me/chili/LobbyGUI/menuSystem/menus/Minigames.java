package me.chili.LobbyGUI.menuSystem.menus;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import me.chili.LobbyGUI.MainGUI;
import me.chili.LobbyGUI.menuSystem.Menu;
import me.chili.LobbyGUI.menuSystem.PlayerMenuUtility;
import net.md_5.bungee.api.ChatColor;

public class Minigames extends Menu{

	private MainGUI plugin = MainGUI.getPlugin(MainGUI.class);

	public Minigames(PlayerMenuUtility playerMenuUtility) {
		super(playerMenuUtility);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getMenuName() {
		// TODO Auto-generated method stub
		return "Minigames";
	}

	@Override
	public int getSlots() {
		// TODO Auto-generated method stub
		return 9;
	}

	@Override
	public void handleMenu(InventoryClickEvent e) {
        if (e.getSlot() == 4) {
            Player player = (Player) e.getWhoClicked();
			player.sendMessage(ChatColor.YELLOW + "Looking for a game");
        }
		
	}

	@Override
	public void setMenuItems() {

		ItemStack item0 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemStack item1 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemStack item2 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemStack item3 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemStack item4 = new ItemStack(Material.STICK);
		ItemStack item5 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemStack item6 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemStack item7 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemStack item8 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);

		ItemMeta meta1 = item4.getItemMeta();
		meta1.setDisplayName(ChatColor.YELLOW + "KnockBackDuels");
		item4.setItemMeta(meta1);
		
    	inventory.setItem(0, item0);
    	inventory.setItem(1, item1);
    	inventory.setItem(2, item2);
    	inventory.setItem(3, item3);
    	inventory.setItem(4, item4);
    	inventory.setItem(5, item5);
    	inventory.setItem(6, item6);
    	inventory.setItem(7, item7);
    	inventory.setItem(8, item8);
	}

}
