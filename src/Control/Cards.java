/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

/**
 *
 * @author q1.02lingmann
 */
public abstract class Cards {
    private int costs;
    private String name;
    private String description;
    private int tileCount;
    
    public abstract boolean action();
    
    public boolean move(Character c, Tile t, int range){
        Tile tile = c.getPosition();
        
        //Tile in range?
        int x;
        if((x = tile.getX()-t.getX()) < 0){
            x*=-1;
        }
        int y;
        if((y = tile.getY()-t.getY()) < 0){
            y*=-1;
        }
        if(x+y > range){
            return false;
        }
        
        //something on tile?
        if(t.getContent() != null){
            if(t.getContent() instanceof Obstacle){
                return false;
            }
            else{
                return false;
            }
        }
        
        //move c to t animation maybe
        Main.map[tile.getX()][tile.getY()].setContent(null);
        Main.map[t.getX()][t.getY()].setContent(c);
        return true;
    }
    
    public boolean attack(Character c, Tile t, int range, int value){
        Tile tile = c.getPosition();
        
        //Tile in range?
        int x;
        if((x = tile.getX()-t.getX()) < 0){
            x*=-1;
        }
        int y;
        if((y = tile.getY()-t.getY()) < 0){
            y*=-1;
        }
        if(x+y > range){
            return false;
        }
        
        //something on tile?
        if(!t.getContent() != null){
            return false;
        }
        
        //gleiches Team?
        if(t.getContent() instanceof Character){
            if(t.getContent().getTeam() != c.getTeam()){
                return false;
            }
        }

        //Tod abfragen
        if(t.getContent().getHealth() - value <= 0){
            t.setContent(null);
        }
        else{
            t.getContent().setHealth(t.getContent().getHealth() - value);
        }
        return true;
    }
    
    public boolean heal(Character c, Tile t, int range, int value){
        Tile tile = c.getPosition();
        
        //Tile in range?
        int x;
        if((x = tile.getX()-t.getX()) < 0){
            x*=-1;
        }
        int y;
        if((y = tile.getY()-t.getY()) < 0){
            y*=-1;
        }
        if(x+y > range){
            return false;
        }
        
        //something on tile?
        if(!t.getContent() instanceof Character){
            return false;
        }
        
        if(t.getContent().getTeam() == c.getTeam()){//nur eigenes Team
            if(t.getContent().getHealth() + value > t.getContent().getMaxHealth()){//kein overheal
                if(t.getContent().getMaxHealth() > t.getContent().getHealth()){
                    t.getContent().setHealth(t.getContent().getMaxHealth());
                }
            }
            else{
                t.getContent().setHealth(t.getContent().getHealth() + value);
            }
            return true;
        }
        return false;
    }
    
    public int getCosts(){
        return costs;
    }
    
    public String getDescription(){
        return description;
    }
    
    public String getName(){
        return name;
    }
    
    public int getTileCount(){
        return tileCount;
    }
}
