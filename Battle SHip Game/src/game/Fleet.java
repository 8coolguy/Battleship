package game;




public class Fleet {
    Ship[] playerFleet =new Ship[5];
    public Fleet(Ship[] inFleet){
        for(int i =0;i<playerFleet.length;i++){
            playerFleet[i] =inFleet[i];
        }
     }
    public String toString(){
        String state ="Fleet:" +"\n";
        for(int i =0;i<playerFleet.length;i++){
            state=state + playerFleet[i].toString();
        }
        return state;
    }
    public Ship getShipAt(int index){
        return playerFleet[index];
    }
    public int size(){
        return playerFleet.length;
    }
    public boolean winCondition(){
        for(int i =0;i<playerFleet.length;i++){
            if(playerFleet[i].getSunk() ==false)
                return false;      
            }
        return true;
        }
}
