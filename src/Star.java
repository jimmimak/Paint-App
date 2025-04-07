//Dimitrios Makris, 3212019119
//Leonidas Kominos, 3212015092

import java.awt.Graphics;

//Asteri
public class Star extends Shape{
    int xStart; //suntetagmenh x tou arxikou shmeiou tou asteriou
    int yStart; //suntetagmenh y tou arxikou shmeiou tou asteriou
    int xEnd; //suntetagmenh x tou telikou shmeiou tou asteriou
    int yEnd; //suntetagmenh y tou arxikou shmeiou tou asteriou
    
    Star(int xStart,int yStart, int xEnd, int yEnd){
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
    }
    
    @Override
    void draw(Graphics gr){
        int xPoints[] = {Math.min(xStart,xEnd)+Math.abs(xStart-xEnd)/2,xStart,xEnd};
        int yPoints[] = {yStart+Math.abs(yStart-yEnd)/6,
                        yStart+2*Math.abs(yStart-yEnd)/3,
                        yStart+2*Math.abs(yStart-yEnd)/3};
        gr.drawPolygon(xPoints,yPoints,3);
        int x2Points[] = {xStart,xEnd,Math.min(xStart,xEnd)+Math.abs(xStart-xEnd)/2};
        int y2Points[] = {yStart+1*Math.abs(yStart-yEnd)/3,
            yStart+1*Math.abs(yStart-yEnd)/3,yEnd-Math.abs(yStart-yEnd)/6};
        gr.drawPolygon(x2Points,y2Points,3);
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
        //metakinoume kai to shmeio apo to opoio arxizei to asteri
        //kai to shmeio sto opoio teleiwnei
        xStart -=xshift;
        yStart -=yshift;
        xEnd -=xshift;
        yEnd -=yshift;
    }

    @Override
    Shape copy() {
        return new Star(xStart,yStart,xEnd,yEnd);
    }

    @Override
    void enlarge() {
        //To palio mhkos tou asteriou htan (xEnd-xStart)
        //To megalwnoume pollaplasiazontas me to 1.25
        //kai sth sunexeia bazoume to xEnd oso mhkos brhkame dejia tou xStart
        //To idio kai ston ajona y
        xEnd = xStart + (int)((xEnd-xStart)*1.25);
        yEnd = yStart + (int)((yEnd-yStart)*1.25);
    }

    @Override
    void shrink() {
        //To palio mhkos tou asteriou htan (xEnd-xStart)
        //To mikrainoume pollaplasiazontas me to 0.75
        //kai sth sunexeia bazoume to xEnd oso mhkos brhkame dejia tou xStart
        //To idio kai ston ajona y
        xEnd = xStart + (int)((xEnd-xStart)*0.75);
        yEnd = yStart + (int)((yEnd-yStart)*0.75);
    }
}