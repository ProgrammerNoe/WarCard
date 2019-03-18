package com.svi.warcard.models;

public class Card {

	private String cardValue; // rank-suit, e.g. D-A, D-K
	private int rankValue; // 1-13
	private String suitCode; // D, H, S, C
	private int suitValue; //1-4, diamonds highest
	private String suitColor; // BLACK for spades and clubs, RED for diamonds and hearts
	private boolean faceUp; // Boolean value if card is facing down

	public Card(int rankValue, String rankCode, String suitCode, int suitValue, String suitColor) {
		String cardCode = suitCode + "-" + rankCode;
		setCardValue(cardCode);
		setSuitCode(suitCode);
		setSuitValue(suitValue);
		setSuitColor(suitColor);
		setRankValue(rankValue);
	}

	public String getCardValue() {
		return cardValue;
	}

	public void setCardValue(String cardValue) {
		this.cardValue = cardValue;
	}

	public int getRankValue() {
		return rankValue;
	}

	public void setRankValue(int rankValue) {
		this.rankValue = rankValue;
	}

	public int getSuitValue() {
		return suitValue;
	}

	public void setSuitValue(int suitValue) {
		this.suitValue = suitValue;
	}

	public String getSuitCode() {
		return suitCode;
	}

	public void setSuitCode(String suitCode) {
		this.suitCode = suitCode;
	}

	public String getSuitColor() {
		return suitColor;
	}

	public void setSuitColor(String suitColor) {
		this.suitColor = suitColor;
	}

	public String getDisplayValue() {
		if (faceUp) {
			return cardValue;
		} else {
			return "X-X";
		}
	}

}
