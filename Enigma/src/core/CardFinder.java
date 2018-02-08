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
public class CardFinder {
    
    private Cards cards;
    private List<CardFinderObject> list;
    
    public CardFinder(Cards cards)
    {
        this.cards = cards;
        list = new ArrayList<CardFinderObject>();
        reset();
    }
    
    public void reset()
    {
        list.clear();
        for(int i=0 ; i<this.cards.count() ; i++)
        {
            list.add(new CardFinderObject(this.cards.get(i), i));
        }
    }
    
    public CardFinder findByColor(Color color)
    {
        int i = 0;
        while(i < list.size())
        {
            if(this.list.get(i).getCard().getColor().getColor() != color)
            {
                list.remove(i);
                i=0;
            }
            else
            {
                i++;
            }
        }
        
        return this;
    }
    
    public CardFinder findByCardName(String name)
    {
        int i = 0;
        while(i < list.size())
        {
            if(!(this.list.get(i).getCard().getCardName()).toLowerCase().equals(name.toLowerCase()))
            {
                this.list.remove(i);
                i=0;
            }
            else
            {
                i++;
            }
        }
        
        return this;
    }
    
    public CardFinderObject[] get()
    {
        return (CardFinderObject[])(this.list.toArray());
    }
    
    public int count()
    {
        return this.list.size();
    }
    
    public CardFinderObject getOne()
    {
        if(list.size() > 0)
        {
            return list.get(0);
        }
        return null;
    }
}
