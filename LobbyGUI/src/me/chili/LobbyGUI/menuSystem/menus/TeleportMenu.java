package me.chili.LobbyGUI.menuSystem.menus;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.chili.LobbyGUI.menuSystem.Menu;
import me.chili.LobbyGUI.menuSystem.PlayerMenuUtility;


public class TeleportMenu extends Menu{

	public TeleportMenu(PlayerMenuUtility playerMenuUtility) {
		super(playerMenuUtility);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getMenuName() {
		// TODO Auto-generated method stub
		return "Teleportation Options";
	}

	@Override
	public int getSlots() {
		// TODO Auto-generated method stub
		return 9;
	}

	@Override
	public void handleMenu(InventoryClickEvent e) {

		//add shit
	}

	@Override
	public void setMenuItems() {

		ItemStack item0 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
    	ItemStack item1 = new ItemStack(Material.GRASS_BLOCK);
    	ItemStack item2 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
    	ItemStack item3 = new ItemStack(Material.DIAMOND_SWORD);
    	ItemStack item4 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
    	ItemStack item5 = new ItemStack(Material.STONE);
    	ItemStack item6 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
    	ItemStack item7 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
    	ItemStack item8 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
    	
    	ItemMeta meta1 = item1.getItemMeta();
    	ItemMeta meta2 = item3.getItemMeta();
    	ItemMeta meta3 = item5.getItemMeta();
    	ItemMeta meta4 = item8.getItemMeta();


		
		meta1.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Normal Survival");
		meta2.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "WarArena");
		meta3.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Parkour");
		meta4.setDisplayName(ChatColor.RED + "Go back");
		
		item1.setItemMeta(meta1);
		item3.setItemMeta(meta2);
		item5.setItemMeta(meta3);
		item8.setItemMeta(meta4);

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
