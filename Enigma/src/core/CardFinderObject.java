/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author Red
 */
public class CardFinderObject {
    
    private Card card;
    private int index;
    
    public CardFinderObject(Card card, int i)
    {
        this.card   = card;
        this.index  = i;
    }

    public Card getCard() {
        return card;
    }

    public int getIndex() {
        return index;
    }
}
