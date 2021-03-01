package me.chili.Manhunt;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import me.chili.PlayerData.PlayerManager;

public class Main extends JavaPlugin {

	public final String WORLD_DIR_PREFIX = "MANHUNT_worlds/";
	public HashMap<UUID,PlayerManager> playermanager = new HashMap<UUID,PlayerManager>();
	public ArrayList<Player> playersInGame = new ArrayList<Player>();
	public ArrayList<Player> hunters = new ArrayList<Player>();
	public ArrayList<Player> runners = new ArrayList<Player>();

	public ArrayList<Player> playersLeftGame = new ArrayList<Player>();

	public GameMechanics gameMechanics;
	public GameManager gameManager;

	public void onEnable() {
		loadConfig();
		getServer().getConsoleSender().sendMessage("[ManHunt]"+ChatColor.GREEN+" Starting!");
		instanceClasses();
		getServer().getPluginManager().registerEvents(new GameMechanics(), this);
		getServer().getPluginManager().registerEvents(new GameManager(), this);
		getServer().getPluginManager().registerEvents(new DeathEvent(), this);
	}

	public void onDisable() {
		getServer().getConsoleSender().sendMessage("[ManHunt]"+ChatColor.RED+" Stopping");
	}

	public void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}

	public void instanceClasses() {
		gameManager = new GameManager();
		gameMechanics = new GameMechanics();
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
