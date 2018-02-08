/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import core.Card;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author Red
 */
public class CardPanel extends javax.swing.JPanel {

    private Card card;
    /**
     * Creates new form CardPanel
     */
    public CardPanel() {
        initComponents();
    }
    
    public CardPanel(Card card) {
        initComponents();
        this.card = card;
        Dimension d = new Dimension(Card.WIDTH, Card.HEIGHT);
        this.setPreferredSize(d);
        this.setMinimumSize(d);

    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Image img = card.getImg();
        int x = (this.getWidth()  - img.getWidth(this))/2;
        int y = (this.getHeight() - img.getHeight(this))/2;

        g.drawImage(card.getImg(), x, y, this);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setMinimumSize(new java.awt.Dimension(1, 1));
        setPreferredSize(new java.awt.Dimension(1, 1));
        setLayout(new java.awt.GridLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
