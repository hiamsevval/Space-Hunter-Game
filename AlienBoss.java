
import javax.swing.*;

public class AlienBoss extends AlienShip{
    private boolean up_down = false; // up->1, down->0

    AlienBoss() {
        super(1100, 300, 0);
        healthPoint = 150;
        maxHealthPoint = 150;
        isDead=false;

        setBounds(coordX, coordY, ufo.getIconWidth(), ufo.getIconHeight());
    }

    public void move() {
        if ((coordY < 24) && (up_down)) {
            up_down = false;
        }
        else if((coordY > 600) && (!up_down)) {
            up_down = true;
        }
        else {
            if (up_down) {
                coordY -= 10;
            }
            else {
                coordY += 10;
            }
        }
        labelUFO.setBounds(coordX,coordY,ufo.getIconWidth(),ufo.getIconHeight());
    }

    @Override
    protected void hit() {
        if (GuiPaintPanel.ball[alienid].x < 0 || GuiPaintPanel.ball[alienid].y < 0 || GuiPaintPanel.ball[alienid].y > 800) {
            GuiPaintPanel.ball[alienid].x = Gui_design.alienBoss.coordX + Gui_design.alienBoss.ufo.getIconWidth()/2;
            GuiPaintPanel.ball[alienid].y = Gui_design.alienBoss.coordY + Gui_design.alienBoss.ufo.getIconHeight()/2;
            GuiPaintPanel.ballCoordyc[0] = (Gui_design.alienBoss).labelUFO.getY() + (Gui_design.alienBoss).ufo.getIconHeight()/2;
            ballcounter++;
        }

        GuiPaintPanel.ballCoordx[alienid] = 0.03 * (Math.abs(Gui_design.spacecoordx - GuiPaintPanel.ball[alienid].x - ((SpaceShip)Gui_design.s1).spaceShipIcon.getIconWidth()/2));
        GuiPaintPanel.ballCoordy[alienid] = 0.03 * (Math.abs(Gui_design.spacecoordy - GuiPaintPanel.ball[alienid].y + ((SpaceShip)Gui_design.s1).spaceShipIcon.getIconHeight()/2));

        if (Gui_design.spacecoordy + ((SpaceShip)Gui_design.s1).spaceShipIcon.getIconHeight()/2 > GuiPaintPanel.ballCoordyc[alienid]) {
            GuiPaintPanel.ball[alienid].x -= GuiPaintPanel.ballCoordx[alienid];
            GuiPaintPanel.ball[alienid].y += GuiPaintPanel.ballCoordy[alienid];
        }
        else {
            GuiPaintPanel.ball[alienid].x -= GuiPaintPanel.ballCoordx[alienid];
            GuiPaintPanel.ball[alienid].y -= GuiPaintPanel.ballCoordy[alienid];
        }
    }
}
