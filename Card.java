
public class Card {
	private String suit = "";
	private int value = 0;
	private String imgStr = "";

	public Card(String suit, int value, String imgStr) {
		this.suit = suit;
		this.value = value;
		this.imgStr = imgStr;
	}
	
	public String getImgStr() {
		return imgStr;
	}

	public void setImgStr(String imgStr) {
		this.imgStr = imgStr;
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String toString() {
		return value + " of " + suit;
	}
	
}
