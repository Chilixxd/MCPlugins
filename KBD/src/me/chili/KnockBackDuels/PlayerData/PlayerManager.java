package me.chili.KnockBackDuels.PlayerData;

import java.util.UUID;

import org.bukkit.event.Listener;

public class PlayerManager implements Listener {

	private UUID uuid;
	private boolean inGame;
	private boolean isDead;
	
	public PlayerManager(UUID uuid, boolean inGame, boolean isDead) {
		this.setUuid(uuid);
		this.setInGame(inGame);
		this.setDead(isDead);
	}


	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public boolean isInGame() {
		return inGame;
	}

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
}
