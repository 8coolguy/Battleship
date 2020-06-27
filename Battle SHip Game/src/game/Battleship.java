package game;

import javafx.application.Application;// for other stuff
import javafx.scene.image.*;//for the image
import javafx.scene.Scene;//for scene
import javafx.scene.shape.*;//made rectangles
import javafx.scene.control.*;//for button
import javafx.scene.layout.Pane;//pane to set stage
import javafx.stage.Stage;//the stage for everything
import javafx.scene.paint.Color; //colored the rectanagles
import java.awt.Dimension;//used for screen size
import java.awt.Toolkit;//got screen size
import java.util.*;//for scanner
import javafx.scene.text.*;
import javafx.event.*;
import javafx.scene.media.*;
import java.io.File;

public class Battleship extends Application {
	 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    
	    final int X =screenSize.width;//screen size for Ui
	    final int Y =screenSize.height;
	    final int ROW =9;
	    final int COLUMN=11;
	    
	    
	    
	 
	    
	    int shipNumber=0;
	    int errorCount =0;
	    
	    public static void main(String[] args){
	        launch(args);
	    }
	    
	    @Override
	        public void start(Stage stage) throws Exception{
	            stage.setTitle("BattleShip Game");
	            
	            Pane layer = new Pane();
	            
	            Scene gameStage =new Scene(layer,X,Y);
	            
	            stage.setScene(gameStage);
	            
	            Media setSound = new Media(new File("set.mp3").toURI().toString());
	            MediaPlayer soundSet =new MediaPlayer(setSound);
	            //draw place where board is drawn
	            Rectangle base1= new Rectangle(0,0,X/2,Y);
	            Rectangle base2 =new Rectangle(X/2,0,X/2,Y);
	            
	                
	            base1.setFill(Color.RED);
	            base2.setFill(Color.GREEN);
	           

	            layer.getChildren().addAll(base1);
	            layer.getChildren().addAll(base2);
	            
	            //buttonfield or map for player1 on the right side 
	            Button[][] player1 = new Button[11][9];
	            for(int row=0; row<COLUMN; row++){
	                for(int column=0; column<ROW;column++){
	                    player1[row][column] =new Button();
	                    player1[row][column].setPrefSize(X/(2*COLUMN),(3*Y)/(4*ROW));
	                    player1[row][column].setLayoutX(row*X/(2*COLUMN)+X/2);
	                    player1[row][column].setLayoutY(column*(3*Y)/(4*ROW)+Y/4);
	                    layer.getChildren().addAll(player1[row][column]);
	                }
	            }
	            
	            //buttonfield or map for player 2
	            Button[][] player2 = new Button[COLUMN][ROW];
	            for(int row=0; row<COLUMN; row++){
	                for(int column=0; column<ROW;column++){
	                    player2[row][column] =new Button();
	                    player2[row][column].setPrefSize(X/(2*COLUMN),(3*Y)/(4*ROW));
	                    player2[row][column].setLayoutX(row*X/(2*COLUMN));
	                    player2[row][column].setLayoutY(column*(3*Y)/(4*ROW)+Y/4);
	                    layer.getChildren().addAll(player2[row][column]);
	                }
	            }
	            //text field scanner
	            // create a textfield 
	            TextField setter = new TextField(""); 

	            setter.setPrefColumnCount(10); 
	            
	            setter.setLayoutX(X/2);
	            setter.setLayoutY(Y/5);
	            
	            //TITLE
	            Text title =new Text();
	            
	            title.setX(3*X/7);
	            title.setY(Y/15);
	            title.setFont(Font.font("Abyssinica SIL",FontWeight.BOLD,FontPosture.REGULAR,55));  
	            title.setFill(Color.WHITE);
	            title.setText("Battle Ship"+"\r\n"+"by Arnav");
	            
	            layer.getChildren().addAll(title);
	            
	            //instructions text box
	            Text instructions = new Text();
	            instructions.setText("Set with 3 Characters:(A-K),(1-9),(N,E,S,W)");
	            
	            instructions.setX(X/2); 
	            instructions.setY(Y/6);
	            
	            Button submit = new Button("set");
	            submit.setLayoutX(X/2);
	            submit.setLayoutY(Y/5+Y/50);
	            //add text field
	            //another text box and button
	            layer.getChildren().addAll(setter);
	            layer.getChildren().addAll(instructions);
	            layer.getChildren().addAll(submit);
	            
	            
	            //boards 
	            Board myShips =new Board();
	            Board opponentShips =new Board();
	            
	            //ships fort player you
	            
	            Ship ship1 = new Ship("Destroyer", 2);
	            Ship ship2 = new Ship("Submarine", 3);
	            Ship ship3 = new Ship("Cruiser", 3);
	            Ship ship4 = new Ship("Battleship", 4);
	            Ship ship5 = new Ship("Carrier", 5);
	            
	             
	            Ship[] fleetArray= {ship1, ship2, ship3, ship4, ship5};
	            //setUo FKeet
	            Fleet myFleet = new Fleet(fleetArray);
	            
	            
	            myShips.drawBoard(player1);
	            opponentShips.drawBoard(player2);    
	            stage.show();        
	            //board set up
	            Text feedback =new Text(); 
	            Text error =new Text();
	            

	            if(shipNumber<5){            
	                submit.setOnAction(new EventHandler<ActionEvent>() {   
	                    @Override
	                    public void handle(ActionEvent e){
	                        
	                        Ship currentShip = myFleet.getShipAt(shipNumber);
	                        
	                        
	                        char[] input =setter.getText().toCharArray();   
	                        char inRow = input[0];
	                        int userColumnT = Character.getNumericValue(input[1])-1;  
	                        char userDirectionT =input[2];
	                            
	                        int userColumn =0;
	                        char userDirection = 'N';
	                        int userRow=0;
	                        
	                        boolean validInput =true;
	                        int x=0;;
	                            boolean goodRow = false;
	                            while (!goodRow) {
	                              if (inRow >= 'A' && inRow <= 'K') {
	                                 if (inRow == 'A') {userRow = 0;}    
	                                 else if (inRow == 'B') {userRow = 1;}
	                                 else if (inRow == 'C') {userRow = 2;}    
	                                 else if (inRow == 'D') {userRow = 3;}    
	                                 else if (inRow == 'E') {userRow = 4;}    
	                                 else if (inRow == 'F') {userRow = 5;}    
	                                 else if (inRow == 'G') {userRow = 6;}    
	                                 else if (inRow == 'H') {userRow = 7;}    
	                                 else if (inRow == 'I') {userRow = 8;} 
	                                 else if (inRow == 'J') {userRow = 9;}    
	                                 else if (inRow == 'K') {userRow = 10;}    
	                                 goodRow = true;
	                              }
	                              else{
	                                 goodRow = true;
	                                 x=1;
	                                 validInput=false;
	                              }
	                       
	                            }      
	                            boolean goodColumn = false;
	                            while (!goodColumn) {
	                                
	                                if (userColumnT > -1  && userColumnT < 9){
	                                    userColumn = userColumnT;
	                                    goodColumn = true;
	                                }
	                                else{
	                                    goodColumn = true;
	                                    x=2;
	                                    validInput=false;
	                                }
	                            }
	                            boolean goodDirection = false;
	                            while (!goodDirection) {
	                                if (userDirectionT == 'N' || userDirectionT == 'E' || userDirectionT == 'S' || userDirectionT == 'W') {
	                                    userDirection = userDirectionT;
	                                    goodDirection = true;
	                                }
	                                else{ 
	                                    goodDirection = true;
	                                    x=3;
	                                    validInput=false;
	                                }
	                            }
	                        if(validInput){
	                            if(myShips.validPlacement(currentShip,userRow,userColumn,userDirection)){

	                                Text feedback =new Text(); 
	                                feedback.setX((shipNumber*X)/(fleetArray.length));
	                                feedback.setY(Y/5);
	                                feedback.setText(myFleet.getShipAt(shipNumber).getName()+" set.");
	                                layer.getChildren().addAll(feedback);
	                                
	                                currentShip.setLocation(userRow,userColumn,userDirection);
	                                myShips.update(currentShip); 
	                                shipNumber++;
	                                myShips.drawBoard(player1);
	                                
	                                setter.clear();
	                                stage.show();
	                                
	                                    if(shipNumber==5){
	                                        submit.setVisible(false);
	                                        submit.setDisable(true);
	                                        setter.setVisible(false);
	                                        setter.setDisable(true);
	                                        instructions.setVisible(false);
	                                        soundSet.play();
	                                        //hit or miss for player 1
	                                        
	                                        
	                                        
	                                            
	                                                myShips.checkHit1(player1,myFleet,layer);
	                                            
	                                                opponentShips.checkHit2(player2,layer);
	                                            
	                                           
	                                        
	                                        
	                                        
	                                    }
	                            }
	                                else{
	                                    displayError(layer,4); 
	                                    setter.clear();
	                            }
	                        }
	                        else{
	                            displayError(layer,x);
	                        }
	                        
	                        
	                    }
	                
	                
	    });
	    
	}

	    
	            
	       

	}
	       
	        public void displayError(Pane layer, int type){
	            Text error =new Text();
	            errorCount++;
	            error.setText("Wrong Position: "+type+ " Error:" +errorCount);
	            error.setX((5*X)/6);
	            error.setY((Y*errorCount)/20);
	            layer.getChildren().addAll(error);
	        }
}
