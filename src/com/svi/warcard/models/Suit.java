package com.svi.warcard.models;

public enum Suit {
	DIAMOND("D", "RED", 4),
	HEART("H", "RED", 3),
	SPADE("S", "BLACK", 2),
	CLUB("C", "BLACK", 1)
	;
	
	private final String code;
	private final String color;
	private final int value;
	
	Suit(String code, String color, int value) {
        this.code = code;
        this.color = color;
        this.value = value;
    }
    
    public String getCode() {
        return this.code;
    }
    
    public String getColor() {
        return this.color;
    }
    
    public int getValue() {
        return this.value;
    }
}
