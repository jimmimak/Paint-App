import java.awt.Graphics;
import java.io.Serializable;

//Sxhma pou ulopoiei to interface Serializable gia na apothikeuetai se arxeio
abstract public class Shape implements Serializable{
    abstract void draw(Graphics gr);
    //An to shmeio (x,y) einai sto sxhma
    abstract boolean pointInShape(int x, int y);
    //Metakinhsh tou sxhmatos kata shift stous ajones x kai y
    abstract void move(int xshift, int yshift);
    //Antigrafh olwn twn stoixeiwn enos sxhmatos kai epistrofh tou
    abstract Shape copy();
    //Megethunsh tou sxhmatos
    abstract void enlarge();
    //Smikrunsh tou sxhmatos
    abstract void shrink();
}
