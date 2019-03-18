package com.svi.warcard.models;

public enum Rank {
	ACE(1, "A"),
	KING(13, "K"),
	QUEEN(12, "Q"),
	JACK(11, "J"),
	TEN(10, "10"),
	NINE(9, "9"),
	EIGHT(8, "8"),
	SEVEN(7, "7"),
	SIX(6, "6"),
	FIVE(5, "5"),
	FOUR(4, "4"),
	THREE(3, "3"),
	TWO(2, "2")
	;

	private final String code;
	private final int value;	
	
	Rank(int value, String code) {
        this.value = value;
        this.code = code;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public String getCode() {
        return this.code;
    }
}
