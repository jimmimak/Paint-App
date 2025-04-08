import java.awt.Color;
import java.awt.Graphics;

//Orthogwnio
public class Rectangle extends Shape{
    int x; //suntetagmenh x ths panw aristera gwnias tou orthogwniou
    int y; //suntetagmenh y ths panw aristera gwnias tou orthogwniou
    int width; //platos tou orthogwniou
    int height; //upsos tou orthogwniou
    Color fill,draw; //xrwma gemismatos kai perigrammatos tou orthogwniou
    
    Rectangle(int x,int y, int width,int height,Color fill, Color draw){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.fill = fill;
        this.draw = draw;
    }
    
    @Override
    void draw(Graphics gr){
        //gemisma
        gr.setColor(fill);
        gr.fillRect(x, y, width, height);
        
        //perigramma
        gr.setColor(draw);
        gr.drawRect(x, y, width, height);
    }

    @Override
    boolean pointInShape(int x, int y) {
        return x>this.x && x < this.x + this.width && y>this.y && y < this.y + this.height;
    }
    
    @Override
    void move(int xshift, int yshift){
        x -=xshift;
        y -=yshift;
    }

    @Override
    Shape copy() {
        return new Rectangle(x,y,width,height,fill,draw);
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
