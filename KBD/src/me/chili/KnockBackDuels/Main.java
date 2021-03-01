package me.chili.KnockBackDuels;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.chili.KnockBackDuels.PlayerData.DeathCheck;
import me.chili.KnockBackDuels.PlayerData.GameManager;
import me.chili.KnockBackDuels.PlayerData.PlayerManager;

public class Main extends JavaPlugin {

	public ArrayList<Player> red = new ArrayList<Player>();
	public ArrayList<Player> blue = new ArrayList<Player>();
	public GameMechanics gameMechanics;
	public ArrayList<Player> playersInGame = new ArrayList<Player>();
	public ArrayList<Player> playersLeftGame = new ArrayList<Player>();
	public GameManager gameManager;
	public HashMap<UUID,PlayerManager> playermanager = new HashMap<UUID,PlayerManager>();
	public void onEnable() {
        getServer().getConsoleSender().sendMessage("[KBD]"+ChatColor.GREEN+" Starting");
        getServer().getPluginManager().registerEvents(new GameMechanics(), this);
        getServer().getPluginManager().registerEvents(new DeathCheck(), this);
        loadConfig();
		gameManager = new GameManager();
		gameMechanics = new GameMechanics();
	}
	

	public void onDisable() {
        getServer().getConsoleSender().sendMessage("[KBD]"+ChatColor.RED+" Stopping");

	}
	
	public void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}

    public void sendToServer(final Player player, final String targetServer) {
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
}
	
