package com.svi.warcard.models;

import java.util.List;

public class Player {

	private int playerNumber;
	private List<Card> hands;
	
	public Player(int number, List<Card> cards){
		this.playerNumber = number;
		this.hands = cards;
    }
	
	public int getPlayerNumber() {
		return playerNumber;
	}
	
	public void setPlayerId(int playerNumber) {
		this.playerNumber = playerNumber;
	}
	
	public List<Card> getHands() {
		return hands;
	}
	
	public void setHand(List<Card> hands) {
		this.hands = hands;
	}
	
}
