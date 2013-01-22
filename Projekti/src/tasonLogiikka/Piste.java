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

    /**
     * Konstruoi määrittelemätön piste
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
    
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
