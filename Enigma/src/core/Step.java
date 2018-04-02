/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author Red
 */
public class Step {
    
    private int nStep;
    private int nChar;
    private String seed;
    
    public Step(int n, int c, String s)
    {
        nStep = n;
        nChar = c;
        seed  = s;
    }

    public int getnStep() {
        return nStep;
    }

    public void setnStep(int nStep) {
        this.nStep = nStep;
    }

    public int getnChar() {
        return nChar;
    }

    public void setnChar(int nChar) {
        this.nChar = nChar;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    
}
