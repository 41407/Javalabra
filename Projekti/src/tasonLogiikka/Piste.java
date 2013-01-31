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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
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
