package me.Protocraft.ManHuntRemastered;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.WorldCreator;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.Protocraft.PlayerData.PlayerManager;

public class ManHuntCore extends JavaPlugin {
	
	public HashMap<UUID,PlayerManager> playermanager = new HashMap<UUID,PlayerManager>();
	public ArrayList<Player> playersInGame = new ArrayList<Player>();
	public ArrayList<Player> hunters = new ArrayList<Player>();
	public ArrayList<Player> runners = new ArrayList<Player>();

	public ArrayList<Player> playersLeftGame = new ArrayList<Player>();

	public GameMechanics gameMechanics;
	public GameManager gameManager;
	

	

	public void onEnable() {
		IsWorldThere();
		loadConfig();
		instanceClasses();
		getServer().getPluginManager().registerEvents(new GameMechanics(), this);
		getServer().getPluginManager().registerEvents(new GameManager(), this);
		getServer().getPluginManager().registerEvents(new DeathEvent(), this);

		getServer().getConsoleSender().sendMessage("[ManHunt]"+ChatColor.GREEN+" Starting!");
		IsWorldThere();
	}
	
	public void onDisable() {
		getServer().getConsoleSender().sendMessage("[ManHunt]"+ChatColor.RED+" Stopping!");
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
	
	public void IsWorldThere() {
		
		if (Bukkit.getWorld("ManHunt") == null) {
			Bukkit.createWorld(WorldCreator.name("ManHunt"));
			Bukkit.createWorld(WorldCreator.name("ManHunt" + "_nether").environment(Environment.NETHER));
			Bukkit.createWorld(WorldCreator.name("ManHunt" + "_the_end").environment(Environment.THE_END));
		}
	}
}
