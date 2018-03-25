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
public class CardColor {
    
    private String name;
    private char pre;
    private Color color;
    private char icon;
    private int value;
    
    public CardColor()
    {
        this.name   = "default";
        this.pre    = '\0';
        this.color  = Color.BLACK;
        this.icon   = '☻';
        this.value  = 0;
    }
    
    public CardColor(String name, char pre, Color color)
    {
        this.name   = name;
        this.pre    = pre;
        this.color  = color;
        this.icon   = ' ';
        this.value  = 0;
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

    public char getIcon() {
        return icon;
    }

    public CardColor setIcon(char icon) {
        this.icon = icon;
        return this;
    }
    
    public int getValue() {
        return value;
    }

    public CardColor setValue(int value) {
        this.value = value;
        return this;
    }
    
    @Override
    public String toString()
    {
        return (this.icon == ' ')?this.name:("" + this.icon);
    }
    
    @Override
    public boolean equals(Object ob)
    {
        CardColor cc = (CardColor)ob;
        return (cc != null)?equals(cc):false;
    }
    
    public boolean equals(CardColor cc)
    {
        return (cc!= null &&
                color == cc.color && 
                value == cc.value && 
                name.toLowerCase().equals(cc.name.toLowerCase())
        );
    }
}
