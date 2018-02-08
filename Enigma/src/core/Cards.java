/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author Red
 */
public class Cards {
    private int count;
    private int max;
    private Card[] cards;
    
    public Cards()
    {
        this.cards   = null;
        this.count   = 0;
        this.max     = 0;
    }
    
    public Cards(int max)
    {
        this.max    = max;
        this.count  = 0;
        this.cards = new Card[max];
    }
    
    public Card get(int i)
    {
        return this.cards[i];
    }
    
    public void add(Card card)
    {
        if(this.count < this.max)
        {
            this.cards[this.count] = card;
            this.count++;
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
        for(int i=0 ; i<this.count ; i++)
        {
            System.out.println(this.cards[i].toString());
        }
        System.out.println("Il y a " + this.count + " Cartes.");
    }
    
    public int count()
    {
        return this.count;
    }
    
    public void switchCards(int i, int j)
    {
        if(i >= 0 && i < this.count && j >= 0 && j < this.count)
        {
            Card c = this.cards[j];
            this.cards[j] = this.cards[i];
            this.cards[i] = c;
        }
    }
    
    public void moveTo(int i, int j)
    {
        if(i >= 0 && i < this.count && j >= 0 && j < this.count)
        {
            if(i > j)
            {
                for(int k=i ; k>j ; k--)
                {
                    switchCards(k-1, k);
                }
            }
            else if (i < j)
            {
                for(int k=i ; k<j ; k++)
                {
                    switchCards(k, k+1);
                }
            }
        }
    }
    
    public void pushDown(int i)
    {
        //si moins de 2 cartes
        if(i == (this.count-1))
        {
            moveTo(i, 1);
        }
        else
        {
            switchCards(i, i+1);
        }
    }
    
    public void mix()
    {
        int max = this.count();
        int min = 0;
        
        for(int i=0 ; i<this.count() ; i++)
        {
            int rnd = (int)(Math.random()*(max-min) + min);
            this.switchCards(i, rnd);
        }
    }
    
    public int getBlackJokerIndex()
    {
        CardFinderObject bj = new CardFinder(this)
            .findByCardName("Joker")
            .findByColor(Color.BLACK)
            .getOne()
        ;
        return (bj != null)?bj.getIndex():-1;
    }
}
