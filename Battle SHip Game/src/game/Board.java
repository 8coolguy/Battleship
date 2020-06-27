package game;

import javafx.application.Application;// for other stuff
import javafx.scene.image.*;//for the image
import javafx.scene.Scene;//for scene
import javafx.scene.shape.*;//made rectangles
import javafx.scene.control.Button;//for button
import javafx.scene.control.Label;//labeled button
import javafx.scene.layout.Pane;//pane to set stage
import javafx.stage.Stage;//the stage for everything
import javafx.scene.paint.Color; //colored the rectanagles
import java.awt.Dimension;//used for screen size
import java.awt.Toolkit;//got screen size
import javafx.event.*;
import javafx.scene.text.*;
import javafx.scene.media.*;
import java.io.File;

public class Board {
	   final int ROWS =9;
	   final int COLUMNS =11;
	   public static final char EMPTY ='-';
	   public static final char SHIP ='O';
	   public static final char SUNK ='X';
	   public static final char HIT ='x';
	   public static final char MISS='o';
	   
	   
	   Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    
	   final int X =screenSize.width;//screen size for Ui
	   final int Y =screenSize.height;
	   //for now making it public change later
	   private char[][] board =new char[COLUMNS][ROWS];
	   
	   String sunk =
	        "-fx-background-color: #000000;" +
	        "-fx-border-color: #000000;" ;
	   String empty =
	        "-fx-background-color: #0000FF;" +
	        "-fx-border-color: #000000;" ;
	   String ship =
	        "-fx-background-color: #C0C0C0;" +
	        "-fx-border-color: #000000;" ;
	   String miss =
	        "-fx-background-color: #00FFFF;" +
	        "-fx-border-color: #000000;" ;
	   String hit =
	        "-fx-background-color: #DC143C;" +
	        "-fx-border-color: #000000;" ;
	   public Board(){
	       for(int row=0; row<board.length; row++){
	           for(int column=0; column<board[row].length;column++){
	               board[row][column]=EMPTY;
	           }
	       }
	   }
	   public void update(Ship ship){
	        int row = ship.getRow(); 
	        int col = ship.getColumn(); 
	        char dir = ship.getDirection();
	        int len = ship.getLength();
	        
	        if (dir == 'E') {
	            for (int i = 0; i < len; i++) {
	                board[row-i][col] = SHIP;
	            }
	        }
	        if (dir == 'N') {
	            for (int i = 0; i < len; i++) {
	                board[row][col+i] = SHIP;
	            }
	        }
	        if (dir == 'W') {
	            for (int i = 0; i < len; i++) {
	                board[row+i][col] = SHIP;
	            }
	        }
	        if (dir == 'S') {
	            for (int i = 0; i < len; i++) {
	                board[row][col-i] = SHIP;
	            }
	        }
	       
	       
	   }
	   public void sunkShip(Ship ship){
	        int row = ship.getRow(); 
	        int col = ship.getColumn(); 
	        char dir = ship.getDirection();
	        int len = ship.getLength();
	        
	        if (dir == 'E') {
	            for (int i = 0; i < len; i++) {
	                board[row-i][col] = SUNK;
	            }
	        }
	        if (dir == 'N') {
	            for (int i = 0; i < len; i++) {
	                board[row][col+i] = SUNK;
	            }
	        }
	        if (dir == 'W') {
	            for (int i = 0; i < len; i++) {
	                board[row+i][col] = SUNK;
	            }
	        }
	        if (dir == 'S') {
	            for (int i = 0; i < len; i++) {
	                board[row][col-i] = SUNK;
	            }
	        }
	       
	       
	   }
	   public void drawBoard(Button[][] map){
	        for(int row=0; row<board.length; row++){
	           for(int column=0; column<board[row].length;column++){
	               if(board[row][column]==EMPTY){
	                   map[row][column].setStyle(empty);
	               }
	               else if(board[row][column]==SHIP){
	                   map[row][column].setStyle(ship);   
	               }
	               else if(board[row][column]==SUNK){
	                   map[row][column].setStyle(sunk);  
	                }
	                else if(board[row][column]==HIT){
	                   map[row][column].setStyle(hit);  
	                }
	                else if(board[row][column]==MISS){
	                   map[row][column].setStyle(miss);  
	                }
	               
	           }
	        }
	   }
	   public boolean validPlacement(Ship testShip, int row, int col, char dir) {
	                boolean OFFBOARD =false;
	                boolean OVERLAP=false;
	                boolean VALID =true;
	                
	                if (dir == 'E') {
	                   if (row - testShip.getLength() < 0) {
	                          return OFFBOARD;
	                        }
	                    }
	                    if (dir == 'W') {
	                        if (row + testShip.getLength() > COLUMNS-1) {
	                            return OFFBOARD;
	                        }
	                    }
	                    if (dir == 'S') {
	                        if (col + testShip.getLength() > ROWS-1) {
	                            return OFFBOARD;
	                        }
	                    }
	                    if (col == 'N') {
	                        if (row - testShip.getLength() < 0) {
	                            return OFFBOARD;
	                        }
	                    }

	                   
	                    for (int i = 0; i < testShip.getLength()-1; i++) {
	                        if (dir == 'E') {
	                            if(board[row-i][col] == SHIP){
	                                return OVERLAP;
	                                
	                            }
	                        }
	                         if (dir == 'W') {
	                            if(board[row+i][col] == SHIP){
	                                return OVERLAP;
	                            }
	                        }
	                         if (dir == 'N') {
	                            if(board[row][col+i] == SHIP){
	                                return OVERLAP;
	                            }
	                        }
	                         if (dir == 'S') {
	                            if(board[row][col-i] == SHIP){
	                                return OVERLAP;
	                            }
	                        }
	                    } 
	                    return VALID;
	   }
	   public void alterBoard(int row,int column,char status){
	       this.board[row][column] =status;
	    }
	    public char getTile(int row, int column){
	        return this.board[row][column];
	    }
	    public void checkHit1(Button[][] player1,Fleet myFleet,Pane layer){
	            for(int row=0; row<COLUMNS; row++){
	                for(int column=0; column<ROWS;column++){
	                    final  int R =row;
	                    Text gameOver =new Text();
	                    final int C =column;
	                    player1[row][column].setOnAction(new EventHandler<ActionEvent>(){
	                    public void handle( ActionEvent event){
	                       Media hitSound = new Media(new File("hit.mp3").toURI().toString());
	                       MediaPlayer soundHit =new MediaPlayer(hitSound);
	                       
	                       Media missSound = new Media(new File("miss.mp3").toURI().toString());
	                       MediaPlayer soundMiss =new MediaPlayer(missSound);
	                       
	                       Media gameSound = new Media(new File("gameOver.mp3").toURI().toString());
	                       MediaPlayer soundGame =new MediaPlayer(gameSound);
	                       if(board[R][C]==EMPTY){
	                           board[R][C] =MISS;
	                           soundMiss.play();
	                       }
	                       else if(board[R][C] ==SHIP){
	                         board[R][C] =HIT;
	                         soundHit.play();
	                         for(int i =0;i<myFleet.size();i++){
	                                     Ship ship =myFleet.getShipAt(i);
	                                     int row = ship.getRow(); 
	                                     int col = ship.getColumn(); 
	                                     char dir = ship.getDirection();
	                                     int len = ship.getLength();
	                                     
	                                     boolean sunk =true;
	        
	                                       if (dir == 'E') {
	                                         for (int j = 0; j < len; j++) {
	                                             if(board[row-j][col] == SHIP)
	                                                  sunk =false;
	                                           }
	                                       }
	                                       if (dir == 'N') {
	                                           for (int j = 0; j < len; j++) {
	                                               if(board[row][col+j] == SHIP)
	                                                   sunk =false;
	                                           }
	                                       }
	                                       if (dir == 'W') {
	                                           for (int j = 0; j< len; j++) {
	                                               if(board[row+j][col] == SHIP)
	                                                   sunk =false;
	                                           }
	                                       }
	                                       if (dir == 'S') {
	                                           for (int j = 0; j < len; j++) {
	                                               if(board[row][col-j] == SHIP)
	                                                   sunk =true;
	                                           }
	                                       }
	                                       if(sunk){
	                                         sunkShip(ship);
	                                         ship.setSunk(true);
	                                            if(myFleet.winCondition()){
	                                                Text gameOver =new Text(); 
	                                                gameOver.setX(0);
	                                                gameOver.setY(Y/3);
	                                                gameOver.setFont(Font.font("Abyssinica SIL",FontWeight.BOLD,FontPosture.REGULAR,400));  
	                                                gameOver.setFill(Color.WHITE);
	                                                gameOver.setText("Game Over"+"\r\n"+"You Lose");
	                                                soundGame.play();
	                                                drawBoard(player1);
	                                                layer.getChildren().addAll(gameOver);
	                                        }
	                                         
	                                     }
	                                     
	       
	                         }
	                         
	                       }
	                       drawBoard(player1);
	                       
	                    }
	                }
	                );
	                }
	            } 
	            
	        
	    }
	    public void checkHit2(Button[][] player2,Pane layer){
	        Media hitSound = new Media(new File("hit.mp3").toURI().toString());
	        MediaPlayer soundHit =new MediaPlayer(hitSound);
	                       
	        Media missSound = new Media(new File("miss.mp3").toURI().toString());
	        MediaPlayer soundMiss =new MediaPlayer(missSound);
	                       
	        Media gameSound = new Media(new File("gameOver.mp3").toURI().toString());
	        MediaPlayer soundGame =new MediaPlayer(gameSound);
	        for(int row=0;row<player2.length;row++){
	            for(int column =0; column<player2[row].length;column++){
	                final int R =row;
	                final int C =column;
	                
	                player2[row][column].setOnAction(new EventHandler<ActionEvent>(){
	                    public void handle( ActionEvent event){
	                        Button sank =new Button("SUNK");
	                        Button hitted=new Button("HIT");
	                        Button missed=new Button("MISS");
	                        Button fleetS =new Button("FLEET HAS SUNK");
	                        
	                        sank.setPrefSize(X/20,Y/20);
	                        sank.setLayoutX(0);
	                        sank.setLayoutY(Y/10);
	                        
	                        hitted.setPrefSize((X)/20,Y/20);
	                        hitted.setLayoutX(X/10);
	                        hitted.setLayoutY(Y/10);
	                        
	                        missed.setPrefSize(X/20,Y/20);
	                        missed.setLayoutX(2*X/10);
	                        missed.setLayoutY(Y/10);
	                        
	                        fleetS.setPrefSize(X/20,Y/20);
	                        fleetS.setLayoutX(3*X/10);
	                        fleetS.setLayoutY(Y/10);
	                        
	                        layer.getChildren().addAll(sank);
	                        layer.getChildren().addAll(hitted);
	                        layer.getChildren().addAll(missed);
	                        layer.getChildren().addAll(fleetS);
	                        
	                    
	                        sank.setOnAction(new EventHandler<ActionEvent>(){
	                        public void handle( ActionEvent event){  
	                           board[R][C] =SUNK;
	                           
	                           sank.setVisible(false);
	                           sank.setDisable(true);
	                           
	                           missed.setVisible(false);
	                           missed.setDisable(true);
	                                        
	                           hitted.setVisible(false);
	                           hitted.setDisable(true);
	                                        
	                           fleetS.setVisible(false);
	                           fleetS.setDisable(true);
	                           
	                           drawBoard(player2);
	                        }
	                    }
	                    );
	                        hitted.setOnAction(new EventHandler<ActionEvent>(){
	                        public void handle( ActionEvent event){ 
	                           board[R][C] =HIT;
	                           sank.setVisible(false);
	                           sank.setDisable(true);
	                           
	                           missed.setVisible(false);
	                           missed.setDisable(true);
	                                        
	                           hitted.setVisible(false);
	                           hitted.setDisable(true);
	                                        
	                           fleetS.setVisible(false);
	                           fleetS.setDisable(true);
	                           
	                           soundHit.play();
	                           
	                           drawBoard(player2);
	                        }
	                    }
	                    );
	                        missed.setOnAction(new EventHandler<ActionEvent>(){
	                        public void handle( ActionEvent event){  
	                           board[R][C] =MISS; 
	                            
	                           sank.setVisible(false);
	                           sank.setDisable(true);
	                           
	                           missed.setVisible(false);
	                           missed.setDisable(true);
	                                        
	                           hitted.setVisible(false);
	                           hitted.setDisable(true);
	                                        
	                           fleetS.setVisible(false);
	                           fleetS.setDisable(true);
	                           
	                           soundMiss.play();
	                           drawBoard(player2);
	                        }
	                    }
	                    );
	                        
	                    fleetS.setOnAction(new EventHandler<ActionEvent>(){
	                        public void handle( ActionEvent event){  
	                           sank.setVisible(false);
	                           sank.setDisable(true);
	                           
	                           missed.setVisible(false);
	                           missed.setDisable(true);
	                                        
	                           hitted.setVisible(false);
	                           hitted.setDisable(true);
	                                        
	                           fleetS.setVisible(false);
	                           fleetS.setDisable(true);
	                           
	                           Text gameOver =new Text(); 
	                           gameOver.setX(0);
	                           gameOver.setY(Y/3);
	                           gameOver.setFont(Font.font("Abyssinica SIL",FontWeight.BOLD,FontPosture.REGULAR,400));  
	                           gameOver.setFill(Color.WHITE);
	                           gameOver.setText("Game Over."+"\r\n"+"You WIN");
	                           soundGame.play();
	                           layer.getChildren().addAll(gameOver);
	                        } 
	                    }
	                    );
	                            
	                    
	                    }
	                }
	                );
	            }
	                                   
	        }
	        
	    }
	            
	    
	    
}
