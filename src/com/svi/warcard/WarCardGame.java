package com.svi.warcard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.svi.warcard.models.Card;
import com.svi.warcard.models.Deck;
import com.svi.warcard.models.Player;
import com.svi.warcard.models.Rank;
import com.svi.warcard.models.Suit;

public class WarCardGame {
	
    public static void main(String[] args) {
		int numberOfPlayers = 0;
		int numberOfShuffles = 0;
						
		System.out.println("Welcome to the War Card Game!");
	
		//Initialize scanner
		Scanner scan = new Scanner(System.in);
		
		//Input number of players			
		boolean continueInput = true;
	    do {
	        try {
	        	System.out.println("Please enter the number of players: ");
	            numberOfPlayers = scan.nextInt();
	            if (numberOfPlayers > 52) {
	            	System.out.println("Only a maximum of 52 players can play.");
	            } else if (numberOfPlayers < 2) {
	            	System.out.println("A minimum of 2 players needed.");
	            } else {
	            	continueInput = false;
	            }
	        } catch (InputMismatchException e) {
	            System.out.println("Please enter a valid number.");
	            scan.nextLine();
	        }
	    }
	    while (continueInput);
	    
	    //Input number of shuffles
	    continueInput = true;
	    do {
	        try {
	        	System.out.println("Please enter the number of times you like the deck shuffled: ");
	            numberOfShuffles = scan.nextInt();
	            if (numberOfShuffles < 1) {
	            	System.out.println("Shuffle the deck at least once!");
	            } else {
	            	continueInput = false;
	            }	            
	        } catch (InputMismatchException e) {
	            System.out.println("Please enter a number.");
	            scan.nextLine();
	        }
	    }
	    while (continueInput);
	    	    
	    //Close the scanner since no more input is needed
	    scan.close();
	    
	    //Transform string sequence into an array list
	    //Use ENUMS to create card object
	    Deck deck = new Deck();
	    for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				Card card = new Card(rank.getValue(), rank.getCode(), suit.getCode(), suit.getValue(), suit.getColor());
		    	deck.add(card);
			}
		}
	    
	    //Display initial deck
	    System.out.println("Initial deck:");
	    for (Card card : deck) {
	    	System.out.print(card.getCardValue() + "|");
	    }
	    System.out.println();
	    System.out.println();
	    	    
	    //Shuffle the deck
	    List<Card> shuffledDeck = shuffleDeck(numberOfShuffles, deck);
	    
	    //Display shuffled deck
	    System.out.println("Shuffled deck:");
	    for (Card card : shuffledDeck) {
	    	System.out.print(card.getCardValue() + "|");
	    }
	    System.out.println();
	    System.out.println();
	    
	    //Distribute cards to players
	    List<Player> players = dealCards(numberOfPlayers, shuffledDeck);
   	    
	    //Compare top cards of players
	    playGame(players);
	    
	}
	
	//Shuffles the deck of cards
	private static List<Card> shuffleDeck(int numberOfShuffles, List<Card> deck){
		List<Card> shuffledDeck = new ArrayList<Card>();
	    for (int i=0; i < numberOfShuffles; i++) {
	    	List<Card> tempDeck = new ArrayList<Card>();
	    	if (shuffledDeck.isEmpty()) {
	    		tempDeck = deck;
	    	} else {
	    		tempDeck = shuffledDeck;
	    	}
	    	
	    	shuffledDeck = new ArrayList<Card>();
	    	for (int j=0; j < 26; j++) {
	    		int k = j + 26;
	    		shuffledDeck.add(tempDeck.get(j));
	    		shuffledDeck.add(tempDeck.get(k));
	    	}
	    }
	    
	    return shuffledDeck;
	}
	
	//Distributes the cards to the players
	private static List<Player> dealCards(int numberOfPlayers, List<Card> deck){
		//Create a hash map as holder for cards
		Map<Integer, List<Card>> hands = new HashMap<Integer, List<Card>>();
	    for (int i=0; i < numberOfPlayers; i++) {
	    	List<Card> hand = new ArrayList<Card>();
	    	hands.put(Integer.valueOf(i), hand);
	    }   
		    	    
	    //Distribute the cards
	    int counter = 0;
	    for (Card card : deck) {
    		hands.get(Integer.valueOf(counter)).add(0, card);	    	
	    	counter++;
	    	//if counter is equal to number of players, reset to 0 to distribute all cards
	    	if (counter == numberOfPlayers) {
	    		counter = 0;
	    	}
	    }
	    
	    //Create array list of Player object and add cards sequentially
	    List<Player> players = new ArrayList<Player>();
		for (int i=0; i < numberOfPlayers; i++) {
			Player player = new Player(i+1, hands.get(i));
			players.add(player);
		}
	    
	    return players;
	}
	
	//Game play
	private static void playGame(List<Player> players){
		boolean continueGame = true;
		int counter = 0;
		do {
			List<Card> topCards = new ArrayList<Card>(); //Card on top of each player per round
						
			int winnerNumber = 0; //playerNumber of winning card
			int playerCount = 0;
			Card winningCard = null;
			
			//Loop around players to determine which has higher card
			for (Player player : players) {
				List<Card> playerHand = player.getHands();				
				if (!playerHand.isEmpty()) {
					playerCount++; //count the players still in the game
					
					//Show player's hand
					System.out.println("Player " + player.getPlayerNumber());
					for (Card card : playerHand) {
			    		System.out.print(card.getCardValue() + "|");
			    	}
					System.out.println();
											
					Card topCard = playerHand.get(0); //top card of the hand
					topCards.add(topCard);
					
					//Remove top card from each player's hand
					playerHand.remove(0);
					//Set the new list as the player's hand
					player.setHand(playerHand);
																			
					if (topCards.size()==1) {
						//If there's only one card, set it first as the winner
						winnerNumber = player.getPlayerNumber();
						winningCard = topCard;
					} else {
						if (topCard.getRankValue() > winningCard.getRankValue()) {
							//Current player's card is higher, new winner
							winnerNumber = player.getPlayerNumber();
							winningCard = topCard;
						} else if (winningCard.getRankValue() == topCard.getRankValue()) {
							if (topCard.getSuitValue() > winningCard.getSuitValue()) {
								//Current player's card is higher, new winner
								winnerNumber = player.getPlayerNumber();
								winningCard = topCard;
							}
						}
					}
				} else {
					System.out.println("Player " + player.getPlayerNumber());
					System.out.println("No more cards.");
				}
			}
			
			if (playerCount > 1) {
				counter++; //Round counter, one cycle is one round
				System.out.println("Round " + counter + ":");
				
				//Display top cards
				for (Card card : topCards) {
		    		System.out.print(card.getCardValue() + "|");
		    	}
				System.out.println();
				
				System.out.println("Winner of Round " + counter + ": Player " + winnerNumber);
				System.out.println();
				
				//Locate winning index from top cards
				int index = topCards.indexOf(winningCard);
				List<Card> collectedCards = new ArrayList<Card>();
				if (index>0) {
					for (int i = 0; i < topCards.size(); i++) {
						//Starting from the winning index, collect the succeeding cards
						if (i >= index) {
							collectedCards.add(topCards.get(i));
						}
					}
					
					//Remove the collected cards from the top cards list
					topCards.removeAll(collectedCards);
					//Add cards left to the collected list
					collectedCards.addAll(topCards);
				} else {
					//Index is 0, meaning the winner is the first card and the collection order is already correct
					collectedCards = topCards;
				}
								
				//Loop again the players, find the winning player then add the collected cards to the player's hand
				for (Player player : players) {
					if (player.getPlayerNumber() == winnerNumber) {
						player.getHands().addAll(collectedCards);
					}
				}
				
			} else {
				continueGame = false; //if only 1 player left, game is over
				System.out.println("Game over! The winner is Player " + winnerNumber);
			}
		} while (continueGame);
	}
	
}
