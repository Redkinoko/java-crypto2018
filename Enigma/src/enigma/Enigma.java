/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigma;

import java.awt.Color;

/**
 *
 * @author Red
 */
public class Enigma {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        //CREATION DES COULEURS
        CardColor diamond  = new CardColor("Carreau",  'd', Color.RED);
        CardColor heart    = new CardColor("Coeur",    'h', Color.RED);
        CardColor club     = new CardColor("Trèfle",   'c', Color.BLACK);
        CardColor spade    = new CardColor("Pique",    's', Color.BLACK);

        //GENERATION DES CARTES
        Cards cards = new Cards(52);
        cards.generate(diamond, 1, 13);        
        cards.generate(heart,   1, 13);
        cards.generate(club,    1, 13);
        cards.generate(spade,   1, 13);
        
        cards.show();
    }
    
    private static void printf(String s)
    {
        System.out.print(s);
    }
    
}
