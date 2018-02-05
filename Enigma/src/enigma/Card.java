/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigma;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author Red
 */
public class Card {
    
    private static String IMG_REP = "ressources";
    private static String IMG_EXT = ".gif";
    
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
    
    @Override
    public String toString()
    {
        return (this.value + " - " + this.color.getName());
    }
}
