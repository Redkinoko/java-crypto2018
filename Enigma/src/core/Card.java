/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author Red
 */
public class Card {
    
    private static String IMG_REP   = "ressources";
    private static String IMG_EXT   = ".gif";
    private static String AS        = "As";
    private static String VALET     = "Valet";
    private static String REINE     = "Reine";
    private static String ROI       = "Roi";
    private static String JOKER     = "Joker";
    
    public static int WIDTH  = 71;
    public static int HEIGHT = 96;
    
    private CardColor color;
    private int value;
    private Image img;
    
    public Card()
    {
        color   = null;
        value   = -1;
        img     = null;
    }
    
    public Card(CardColor color, int value)
    {
        this.color  = color;
        this.value  = value;
        this.img    = makeImage();
    }
    
    public URL getImagePath()
    {
        return this.getClass().getResource(
                "/" +
                IMG_REP +
                "/" +
                color.getPre() +
                this.value + 
                IMG_EXT
                );
    }
    
    private Image makeImage()
    {
        Image img = null;
        try 
        {
            img = ImageIO.read(this.getImagePath());
        } 
        catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return img;
    }

    public CardColor getColor() {
        return color;
    }

    public int getValue() {
        return value;
    }

    public Image getImg() {
        return img;
    }
    
    public String getCardName()
    {
        switch(this.value)
        {
            case 1:
                return AS;
            case 11:
                return VALET;
            case 12:
                return REINE;
            case 13:
                return ROI;
            case 14:
            case 15:
                return JOKER;
            default:
                return "" + this.value;
        }
    }
    
    @Override
    public String toString()
    {
        return (this.color.toString() + " " + getCardName());
    }
}
