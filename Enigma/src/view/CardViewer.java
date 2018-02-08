/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import core.Card;
import core.Cards;
import java.awt.Component;
import java.awt.Dimension;

/**
 *
 * @author Red
 */
public class CardViewer extends javax.swing.JPanel {

    private Cards cards;
    private int rows;
    private int cols;
    private int rowsFactor = 14;
    private int colsFactor = 40;
    /**
     * Creates new form CardViewer
     */
    public CardViewer() {
        initComponents();
    }
    public CardViewer(Cards cards) {
        initComponents();
        this.cards = cards;
        this.rows = 1;
        this.cols = 1;
        init();
    }
    
    private void init()
    {
        int size = this.cols * this.rows;
        for(int i=0 ; i<cards.count() ; i++)
        {
            this.add(cards.get(i).getCardPanel());
        }
    }
    
    private void updateRows()
    {
        this.rows = this.getWidth()/ (this.rowsFactor + Card.WIDTH);
    }
    
    private void updateCols()
    {
        this.cols = 1 +this.getHeight()/ (this.colsFactor + Card.HEIGHT);
    }
    
    private void showCards()
    {
        int count = (this.cols * this.rows);
        int size = (count>cards.count())?cards.count():count;
        for(int i=0 ; i<size ; i++)
        {
            CardPanel cp = cards.get(i).getCardPanel();
            cp.setVisible(i < size);
        }
    }
    
    private void update()
    {
        this.updateRows();
        this.updateCols();
        this.showCards();
    }
    
    @Override
    public Component add(Component comp)
    {
        super.add(comp);
        int w = (rowsFactor + comp.getPreferredSize().width) * cols;//171
        int h = (colsFactor + comp.getPreferredSize().height) * rows;//144

        Dimension d = new Dimension(w, h);
        this.setPreferredSize(d);
        //this.setMinimumSize(d);
        
        return comp;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        this.update();
    }//GEN-LAST:event_formComponentResized


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
