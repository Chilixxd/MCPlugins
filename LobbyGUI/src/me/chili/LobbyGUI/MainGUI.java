/**
 * Made by u/chilixxd with <3
 * Version: 1.0
 */

package me.chili.LobbyGUI;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.io.ByteArrayDataInput;

import me.chili.LobbyGUI.menuSystem.MenuListener;
import me.chili.LobbyGUI.menuSystem.PlayerMenuUtility;
import me.chili.LobbyGUI.scoreboard.EventsClass;



//BUILD BEFORE TESTING
//BUILD BEFORE TESTING//BUILD BEFORE TESTING//BUILD BEFORE TESTING
public class MainGUI extends JavaPlugin implements Listener {
    
	public Inventory inv;
	
    @Override
    public void onEnable(){
    	getServer().getPluginManager().registerEvents(this, this);
        getServer().getConsoleSender().sendMessage("======================");
        getServer().getConsoleSender().sendMessage("[CGUI]"+ChatColor.GREEN+" Starting");
        getServer().getConsoleSender().sendMessage("[CGUI] Version: 1.0");
        getServer().getConsoleSender().sendMessage("[CGUI] Working version(s): 1.15");
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        Bukkit.getServer().getPluginManager().registerEvents(new OpenMenu(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new MenuListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new EventsClass(), this);
        getServer().getConsoleSender().sendMessage("======================");
        loadConfig();
    }
    
    
    @Override
    public void onDisable(){
    	getServer().getConsoleSender().sendMessage("======================");
        getServer().getConsoleSender().sendMessage("[CGUI]" +ChatColor.RED+" Stopping");
        getServer().getConsoleSender().sendMessage("[CGUI] Version: 1.0");
        getServer().getConsoleSender().sendMessage("[CGUI] Working version(s): 1.15");
        getServer().getConsoleSender().sendMessage("======================");
    }
        

    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();
    public static PlayerMenuUtility getPlayerMenuUtility(Player p) {
        PlayerMenuUtility playerMenuUtility;
        if (!(playerMenuUtilityMap.containsKey(p))) {
            playerMenuUtility = new PlayerMenuUtility(p);
            playerMenuUtilityMap.put(p, playerMenuUtility);
            return playerMenuUtility;
        } else {
            return playerMenuUtilityMap.get(p);
        }
    }

    public void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
    }

    public void sendToHub(final Player player, final String targetServer) {
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.reloadConfig();
        final ByteArrayOutputStream b = new ByteArrayOutputStream();
        final DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF(targetServer);
        }
        catch (Exception eee) {
            Bukkit.getLogger().info("Player connected to " + this.getConfig().getString("Settings.Hub") + " server.");
        }
        player.sendPluginMessage(this, "BungeeCord", b.toByteArray());
    }

	public void onPluginMessageReceived(String channel, Player player, byte[] message) {
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		this.reloadConfig();
		final ByteArrayOutputStream ba = new ByteArrayOutputStream();
		final DataOutputStream out = new DataOutputStream(ba);
		try {
			out.writeUTF("PlayerCount");
			out.writeUTF("Lobby");
		}
		catch (Exception eee) {
			String server = ((ByteArrayDataInput) ba).readUTF(); // Name of server, as given in the arguments
			int playercount = ((ByteArrayDataInput) ba).readInt();
			Bukkit.broadcastMessage(ChatColor.YELLOW + "There are " + playercount + " players online!");
		}

	}

}