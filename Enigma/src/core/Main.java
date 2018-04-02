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

    public static void main(String[] args)
    {
        //GENERATION DES CARTES
        Cards cards = new Cards(54);
        cards.generate(CardColor.club,    1, 13);
        cards.generate(CardColor.diamond, 1, 13);        
        cards.generate(CardColor.heart,   1, 13);
        cards.generate(CardColor.spade,   1, 13);
        cards.generate(CardColor.jokerBlack, 14, 14);
        cards.generate(CardColor.jokerRed, 15, 15);
        cards.saveCurrentState();
        
        //CREATION DE L'ENCODEUR
        Encoder encoder = new Encoder(cards);

        //CREATION DU PANEL DE VISUALISATION DES CARTES
        CardViewer cw   = new CardViewer(cards);
        
        //CREATION DE L'APPLICATION PRINCIPALE
        App app = new App(cards, encoder, cw);
        app.setTitle("java-crypto2018 - M1 - 2017/2018 - Projet Cryptographie et jeu de cartes");
        app.setVisible(true);
        
        //Test.justDoIt();
    }

    
}
