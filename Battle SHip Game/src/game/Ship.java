package game;

public class Ship {
	  //private char[][] board = new char[ROWS][COLUMNS];
	   
	   private String name;     //intance variables of the 
	   private int length;      
	   private int row;
	   private int column;
	   private char direction;
	   private boolean sunk =false;
	   private boolean placed =false;
	   
	  
	   public Ship(String name, int length){
	       this.name =name;
	       this.length=length;
	   }
	   
	   //accesors and getters 
	   public int getLength() {
	        return length;
	   }
	   public String getName(){
	      return this.name; 
	    }
	   public int getRow() {
	        return row;
	   }
	    
	   public int getColumn() {
	        return column;
	   }
	    
	   public char getDirection() {
	        return direction;
	   }
	    
	   public boolean getSunk(){
	        return sunk;
	   }
	    
	   public boolean getPlaced(){
	        return placed;
	   }
	    
	   public void setSunk(boolean status){
	        sunk = status;
	   }
	    
	   public void setLocation(int rw, int col, char dir){
	        row = rw;
	        column = col;
	        direction = dir;
	        placed = true;
	    }

}
