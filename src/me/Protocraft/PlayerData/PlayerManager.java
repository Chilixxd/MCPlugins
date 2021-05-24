package me.Protocraft.PlayerData;

import java.util.UUID;

import org.bukkit.event.Listener;

public class PlayerManager implements Listener {

	private UUID uuid;
	private boolean ingame;
	private boolean isdead;

	
	public PlayerManager(UUID uuid, boolean ingame, boolean isDead) {
		this.setUuid(uuid);
		this.setIngame(ingame);
		this.setIsdead(isDead);
	}


	public UUID getUuid() {
		return uuid;
	}


	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}


	public boolean isIngame() {
		return ingame;
	}


	public void setIngame(boolean ingame) {
		this.ingame = ingame;
	}


	public boolean isIsdead() {
		return isdead;
	}


	public void setIsdead(boolean isdead) {
		this.isdead = isdead;
	}
}
