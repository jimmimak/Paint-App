//Dimitrios Makris, 3212019119
//Leonidas Kominos, 3212015092

import java.awt.Color;
import java.awt.Graphics;

// Ορθογώνιο
public class Circle extends Shape{
    int x; //suntetagmenh x ths panw aristera gwnias tou kuklou
    int y; //suntetagmenh y ths panw aristera gwnias tou kuklou
    int width; //platos tou kuklou
    Color fill,draw; //xrwma gemismatos kai perigrammatos tou kuklou
    Circle(int x,int y, int width, Color fill){
        this.x = x;
        this.y = y;
        this.width = width;
        this.fill = fill;
        this.draw = Color.black; //to xrwma perigrammatos tou kuklou den mporei na allajei gi auto kai oti kai na ginei menei sto prokathosrismeno xrwma
    }
    
    @Override
    void draw(Graphics gr){
        //gemisma
        gr.setColor(fill);
        gr.fillOval(x, y, width, width);
        
        //perigramma
        gr.setColor(draw);
        gr.drawOval(x, y, width, width);
    }

    @Override
    boolean pointInShape(int x, int y) {
        return x>this.x && x < this.x + this.width && y>this.y && y < this.y + this.width;
    }

    @Override
    void move(int xshift, int yshift) {
        x -=xshift;
        y -=yshift;
    }

    @Override
    Shape copy() {
        return new Circle(x,y,width,fill);
    }

    @Override
    void enlarge() {
        this.width *=1.25;
    }

    @Override
    void shrink() {
        this.width *=0.75;
    }
}