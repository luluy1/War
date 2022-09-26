import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Stack;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class War extends Application{

	Cards cards = new Cards();
	Stack<Card> deck1 = new Stack<Card>();
	Stack<Card> deck2 = new Stack<Card>();
	Stack<Card> played = new Stack<Card>();

	Card drawn1 = null;
	Card drawn2 = null;

	// multiplayer page variables
	Text numCardsP1 = new Text("Number of cards for player 1: 26");
	Text numCardsP2 = new Text("Number of cards for player 2: 26");
	
	Button war = new Button("War!");
	Button draw = new Button("Draw");
	Button clear = new Button("Clear the board");
	Image card1 = null;
	Image card2 = null;
	Image card3 = null;
	Image card4 = null;
	Image cardBack = null;
	Image blankSpace = null;

	ImageView iv1 = null;
	ImageView iv2 = null;
	ImageView iv3 = null;
	ImageView iv4 = null;
	ImageView iv5 = null;
	ImageView iv6 = null;
	ImageView iv7 = null;
	ImageView iv8 = null;
	ImageView iv9 = null;
	ImageView iv10 = null;

	
	HBox cardsP1 = new HBox(20);
	HBox cardsP2 = new HBox(20);
	VBox players = new VBox(30);
	VBox menu = new VBox(30);
	HBox total = new HBox(150);
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		for (int i = 0; i < 26; i++) {
			deck1.push((cards.getDeck()).pop());
		}
		deck2 = (Stack<Card>) (cards.getDeck()).clone();
		
		int rand = (int)Math.random()*10+1;
		for (int i = 0; i < rand; i++) {
			Cards.shuffle(deck1);
			Cards.shuffle(deck2);
		}
		
		war.setVisible(false);
		clear.setVisible(false);
		
		//assign a blank page to all cards
		try {
			blankSpace = new Image(new FileInputStream("Blank.gif"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assignBlankSpaces();
		
		//for the backs of cards
		try {
			cardBack = new Image(new FileInputStream("Back.gif"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		clear.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				assignBlankSpaces();
				clear.setVisible(false);
				draw.setVisible(true);
				numCardsP1.setText("Number of cards for Player 1: " + deck1.size());
				//smth is wrong with deck2.size
				numCardsP2.setText("Number of cards for Player 1: " + deck2.size());
				
				menu = menuDisplay(menu, draw, war, clear, numCardsP1, numCardsP2);
				total = totalDisplay(total, players, menu);
			}
			
		});
		
		draw.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				drawn1 = deck1.pop();
				try {
					card1 = new Image(new FileInputStream(drawn1.getImgStr()));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				iv1 = new ImageView(card1);
				fixImage(iv1);				
				
				drawn2 = deck2.pop();
				try {
					card2 = new Image(new FileInputStream(drawn2.getImgStr()));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				iv2 = new ImageView(card2);
				fixImage(iv2);		
				
				played.push(drawn1);
				played.push(drawn2);
				
				if (drawn1.getValue()==drawn2.getValue()) {
					war.setVisible(true);
					draw.setVisible(false);
					clear.setVisible(false);
				}	
				
				else if (drawn1.getValue() > drawn2.getValue()) {
					while(!played.isEmpty()) {
						deck1.push(played.pop());
					}
					clear.setVisible(true);
					draw.setVisible(false);
				}
				else {
					while(!played.isEmpty()) {
						deck2.push(played.pop());
					}
					clear.setVisible(true);
					draw.setVisible(false);
				}
				
				Cards.shuffle(deck1);
				Cards.shuffle(deck2);
				
				if (deck1.size() == 52) {
					numCardsP1.setText("You won!");
					numCardsP2.setText("You lost!");
				}
				if (deck2.size()== 52) {
					numCardsP2.setText("You won!");
					numCardsP1.setText("You lost");
				}
				
				cardsP1 = cardsDisplay(cardsP1, iv1, iv3, iv5, iv6, iv7);
				cardsP2 = cardsDisplay(cardsP2, iv2, iv4, iv8, iv9, iv10);
				players = playersDisplay(players, cardsP1, cardsP2);
				menu = menuDisplay(menu, draw, war, clear,  numCardsP1, numCardsP2);
				total = totalDisplay(total, players, menu);
			}
			
		});
		
		war.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				draw.setVisible(false);
				drawn1 = deck1.pop();
				try {
					card3 = new Image(new FileInputStream(drawn1.getImgStr()));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				iv3 = new ImageView(card3);
				fixImage(iv3);	
				
				drawn2 = deck2.pop();
				try {
					card4 = new Image(new FileInputStream(drawn2.getImgStr()));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				iv4 = new ImageView(card4);
				fixImage(iv4);	
				
				played.push(drawn1);
				played.push(drawn2);
				for (int i = 0; i < 3; i++) {
					played.push(deck1.pop());
					played.push(deck2.pop());
				}
				
				if (drawn1.getValue() > drawn2.getValue()) {
					while(!played.isEmpty()) {
						deck1.push(played.pop());
					}
					war.setVisible(false);
					clear.setVisible(true);
				}
				else if (drawn1.getValue() < drawn2.getValue()) {
					while(!played.isEmpty()) {
						deck2.push(played.pop());
					}
					war.setVisible(false);
					clear.setVisible(true);
				}

				drawn1 = null;
				drawn2 = null;
				
				iv5 = new ImageView(cardBack);
				fixImage(iv5);
				iv6 = new ImageView(cardBack);
				fixImage(iv6);
				iv7 = new ImageView(cardBack);
				fixImage(iv7);
				iv8 = new ImageView(cardBack);
				fixImage(iv8);
				iv9 = new ImageView(cardBack);
				fixImage(iv9);
				iv10 = new ImageView(cardBack);
				fixImage(iv10);
				
				if (deck1.size() == 52) {
					numCardsP1.setText("You won!");
					numCardsP2.setText("You lost!");
				}
				if (deck2.size()== 52) {
					numCardsP2.setText("You won!");
					numCardsP1.setText("You lost");
				}
				
				Cards.shuffle(deck1);
				Cards.shuffle(deck2);
				
				
				cardsP1 = cardsDisplay(cardsP1, iv1, iv3, iv5, iv6, iv7);
				cardsP2 = cardsDisplay(cardsP2, iv2, iv4, iv8, iv9, iv10);
				players = playersDisplay(players, cardsP1, cardsP2);
				menu = menuDisplay(menu, draw, war, clear, numCardsP1, numCardsP2);
				total = totalDisplay(total, players, menu);
			}	
			
		});
		
		cardsP1 = cardsDisplay(cardsP1, iv1, iv3, iv5, iv6, iv7);
		cardsP2 = cardsDisplay(cardsP2, iv2, iv4, iv8, iv9, iv10);
		players = playersDisplay(players, cardsP1, cardsP2);
		menu = menuDisplay(menu, draw, war, clear, numCardsP1, numCardsP2);
		total = totalDisplay(total, players, menu);
		
		Scene scene = new Scene(total);
		primaryStage.setTitle("War!");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	// adjusting the size of a card
		public void fixImage(ImageView iv) {
			iv.setFitHeight(120);
			iv.setFitWidth(100);
			iv.setPreserveRatio(false);
		}

		public HBox cardsDisplay(HBox hbox, ImageView iv1, ImageView iv2, ImageView iv3, ImageView iv4, ImageView iv5) {
			hbox.getChildren().clear();
			hbox.getChildren().addAll(iv1, iv2, iv3, iv4, iv5);
			return hbox;
		}
		
		public VBox playersDisplay(VBox vbox, HBox hbox1, HBox hbox2) {
			vbox.getChildren().clear();
			vbox.getChildren().addAll(hbox1, hbox2);
			return vbox;
		}
		
		public VBox menuDisplay(VBox vbox, Button button, Button war, Button clear, Text numCardsP1, Text numCardsP2) {
			vbox.getChildren().clear();
			vbox.getChildren().addAll(button, war, clear, numCardsP1, numCardsP2);
			return vbox;
		}
		
		public HBox totalDisplay(HBox total, VBox players, VBox menu) {
			total.getChildren().clear();
			total.getChildren().addAll(players, menu);
			return total;
		}
		
		public void assignBlankSpaces() {
			iv1 = new ImageView(blankSpace);
			fixImage(iv1);
			iv2 = new ImageView(blankSpace);
			fixImage(iv2);
			iv3 = new ImageView(blankSpace);
			fixImage(iv3);
			iv4 = new ImageView(blankSpace);
			fixImage(iv4);
			iv5 = new ImageView(blankSpace);
			fixImage(iv5);
			iv6 = new ImageView(blankSpace);
			fixImage(iv6);
			iv7 = new ImageView(blankSpace);
			fixImage(iv7);
			iv8 = new ImageView(blankSpace);
			fixImage(iv8);
			iv9 = new ImageView(blankSpace);
			fixImage(iv9);
			iv10 = new ImageView(blankSpace);
			fixImage(iv10);
			
			cardsP1 = cardsDisplay(cardsP1, iv1, iv3, iv5, iv6, iv7);
			cardsP2 = cardsDisplay(cardsP2, iv2, iv4, iv8, iv9, iv10);
			players = playersDisplay(players, cardsP1, cardsP2);
			menu = menuDisplay(menu, draw, war,clear, numCardsP1, numCardsP2);
			total = totalDisplay(total, players, menu);
			
		}
	
		public static void main(String[] args) {
			launch(args);
		}

}
