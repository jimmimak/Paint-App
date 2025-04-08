import java.awt.Color;
import java.awt.Graphics;

//Trigwno
public class Triangle extends Shape{
    int xStart; //suntetagmenh x tou arxikou shmeiou tou trigwnou
    int yStart; //suntetagmenh y tou arxikou shmeiou tou trigwnou
    int xEnd; //suntetagmenh x tou telikou shmeiou tou trigwnou
    int yEnd; //suntetagmenh y tou arxikou shmeiou tou trigwnou
    Color fill; //xrwma gemismatos tou trigwnou
    Color draw; //xrwma perigrammatos tou trigwnou
    
    Triangle(int xStart,int yStart, int xEnd, int yEnd, Color fill){
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.fill = fill;
        this.draw = Color.black; //to xrwma perigrammatos tou trigwnou den mporei na allajei gi auto kai oti kai na ginei menei sto prokathosrismeno xrwma
    }
    
    @Override
    void draw(Graphics gr){
        //H mesh ston ajona x einai to aristero shmeio (Math.min(xStart,xEnd)
        //sun thn hmidiafora tous (Math.abs(xStart-xEnd)/2)
        int xPoints[] = {xStart,xEnd,Math.min(xStart,xEnd)+Math.abs(xStart-xEnd)/2};
        int yPoints[] = {yStart,yStart,yEnd};
        
        // γέμισμα
        gr.setColor(fill);
        gr.fillPolygon(xPoints, yPoints, 3);
        
        //perigramma
        gr.setColor(draw);
        gr.drawPolygon(xPoints, yPoints, 3);
    }

    @Override
    boolean pointInShape(int x, int y) {
        //Ta x kai y prepei na einai anamesa sta megista kai elaxista
        //twn xStart, xEnd kai yStart, yEnd
        return x>Math.min(xStart, xEnd) && x < Math.max(xStart, xEnd)
                &&  y>Math.min(yStart, yEnd) && y < Math.max(yStart, yEnd);
    }

    @Override
    void move(int xshift, int yshift) {
        //metakinoume kai to shmeio apo to opoio arxizei to trigwno
        //kai to shmeio sto opoio teleiwnei
        xStart -=xshift;
        yStart -=yshift;
        xEnd -=xshift;
        yEnd -=yshift;
    }

    @Override
    Shape copy() {
        return new Triangle(xStart,yStart,xEnd,yEnd,fill);
    }

    @Override
    void enlarge() {
        //Gia na megalwsw to trigwno, briskw to shmeio katw dejia
        //kai mettaferw auto dejiotera
       if (xEnd>xStart)
        xEnd = xStart + (int)((xEnd-xStart)*1.25);
       else
         xStart = xEnd + (int)((xStart-xEnd)*1.25); 
       if (yEnd>yStart)
        yEnd = yStart + (int)((yEnd-yStart)*1.25);
       else
         yStart = yEnd + (int)((yStart-yEnd)*1.25); 
    }

    @Override
    void shrink() {
        //Gia na mikrunw to trigwno, briskw to shmeio katw dejia
        //kai mettaferw auto aristerotera
        if (xEnd>xStart)
        xEnd = xStart + (int)((xEnd-xStart)*0.75);
       else
         xStart = xEnd + (int)((xStart-xEnd)*0.75); 
       if (yEnd>yStart)
        yEnd = yStart + (int)((yEnd-yStart)*0.75);
       else
         yStart = yEnd + (int)((yStart-yEnd)*0.75);
    }
}
