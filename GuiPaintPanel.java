import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GuiPaintPanel extends JPanel {//implements ActionListener

    static int px=0;//mouse's x
    static int py=0;//mouse's y
    static int startX=((SpaceShip)(Gui_design.s1)).spaceX+SpaceShip.spaceShipIcon.getIconWidth();//x coordinate of the nose of the spaceShip
    static int startY=((SpaceShip)(Gui_design.s1)).spaceY+SpaceShip.spaceShipIcon.getIconWidth()/2;//y coordinate of the nose of the spaceShip

    static double ballCoordxc;
    static double[] ballCoordyc = new double[5];
    static double[] ballCoordx = new double[5];
    static double[] ballCoordy = new double[5];


    static protected Ellipse2D.Double[][] ballsx = new Ellipse2D.Double[5][3];
    static protected Ellipse2D.Double[] ball = new Ellipse2D.Double[5];


    protected static ArrayList<Ellipse2D.Double> balls = new ArrayList<>();//sapceShip bullets

    GuiPaintPanel() {
        for(int i = 0; i < 5; i++) {
            ball[i] = new Ellipse2D.Double(ballCoordx[i], ballCoordy[i], 15, 15);
        }
    }


    protected static void addBall() {
        Ellipse2D.Double bal=new Ellipse2D.Double(startX,startY,20,20);
        balls.add(bal);
    }

    public void initializeCoords() {
        if(Gui_design.levelcounter == 1) {
            for (int i = 0; i < 3; i++) {
                ballCoordyc[i] = ((AlienShip)Gui_design.alienShip[i]).coordY + ((AlienShip)Gui_design.alienShip[i]).ufo.getIconHeight()/2;
                ballCoordy[i] = ((AlienShip)Gui_design.alienShip[i]).coordY + ((AlienShip)Gui_design.alienShip[i]).ufo.getIconHeight()/2;
                ballCoordx[i] = ((AlienShip)Gui_design.alienShip[i]).coordX + ((AlienShip)Gui_design.alienShip[i]).ufo.getIconWidth()/2;
            }
            ballCoordxc = ((AlienShip)Gui_design.alienShip[0]).coordX + ((AlienShip)Gui_design.alienShip[0]).ufo.getIconWidth()/2;
        }
        else if(Gui_design.levelcounter == 2 || Gui_design.levelcounter == 3) {
            for (int i = 0; i < 5; i++) {


                ballCoordyc[i] = ((AlienShip)Gui_design.alienShip[i]).labelUFO.getY()+  ((AlienShip)Gui_design.alienShip[i]).ufo.getIconHeight()/2;
                ballCoordy[i] = ((AlienShip)Gui_design.alienShip[i]).labelUFO.getY() + ((AlienShip)Gui_design.alienShip[i]).ufo.getIconHeight()/2;
                ballCoordx[i] = ((AlienShip)Gui_design.alienShip[i]).labelUFO.getX() + ((AlienShip)Gui_design.alienShip[i]).ufo.getIconWidth()/2;
            }
            ballCoordxc = ((AlienShip)Gui_design.alienShip[0]).coordX + ((AlienShip)Gui_design.alienShip[0]).ufo.getIconWidth()/2;
        }
        else if(Gui_design.levelcounter == 4) {
            ballCoordy[0] = (Gui_design.alienBoss).labelUFO.getY() + (Gui_design.alienBoss).ufo.getIconHeight()/2;
            ballCoordx[0] = (Gui_design.alienBoss).labelUFO.getX() + (Gui_design.alienBoss).ufo.getIconWidth()/2;
            ballCoordxc = (Gui_design.alienBoss).labelUFO.getX() + (Gui_design.alienBoss).ufo.getIconWidth()/2;
            ballCoordyc[0] = (Gui_design.alienBoss).labelUFO.getY() + (Gui_design.alienBoss).ufo.getIconHeight()/2;
        }

    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;


        ImageIcon bg= new ImageIcon("space.jpg");//background
        g2d.drawImage(bg.getImage(),0,0,null);

        if(Gui_design.levelcounter!=0) {
            g2d.setColor(Color.WHITE);


            Point p= getMousePosition();
            if(p!=null ) {
                px=p.x;
                py=p.y;
                startX=((SpaceShip)(Gui_design.s1)).spaceX+SpaceShip.spaceShipIcon.getIconWidth();
                startY=((SpaceShip)(Gui_design.s1)).spaceY+SpaceShip.spaceShipIcon.getIconWidth()/2;

            }
            float dash1[] = {10.0f};
            BasicStroke dashed =new BasicStroke(1.0f,
                    BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER,
                    10.0f, dash1, 0.0f);
            g2d.setStroke(dashed);


            g2d.draw(new Line2D.Double(startX,
                    startY,px, py));//	aim dot steering(line 98-107)



            for(Ellipse2D.Double ball:balls) {//draw sapceShip bullets
                g2d.setColor(Color.RED);
                g2d.fill(ball);

            }

            g2d.setPaint(Color.BLUE);
            if(Gui_design.levelcounter == 1) {
                g2d.fill(ball[0]);
                g2d.fill(ball[1]);
                g2d.fill(ball[2]);
            }
            else if (Gui_design.levelcounter == 2) {
                g2d.fill(ball[0]);
                g2d.fill(ball[1]);
                g2d.fill(ball[2]);
                g2d.fill(ball[3]);
                g2d.fill(ball[4]);
            }
            else if (Gui_design.levelcounter == 3) {
                g2d.fill(ball[0]);
            }
        }
    }

}
