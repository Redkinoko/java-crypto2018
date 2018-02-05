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
public class CardColor {
    
    private String name;
    private char pre;
    private Color color;
    
    public CardColor()
    {
        this.name   = "default";
        this.pre    = '\0';
        this.color  = Color.BLACK;
    }
    
    public CardColor(String name, char pre, Color color)
    {
        this.name   = name;
        this.pre    = pre;
        this.color  = color;
    }

    public String getName() {
        return name;
    }

    public char getPre() {
        return pre;
    }

    public Color getColor() {
        return color;
    }

}
