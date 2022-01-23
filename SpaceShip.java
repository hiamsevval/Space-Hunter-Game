import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class SpaceShip extends Ship {

    protected JLabel LSpaceShip;
    protected static ImageIcon spaceShipIcon;

    protected int spaceX;
    protected int spaceY;

    SpaceShip(){
        healthPoint=150;
        maxHealthPoint = 150;
        spaceX=10;
        spaceY=200;
        spaceShipIcon=new ImageIcon("space_ship1.png");

        LSpaceShip = new JLabel(spaceShipIcon);
        LSpaceShip.setBounds(spaceX,spaceY,spaceShipIcon.getIconWidth(),spaceShipIcon.getIconHeight());
    }


    @Override
    protected void hit() {
        boolean intersected;
        boolean intersected2;
        boolean intersected3;
        boolean intersected4;
        boolean intersected5;//check for each alien in every level if hit
        
        if(Gui_design.levelcounter ==1) {//check in level 1
            for(int i=0; i<Gui_design.mClickCount; i++) {

                intersected=GuiPaintPanel.balls.get(i).intersects(Gui_design.alienX, Gui_design.alieny1_lev1,
                        Gui_design.ufoW,Gui_design.ufoH);
                intersected2=GuiPaintPanel.balls.get(i).intersects(Gui_design.alienX, Gui_design.alieny2_lev1,
                        Gui_design.ufoW,Gui_design.ufoH);
                intersected3=GuiPaintPanel.balls.get(i).intersects(Gui_design.alienX, Gui_design.alieny3_lev1,
                        Gui_design.ufoW,Gui_design.ufoH);
              

                if(intersected && !((AlienShip)(Gui_design.alienShip[0])).isDead) {//check for alien1
                    GuiPaintPanel.balls.get(i).x=1400;
                    GuiPaintPanel.balls.get(i).y=1400;
                    if(!((AlienShip)(Gui_design.alienShip[0])).shieldIcon() ) {
                        Gui_design.alienShip[0].healthPoint-=10;
                        Gui_design.alienHP-=10;
                       
                    }

                }

                if(intersected2 && !((AlienShip)(Gui_design.alienShip[1])).isDead) {//check for alien2
                    GuiPaintPanel.balls.get(i).x=1400;
                    GuiPaintPanel.balls.get(i).y=1400;
                    if(!((AlienShip)(Gui_design.alienShip[1])).shieldIcon() ) {
                        Gui_design.alienShip[1].healthPoint-=10;
                        Gui_design.alienHP-=10;
                    }
                }
                if(intersected3 && !((AlienShip)(Gui_design.alienShip[2])).isDead) {//check for alien3
                    GuiPaintPanel.balls.get(i).x=1400;
                    GuiPaintPanel.balls.get(i).y=1400;
                    if(!((AlienShip)(Gui_design.alienShip[2])).shieldIcon() ) {
                        Gui_design.alienShip[2].healthPoint-=10;
                        Gui_design.alienHP-=10;
                    }
                }
                
            }
        }//end of level1
        
        if(Gui_design.levelcounter ==2) {//check in level2
            for(int i=0; i<Gui_design.mClickCount; i++) {

                intersected=GuiPaintPanel.balls.get(i).intersects(Gui_design.alienX, Gui_design.alieny1_lev23,
                        Gui_design.ufoW,Gui_design.ufoH);
                intersected2=GuiPaintPanel.balls.get(i).intersects(Gui_design.alienX, Gui_design.alieny2_lev23,
                        Gui_design.ufoW,Gui_design.ufoH);
                intersected3=GuiPaintPanel.balls.get(i).intersects(Gui_design.alienX, Gui_design.alieny3_lev23,
                        Gui_design.ufoW,Gui_design.ufoH);
                intersected4=GuiPaintPanel.balls.get(i).intersects(Gui_design.alienX, Gui_design.alieny4_lev23,
                        Gui_design.ufoW,Gui_design.ufoH);
                intersected5=GuiPaintPanel.balls.get(i).intersects(Gui_design.alienX, Gui_design.alieny5_lev23,
                        Gui_design.ufoW,Gui_design.ufoH);

                if(intersected && !((AlienShip)(Gui_design.alienShip[0])).isDead) {//check for alien1
                    GuiPaintPanel.balls.get(i).x=1400;
                    GuiPaintPanel.balls.get(i).y=1400;
                    if(!((AlienShip)(Gui_design.alienShip[0])).shieldIcon() ) {
                        Gui_design.alienShip[0].healthPoint-=10;
                        Gui_design.alienHP-=10;
                        
                    }

                }

                if(intersected2  && !((AlienShip)(Gui_design.alienShip[1])).isDead) {//check for alien2
                    GuiPaintPanel.balls.get(i).x=1400;
                    GuiPaintPanel.balls.get(i).y=1400;
                    if(!((AlienShip)(Gui_design.alienShip[1])).shieldIcon()) {
                        Gui_design.alienShip[1].healthPoint-=10;
                        Gui_design.alienHP-=10;
                    }
                }
                if(intersected3 && !((AlienShip)(Gui_design.alienShip[2])).isDead ) {//check for alien3
                    GuiPaintPanel.balls.get(i).x=1400;
                    GuiPaintPanel.balls.get(i).y=1400;
                    if(!((AlienShip)(Gui_design.alienShip[2])).shieldIcon()) {
                        Gui_design.alienShip[2].healthPoint-=10;
                        Gui_design.alienHP-=10;
                    }
                }
                
                if(intersected4 && !((AlienShip)(Gui_design.alienShip[3])).isDead) {//check for alien4
                    GuiPaintPanel.balls.get(i).x=1400;
                    GuiPaintPanel.balls.get(i).y=1400;
                    if(!((AlienShip)(Gui_design.alienShip[3])).shieldIcon()) {
                        Gui_design.alienShip[3].healthPoint-=10;
                        Gui_design.alienHP-=10;
                    }
                }
                
                if(intersected5 && !((AlienShip)(Gui_design.alienShip[4])).isDead) {//check for alien5
                    GuiPaintPanel.balls.get(i).x=1400;
                    GuiPaintPanel.balls.get(i).y=1400;
                    if(!((AlienShip)(Gui_design.alienShip[4])).shieldIcon() ) {
                        Gui_design.alienShip[4].healthPoint-=10;
                        Gui_design.alienHP-=10;
                    }
                }

            }
        }
        
        if(Gui_design.levelcounter ==3) {//check in level 3
            for(int i=0; i<Gui_design.mClickCount; i++) {

                intersected=GuiPaintPanel.balls.get(i).intersects(Gui_design.alienX, Gui_design.alienBoss.coordY,
                        Gui_design.ufoW,Gui_design.ufoH);
           
                if(intersected && !(Gui_design.alienBoss.isDead)) {//check for alien boss
                    GuiPaintPanel.balls.get(i).x=1400;
                    GuiPaintPanel.balls.get(i).y=1400;
                    if(!(Gui_design.alienBoss).shieldIcon() ) {
                        Gui_design.alienBoss.healthPoint-=10;
                        Gui_design.alienHP-=10;
                        
                    }
                }
            }
        }
        

    }
    protected void moveUp() {//moves the coordinate of spaceShip by 20 unit up
        spaceY=spaceY-20;
        if(spaceY<=0) {
            spaceY=0;
        }
        LSpaceShip.setBounds(spaceX,spaceY,spaceShipIcon.getIconWidth(),spaceShipIcon.getIconHeight());
    }

    protected void moveDown() {//moves the coordinate of spaceShip by 20 unit down
        spaceY=spaceY+20;
        if(spaceY>=576) {
            spaceY=576;
        }
        LSpaceShip.setBounds(spaceX,spaceY,spaceShipIcon.getIconWidth(),spaceShipIcon.getIconHeight());
    }

    
   

}
