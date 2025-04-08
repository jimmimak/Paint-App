import java.awt.Color;
import java.awt.Graphics;

//Eutheia grammh
public class Line extends Shape{
    int xStart; //suntetagmenh x tou arxikou shmeiou ths grammhs
    int yStart; //suntetagmenh y tou arxikou shmeiou ths grammhs
    int xEnd; //suntetagmenh x tou telikou shmeiou ths grammhs
    int yEnd; //suntetagmenh y tou arxikou shmeiou ths grammhs
    Color draw; //xrwma perigrammatos ths grammhs
    
    Line(int xStart,int yStart, int xEnd, int yEnd, Color draw){
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.draw = draw;
    }
    
    @Override
    void draw(Graphics gr){
        //perigramma
        gr.setColor(draw);
        gr.drawLine(xStart, yStart, xEnd, yEnd);
    }

    @Override
    boolean pointInShape(int x, int y) {
        //Ta x kai y prepei na einai anamesa sta megista kai elaxista
        //twn xStart, xEnd kai yStart, yEnd
        return x>Math.min(xStart,xEnd) && x <Math.max(xStart,xEnd) && 
                y>Math.min(yStart,yEnd) && y < Math.max(yStart,yEnd);
    
    }

    @Override
    void move(int xshift, int yshift) {
        //metakinoume kai to shmeio apo to opoio arxizei h grammh
        //kai to shmeio sto opoio teleiwnei
        xStart -= xshift;        
        yStart -= yshift;
        xEnd -= xshift;
        yEnd -= yshift;

    }

    @Override
    Shape copy() {
        return new Line(xStart,yStart,xEnd,yEnd,draw);
    }

    @Override
    void enlarge() {
        //Gia na megalwsw th grammh, briskw to shmeio katw dejia
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
        //Gia na mikrunw th grammh, briskw to shmeio katw dejia
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
