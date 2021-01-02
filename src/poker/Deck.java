
package poker;
import java.util.*;

public class Deck {
	String[] suits = { "hearts", "daimonds", "clubs", "spades" };
	String[] Values = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace" };

	public ArrayList<Cards> deck;

	public Deck() {

		deck = new ArrayList<Cards>();

		for (int i = 0; i < 4; i++) {

			for (int j = 0; j < 13; j++) {
				Cards card = new Cards(suits[i], Values[j], j + 2);
				deck.add(card);
			}
		}
	}

	public Cards deal() {
		return (deck.remove(0));
	}

	public void addCard(Cards c) {
		deck.add(c);
	}

	public int remainingCards() {
		return deck.size();
	}

	public String toString() {
		return deck.toString();
	}

	public void printHand() {
		System.out.println(deck.toString());
	}

	public Cards getCard(int k) {
		return deck.get(k);
	}

	public void shuffle() {
		int b = 0;
		Cards tempcard;
		Cards tempcard2;
		for (int i = 0; i < deck.size() - 1; i++) {
			tempcard = deck.get(i);
			b = (int) (Math.random() * deck.size());
			tempcard2 = deck.get(b);
			deck.set(i, tempcard2);
			deck.set(b, tempcard);

		}
	}

	public void empty() {
		while (deck.size() != 0) {
			deck.remove(0);
		}
	}

	public void setCard(int a, Cards c) {
		deck.set(a, c);

	}

	public void deal2(Deck mainDeck) {
		for (int i = 0; i < 2; i++) {
			deck.add(mainDeck.deal());

		}
	}

	public void flop(Deck mainDeck) {
		for (int i = 0; i < 3; i++) {
			deck.add(mainDeck.deal());

		}
	}

	public boolean checkFlush() {
		int hearts = 0;
		int clovers = 0;
		int daimonds = 0;
		int spades = 0;
		String str;
		for (int i = 0; i < deck.size(); i++) {
			str = deck.get(i).getSuit();
			if (str.equals("spades")) {

				spades += 1;
			}
			if (str.equals("clubs")) {

				clovers += 1;
			}
			if (str.equals("daimonds")) {

				daimonds += 1;
			}
			if (str.equals("hearts")) {

				hearts += 1;
			}
		}
		if (hearts >= 5 || daimonds >= 5 || clovers >= 5 || spades >= 5) {
			return true;
		} else
			return false;
	}

	public boolean checkStraight() {
		ArrayList<Integer> a = new ArrayList<Integer>();
		for (int k = 13; k > 0; k--) {
			a.add(0);

		}

		for (int i = 0; i < deck.size(); i++) {
			a.set(deck.get(i).getValue() - 2, a.get(deck.get(i).getValue() - 2) + 1);
		}
		System.out.print(a.toString());
		for (int j = 0; j < 9; j++) {
			if (a.get(j) > 0 && a.get(j + 1) > 0 && a.get(j + 2) > 0 && a.get(j + 3) > 0 && a.get(j + 4) > 0) {
				return true;
			}
		}
		return false;
	}

	public int mostofaKind() {
		ArrayList<Integer> a = new ArrayList<Integer>();
		int highestK = 0;
		for (int k = 13; k > 0; k--) {
			a.add(0);

		}

		for (int i = 0; i < deck.size(); i++) {
			a.set(deck.get(i).getValue() - 2, a.get(deck.get(i).getValue() - 2) + 1);
		}
      
		for (int j = 12; j >= 0; j--) {
			if (highestK < a.get(j)) {
				highestK = a.get(j);

			}

		}
		return highestK;
	}

	public boolean checkfullHouse() {
		ArrayList<Integer> a = new ArrayList<Integer>();
		boolean two = false;
		boolean three = false;
		for (int k = 13; k > 0; k--) {
			a.add(0);

		}

		for (int i = 0; i < deck.size(); i++) {
			a.set(deck.get(i).getValue() - 2, a.get(deck.get(i).getValue() - 2) + 1);
		}
		for (int j = 12; j >= 0; j--) {
			if (2 == a.get(j)) {
				two = true;
			}
			if (3 == a.get(j)) {
				three = true;
			}
		}
		if (two == true && three == true) {
			return true;
		}
		return false;
	}

	public boolean checktwoPair() {
		ArrayList<Integer> a = new ArrayList<Integer>();
		boolean pair1 = false;
		boolean pair2 = false;
		for (int k = 13; k > 0; k--) {
			a.add(0);

		}

		for (int i = 0; i < deck.size(); i++) {
			a.set(deck.get(i).getValue() - 2, a.get(deck.get(i).getValue() - 2) + 1);
		}
		for (int j = 12; j >= 0; j--) {
			if (2 == a.get(j) && pair1 == false) {
				pair1 = true;
			} else if (2 == a.get(j)) {
				pair2 = true;
			}

		}
		if (pair1 == true && pair2 == true) {
			return true;
		}
		return false;
	}
// 
	public static double checkHand(Deck hand, Deck five) {
		Deck c = new Deck();
      c.empty();
      

		for (int i = 0; i < hand.remainingCards(); i++) {
			c.addCard(hand.getCard(i));
		}
		for (int i = 0; i < five.remainingCards(); i++) {
			c.addCard(five.getCard(i));
		}
		if (c.checkFlush() == true && c.checkStraight() == true) {
    			return 9;
         
		}
		if (c.mostofaKind() == 4) {
			return 8;
		}
		if (c.checkfullHouse() == true) {
			return 7;
		}
		if (c.checkFlush() == true) {
			return 6;
		}
		if (c.checkStraight() == true) {
			return 5;
		}
		if (c.mostofaKind() == 3) {
			return 4;
		}
		if (c.checktwoPair() == true) {
			return 3;
		}
		if (c.mostofaKind() == 2) {
			return 2;
		}
		return 1;
	}
public static double botBet(int round, int strat, Deck five, Deck hand){

		   Deck c = new Deck();
		   c.empty();

			for (int i = 0; i < hand.remainingCards(); i++) {
				c.addCard(hand.getCard(i));
			}
			for (int i = 0; i < five.remainingCards(); i++) {
				c.addCard(five.getCard(i));
			}
	      if(round==1){
	    	  if(strat==0) {
	    		
	    			if (c.mostofaKind() == 2) {
	    				return .05;
	    			}
	    		  return 0;
	    	  }
	    	  if(strat==1) {
		    		
	    			if (c.mostofaKind() == 2) {
	    				return .1;
	    			}
	    		  return 0;
	    	  }
	    	  if(strat==2) {
		    		
	    			if (c.mostofaKind() == 2) {
	    				return .2;
	    			}
	    		  return .05;
	    	  }
	    	  if(strat==3) {
		    		
	    			if (c.mostofaKind() == 2) {
	    				return .2;
	    			}
	    		  return .2;
	    	  }
	      
	      }
	      if(round==2) {
	    	  if(strat==0) {
	    	  if (c.checkFlush() == true && c.checkStraight() == true) {
	  			return 1;
	  		}
	  		if (c.mostofaKind() == 4) {
	  			return 0.6;
	  		}
	  		if (c.checkfullHouse() == true) {
	  			return .35;
	  		}
	  		if (c.checkFlush() == true) {
	  			return .3;
	  		}
	  		if (c.checkStraight() == true) {
	  			return .25;
	  		}
	  		if (c.mostofaKind() == 3) {
	  			return .2;
	  		}
	  		if (c.checktwoPair() == true) {
	  			return .13;
	  		}
	  		if (c.mostofaKind() == 2) {
	  			return .5;
	  		}
	  		return 0;
	      }
	    	  if(strat==1) {
		    	  if (c.checkFlush() == true && c.checkStraight() == true) {
		  			return 1;
		  		}
		  		if (c.mostofaKind() == 4) {
		  			return 0.7;
		  		}
		  		if (c.checkfullHouse() == true) {
		  			return .60;
		  		}
		  		if (c.checkFlush() == true) {
		  			return .45;
		  		}
		  		if (c.checkStraight() == true) {
		  			return .3;
		  		}
		  		if (c.mostofaKind() == 3) {
		  			return .15;
		  		}
		  		if (c.checktwoPair() == true) {
		  			return .2;
		  		}
		  		if (c.mostofaKind() == 2) {
		  			return .05;
		  		}
		  		return 0;
		      } 
	    	  if(strat==2) {
		    	  if (c.checkFlush() == true && c.checkStraight() == true) {
		  			return 1;
		  		}
		  		if (c.mostofaKind() == 4) {
		  			return 1;
		  		}
		  		if (c.checkfullHouse() == true) {
		  			return 1;
		  		}
		  		if (c.checkFlush() == true) {
		  			return 1;
		  		}
		  		if (c.checkStraight() == true) {
		  			return .5;
		  		}
		  		if (c.mostofaKind() == 3) {
		  			return .3;
		  		}
		  		if (c.checktwoPair() == true) {
		  			return .25;
		  		}
		  		if (c.mostofaKind() == 2) {
		  			return .15;
		  		}
		  		return .1;
		      } 
	    	  if(strat==3) {
		    	  if (c.checkFlush() == true && c.checkStraight() == true) {
		  			return 1;
		  		}
		  		if (c.mostofaKind() == 4) {
		  			return 1;
		  		}
		  		if (c.checkfullHouse() == true) {
		  			return 1;
		  		}
		  		if (c.checkFlush() == true) {
		  			return 1;
		  		}
		  		if (c.checkStraight() == true) {
		  			return 1;
		  		}
		  	
		  		return .3;
		      } 


	      }
	      if(round==1) {
	    	  if(strat==0) {
	    	  if (c.checkFlush() == true && c.checkStraight() == true) {
	  			return 1;
	  		}
	  		if (c.mostofaKind() == 4) {
	  			return 0.6;
	  		}
	  		if (c.checkfullHouse() == true) {
	  			return .35;
	  		}
	  		if (c.checkFlush() == true) {
	  			return .3;
	  		}
	  		if (c.checkStraight() == true) {
	  			return .20;
	  		}
	  		if (c.mostofaKind() == 3) {
	  			return .13;
	  		}
	  		if (c.checktwoPair() == true) {
	  			return .10;
	  		}
	  		if (c.mostofaKind() == 2) {
	  			return .0;
	  		}
	  		return 0;
	      }
	    	  if(strat==1) {
		    	  if (c.checkFlush() == true && c.checkStraight() == true) {
		  			return 1;
		  		}
		  		if (c.mostofaKind() == 4) {
		  			return 0.7;
		  		}
		  		if (c.checkfullHouse() == true) {
		  			return .60;
		  		}
		  		if (c.checkFlush() == true) {
		  			return .45;
		  		}
		  		if (c.checkStraight() == true) {
		  			return .3;
		  		}
		  		if (c.mostofaKind() == 3) {
		  			return .15;
		  		}
		  		if (c.checktwoPair() == true) {
		  			return .2;
		  		}
		  		if (c.mostofaKind() == 2) {
		  			return .05;
		  		}
		  		return 0;
		      } 
	    	  if(strat==2) {
		    	  if (c.checkFlush() == true && c.checkStraight() == true) {
		  			return 1;
		  		}
		  		if (c.mostofaKind() == 4) {
		  			return 1;
		  		}
		  		if (c.checkfullHouse() == true) {
		  			return 1;
		  		}
		  		if (c.checkFlush() == true) {
		  			return 1;
		  		}
		  		if (c.checkStraight() == true) {
		  			return .5;
		  		}
		  		if (c.mostofaKind() == 3) {
		  			return .3;
		  		}
		  		if (c.checktwoPair() == true) {
		  			return .25;
		  		}
		  		if (c.mostofaKind() == 2) {
		  			return .15;
		  		}
		  		return .1;
		      } 
	    	  if(strat==3) {
		    	  if (c.checkFlush() == true && c.checkStraight() == true) {
		  			return 1;
		  		}
		  		if (c.mostofaKind() == 4) {
		  			return 1;
		  		}
		  		if (c.checkfullHouse() == true) {
		  			return 1;
		  		}
		  		if (c.checkFlush() == true) {
		  			return 1;
		  		}
		  		if (c.checkStraight() == true) {
		  			return 1;
		  		}
		  	
		  		return .3;
		      } 


	      }
	      if(round==2) {
	    	  if(strat==0) {
	    	  if (c.checkFlush() == true && c.checkStraight() == true) {
	  			return 1;
	  		}
	  		if (c.mostofaKind() == 4) {
	  			return 0.6;
	  		}
	  		if (c.checkfullHouse() == true) {
	  			return .35;
	  		}
	  		if (c.checkFlush() == true) {
	  			return .3;
	  		}
	  		if (c.checkStraight() == true) {
	  			return .25;
	  		}
	  		if (c.mostofaKind() == 3) {
	  			return .2;
	  		}
	  		if (c.checktwoPair() == true) {
	  			return .15;
	  		}
	  		if (c.mostofaKind() == 2) {
	  			return 0;
	  		}
	  		return 0;
	      }
	    	  if(strat==1) {
		    	  if (c.checkFlush() == true && c.checkStraight() == true) {
		  			return 1;
		  		}
		  		if (c.mostofaKind() == 4) {
		  			return 0.7;
		  		}
		  		if (c.checkfullHouse() == true) {
		  			return .60;
		  		}
		  		if (c.checkFlush() == true) {
		  			return .5;
		  		}
		  		if (c.checkStraight() == true) {
		  			return .3;
		  		}
		  		if (c.mostofaKind() == 3) {
		  			return .15;
		  		}
		  		if (c.checktwoPair() == true) {
		  			return .15;
		  		}
		  		if (c.mostofaKind() == 2) {
		  			return .05;
		  		}
		  		return 0;
		      } 
	    	  if(strat==2) {
		    	  if (c.checkFlush() == true && c.checkStraight() == true) {
		  			return 1;
		  		}
		  		if (c.mostofaKind() == 4) {
		  			return 1;
		  		}
		  		if (c.checkfullHouse() == true) {
		  			return .8;
		  		}
		  		if (c.checkFlush() == true) {
		  			return .6;
		  		}
		  		if (c.checkStraight() == true) {
		  			return .4;
		  		}
		  		if (c.mostofaKind() == 3) {
		  			return .3;
		  		}
		  		if (c.checktwoPair() == true) {
		  			return .25;
		  		}
		  		if (c.mostofaKind() == 2) {
		  			return .15;
		  		}
		  		return .1;
		      } 
	    	  if(strat==3) {
		    	  if (c.checkFlush() == true && c.checkStraight() == true) {
		  			return 1;
		  		}
		  		if (c.mostofaKind() == 4) {
		  			return 1;
		  		}
		  		if (c.checkfullHouse() == true) {
		  			return 1;
		  		}
		  		if (c.checkFlush() == true) {
		  			return 1;
		  		}
		 
		  	
		  		return .3;
		      } 


	      }
	      if(round==3) {
	    	  if(strat==0) {
	    	  if (c.checkFlush() == true && c.checkStraight() == true) {
	  			return 1;
	  		}
	  		if (c.mostofaKind() == 4) {
	  			return 0.6;
	  		}
	  		if (c.checkfullHouse() == true) {
	  			return .35;
	  		}
	  		if (c.checkFlush() == true) {
	  			return .3;
	  		}
	  		if (c.checkStraight() == true) {
	  			return .25;
	  		}
	  		if (c.mostofaKind() == 3) {
	  			return .15;
	  		}
	  		if (c.checktwoPair() == true) {
	  			return .10;
	  		}
	  		if (c.mostofaKind() == 2) {
	  			return .0;
	  		}
	  		return 0;
	      }
	    	  if(strat==1) {
		    	  if (c.checkFlush() == true && c.checkStraight() == true) {
		  			return 1;
		  		}
		  		if (c.mostofaKind() == 4) {
		  			return 0.7;
		  		}
		  		if (c.checkfullHouse() == true) {
		  			return .60;
		  		}
		  		if (c.checkFlush() == true) {
		  			return .5;
		  		}
		  		if (c.checkStraight() == true) {
		  			return .3;
		  		}
		  		if (c.mostofaKind() == 3) {
		  			return .15;
		  		}
		  		if (c.checktwoPair() == true) {
		  			return .15;
		  		}
		  		if (c.mostofaKind() == 2) {
		  			return .05;
		  		}
		  		return 0;
		      } 
	    	  if(strat==2) {
		    	  if (c.checkFlush() == true && c.checkStraight() == true) {
		  			return 1;
		  		}
		  		if (c.mostofaKind() == 4) {
		  			return 1;
		  		}
		  		if (c.checkfullHouse() == true) {
		  			return .8;
		  		}
		  		if (c.checkFlush() == true) {
		  			return .6;
		  		}
		  		if (c.checkStraight() == true) {
		  			return .4;
		  		}
		  		if (c.mostofaKind() == 3) {
		  			return .3;
		  		}
		  		if (c.checktwoPair() == true) {
		  			return .25;
		  		}
		  		if (c.mostofaKind() == 2) {
		  			return .15;
		  		}
		  		return .1;
		      } 
	    	  if(strat==3) {
		    	  if (c.checkFlush() == true && c.checkStraight() == true) {
		  			return 1;
		  		}
		  		if (c.mostofaKind() == 4) {
		  			return 1;
		  		}
		  		if (c.checkfullHouse() == true) {
		  			return 1;
		  		}
		  		if (c.checkFlush() == true) {
		  			return 1;
		  		}
		  		if (c.checkStraight() == true) {
		  			return 1;
		  		}
		  	
		  		return .3;
		      } 


	      }
	      if(round==4) {
	    	  if(strat==0) {
	    	  if (c.checkFlush() == true && c.checkStraight() == true) {
	  			return 1;
	  		}
	  		if (c.mostofaKind() == 4) {
	  			return 0.6;
	  		}
	  		if (c.checkfullHouse() == true) {
	  			return .35;
	  		}
	  		if (c.checkFlush() == true) {
	  			return .3;
	  		}
	  		if (c.checkStraight() == true) {
	  			return .20;
	  		}
	  		if (c.mostofaKind() == 3) {
	  			return .13;
	  		}
	  		if (c.checktwoPair() == true) {
	  			return 0;
	  		}
	  		if (c.mostofaKind() == 2) {
	  			return .0;
	  		}
	  		return 0;
	      }
	    	  if(strat==1) {
		    	  if (c.checkFlush() == true && c.checkStraight() == true) {
		  			return 1;
		  		}
		  		if (c.mostofaKind() == 4) {
		  			return 0.7;
		  		}
		  		if (c.checkfullHouse() == true) {
		  			return .60;
		  		}
		  		if (c.checkFlush() == true) {
		  			return .5;
		  		}
		  		if (c.checkStraight() == true) {
		  			return .3;
		  		}
		  		if (c.mostofaKind() == 3) {
		  			return .15;
		  		}
		  		if (c.checktwoPair() == true) {
		  			return .15;
		  		}
		  		if (c.mostofaKind() == 2) {
		  			return .0;
		  		}
		  		return 0;
		      } 
	    	  if(strat==2) {
		    	  if (c.checkFlush() == true && c.checkStraight() == true) {
		  			return 1;
		  		}
		  		if (c.mostofaKind() == 4) {
		  			return 1;
		  		}
		  		if (c.checkfullHouse() == true) {
		  			return 1;
		  		}
		  		if (c.checkFlush() == true) {
		  			return 1;
		  		}
		  		if (c.checkStraight() == true) {
		  			return .4;
		  		}
		  		if (c.mostofaKind() == 3) {
		  			return .3;
		  		}
		  		if (c.checktwoPair() == true) {
		  			return .25;
		  		}
		  		if (c.mostofaKind() == 2) {
		  			return .15;
		  		}
		  		return .1;
		      } 
	    	  if(strat==3) {
		    	  if (c.checkFlush() == true && c.checkStraight() == true) {
		  			return 1;
		  		}
		  		if (c.mostofaKind() == 4) {
		  			return 1;
		  		}
		  		if (c.checkfullHouse() == true) {
		  			return 1;
		  		}
		  		if (c.checkFlush() == true) {
		  			return 1;
		  		}
		  		if (c.checkStraight() == true) {
		  			return 1;
		  		}
		  	
		  		return .3;
		      } 


	      }
	      return 0;
	      }
public static int call(int b, int r) {
	 return (int)(7*r*b*Math.random());
}
	public static void main(String args[]) {
		boolean gameEnd = false;

		Deck mainDeck = new Deck();
		Deck p1Hand = new Deck();
		Deck b1Hand = new Deck();
		Deck fiveCards = new Deck();
      System.out.println(botBet(1,1,p1Hand, b1Hand));
      
		fiveCards.empty();
		p1Hand.empty();
		b1Hand.empty();
		boolean p1Fold=false;
		boolean b1Fold=false;
		int p1Bet = 0;
		int b1Bet = 20;
		int p1Money = 100;
		int b1Money=100;
		int maxBet=0;
		int temp=0;
		int round=1;
		
		Scanner scan = new Scanner(System.in);

		mainDeck.shuffle();
		System.out.println(mainDeck.mostofaKind());

		while (gameEnd == false) {
			mainDeck.shuffle();

			p1Hand.deal2(mainDeck);
         b1Hand.deal2(mainDeck);
         

			System.out.print("Your hand is ");
			p1Hand.printHand();

			System.out.println("place your bet");
			p1Bet = scan.nextInt();
			while (p1Bet > p1Money) {
				System.out.println("You dont have that much money, please place a bet that you can afford.");
				p1Bet = scan.nextInt();
			}
			while((maxBet!=p1Bet ||maxBet!=b1Bet)&&(b1Fold==false &&p1Fold==false)) {
				if(p1Bet<b1Bet) {
				System.out.println("bot 1 placed a bet of "+ b1Bet +"which is "+ (b1Bet-p1Bet) +" higher than your bet. Enter 0 to fold, " + (b1Bet-p1Bet) + " to call or higher to reraise");
				p1Bet = p1Bet+scan.nextInt();
				System.out.println(p1Bet);
				if(p1Bet<b1Bet) {
					p1Fold=true;
					
					System.out.println("player 1 folds!");
				}
				else maxBet=p1Bet;
				}
		if(p1Bet>=b1Bet) {
			temp =call(b1Bet, round);
			System.out.println(temp);
			if((temp+b1Bet)>b1Money) {
				temp=b1Money-b1Bet;
			}
			else if((temp+b1Bet)>maxBet) {
				b1Bet= b1Bet+temp;
			}
			
			if(Math.abs(b1Bet-maxBet)<=10) {
				b1Bet=maxBet;
				
				System.out.println("b1 calls");
			}
			else if((b1Bet)>=maxBet) {
				
				System.out.println("b1 raises to " + (b1Bet));
				maxBet=b1Bet;
			}
			else {
				b1Fold=true;
				System.out.println("b1Folds!");
			}
		}
				System.out.println("p1 bet ="+p1Bet+"b1 bet ="+b1Bet+"max bet ="+maxBet);
				
			}

			p1Money = p1Money - p1Bet;
			p1Bet = 0;
			fiveCards.flop(mainDeck);
			System.out.print("these cards opened up ");
			fiveCards.printHand();
			System.out.println("place your bet");
			p1Bet = scan.nextInt();
			while (p1Bet > p1Money) {
				System.out.println("You dont have that much money, please place a bet that you can afford.");
				p1Bet = scan.nextInt();

			}
			p1Money = p1Money - p1Bet;
			p1Bet = 0;
			fiveCards.addCard(mainDeck.deal());

			System.out.print("these cards opened up ");
			fiveCards.printHand();
			p1Bet = scan.nextInt();
			while (p1Bet > p1Money) {
				System.out.println("You dont have that much money, please place a bet that you can afford.");
				p1Bet = scan.nextInt();
			}

			p1Money = p1Money - p1Bet;
			p1Bet = 0;
			fiveCards.addCard(mainDeck.deal());

			System.out.print("All cards open place your final bets");
			fiveCards.printHand();
			p1Bet = scan.nextInt();
			while (p1Bet > p1Money) {
				System.out.println("You dont have that much money, please place a bet that you can afford.");
				p1Bet = scan.nextInt();
			}

			p1Money = p1Money - p1Bet;
			p1Bet = 0;
			System.out.println(botBet(3,2,p1Hand, fiveCards));

//for ai do something like for a balaced bot do 20% defenceive 50% chance normal 20% agro 10% bluff
			gameEnd = true;
		}

	}
}