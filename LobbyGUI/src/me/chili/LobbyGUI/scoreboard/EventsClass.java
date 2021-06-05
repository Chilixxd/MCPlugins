package me.chili.LobbyGUI.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import me.chili.LobbyGUI.MainGUI;
import net.md_5.bungee.api.ChatColor;

public class EventsClass implements Listener {
	
	private MainGUI plugin = MainGUI.getPlugin(MainGUI.class);

	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		player.setHealth(20);
		player.getInventory().clear();
		player.setFoodLevel(20);
		
		ScoreboardManager m = Bukkit.getScoreboardManager();
		Scoreboard b = m.getNewScoreboard();
		
		@SuppressWarnings("deprecation")
		Objective o = b.registerNewObjective("Lobby", "");
		o.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "ProtoCraft" + ChatColor.RED + "" + ChatColor.BOLD + " BETA");
		o.setDisplaySlot(DisplaySlot.SIDEBAR);

		Score empty1 = o.getScore("                               ");
		empty1.setScore(9);
		
		Score OnlinePlayers = o.getScore(ChatColor.GREEN + "" + ChatColor.BOLD +  "Online Players:");
		OnlinePlayers.setScore(8);
		
		Score empty3 = o.getScore("              ");
		empty3.setScore(7);
		
		Score total = o.getScore(ChatColor.GOLD + "" + ChatColor.BOLD + ">>" + ChatColor.GRAY + " Total:");
		total.setScore(6);
		
		Score Survival = o.getScore(ChatColor.GOLD + "" + ChatColor.BOLD + ">>" + ChatColor.GRAY + " Survival:");
		Survival.setScore(5);
		
		Score Manhunt = o.getScore(ChatColor.GOLD + "" + ChatColor.BOLD + ">>" + ChatColor.GRAY + " ManHunt:");
		Manhunt.setScore(4);
		
		Score kbd = o.getScore(ChatColor.GOLD + "" + ChatColor.BOLD + ">>" + ChatColor.GRAY + " KnockBackDuels:");
		kbd.setScore(3);
		
		Score empty2 = o.getScore("   ");
		empty2.setScore(2);
		
		Score empty4 = o.getScore("         ");
		empty4.setScore(1);
		
		Score stay = o.getScore("Enjoy your stay " + ChatColor.RED + "<3");
		stay.setScore(0);
		
		player.setScoreboard(b);
	}

    //i need to add getTotalCount() to ">> Total:"

}
