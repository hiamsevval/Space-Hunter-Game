import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlienShip extends Ship {

    protected JLabel labelUFO;
    protected ImageIcon ufo, shieldUFO;
    protected boolean isDead;
    

    protected int alienid;
    protected int coordX, coordY;

    static int ballcounter = 0;

    public AlienShip(int x, int y, int id) {
        super();
        healthPoint = 50;
        maxHealthPoint = 50;
        alienid = id;
        isDead=false;

        ufo = new ImageIcon("ufo.png");
        shieldUFO = new ImageIcon("ufoshield2.png");

        labelUFO = new JLabel(ufo);

        coordX = x;
        coordY = y;

        labelUFO.setBounds(coordX, coordY, ufo.getIconWidth(), ufo.getIconHeight());
    }

    public void bonds(int x, int y) {
        labelUFO.setBounds(x, y, ufo.getIconWidth(), ufo.getIconHeight());
    }

    public void seticon() {
        if (labelUFO.getIcon() == ufo)
            labelUFO.setIcon(shieldUFO);
        else
            labelUFO.setIcon(ufo);
    }

    public boolean shieldIcon() {
        if (labelUFO.getIcon() == shieldUFO) return true;
        else return false;
    }

    public void calculatTargetCoordinates(int x, int y) {
        System.out.println(Gui_design.spacecoordx + ", " + Gui_design.spacecoordy);
        if(Gui_design.spacecoordy < coordY) { Gui_design.spacecoordy += (Gui_design.spacecoordy - GuiPaintPanel.ballCoordyc[alienid]);}
        else {Gui_design.spacecoordy -= (Gui_design.spacecoordy - GuiPaintPanel.ballCoordyc[alienid]); }
        Gui_design.spacecoordx -= (GuiPaintPanel.ballCoordxc - Gui_design.spacecoordx);
        System.out.println(Gui_design.spacecoordx + ", " + Gui_design.spacecoordy);
    }

    @Override
    protected void hit() {
        if (GuiPaintPanel.ball[alienid].x < 0 || GuiPaintPanel.ball[alienid].y < 0 || GuiPaintPanel.ball[alienid].y > 800) {
            GuiPaintPanel.ball[alienid].x = GuiPaintPanel.ballCoordxc;
            GuiPaintPanel.ball[alienid].y = GuiPaintPanel.ballCoordyc[alienid];
            ballcounter++;
        }

        GuiPaintPanel.ballCoordx[alienid] = 0.01 * (Math.abs(Gui_design.spacecoordx - GuiPaintPanel.ball[alienid].x - ((SpaceShip)Gui_design.s1).spaceShipIcon.getIconWidth()/2));
        GuiPaintPanel.ballCoordy[alienid] = 0.01 * (Math.abs(Gui_design.spacecoordy - GuiPaintPanel.ball[alienid].y + ((SpaceShip)Gui_design.s1).spaceShipIcon.getIconHeight()/2));

        if (Gui_design.spacecoordy + ((SpaceShip)Gui_design.s1).spaceShipIcon.getIconHeight()/2 > GuiPaintPanel.ballCoordyc[alienid]) {
            GuiPaintPanel.ball[alienid].x -= GuiPaintPanel.ballCoordx[alienid];
            GuiPaintPanel.ball[alienid].y += GuiPaintPanel.ballCoordy[alienid];
        }
        else {
            GuiPaintPanel.ball[alienid].x -= GuiPaintPanel.ballCoordx[alienid];
            GuiPaintPanel.ball[alienid].y -= GuiPaintPanel.ballCoordy[alienid];
        }
        repaint();

    }
}