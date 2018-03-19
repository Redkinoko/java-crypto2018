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
    
    /*
    
    */
    public void generate(CardColor color, int min, int max)
    {
        for(int i=min ; i<=max ; i++)
        {
            this.add(new Card(color, i));
        }
    }
    
    /*
    
    */
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
    
    /*
    
    */
    public void switchCards(int i, int j)
    {
        if(i >= 0 && i < this.count && j >= 0 && j < this.count)
        {
            Card c = this.cards[j];
            this.cards[j] = this.cards[i];
            this.cards[i] = c;
        }
    }
    
    /*
    
    */
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
    
    /*
    
    */
    public void pushDown(int i, int j)
    {
        //si moins de 2 cartes
        if(i >= (this.count-j))
        {
            moveTo(i, j);
        }
        else
        {
            switchCards(i, i+j);
        }
    }
    
    /*
    Mélange les cartes de façon aléatoire
    */
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
    
    /*
    Retourne un entier qui est le numéro d'index du Joker Noir
    */
    public int getBlackJokerIndex()
    {
        CardFinderObject bj = new CardFinder(this)
            .findByCardName("Joker")
            .findByColor(Color.BLACK)
            .getOne()
        ;
        return (bj != null)?bj.getIndex():-1;
    }
    
    /*
    Retourne un entier qui est le numéro d'index du Joker Rouge
    */
    public int getRedJokerIndex()
    {
        CardFinderObject bj = new CardFinder(this)
            .findByCardName("Joker")
            .findByColor(Color.RED)
            .getOne()
        ;
        return (bj != null)?bj.getIndex():-1;
    }
    
    /*
    Méthode qui récupère la position des deux jokers et les utilise pour appeler la fonction de double coupe
    */
    public void generateJokerCut()
    {
        int B = this.getBlackJokerIndex();
        int R = this.getRedJokerIndex();
        
        if(B >= R)
        {
            this.jokerCut(R,B);
        }
        else
        {
            this.jokerCut(B,R);
        }
    }
    
    /*
    Méthode effectuant une double coupe du paquet par rapport aux positions des deux jokers.
    Les cartes au-dessus du premier joker passent sous le second et inversement.
    Le premier est celui arrivant en premier lors du parcours des cartes.
    */
    private void jokerCut(int a, int b)
    {
        Card[] res = new Card[max];
        int ind = 0;
        for(int i=0 ; i<max-b ; i++)
        {
            if(ind<max)
            {
                res[ind] = (this.cards[b+i]);
                ind++;
            }
        }
        for(int i=0 ; i<b-a+1 ; i++)
        {
            if(ind<max)
            {
                res[ind] = (this.cards[a+i-1]);
                ind++;
            }
        }
        for(int i=0 ; i<a-1 ; i++)
        {
            if(ind<max)
            {
                res[ind] = (this.cards[i]);
                ind++;
            }
        }
        
        this.cards = res;
    }
    
    /*
    Coupe par rapport à la valeur de la dernière carte.
    On déplace le nombre de cartes désignées, depuis le dessus du paquet, par la valeur de la dernière carte au dessus de cette dernière
    */
    public void cutFromLast()
    {
        Card[] res = new Card[max];
        int nb = this.get(max).getTotalValue();
        
        for(int i=nb;i<max-1;i++)
        {
            res[i-nb]=this.cards[i];
        }
        for(int i=0;i<nb;i++)
        {
            res[i+nb]=this.cards[i];
        }
        res[max]=this.cards[max];
        
        this.cards = res;
    }
    
    /*
    Fonction retournant une valeur de clef pour le chiffrement
    Utilise la valeur de la première carte pour aller chercher une autre carte
    dans le paquet dont la valeur modulo 26 sera le caractère utilisé.
    Rencontrer un Joker relance les opérations précédentes sans donner de valeur
    */
    public int nextKey()
    {
        useSteps();
        
        int first = this.cards[0].getTotalValue();
        int preKey = this.cards[first].getTotalValue();
        
        if(preKey == 53)
        {
            nextKey();
        }
        else
        {
            return preKey%26;
        }
        
        return -1;
    }
    
    public void useSteps()
    {
        mix();
        int indJ = getBlackJokerIndex();
        pushDown(indJ, 1);
        indJ = getRedJokerIndex();
        pushDown(indJ,2);
        generateJokerCut();
        cutFromLast();
    }
}
