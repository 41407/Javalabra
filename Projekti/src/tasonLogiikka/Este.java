/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tasonLogiikka;


/**
 *
 * @author 41407
 */
public class Este extends Piste {

    private Piste vasenYlakulma;
    private Piste oikeaAlakulma;

    public Este(int x0, int y0, int x1, int y1) {
        super(x0, y0);
        this.vasenYlakulma = new Piste(x0, y0);
        this.oikeaAlakulma = new Piste(x1, y1);
    }
    
    public boolean kuuluukoPiste(Piste piste) {
        if(piste.getX() >= vasenYlakulma.getX() && piste.getX() <= oikeaAlakulma.getX()
                && piste.getY() >= vasenYlakulma.getY() && piste.getY() <= oikeaAlakulma.getY()) {
            return true;
        }
        return false;
    }
}
