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
        CardColor club       = new CardColor("Trèfle",   'c', Color.BLACK).setIcon('♣');
        CardColor diamond    = new CardColor("Carreau",  'd', Color.RED).setIcon('♦').setValue(13);
        CardColor heart      = new CardColor("Coeur",    'h', Color.RED).setIcon('♥').setValue(26);
        CardColor spade      = new CardColor("Pique",    's', Color.BLACK).setIcon('♠').setValue(39);
        CardColor jokerRed   = new CardColor("Joker",    'j', Color.RED).setIcon('☺').setValue(53);
        CardColor jokerBlack = new CardColor("Joker",    'j', Color.BLACK).setIcon('☻').setValue(53);

        //GENERATION DES CARTES
        Cards cards = new Cards(54);
        cards.generate(club,    1, 13);
        cards.generate(diamond, 1, 13);        
        cards.generate(heart,   1, 13);
        cards.generate(spade,   1, 13);
        cards.generate(jokerBlack, 14, 14);
        cards.generate(jokerRed, 15, 15);
        cards.saveCurrentState();
        
        //CREATION DE L'ENCODEUR
        Encoder encoder = new Encoder(cards);
        
        //CREATION DU PANEL DE VISUALISATION DES CARTES
        CardViewer cw   = new CardViewer(cards);
        
        //CREATION DE L'APPLICATION PRINCIPALE
        App app = new App(encoder);
        app.setTitle("java-crypto2018 - M1 - 2017/2018 - Projet Cryptographie et jeu de cartes");
        app.setCardViewer(cw);
        app.setVisible(true);
    }

    
}
