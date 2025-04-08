import java.awt.Color;
import java.awt.Graphics;

//Elleipsh
public class Oval extends Shape{
    int x; //suntetagmenh x ths panw aristera gwnias ths elleipshs
    int y; //suntetagmenh y ths panw aristera gwnias ths elleipshs
    int width; //platos ths elleipshs
    int height; //upsos ths elleishs
    Color draw; //xrwma perigrammatos
    Oval(int x,int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.draw = Color.black; //to xrwma perigrammatos ths elleipshs den mporei na allajei gi auto kai oti kai na ginei menei sto prokathosrismeno xrwma
    }
    
    @Override
    void draw(Graphics gr){
        gr.setColor(draw);
        gr.drawOval(x, y, width, height);
    }

    @Override
    boolean pointInShape(int x, int y) {
        return x>this.x && x < this.x + this.width && y>this.y && y < this.y + this.height;
    }

    @Override
    void move(int xshift, int yshift) {
        x -=xshift;
        y -=yshift;
    }

    @Override
    Shape copy() {
        return new Oval(x,y,width,height);
    }

    @Override
    void enlarge() {
        this.width *=1.25;
        this.height *=1.25;
    }

    @Override
    void shrink() {
        this.width *=0.75;
        this.height *=0.75;
    }
}
