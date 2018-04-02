/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Red
 */
public class Encoder {
    
    private Cards cards;
    private int msgLength;
    private List<Step> steps;
    private List<Integer> nbSteps;
    
    public Encoder(Cards cards)
    {
        this.cards  = cards;
        steps       = new ArrayList<Step>();
        nbSteps     = new ArrayList<Integer>();
        msgLength   = 0;
    }
    
    public List<Step> getSteps()
    {
        return steps;
    }
    
    public int getNbStepsSize()
    {
        return nbSteps.size();
    }
    
    public int getNbSteps(int n)
    {
        return (nbSteps.size() > 0 && n >= 0)?nbSteps.get(n):0;
    }
    
    public String getSeed(int c, int n)
    {
        int ec = 0;
        for(int j=0 ; j<c ; j++)
        {
            ec += getNbSteps(j);
        }
        int i = n + (ec);
        //System.out.println(i);
        return steps.get(i).getSeed();
    }
    
    public int getTotalNbSteps()
    {
        int cmpt = 0;
        for(int i=0 ; i<nbSteps.size() ; i++)
        {
            cmpt += nbSteps.get(i);
        }
        return cmpt;
    }
    
    public int getMsgLength()
    {
        return msgLength;
    }
    
    public int charToInt(char c)
    {
        return 1+(int)(c - 'A');
    }
    
    public int upperCaseCharToInt(char c)
    {
        return 1+(int)(Character.toUpperCase(c) - 'A');
    }
    
    public char intToUpperCaseChar(int i)
    {
        return (char)('A'+(i-1));
    }
    
    public void addStep(int n, int c)
    {
        steps.add(new Step(n, c, generateSeed()));
        if(nbSteps.size() <= c)
        {
            nbSteps.add(1);
        }
        else
        {
            int v = nbSteps.get(c);
            nbSteps.set(c, v+1);
        }
    }
    
    public int getNextKey(int n)
    {
        int pre = 0;
        do
        {
            cards.stepOne();
            addStep(1, n);
            cards.stepTwo();
            addStep(2, n);
            cards.stepThree();
            addStep(3, n);
            cards.stepFour();
            addStep(4, n);
            pre = cards.preKey();
        } while(cards.preKeyIsNotValid(pre));
        return cards.manualKey(pre);
    }
    
    public String generateKey(int length)
    {
        msgLength   = length;
        nbSteps.clear();
        steps.clear();
        cards.loadBackup();
        String key = "";
        for(int i=0 ; i<length ; i++)
        {
            key += intToUpperCaseChar(getNextKey(i));
        }
        return key;
    }
    
    public String encrypt(String message)
    {
        int length = message.length();
        
        //On génère une clef
        String key = generateKey(length);

        //On l'utilise
        String secret = "";
        int j = 0;
        for(int i=0 ; i<length ; i++)
        {
            j = upperCaseCharToInt(message.charAt(i)) + upperCaseCharToInt(key.charAt(i));
            if(j > 26)
            {
                j = j - 26;
            }
            secret += intToUpperCaseChar(j);
        }

        return secret;
    }
    
    public String decrypt(String secret)
    {
        int length = secret.length();
        
        //On génère une clef
        String key = generateKey(length);

        //On l'utilise
        String message = "";
        int j = 0;
        for(int i=0 ; i<length ; i++)
        {
            j  = upperCaseCharToInt(secret.charAt(i)) - upperCaseCharToInt(key.charAt(i));
            if(j < 1)
            {
                j = j + 26;
            }
            message += intToUpperCaseChar(j);
        }

        return message;
    }
    
    public String generateSeed()
    {
        String out = "";
        int val = 0;
        for(int i=0 ; i<cards.count() ; i++)
        {
            val = cards.get(i).getTotalValue();
            if(val == 53)
            {
                val = (cards.get(i).getColor().getColor().equals(Color.RED))?val+1:val;
            }
            out += this.intToUpperCaseChar(val);
        }
        return out;
        
    }

    public boolean validateSeed(String seed)
    {
        if(seed.length() != cards.count())
        {
            return false;
        }
        int val = 0;
        for(int i=0 ; i<seed.length() ; i++)
        {
            val = this.charToInt(seed.charAt(i));
            if(val < 1 || val > 54)
            {
                return false;
            }
        }
        return true;
    }
    
    public void decodeSeed(String seed)
    {
        int index = 0;
        Card[] cards = new Card[seed.length()];
        
        int val = 0;
        for(int i=0 ; i<seed.length() ; i++)
        {
            val = this.charToInt(seed.charAt(i));
            if(val >= 1 && val <= 54 && i<seed.length())
            {
                if(val%54 == 0)
                {
                    cards[index] = new Card(CardColor.jokerRed, 15);
                }
                else if(val%53 == 0)
                {
                    cards[index] = new Card(CardColor.jokerBlack, 14);
                }
                else if(val/40 == 1)
                {
                    cards[index] = new Card(CardColor.spade, (1+(val%40)));
                }
                else if(val/27 == 1)
                {
                    cards[index] = new Card(CardColor.heart, (1+(val%27)));
                }
                else if(val/14 == 1)
                {
                    cards[index] = new Card(CardColor.diamond, (1+(val%14)));
                }
                else 
                {
                    cards[index] = new Card(CardColor.club, val);
                }
                index++;
            }
        }
        this.cards.setCards(cards);
    }
}
