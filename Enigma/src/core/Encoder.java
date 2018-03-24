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
public class Encoder {
    
    private Cards cards;
    
    public Encoder(Cards cards)
    {
        this.cards      = cards;
    }
    
    public String generateKey(int length)
    {
        String key = "";
        cards.saveCurrentState();
        for(int i=0 ; i<length ; i++)
        {
            key += cards.nextKeyToChar();
        }
        cards.loadBackup();
        return key;
    }
    
    public int charToInt(char c)
    {
        return 1+(int)('z'-c);
    }
    
    public char intToChar(int i)
    {
        return (char)('a'+(i-1));
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
            j = charToInt(message.charAt(i)) + charToInt(key.charAt(i));
            if(j > 26)
            {
                j = j - 26;
            }
            secret += intToChar(j);
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
            j  = charToInt(secret.charAt(i)) - charToInt(key.charAt(i));
            if(j < 0)
            {
                j = j + 26;
            }
            System.out.println(""+j);
            message += intToChar(j);
        }

        return message;
    }
}
