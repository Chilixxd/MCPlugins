package me.chili.LobbyGUI.menuSystem.menus;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.chili.LobbyGUI.MainGUI;
import me.chili.LobbyGUI.menuSystem.Menu;
import me.chili.LobbyGUI.menuSystem.PlayerMenuUtility;
import net.md_5.bungee.api.ChatColor;

public class MainMenu extends Menu{

	public MainMenu(PlayerMenuUtility playerMenuUtility) {
		super(playerMenuUtility);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getMenuName() {
		// TODO Auto-generated method stub
		return "Hub Selector";
	}

	@Override
	public int getSlots() {
		// TODO Auto-generated method stub
		return 9;
	}

	@Override
	public void handleMenu(InventoryClickEvent e) {
        if (e.getSlot() == 3) {
            Player player = (Player) e.getWhoClicked();
			new Minigames(MainGUI.getPlayerMenuUtility(player)).open();
        }
        
        if (e.getSlot() == 5) {
            Player player = (Player) e.getWhoClicked();
			new TeleportMenu(MainGUI.getPlayerMenuUtility(player)).open();
        }
        
        if (e.getSlot() == 8) {
            Player player = (Player) e.getWhoClicked();
			player.closeInventory();
        }
	} 

	@Override
	public void setMenuItems() {

		ItemStack item0 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
    	ItemStack item1 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
    	ItemStack item2 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
    	ItemStack item3 = new ItemStack(Material.IRON_SWORD);
    	ItemStack item4 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
    	ItemStack item5 = new ItemStack(Material.END_PORTAL_FRAME);
    	ItemStack item6 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
    	ItemStack item7 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
    	ItemStack item8 = new ItemStack(Material.BARRIER);
    	
    	ItemMeta meta1 = item3.getItemMeta();
    	ItemMeta meta2 = item5.getItemMeta();
    	ItemMeta meta3 = item8.getItemMeta();

    	meta1.setDisplayName(ChatColor.YELLOW + "Minigames");
    	meta2.setDisplayName(ChatColor.AQUA + "Teleport Options");
    	meta3.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "EXIT MENU");
    	
    	item3.setItemMeta(meta1);
    	item5.setItemMeta(meta2);
    	item8.setItemMeta(meta3);
    	
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
