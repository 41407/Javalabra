/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tasonLogiikka;

/**
 *
 * @author 41407
 */
public class Piste {

    private int x;
    private int y;

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * Konstruoi piste jonka sijainti on (0,0)
     */
    public Piste() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Konstruoi piste jolla on sijainti x, y
     *
     * @param x
     * @param y
     *
     */
    public Piste(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Siirrä pistettä dx ja dy verran
     *
     * @param dx
     * @param dy
     *
     *
     */
    public void siirra(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    /**
     * Aseta pisteen x-sijainti suoraan
     * 
     * @param x 
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Aseta pisteen y-sijainti suoraan
     * 
     * @param y 
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * Aseta pisteen x ja y -sijainti suoraan
     * 
     * @param piste 
     */
    public void setXY(Piste piste) {
        this.x = piste.getX();
        this.y = piste.getY();
    }
    
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
