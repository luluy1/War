import java.util.Stack;

public class Cards {
	private Stack<Card> deck = new Stack<Card>();

	public Cards() {
		for (int i=0; i < 4; i++) {
			for(int value=1; value < 12; value++) {
				String suit = "";
				
				if(i == 0) {
					suit = "Spades";
				}
				else if (i ==1) {
					suit = "Clubs";
				}
				else if (i ==2) {
					suit = "Hearts";
				}
				else {
					suit = "Diamonds";
				}
				
				if (value == 1) {
					deck.push(new Card(suit, 1, suit.substring(0,1) + "A.gif"));
				}
				else if (value == 11) {
					deck.push(new Card(suit, 11, suit.substring(0,1) + "K.gif"));
					deck.push(new Card(suit, 11, suit.substring(0,1) + "Q.gif"));
					deck.push(new Card(suit, 11, suit.substring(0,1) + "J.gif"));
				}
				else {
					deck.push(new Card(suit, value, suit.substring(0,1) + value + ".gif"));
				}
			}
			
		}
		shuffle();
	}
	

	public Stack<Card> getDeck() {
		return deck;
	}

	public void setDeck(Stack<Card> deck) {
		this.deck = deck;
	}
	
	public void shuffle() {
		//every other card after splitting deck in half
		Stack<Card> stack1 = new Stack<Card>();
		Stack<Card> stack2 = new Stack<Card>();
		
		while (deck.size() >= 26) {
			stack1.push(deck.pop());
		}
		while (!deck.isEmpty()) {
			stack2.push(deck.pop());
		}
		
		while (!stack1.isEmpty() && !stack2.isEmpty()) {
			deck.push(stack1.pop());
			deck.push(stack2.pop());
		}
		while(!stack1.isEmpty()) {
			deck.push(stack1.pop());
		}
		while(!stack2.isEmpty()) {
			deck.push(stack2.pop());
		}
	}
	
	public static Stack<Card> shuffle(Stack<Card> deck) {
		Stack<Card> stack1 = new Stack<Card>();
		Stack<Card> stack2 = new Stack<Card>();
		
		while (deck.size() >= 26) {
			stack1.push(deck.pop());
		}
		while (!deck.isEmpty()) {
			stack2.push(deck.pop());
		}
		
		while (!stack1.isEmpty() && !stack2.isEmpty()) {
			deck.push(stack1.pop());
			deck.push(stack2.pop());
		}
		while(!stack1.isEmpty()) {
			deck.push(stack1.pop());
		}
		while(!stack2.isEmpty()) {
			deck.push(stack2.pop());
		}
		
		return deck;
	}
	
	public static void main(String[]args) {
		Cards card = new Cards();
		
		card.getDeck().pop();
		card.getDeck().pop();
		card.getDeck().pop();
		card.getDeck().pop();
		card.getDeck().pop();
		card.getDeck().pop();
		card.getDeck().pop();
		card.getDeck().pop();
		card.getDeck().pop();
		card.getDeck().pop();
		card.getDeck().pop();
		card.getDeck().pop();
		card.getDeck().pop();
		card.getDeck().pop();
		card.getDeck().pop();
		card.getDeck().pop();


		while(!card.getDeck().isEmpty()) {
			Card drawn1 = card.getDeck().pop();
			Card drawn2 = card.getDeck().pop();
			
			System.out.println(drawn1);
			System.out.println(drawn2);
			System.out.println(drawn1.getValue()==drawn2.getValue());
			System.out.println();
		}
	}
}
