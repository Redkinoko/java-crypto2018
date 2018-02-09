/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Color;
import java.awt.Dimension;
import view.App;
import view.CardViewer;

/**
 *
 * @author Red
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        //CREATION DES COULEURS
        CardColor diamond    = new CardColor("Carreau",  'd', Color.RED).setIcon('♦');
        CardColor heart      = new CardColor("Coeur",    'h', Color.RED).setIcon('♥');
        CardColor jokerRed   = new CardColor("Joker",    'j', Color.RED).setIcon('☺');
        CardColor club       = new CardColor("Trèfle",   'c', Color.BLACK).setIcon('♣');
        CardColor spade      = new CardColor("Pique",    's', Color.BLACK).setIcon('♠');
        CardColor jokerBlack = new CardColor("Joker",    'j', Color.BLACK).setIcon('☻');

        //GENERATION DES CARTES
        Cards cards = new Cards(54);
        cards.generate(diamond, 1, 13);        
        cards.generate(heart,   1, 13);
        cards.generate(club,    1, 13);
        cards.generate(spade,   1, 13);
        cards.generate(jokerBlack, 14, 14);
        cards.generate(jokerRed, 15, 15);
        
        //AFFICHAGE DU DECK
        //cards.show();
        
        App app = new App();
        app.setTitle("java-crypto2018 - M1 - 2017/2018 - Projet Cryptographie et jeu de cartes");

        CardViewer cw = new CardViewer(cards);
        app.addToJPanel1(cw);
        
        int bj = cards.getBlackJokerIndex();
        cards.pushDown(bj, 2);
        //cards.mix();
        cw.repaint();
        printf("Le joker noir est à la position " + (cards.getBlackJokerIndex()+1) + "/" + (cards.count()) + ".\n");
        app.setVisible(true);
    }
    
    private static void printf(String s)
    {
        System.out.print(s);
    }
    
}
