import java.awt.Color;
import java.awt.Graphics;

//Tetragwno
public class Square extends Shape{
    int x; //suntetagmenh x ths panw aristera gwnias tou tetragwnou
    int y; //suntetagmenh y ths panw aristera gwnias tou tetragwnou
    int width; //platos
    Color fill,draw; //xrwma gemismatos kai perigrammatos tou orthogwniou
    
    Square(int x,int y, int width, Color fill, Color draw){
        this.x = x;
        this.y = y;
        this.width = width;
        this.fill = fill;
        this.draw = draw;
    }
    
    @Override
    void draw(Graphics gr){
        //gemisma
        gr.setColor(fill);
        gr.fillRect(x, y, width, width);
        
        //perigramma
        gr.setColor(draw);
        gr.drawRect(x, y, width, width);
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
        return new Square(x,y,width,fill,draw);
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
