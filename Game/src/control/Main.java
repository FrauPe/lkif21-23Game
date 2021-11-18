/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.*;

/**
 *
 * @author KS
 */
public class Main {
    private Cards selectedCard;
    private int team;
    private boolean yourTurn;
    private Character selectedCharacter;
    private Character[] Characters;
    private Tile[][] map;
    private ArrayList<Tile> selectedTiles;
    
    public boolean playCard(Cards card, Character c, ArrayList<Tile> positions)
    {
         return card.action(c, positions);
    }
    
    public boolean playCurrentCard()
    {
        if(selectedCard!=null)
            return selectedCard.action(selectedCharacter, selectedTiles)
    }
}
