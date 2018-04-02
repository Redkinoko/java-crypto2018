/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import core.Cards;
import core.Encoder;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.System.exit;
import javax.swing.border.TitledBorder;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;

/**
 *
 * @author Red
 */
public class App extends javax.swing.JFrame {
    
    private Cards cards;
    private Encoder encoder;
    private CardViewer cardViewer;
    private TitledBorder messageBorder;
    private int currentChar;
    private int currentStep;

    public App(Cards cards, Encoder encoder, CardViewer cv) {
        initComponents();
        this.cards      = cards;
        this.encoder    = encoder;
        cardViewer = cv;
        setCardViewer(cv);
        messageBorder = new TitledBorder("MODE PAS A PAS");
        messageBorder.setTitleJustification(TitledBorder.CENTER);
        messageBorder.setTitlePosition(TitledBorder.TOP);
        jPanelSteps.setVisible(false);
        currentChar = 0;
        currentStep = 0;
    }
    
    private void setCardViewer(CardViewer cv)
    {
        this.cardViewer = cv;
        this.jPanelCenter.add(cv);
        int w = (this.getWidth() + cv.getPreferredSize().width);//171
        int h = (this.getHeight() + cv.getPreferredSize().height);//144

        Dimension d = new Dimension(w, h);
        this.jPanelCenter.setPreferredSize(d);
        this.jPanelCenter.setMinimumSize(d);
        
        Dimension d2 = new Dimension(w + this.getMinimumSize().width, h + this.getMinimumSize().height + 15);
        this.setMinimumSize(d2);
        this.setPreferredSize(d2);
    }
    
    private String cleanString(String in)
    {
        return in.replaceAll("[^a-zA-Z]", "").toUpperCase();
    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        int max = encoder.getSteps().size();
        this.menuStepByStep.setEnabled(max > 0);
        
        int possi  = encoder.getMsgLength() * 4;
        int fails  = (encoder.getTotalNbSteps()-possi)/4;
        this.jLabelTotal.setText("Total: " + max + " (dont " + fails + " recalculs)");
        this.jLabelNChar.setText("Caractère n°" + (1+currentChar) + "/" + encoder.getMsgLength());
        this.jLabelNStep.setText("Etape n°" + (1+currentStep) + "/" + encoder.getNbSteps(currentChar));
    }
    
    public static void copy(String text)
    {
        Clipboard clipboard = getSystemClipboard();
        clipboard.setContents(new StringSelection(text), null);
    }

    public static String ClipboardGet() throws Exception
    {
        Clipboard systemClipboard = getSystemClipboard();
        DataFlavor dataFlavor = DataFlavor.stringFlavor;

        if (systemClipboard.isDataFlavorAvailable(dataFlavor))
        {
            Object text = systemClipboard.getData(dataFlavor);
            return (String) text;
        }

        return null;
    }

    private static Clipboard getSystemClipboard()
    {
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        Clipboard systemClipboard = defaultToolkit.getSystemClipboard();

        return systemClipboard;
    }
    
    public void updateSteps()
    {
        if(this.menuStepByStep.isEnabled())
        {
            String seed = encoder.getSeed(currentChar, currentStep);
            encoder.decodeSeed(seed);
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

        jPanel1 = new javax.swing.JPanel();
        jPanelCenter = new javax.swing.JPanel();
        jPanelMessages = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        encryptButton = new javax.swing.JButton();
        textToEncrypt = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        encryptedText = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        decryptButton = new javax.swing.JButton();
        textToDecrypt = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        decryptedText = new javax.swing.JTextField();
        jPanelSteps = new javax.swing.JPanel();
        jLabelTotal = new javax.swing.JLabel();
        jLabelNChar = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButCharPrev = new javax.swing.JButton();
        jButCharNext = new javax.swing.JButton();
        jLabelNStep = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButStepPrev = new javax.swing.JButton();
        jButStepNext = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        menuShowSeed = new javax.swing.JMenuItem();
        menuLoadSeed = new javax.swing.JMenuItem();
        menuQuitter = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuLoadBackup = new javax.swing.JMenuItem();
        menuStepByStep = new javax.swing.JCheckBoxMenuItem();
        jMenu3 = new javax.swing.JMenu();
        menuNaturalMix = new javax.swing.JMenuItem();
        menuColorMix = new javax.swing.JMenuItem();
        menuValueMix = new javax.swing.JMenuItem();
        menuRandomMix = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1, 1));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanelCenter.setMinimumSize(new java.awt.Dimension(1, 1));
        jPanelCenter.setPreferredSize(new java.awt.Dimension(1, 1));
        jPanelCenter.setLayout(new java.awt.BorderLayout());
        jPanel1.add(jPanelCenter, java.awt.BorderLayout.CENTER);

        jPanelMessages.setLayout(new java.awt.GridLayout(4, 1, 1, 1));

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Message à chiffrer"));
        jPanel7.setLayout(new java.awt.BorderLayout());

        encryptButton.setText("Chiffrer");
        encryptButton.setMaximumSize(new java.awt.Dimension(81, 23));
        encryptButton.setMinimumSize(new java.awt.Dimension(81, 23));
        encryptButton.setPreferredSize(new java.awt.Dimension(105, 23));
        encryptButton.setRequestFocusEnabled(false);
        encryptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encryptButtonActionPerformed(evt);
            }
        });
        jPanel7.add(encryptButton, java.awt.BorderLayout.LINE_END);

        textToEncrypt.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel7.add(textToEncrypt, java.awt.BorderLayout.CENTER);

        jPanelMessages.add(jPanel7);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Message chiffré"));
        jPanel6.setLayout(new java.awt.BorderLayout());

        encryptedText.setEditable(false);
        jPanel6.add(encryptedText, java.awt.BorderLayout.CENTER);

        jPanelMessages.add(jPanel6);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Message à déchiffrer"));
        jPanel5.setLayout(new java.awt.BorderLayout());

        decryptButton.setText("Déchiffrer");
        decryptButton.setPreferredSize(new java.awt.Dimension(105, 25));
        decryptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decryptButtonActionPerformed(evt);
            }
        });
        jPanel5.add(decryptButton, java.awt.BorderLayout.LINE_END);
        jPanel5.add(textToDecrypt, java.awt.BorderLayout.CENTER);

        jPanelMessages.add(jPanel5);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Message déchiffré"));
        jPanel8.setLayout(new java.awt.BorderLayout());

        decryptedText.setEditable(false);
        jPanel8.add(decryptedText, java.awt.BorderLayout.CENTER);

        jPanelMessages.add(jPanel8);

        jPanel1.add(jPanelMessages, java.awt.BorderLayout.SOUTH);

        jPanelSteps.setLayout(new java.awt.GridLayout(5, 1, 1, 1));

        jLabelTotal.setText("Total :");
        jPanelSteps.add(jLabelTotal);

        jLabelNChar.setText("Caractère n°");
        jPanelSteps.add(jLabelNChar);

        jPanel3.setLayout(new java.awt.GridLayout(1, 0));

        jButCharPrev.setText("-");
        jButCharPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButCharPrevActionPerformed(evt);
            }
        });
        jPanel3.add(jButCharPrev);

        jButCharNext.setText("+");
        jButCharNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButCharNextActionPerformed(evt);
            }
        });
        jPanel3.add(jButCharNext);

        jPanelSteps.add(jPanel3);

        jLabelNStep.setText("Etape n°");
        jPanelSteps.add(jLabelNStep);

        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        jButStepPrev.setText("-");
        jButStepPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButStepPrevActionPerformed(evt);
            }
        });
        jPanel4.add(jButStepPrev);

        jButStepNext.setText("+");
        jButStepNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButStepNextActionPerformed(evt);
            }
        });
        jPanel4.add(jButStepNext);

        jPanelSteps.add(jPanel4);

        jPanel1.add(jPanelSteps, java.awt.BorderLayout.EAST);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jMenu1.setText("Fichier");

        jMenu4.setText("Seed");

        menuShowSeed.setText("Voir");
        menuShowSeed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuShowSeedActionPerformed(evt);
            }
        });
        jMenu4.add(menuShowSeed);

        menuLoadSeed.setText("Charger");
        menuLoadSeed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLoadSeedActionPerformed(evt);
            }
        });
        jMenu4.add(menuLoadSeed);

        jMenu1.add(jMenu4);

        menuQuitter.setText("Quitter");
        menuQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuQuitterActionPerformed(evt);
            }
        });
        jMenu1.add(menuQuitter);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Etapes");

        menuLoadBackup.setText("Paquet initial");
        menuLoadBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLoadBackupActionPerformed(evt);
            }
        });
        jMenu2.add(menuLoadBackup);

        menuStepByStep.setText("Pas à pas");
        menuStepByStep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuStepByStepActionPerformed(evt);
            }
        });
        jMenu2.add(menuStepByStep);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Mélanger");

        menuNaturalMix.setText("Naturel");
        menuNaturalMix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNaturalMixActionPerformed(evt);
            }
        });
        jMenu3.add(menuNaturalMix);

        menuColorMix.setText("Couleur");
        menuColorMix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuColorMixActionPerformed(evt);
            }
        });
        jMenu3.add(menuColorMix);

        menuValueMix.setText("Valeur");
        menuValueMix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuValueMixActionPerformed(evt);
            }
        });
        jMenu3.add(menuValueMix);

        menuRandomMix.setText("Aléatoire");
        menuRandomMix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRandomMixActionPerformed(evt);
            }
        });
        jMenu3.add(menuRandomMix);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void encryptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_encryptButtonActionPerformed
        String tmp = cleanString(textToEncrypt.getText());
        this.textToEncrypt.setText(tmp);
        this.currentChar = 0;
        this.currentStep = 0;
        if(!tmp.equals(""))
        {
            tmp = encoder.encrypt(tmp);
            this.encryptedText.setText(tmp);
            this.updateSteps();
        }
        repaint();
    }//GEN-LAST:event_encryptButtonActionPerformed

    private void decryptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decryptButtonActionPerformed
        String tmp = cleanString(textToDecrypt.getText());
        this.textToDecrypt.setText(tmp);
        this.currentChar = 0;
        this.currentStep = 0;
        if(!tmp.equals(""))
        {
            tmp = encoder.decrypt(tmp);
            this.decryptedText.setText(tmp);
            this.updateSteps();
        }
        repaint();
    }//GEN-LAST:event_decryptButtonActionPerformed

    private void menuColorMixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuColorMixActionPerformed
        this.cards.colorMix();
        repaint();
    }//GEN-LAST:event_menuColorMixActionPerformed

    private void menuValueMixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuValueMixActionPerformed
        this.cards.valueMix();
        repaint();
    }//GEN-LAST:event_menuValueMixActionPerformed

    private void menuRandomMixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRandomMixActionPerformed
        this.cards.randomMix();
        repaint();
    }//GEN-LAST:event_menuRandomMixActionPerformed

    private void menuNaturalMixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNaturalMixActionPerformed
        this.cards.naturalMix();
        repaint();
    }//GEN-LAST:event_menuNaturalMixActionPerformed

    private void menuQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuQuitterActionPerformed
        exit(0);
    }//GEN-LAST:event_menuQuitterActionPerformed

    private void menuStepByStepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuStepByStepActionPerformed
        if(this.menuStepByStep.isSelected())
        {
            this.jPanel1.setBorder(messageBorder);
            this.jPanelSteps.setVisible(true);
            updateSteps();
        }
        else
        {
            this.jPanel1.setBorder(null);
            this.jPanelSteps.setVisible(false);
        }
        repaint();
    }//GEN-LAST:event_menuStepByStepActionPerformed

    private void menuLoadBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLoadBackupActionPerformed
        this.cards.loadBackup();
        repaint();
    }//GEN-LAST:event_menuLoadBackupActionPerformed

    private void menuLoadSeedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLoadSeedActionPerformed
        SeedViewer sv = new SeedViewer("Charger une seed", "Coller", "Charger");
        sv.getButton1().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                try 
                {
                    sv.getjText().setText(ClipboardGet());
                }
                catch (Exception ex)
                {
                    
                }
            }
        });
        sv.getButton2().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seed = sv.getjText().getText();
                if(seed.equals(""))
                {
                    sv.getjNoti().setText("La seed ne peut pas être vide!");
                }
                else
                {
                    if(encoder.validateSeed(seed))
                    {
                        encoder.decodeSeed(seed);
                        cards.saveCurrentState();
                        repaint();
                        sv.dispose();
                    }
                    else
                    {
                        sv.getjNoti().setText("La seed est incorrect!");
                    }
                    
                }
            }
        });
        sv.setVisible(true);
    }//GEN-LAST:event_menuLoadSeedActionPerformed

    private void menuShowSeedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuShowSeedActionPerformed
        String seed = encoder.generateSeed();
        SeedViewer sv = new SeedViewer("Voir la seed", "Copier", "Ok");
        sv.getjText().setText(seed);
        sv.getButton1().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                copy(seed);
                sv.getjNoti().setText("Copié dans le presse-papier!");
            }
        });
        sv.getButton2().addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                sv.dispose();
            }
        });
        sv.setVisible(true);
    }//GEN-LAST:event_menuShowSeedActionPerformed

    private void jButCharNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButCharNextActionPerformed
        if(currentChar < (encoder.getMsgLength()-1))
        {
            currentStep = 0;
            currentChar++;
            updateSteps();
        }
        repaint();
    }//GEN-LAST:event_jButCharNextActionPerformed

    private void jButCharPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButCharPrevActionPerformed
        if(currentChar > 0)
        {
            currentStep = 0;
            currentChar--;
            updateSteps();
        }
        repaint();
    }//GEN-LAST:event_jButCharPrevActionPerformed

    private void jButStepNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButStepNextActionPerformed
        if(currentStep < (encoder.getNbSteps(currentChar)-1))
        {
            currentStep++;
            updateSteps();
        }
        repaint();
    }//GEN-LAST:event_jButStepNextActionPerformed

    private void jButStepPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButStepPrevActionPerformed
        if(currentStep > 0)
        {
            currentStep--;
            updateSteps();
        }
        repaint();
    }//GEN-LAST:event_jButStepPrevActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton decryptButton;
    private javax.swing.JTextField decryptedText;
    private javax.swing.JButton encryptButton;
    private javax.swing.JTextField encryptedText;
    private javax.swing.JButton jButCharNext;
    private javax.swing.JButton jButCharPrev;
    private javax.swing.JButton jButStepNext;
    private javax.swing.JButton jButStepPrev;
    private javax.swing.JLabel jLabelNChar;
    private javax.swing.JLabel jLabelNStep;
    private javax.swing.JLabel jLabelTotal;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanelCenter;
    private javax.swing.JPanel jPanelMessages;
    private javax.swing.JPanel jPanelSteps;
    private javax.swing.JMenuItem menuColorMix;
    private javax.swing.JMenuItem menuLoadBackup;
    private javax.swing.JMenuItem menuLoadSeed;
    private javax.swing.JMenuItem menuNaturalMix;
    private javax.swing.JMenuItem menuQuitter;
    private javax.swing.JMenuItem menuRandomMix;
    private javax.swing.JMenuItem menuShowSeed;
    private javax.swing.JCheckBoxMenuItem menuStepByStep;
    private javax.swing.JMenuItem menuValueMix;
    private javax.swing.JTextField textToDecrypt;
    private javax.swing.JTextField textToEncrypt;
    // End of variables declaration//GEN-END:variables
}
