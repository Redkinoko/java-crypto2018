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
public class Cards {
    private int index;
    private int max;
    private Card[] cards;
    
    public Cards()
    {
        this.cards   = null;
        this.index   = 0;
        this.max     = 0;
    }
    
    public Cards(int max)
    {
        this.max    = max;
        this.index  = 0;
        this.cards = new Card[max];
    }
    
    public Card get(int i)
    {
        return this.cards[i];
    }
    
    public void add(Card card)
    {
        if(this.index < this.max)
        {
            this.cards[this.index] = card;
            this.index++;
        }
    }
    
    public void generate(CardColor color, int min, int max)
    {
        for(int i=min ; i<=max ; i++)
        {
            this.add(new Card(color, i));
        }
    }
    
    public void show()
    {
        for(int i=0 ; i<this.index ; i++)
        {
            System.out.println(this.cards[i].toString());
        }
    }
}
