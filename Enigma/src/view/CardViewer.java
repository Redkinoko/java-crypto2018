/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import core.Card;
import core.Cards;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Red
 */
public class CardViewer extends javax.swing.JPanel {

    private Cards cards;
    private TitledBorder borderNbPages;
    
    private int rows;
    private int cols;
    private int index;
    private int marginTop;
    private int marginBottom;
    private int marginLeft;
    private int marginRight;
    private int originX;
    private int originY;
    private int maxX;
    private int maxY;
    private Point[] coords;
    
    private boolean mouseSelect;
    private int mouseSelectX;
    private int mouseSelectY;
    private int cardSelected;
    /**
     * Creates new form CardViewer
     */
    public CardViewer() {
        initComponents();
    }
    public CardViewer(Cards cards) {
        initComponents();
        this.cards      = cards;
        rows       = 1;
        cols       = 1;
        index      = 0;
        marginTop       = 1;
        marginBottom    = 13;
        marginLeft      = 1;
        marginRight     = 1;
        maxX            = 0;
        maxY            = 0;
        int w = (marginLeft + Card.WIDTH + marginRight)*6;
        int h = marginTop+126+marginBottom;
        this.jPanelCenter.setPreferredSize(new Dimension(w, h));
        this.jPanelCenter.setMinimumSize(new Dimension(w, h));
        coords = new Point[cards.count()];
        for(int i=0 ; i<cards.count() ; i++)
        {
            coords[i] = new Point(0,0);
        }
        mouseSelect     = false;
        mouseSelectX    = 0;
        mouseSelectY    = 0;
        cardSelected    = -1;
        
        borderNbPages = new TitledBorder("");
        borderNbPages.setTitleJustification(TitledBorder.CENTER);
        borderNbPages.setTitlePosition(TitledBorder.TOP);
    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        
        this.rows = this.jPanelCenter.getWidth()/ (marginLeft + Card.WIDTH + marginRight);
        this.cols = this.jPanelCenter.getHeight()/ (marginTop + Card.HEIGHT + marginBottom);
        if((this.rows*this.cols) >= this.cards.count())
        {
            index = 0;
        }
        originX = 5 + this.jPanelCenter.getX();
        originY = 2 + this.jPanelCenter.getY();
        maxX = (this.cols>1)?this.cols:this.rows;
        maxY = (this.cols>1)?this.rows:this.cols;
        
        int k = index;
        int x = 0;
        int y = 0;
        for(int i=0 ; i <maxX  ; i++)
        {
            for(int j=0 ; j<maxY ; j++)
            {
                x = originX + (i * (marginLeft + Card.WIDTH + marginRight));
                y = originY + (j * (marginTop + Card.HEIGHT + marginBottom));
                if(this.cols >1)
                {
                    x = originX + (j * (marginLeft + Card.WIDTH + marginRight));
                    y = originY + (i * (marginTop + Card.HEIGHT + marginBottom));
                }
                if(k < this.cards.count())
                {
                    //Afficher la carte n°k
                    drawCard(g, k, x, y);
                    
                    //Afficher le cadre de légende
                    drawCardDesc(g, k, x, y+Card.HEIGHT);
                    
                    if(cardSelected > -1 && cardSelected == k)
                    {
                        g.setColor(Color.GREEN);
                        g.drawRect(x, y, Card.WIDTH, Card.HEIGHT);
                    }
                    
                    coords[k].setLocation(x, y);
                }
                k++;
            }
        }
        //Cacher les boutons de changement de page si toutes les cartes sont affichées
        drawNbPageIndicator();
        showPanels();
    }
    
    public void drawCard(Graphics g, int k, int x, int y)
    {
        g.drawImage(cards.get(k).getImg(), x, y, this);
    }
    
    public void drawCardDesc(Graphics g, int k, int x, int y)
    {
        //Afficher la bordure de fond
        drawCardDescBorder(g, x, y);
        //Afficher l'index de la carte n°k dans le paquet
        drawCardNb(g, k, x+8, y+11);
        //Afficher la valeur d'une carte utilisée pour l'encodage
        drawCardValue(g, k, 10+x+32, y+11);
    }
    
    public void drawCardDescBorder(Graphics g, int x, int y)
    {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, Card.WIDTH, 12);
        g.setColor(Color.BLACK);
        g.drawRoundRect(x, y, Card.WIDTH, 12, 2, 2);
    }
    
    public void drawCardNb(Graphics g, int k, int x, int y)
    {
        int n = (k+1);
        String text1 = (n<10)?("0" + n):("" + n);
        String text2 = "/" + this.cards.count();
        
        g.setColor(Color.RED);
        g.drawString(text1,x,y);
        g.setColor(Color.BLACK);
        g.drawString(text2, x+14, y);
    }
    
    public void drawCardValue(Graphics g, int k, int x, int y)
    {
        int n = this.cards.get(k).getTotalValue();
        String text1 = "v:";
        String text2 = (n<10)?("0" + n):("" + n);
        
        g.setColor(Color.BLACK);
        g.drawString(text1, x, y);
        g.setColor(Color.RED);
        g.drawString(text2, x+8, y);
    }
    
    public void drawNbPageIndicator()
    {
        if(this.cols > 0 && this.rows > 0)
        {
            int nbPages   = 1+(this.index/this.rows);
            int maxPages  = 1+(this.cards.count()/this.rows);
            
            this.borderNbPages.setTitle("page: " + nbPages + "/" + maxPages);
        }
    }
    
    public void showPanels()
    {
        boolean b = !((this.cols * this.rows) >= this.cards.count());
        this.jPanelWest.setVisible(b);            
        this.jPanelEast.setVisible(b);
        if(b)
        {
            this.setBorder(borderNbPages);
        }
        else
        {
            this.setBorder(null);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelCenter = new javax.swing.JPanel();
        jPanelWest = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanelEast = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        setLayout(new java.awt.BorderLayout());

        jPanelCenter.setMinimumSize(new java.awt.Dimension(10, 97));
        jPanelCenter.setPreferredSize(new java.awt.Dimension(100, 97));
        jPanelCenter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelCenterMouseClicked(evt);
            }
        });
        jPanelCenter.setLayout(new java.awt.BorderLayout());
        add(jPanelCenter, java.awt.BorderLayout.CENTER);

        jPanelWest.setLayout(new java.awt.CardLayout());

        jButton1.setText("<");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanelWest.add(jButton1, "card2");

        add(jPanelWest, java.awt.BorderLayout.WEST);

        jPanelEast.setLayout(new java.awt.CardLayout());

        jButton2.setText(">");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanelEast.add(jButton2, "card2");

        add(jPanelEast, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        //System.out.println(this.jPanel1.getWidth() + " " + this.jPanel1.getHeight());
    }//GEN-LAST:event_formComponentResized

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(this.index < (this.cards.count()-this.rows))
        {
            this.index += this.rows;
        }
        else if(this.index < (this.cards.count()-1))
        {
            this.index += 1;
        }
        this.repaint();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(this.index >= this.rows)
        {
            this.index -= this.rows;
        }
        else if(this.index > 0)
        {
            this.index -= 1;
        }
        
        this.repaint();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jPanelCenterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelCenterMouseClicked
        int x = 0;
        int y = 0;
        int i = index;
        mouseSelectX = evt.getX() + this.originX;
        mouseSelectY = evt.getY() + this.originY;
        mouseSelect = true;

        while(mouseSelect && i<cards.count())
        {
            x = coords[i].x;
            y = coords[i].y;
            if(mouseSelectX >= x && mouseSelectX <= (x+Card.WIDTH) &&
               mouseSelectY >= y && mouseSelectY <=(y+Card.HEIGHT))
            {
                if(cardSelected == i)
                {
                    cardSelected = -1;
                }
                else if(cardSelected > -1)
                {
                    cards.switchCards(cardSelected, i);
                    cardSelected = -1;
                    cards.saveCurrentState();
                }
                else
                {
                    cardSelected = i;
                }
                mouseSelect = false;
            }
            i++;
        }
        repaint();
    }//GEN-LAST:event_jPanelCenterMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanelCenter;
    private javax.swing.JPanel jPanelEast;
    private javax.swing.JPanel jPanelWest;
    // End of variables declaration//GEN-END:variables
}
