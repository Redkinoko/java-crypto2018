/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Color;

/**
 *
 * @author Red
 */
public class Encoder {
    
    private Cards cards;
    private boolean stepToStep;
    
    public Encoder(Cards cards)
    {
        this.cards = cards;
    }
    
    public String generateKey(int length)
    {
        cards.loadBackup();
        String key = "";
        for(int i=0 ; i<length ; i++)
        {
            key += intToUpperCaseChar(cards.nextKey());
        }
        
        return key;
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
    
    public void decodeSeed(String seed)
    {
        int val = 0;
        for(int i=0 ; i<seed.length() ; i++)
        {
            val = this.charToInt(seed.charAt(i));
            val = (val>53)?val-1:val;
            //System.out.println(val);
        }
    }
}
