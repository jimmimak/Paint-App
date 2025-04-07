//Dimitrios Makris, 3212019119
//Leonidas Kominos, 3212015092

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PaintFrame extends JFrame{
    
    ArrayList<Shape> shapelist = new ArrayList(); //Lista me sxhmata pou zwgrafizei o xrhsths, dhladh ta sxhmata pou uparxoun panw ston kamba
    ArrayList<Shape> dshapelist = new ArrayList(); //Lista me sxhmata pou diagrafhkan
    
    //Platos kai upsos tou frame
    int width = 600;
    int height = 600;
    
    PaintPanel paintpanel; //Panel gia zwgrafikh
    PaintMenu paintmenu; //Menu epilogwn sthn korufh tou frame
    
    PaintFrame(){
        super("My Painting Program");
        
        //arxikopoihsh twn antikeimenwn PaintPanel kai PaintMenu
        paintpanel = new PaintPanel(this);
        paintmenu = new PaintMenu(this);
        
        this.setJMenuBar(paintmenu); //thetoume to menubar sthn korufh tou frame
        
        //ex orismou layout tou JFrame einai to BorderLayout kai
        //to paintpanel prostithetai sto kentro
        this.add(paintpanel);
         
         setSize(width,height);//Orizoume to megethos tou parathurou se 600 px platos kai 600 px upsos 
         setLocationRelativeTo(null);//Auth h grammh kwdika kentrarei to parathuro sthn othonh
         setResizable(false);//To parathuro den allazei megethos 
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Kleinei h efarmogh otan kanoume klik sto koumpi "Kleisimo" ths grammhs titlou
         setVisible(true); //To plaisio na einai orato
    }
    
    //epistrofh sxhmatos pou uparxei sto shmeio
    Shape getShape(int x, int y){
        //diasxish ths listas me ta sxhmata pou zwgrafise o xrhsths
        for(Shape s:shapelist){
            //an o xrhsths ekane klik se shmeio pou uparxei sxhma
            if (s.pointInShape(x, y))
                return s; //epistrefetai to sxhma pou uphrxe se ekeino to shmeio
        }
        return null; //den uphrxe sxhma sto shmeio (x,y)
    }
}

//Panel gia zwgrafikh
class PaintPanel extends JPanel{

    PaintFrame paintframe; //Handler gia to basiko parathuro ths efarmoghs
    MouseHandler mousehandler;
    
    public PaintPanel(PaintFrame paintframe) {
        this.paintframe = paintframe;
        this.setBackground(Color.white); //zwgrafikh se aspro fonto
        mousehandler = new MouseHandler(paintframe);
        
        //prosthetoume to atnikeimeno mousehandler sto panel ths zwgrafikhs
        //kathws panw sto panel tha dra to pontiki
        this.addMouseListener(mousehandler);
    }
    
    //sunarthsh gia sxediasmo twn sxhmatwn pou briskontai sth kista shapelist ston kamva
    @Override
    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        //diasxish ths listas me ta sxhmata pou uparxoun sth lista shapelist
        //kai sxediasmos tous panw ston kamva
        for(Shape s:paintframe.shapelist){
            s.draw(gr);
        }
    }
    
}
// Menu epilogwn sthn korufh tou frame
class PaintMenu extends JMenuBar{
   
    //metablhtes xrwmatos gemismatos kai perigrammatos twn sxhmatwn
    //pou by default tous exoume anathesei ta xrvmata aspro kai mauro antistoixa
    Color fill = Color.white, draw = Color.black;
    
    PaintFrame paintframe; //Handler gia to basiko parathuro ths efarmoghs
    MenuHandler menuhandler; //handler gia xeirismo energeiwn sto menu
    
    //H epilogh "File" sto menu kai oi epiloges pou auth periexei
    private final JMenu filemenu;
    JMenuItem savemenuitem;
    JMenuItem openmenuitem;
    JMenuItem exitmenuitem;
    
    //Epilogh sxhmatos
    private final JMenu shapemenu;
    JMenuItem rectanglemenuitem;
    JMenuItem squaremenuitem;
    JMenuItem ovalmenuitem;
    JMenuItem circlemenuitem;
    JMenuItem linemenuitem;
    JMenuItem trianglemenuitem;
    JMenuItem starmenuitem;
    
    //Epilogh xrwmatos gemismatos
    private final JMenu fillcolormenu;
    JMenuItem fillwhitemenuitem;
    JMenuItem fillblackmenuitem;
    JMenuItem fillredmenuitem;
    JMenuItem fillbluemenuitem;
    JMenuItem fillgreenmenuitem;
    JMenuItem fillyellowmenuitem;
    JMenuItem fillpinkmenuitem;
    JMenuItem fillcyanmenuitem;
    JMenuItem fillmagentamenuitem;
    JMenuItem fillorangemenuitem;
    
    //Epilogh xrwmatos perigrammatos
    private final JMenu drawcolormenu;
    JMenuItem drawblackmenuitem;
    JMenuItem drawredmenuitem;
    JMenuItem drawbluemenuitem;
    JMenuItem drawgreenmenuitem;
    JMenuItem drawyellowmenuitem;
    JMenuItem drawpinkmenuitem;
    JMenuItem drawcyanmenuitem;
    JMenuItem drawmagentamenuitem;
    JMenuItem draworangemenuitem;
    
    //Energeies tou xrhsth (p.x Antigrafh ktl)
    private final JMenu editmenu;
    JMenuItem copymenuitem;
    JMenuItem movemenuitem;
    JMenuItem deletemenuitem;
    JMenuItem undodeletemenuitem;
    JMenuItem shrinkmenuitem;
    JMenuItem enlargemenuitem;
    
    //sxhma pou exei epilegei gia dhmiourgia h energeia tou xrhsth pou exei epilegei gia ektelesh
    //an o xrhsths paei na sxeidasei ena sxhma xwris na epilejei kapoio tote by default
    //sxediazetai orthogwnio
    String shape = "rectangle";
    
    //constructor mesa ston opoio arxikopoiountai ta JMenu kai ta JMenuItems
    PaintMenu(PaintFrame paintframe){
        this.paintframe = paintframe;
        menuhandler = new MenuHandler(paintframe);
        
        filemenu = new JMenu("File");
        savemenuitem = new JMenuItem("Save");
        filemenu.add(savemenuitem);
        savemenuitem.addActionListener(menuhandler);
        openmenuitem = new JMenuItem("Open");
        filemenu.add(openmenuitem);
        openmenuitem.addActionListener(menuhandler);
        exitmenuitem = new JMenuItem("Exit");
        filemenu.add(exitmenuitem);
        exitmenuitem.addActionListener(menuhandler);
        this.add(filemenu);
        
        
        shapemenu = new JMenu("Shapes");
        rectanglemenuitem = new JMenuItem("Rectangle");
        shapemenu.add(rectanglemenuitem);
        rectanglemenuitem.addActionListener(menuhandler);
        
        squaremenuitem = new JMenuItem("Square");
        shapemenu.add(squaremenuitem);
        squaremenuitem.addActionListener(menuhandler);
        ovalmenuitem = new JMenuItem("Oval");
        shapemenu.add(ovalmenuitem);
        ovalmenuitem.addActionListener(menuhandler);
        circlemenuitem = new JMenuItem("Circle");
        shapemenu.add(circlemenuitem);
        circlemenuitem.addActionListener(menuhandler);
        linemenuitem = new JMenuItem("Line");
        shapemenu.add(linemenuitem);
        linemenuitem.addActionListener(menuhandler);
        trianglemenuitem = new JMenuItem("Triangle");
        shapemenu.add(trianglemenuitem);
        trianglemenuitem.addActionListener(menuhandler);
        starmenuitem = new JMenuItem("Star");
        shapemenu.add(starmenuitem);
        starmenuitem.addActionListener(menuhandler);
        this.add(shapemenu);
        
        fillcolormenu = new JMenu("Fill Colors");
        fillwhitemenuitem = new JMenuItem("White");
        fillcolormenu.add(fillwhitemenuitem);
        fillwhitemenuitem.addActionListener(menuhandler);
        fillblackmenuitem = new JMenuItem("Black");
        fillcolormenu.add(fillblackmenuitem);
        fillblackmenuitem.addActionListener(menuhandler);
        fillredmenuitem = new JMenuItem("Red");
        fillcolormenu.add(fillredmenuitem);
        fillredmenuitem.addActionListener(menuhandler);
        fillbluemenuitem = new JMenuItem("Blue");
        fillcolormenu.add(fillbluemenuitem);
        fillbluemenuitem.addActionListener(menuhandler);
        fillgreenmenuitem = new JMenuItem("Green");
        fillcolormenu.add(fillgreenmenuitem);
        fillgreenmenuitem.addActionListener(menuhandler);
        fillyellowmenuitem = new JMenuItem("Yellow");
        fillcolormenu.add(fillyellowmenuitem);
        fillyellowmenuitem.addActionListener(menuhandler);
        fillpinkmenuitem = new JMenuItem("Pink");
        fillcolormenu.add(fillpinkmenuitem);
        fillpinkmenuitem.addActionListener(menuhandler);
        fillcyanmenuitem = new JMenuItem("Cyan");
        fillcolormenu.add(fillcyanmenuitem);
        fillcyanmenuitem.addActionListener(menuhandler);
        fillmagentamenuitem = new JMenuItem("Magenta");
        fillcolormenu.add(fillmagentamenuitem);
        fillmagentamenuitem.addActionListener(menuhandler);
        fillorangemenuitem = new JMenuItem("Orange");
        fillcolormenu.add(fillorangemenuitem);
        fillorangemenuitem.addActionListener(menuhandler);
        this.add(fillcolormenu);
        
        drawcolormenu = new JMenu("Draw Colors");
        drawblackmenuitem = new JMenuItem("Black");
        drawcolormenu.add(drawblackmenuitem);
        drawblackmenuitem.addActionListener(menuhandler);
        drawredmenuitem = new JMenuItem("Red");
        drawcolormenu.add(drawredmenuitem);
        drawredmenuitem.addActionListener(menuhandler);
        drawbluemenuitem = new JMenuItem("Blue");
        drawcolormenu.add(drawbluemenuitem);
        drawbluemenuitem.addActionListener(menuhandler);
        drawgreenmenuitem = new JMenuItem("Green");
        drawcolormenu.add(drawgreenmenuitem);
        drawgreenmenuitem.addActionListener(menuhandler);
        drawyellowmenuitem = new JMenuItem("Yellow");
        drawcolormenu.add(drawyellowmenuitem);
        drawyellowmenuitem.addActionListener(menuhandler);
        drawpinkmenuitem = new JMenuItem("Pink");
        drawcolormenu.add(drawpinkmenuitem);
        drawpinkmenuitem.addActionListener(menuhandler);
        drawcyanmenuitem = new JMenuItem("Cyan");
        drawcolormenu.add(drawcyanmenuitem);
        drawcyanmenuitem.addActionListener(menuhandler);
        drawmagentamenuitem = new JMenuItem("Magenta");
        drawcolormenu.add(drawmagentamenuitem);
        drawmagentamenuitem.addActionListener(menuhandler);
        draworangemenuitem = new JMenuItem("Orange");
        drawcolormenu.add(draworangemenuitem);
        draworangemenuitem.addActionListener(menuhandler);
        this.add(drawcolormenu);
        
        editmenu = new JMenu("Edit");
        copymenuitem = new JMenuItem("Copy");
        editmenu.add(copymenuitem);
        copymenuitem.addActionListener(menuhandler);
        movemenuitem = new JMenuItem("Move");
        editmenu.add(movemenuitem);
        movemenuitem.addActionListener(menuhandler);
        deletemenuitem = new JMenuItem("Delete");
        editmenu.add(deletemenuitem);
        deletemenuitem.addActionListener(menuhandler);
        undodeletemenuitem = new JMenuItem("Undo Delete");
        editmenu.add(undodeletemenuitem);
        undodeletemenuitem.addActionListener(menuhandler);
        shrinkmenuitem = new JMenuItem("Shrink");
        editmenu.add(shrinkmenuitem);
        shrinkmenuitem.addActionListener(menuhandler);
        enlargemenuitem = new JMenuItem("Enlarge");
        editmenu.add(enlargemenuitem);
        enlargemenuitem.addActionListener(menuhandler);
        this.add(editmenu);
        
    }
}

//klash pou diaxeirizetai ta actions pou sumbainoun sto menu
class MenuHandler implements ActionListener{

    PaintFrame paintframe; //Handler gia to basiko parathuro ths efarmoghs
    
    MenuHandler(PaintFrame paintframe){
        this.paintframe = paintframe;
    }
    
    //sunarthsh pou diaxeirizetai ta actions pou sumbainoun sto menu
    @Override
    public void actionPerformed(ActionEvent e) {
        //an o xrhsths dialejei thn epilogh "Save"
        if (e.getSource() == paintframe.paintmenu.savemenuitem){
            JFileChooser jfc = new JFileChooser();
            int accept= jfc.showSaveDialog(paintframe);
            // Elegxos oti o xrhsths epeleje to save kai oxi to cancel
            if (accept == JFileChooser.APPROVE_OPTION){
                try {
                    FileSystem.saveToFile(jfc.getSelectedFile(), paintframe.shapelist);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(paintframe, "Problem in Files");
                }
            }
        }
        //an o xrhsths dialejei thn epilogh "Open"
        else if (e.getSource() == paintframe.paintmenu.openmenuitem){
             JFileChooser jfc = new JFileChooser();
            int accept= jfc.showOpenDialog(paintframe);
            // Elegxos oti o xrhsths epeleje to open kai oxi to cancel
            if (accept == JFileChooser.APPROVE_OPTION){
                try {
                    paintframe.shapelist = FileSystem.readFromFile(jfc.getSelectedFile());
                    paintframe.paintpanel.repaint();
                } catch (IOException | ClassNotFoundException ex) {
                   JOptionPane.showMessageDialog(paintframe, "Problem in Files"); 
                }
            }
        }
        else if (e.getSource() == paintframe.paintmenu.exitmenuitem){
            System.exit(0);
        }
        else if (e.getSource() == paintframe.paintmenu.rectanglemenuitem){
            paintframe.paintmenu.shape = "rectangle";
        }
        else if (e.getSource() == paintframe.paintmenu.squaremenuitem){
            paintframe.paintmenu.shape = "square";
        }
        else if (e.getSource() == paintframe.paintmenu.ovalmenuitem){
            paintframe.paintmenu.shape = "oval";
        }
        else if (e.getSource() == paintframe.paintmenu.circlemenuitem){
            paintframe.paintmenu.shape = "circle";
        }
        else if (e.getSource() == paintframe.paintmenu.linemenuitem){
            paintframe.paintmenu.shape = "line";
        }
        else if (e.getSource() == paintframe.paintmenu.trianglemenuitem){
            paintframe.paintmenu.shape = "triangle";
        }
        else if (e.getSource() == paintframe.paintmenu.starmenuitem){
            paintframe.paintmenu.shape = "star";
        }
        else if (e.getSource() == paintframe.paintmenu.fillwhitemenuitem){
            paintframe.paintmenu.fill = Color.white;
        }
        else if (e.getSource() == paintframe.paintmenu.fillblackmenuitem){
            paintframe.paintmenu.fill = Color.black;
        }
        else if (e.getSource() == paintframe.paintmenu.fillredmenuitem){
            paintframe.paintmenu.fill = Color.red;
        }
        else if (e.getSource() == paintframe.paintmenu.fillbluemenuitem){
            paintframe.paintmenu.fill = Color.blue;
        }
        else if (e.getSource() == paintframe.paintmenu.fillgreenmenuitem){
            paintframe.paintmenu.fill = Color.green;
        }
        else if (e.getSource() == paintframe.paintmenu.fillyellowmenuitem){
            paintframe.paintmenu.fill = Color.yellow;
        }
        else if (e.getSource() == paintframe.paintmenu.fillpinkmenuitem){
            paintframe.paintmenu.fill = Color.pink;
        }
        else if (e.getSource() == paintframe.paintmenu.fillcyanmenuitem){
            paintframe.paintmenu.fill = Color.cyan;
        }
        else if (e.getSource() == paintframe.paintmenu.fillmagentamenuitem){
            paintframe.paintmenu.fill = Color.magenta;
        }
        else if (e.getSource() == paintframe.paintmenu.fillorangemenuitem){
            paintframe.paintmenu.fill = Color.orange;
        }
        else if (e.getSource() == paintframe.paintmenu.drawblackmenuitem){
            paintframe.paintmenu.draw = Color.black;
        }
        else if (e.getSource() == paintframe.paintmenu.drawredmenuitem){
            paintframe.paintmenu.draw = Color.red;
        }
        else if (e.getSource() == paintframe.paintmenu.drawbluemenuitem){
            paintframe.paintmenu.draw = Color.blue;
        }
        else if (e.getSource() == paintframe.paintmenu.drawgreenmenuitem){
            paintframe.paintmenu.draw = Color.green;
        }
        else if (e.getSource() == paintframe.paintmenu.drawyellowmenuitem){
            paintframe.paintmenu.draw = Color.yellow;
        }
        else if (e.getSource() == paintframe.paintmenu.drawpinkmenuitem){
            paintframe.paintmenu.draw = Color.pink;
        }
        else if (e.getSource() == paintframe.paintmenu.drawcyanmenuitem){
            paintframe.paintmenu.draw = Color.cyan;
        }
        else if (e.getSource() == paintframe.paintmenu.drawmagentamenuitem){
            paintframe.paintmenu.draw = Color.magenta;
        }
        else if (e.getSource() == paintframe.paintmenu.draworangemenuitem){
            paintframe.paintmenu.draw = Color.orange;
        }
        else if (e.getSource() == paintframe.paintmenu.copymenuitem){
            paintframe.paintmenu.shape = "Copy";
        }
        else if (e.getSource() == paintframe.paintmenu.movemenuitem){
            paintframe.paintmenu.shape = "Move";
        }
        else if (e.getSource() == paintframe.paintmenu.deletemenuitem){
            paintframe.paintmenu.shape = "Delete";
        }
        else if (e.getSource() == paintframe.paintmenu.undodeletemenuitem){
            if (paintframe.dshapelist.size()>0){
                
                Shape s = paintframe.dshapelist.remove(paintframe.dshapelist.size()-1);
                paintframe.shapelist.add(s);
                paintframe.paintpanel.repaint();
            }
        }
        else if (e.getSource() == paintframe.paintmenu.shrinkmenuitem){
            
            paintframe.paintmenu.shape = "Shrink";
            
        }
        else if (e.getSource() == paintframe.paintmenu.enlargemenuitem){
            
            paintframe.paintmenu.shape = "Enlarge";
        }
    
    }
    
}

//klash pou diaxeirizetai tis energeies tou pintikiou
class MouseHandler implements MouseListener{
    
    PaintFrame paintframe; //Handler gia to basiko parathuro ths efarmoghs
    int x,y; //suntetagmenes pou ginetai pressed h clicked to pontiki
    Shape s=null; //epilegmeno sxhma
    
    MouseHandler(PaintFrame paintframe){
        this.paintframe = paintframe;
    }
    
    //sunarthsh pou kaleitai otan ginetai clicked to pontiki
    @Override
    public void mouseClicked(MouseEvent e) {
        //suntetagmenes pou egine clicked to pontiki
        x = e.getX();
        y = e.getY();
        Shape s = paintframe.getShape(x, y); //sxhma mesa sto opoio egine click
        
        //an exei epilegei kapoio sxhma
        if (s!=null){
            //an o xrhsths eixe epilejei thn epilogh "Delete" apo to menu
            if (paintframe.paintmenu.shape.equals("Delete")){

                    paintframe.shapelist.remove(s); //to sxhma feugei apo thn lista me ta sxhmata
                    paintframe.dshapelist.add(s); //to sxhma mpainei sth lista me ta diagegrammena sxhmata
                    paintframe.paintpanel.repaint(); //kaleitai h paintComponent() kai o kamvas janasxediazetai xwris ta shmata pou diagrafthkan
            }
            //an o xrhsths eixe epilejei thn epilogh "Copy" apo to menu
            else if (paintframe.paintmenu.shape.equals("Copy")){
                Shape newshape = s.copy(); //antikeimeno tou kainouriou sxhmatos pou prokuptei apo thn antigafh
                paintframe.shapelist.add(newshape); //to kainourio sxhma prostithetai sth lista me ta sxhmata
                paintframe.paintpanel.repaint(); //kaleitai h paintComponent() kai o kamvas janasxediazetai me to kainourio sxhma
                JOptionPane.showMessageDialog(paintframe, "Shape Copied."); //emfanish mhnumatos oti h antografh tou sxhmatos htan epituxhs
            }
            //an o xrhsths eixe epilejei thn epilogh "Enlarge" apo to menu
            else if (paintframe.paintmenu.shape.equals("Enlarge")){
                s.enlarge();
                paintframe.paintpanel.repaint();        
            }
            //an o xrhsths eixe epilejei thn epilogh "Shrink" apo to menu
            else if (paintframe.paintmenu.shape.equals("Shrink")){
                s.shrink();
                paintframe.paintpanel.repaint();        
            }
        }
    }

    //sunarthsh pou kaleitai otan to clixk to pontikiou ginetai press
    @Override
    public void mousePressed(MouseEvent e) {
        //suntetagmenes pou egine pressed to pontiki
        x = e.getX();
        y = e.getY();
        s = paintframe.getShape(x, y); //sxhma mesa sto opoio egine press
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // An to pontiki egine release mesa sta oria
        if (e.getX()>=0 && e.getX()<=paintframe.width && e.getY()>=0 && e.getY()<=paintframe.height){
            //Ta sxhmata tha prepei na exoun megethos megalutero tou 30
            if (Math.abs(x-e.getX())>30 && Math.abs(y-e.getY())>30){
                //an o xrhsths epeleje na sxediasei ena orthogwnio
                if (paintframe.paintmenu.shape.equals("rectangle")){
                    //dhmiourgoume kai prosthetoume ena antikeimeno orthogwniou sth lista me ta sxhmata,
                    //o logos pou xrhsimopoioume thn methodo min() einai gia na jerei to programma
                    //oti panta h panw aristera gwnia einai h arxh tou sxhmatos anejarthtws an emeis
                    //sxediasoume to sxhma apokatw dejia pros panw aristera
                    paintframe.shapelist.add(new Rectangle(Math.min(x, e.getX()),Math.min(y,e.getY()),
                           Math.abs(x-e.getX()),Math.abs(y-e.getY()),
                            paintframe.paintmenu.fill,paintframe.paintmenu.draw));
                    paintframe.paintpanel.repaint(); //ananewnei to panel me th zwgrafikh
                }
                //an o xrhsths epeleje na sxediasei ena tetragwno
                else if (paintframe.paintmenu.shape.equals("square")){
                    //dhmiourgoume kai prosthetoume ena antikeimeno tetragwnou sth lista me ta sxhmata,
                    //o logos pou xrhsimopoioume thn methodo min() einai gia na jerei to programma
                    //oti panta h panw aristera gwnia einai h arxh tou sxhmatos anejarthtws an emeis
                    //sxediasoume to sxhma apokatw dejia pros panw aristera
                    paintframe.shapelist.add(new Square(Math.min(x, e.getX()),Math.min(y,e.getY()),
                           Math.min(Math.abs(x-e.getX()),Math.abs(y-e.getY())), paintframe.paintmenu.fill,paintframe.paintmenu.draw));
                    paintframe.paintpanel.repaint(); //ananewnei to panel me th zwgrafikh
                }
                //an o xrhsths epeleje na sxediasei mia elleipsh
                else if (paintframe.paintmenu.shape.equals("oval")){
                    //dhmiourgoume kai prosthetoume ena antikeimeno elleipshs sth lista me ta sxhmata,
                    //o logos pou xrhsimopoioume thn methodo min() einai gia na jerei to programma
                    //oti panta h panw aristera gwnia einai h arxh tou sxhmatos anejarthtws an emeis
                    //sxediasoume to sxhma apokatw dejia pros panw aristera
                    paintframe.shapelist.add(new Oval(Math.min(x, e.getX()),Math.min(y,e.getY()),
                           Math.abs(x-e.getX()),Math.abs(y-e.getY())));
                    paintframe.paintpanel.repaint(); //ananewnei to panel me th zwgrafikh
                }
                //an o xrhsths epeleje na sxediasei enan kuklo
                else if (paintframe.paintmenu.shape.equals("circle")){
                    //dhmiourgoume kai prosthetoume ena antikeimeno kuklou sth lista me ta sxhmata,
                    //o logos pou xrhsimopoioume thn methodo min() einai gia na jerei to programma
                    //oti panta h panw aristera gwnia einai h arxh tou sxhmatos anejarthtws an emeis
                    //sxediasoume to sxhma apokatw dejia pros panw aristera
                    paintframe.shapelist.add(new Circle(Math.min(x, e.getX()),Math.min(y,e.getY()),
                           Math.abs(x-e.getX()), paintframe.paintmenu.fill));
                    paintframe.paintpanel.repaint(); //ananewnei to panel me th zwgrafikh
                }
                //an o xrhsths epeleje na sxediasei mia grammh
                else if (paintframe.paintmenu.shape.equals("line")){
                    //dhmiourgoume kai prosthetoume ena antikeimeno kuklou sth lista me ta sxhmata
                    paintframe.shapelist.add(new Line(x, y, e.getX(), e.getY(),paintframe.paintmenu.draw));
                    paintframe.paintpanel.repaint(); //ananewnei to panel me th zwgrafikh
                }
                //an o xrhsths epeleje na sxediasei ena trigwno
                else if (paintframe.paintmenu.shape.equals("triangle")){
                    //dhmiourgoume kai prosthetoume ena antikeimeno trigwnou sth lista me ta sxhmata
                    paintframe.shapelist.add(new Triangle(x, y, e.getX(), e.getY(), paintframe.paintmenu.fill));
                    paintframe.paintpanel.repaint(); //ananewnei to panel me th zwgrafikh
                }
                //an o xrhsths epeleje na sxediasei ena asteri
                else if (paintframe.paintmenu.shape.equals("star")){
                    int xStart,xEnd,yStart,yEnd;
                    //Panta sto asteri, h panw aristera gwnia tha einai to
                    // (xStart,yStart) kai h katw dejia h (xEnd,yEnd)
                    xStart = Math.min(x, e.getX());
                    yStart = Math.min(y, e.getY());
                    xEnd = Math.max(x, e.getX());
                    yEnd = Math.max(y, e.getY());
                    //dhmiourgoume kai prosthetoume ena antikeimeno asteriou sth lista me ta sxhmata
                    paintframe.shapelist.add(new Star(xStart, yStart, xEnd, yEnd));
                    paintframe.paintpanel.repaint(); //ananewnei to panel me th zwgrafikh
                }
                //an o xrhsths epeleje to "Move" apo thn epilogh "Edit" tou menu
                else if (paintframe.paintmenu.shape.equals("Move")){
                    //an exei epilegei kapoio sxhma
                    if (s!=null){
                        s.move(x-e.getX(), y-e.getY());
                        paintframe.paintpanel.repaint();
                    }
                }
            }
        
        }
        //an to pontiki ginei release ejw apo ta oria tou panel
        else{
            JOptionPane.showMessageDialog(paintframe, "Mouse released out of bounds");
        }   
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
       
    }
    
}