import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.atan;
import static java.lang.Math.toDegrees;

public class Gui_design extends JFrame implements KeyListener, MouseListener{

    private JPanel jp;

    private JButton bStart,bInfo,bScore,bQuit;

    static Ship s1= new SpaceShip();
    protected static Ship[] alienShip = new AlienShip[5];
    protected static AlienBoss alienBoss;

    static Timer bossMove,t5;
    static long timeStart;
    private String tim;

    protected static int alienX = 1100;
    protected static int alieny1_lev1 = 90;
    protected static int alieny2_lev1 = 320;
    protected static int alieny3_lev1 = 550;
    protected static int alieny1_lev23 = 24;
    protected static int alieny2_lev23 = 160;
    protected static int alieny3_lev23 = 314;
    protected static int alieny4_lev23 = 459;
    protected static int alieny5_lev23 = 604;

    protected static int alienHP;
    protected static int alienMaxHP;

    private JProgressBar userPoint, alienPoint;
    private JLabel u_pnt, a_pnt, velocity, time, angle;

    protected static int levelcounter;
    protected static int ufoW;
    protected static int ufoH;
    protected int levelinfo;
    
    protected boolean ifLevelEnd=false;

    Gui_design(){
        jp=new GuiPaintPanel();
        setLayout(null);
        setResizable(false);
        setContentPane(jp);
        setSize(1300, 800);

        addMouseListener(this);
        addKeyListener(this);
        
        velocity = new JLabel("Velocity : " + 0 + "km/h"); // + V --> calculate the velocity
        time = new JLabel("Time : " + tim); // + T

        tim = "0:0";

        alienShip[0] = new AlienShip(alienX, alieny1_lev1, 0);
        alienShip[1] = new AlienShip(alienX, alieny2_lev1, 1);
        alienShip[2] = new AlienShip(alienX, alieny3_lev1, 2);
        alienShip[3] = new AlienShip(alienX, alieny4_lev23, 3);
        alienShip[4] = new AlienShip(alienX, alieny5_lev23, 4);
        alienBoss = new AlienBoss();

        alienHP = 150;
        alienMaxHP = 150;
        userPoint = new JProgressBar(0, 150); // progressbar of user
        u_pnt = new JLabel("X / "+ 150, JLabel.LEFT);
        alienPoint = new JProgressBar(0, 150); // progressbar of alien
        a_pnt = new JLabel(150 + " / X", JLabel.RIGHT);
        angle = new JLabel("Angle : " + 0 + "\u00B0");

        t.start();

        menu_center();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        alienIconCoords.start();
        timeSec.start();
    }

    private void menu() {//home page design
    	  jp.setLayout(null);
          bStart= new JButton("START GAME");
          bInfo= new JButton("HOW TO PLAY");
          bQuit= new JButton("QUIT GAME");
          bScore= new JButton("HIGH SCORE LIST");

          bStart.setBackground(Color.WHITE);
          bQuit.setBackground(Color.WHITE);
          bInfo.setBackground(Color.WHITE);
          bScore.setBackground(Color.WHITE);

          bStart.setForeground(Color.DARK_GRAY);
          bInfo.setForeground(Color.DARK_GRAY);
          bQuit.setForeground(Color.DARK_GRAY);
          bScore.setForeground(Color.DARK_GRAY);
          
          bStart.setBounds(550, 300, 150, 40);
          jp.add(bStart);
          bInfo.setBounds(550, 360, 150, 40);
          jp.add(bInfo);
          bScore.setBounds(550, 420, 150, 40);
          jp.add(bScore);
          bQuit.setBounds(550, 480, 150, 40);
          jp.add(bQuit);
    }

    private void setPage() {
        setNorth();
        setSouth();
        setVisible(true);
    }
    private void setNorth() {
        userPoint.setString("");
        userPoint.setStringPainted(true);
        userPoint.setForeground(Color.CYAN);
        userPoint.setBackground(Color.DARK_GRAY);
        userPoint.setValue(150);
        userPoint.setBounds(5, 5, 150, 20);
        jp.add(userPoint);

        u_pnt.setForeground(Color.white);
        u_pnt.setBounds( 160, 10, 70, 10);

        jp.add(u_pnt);

        alienPoint.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        alienPoint.setString("");
        alienPoint.setStringPainted(true);
        alienPoint.setForeground(Color.GREEN);
        alienPoint.setBackground(Color.DARK_GRAY);
        alienPoint.setValue(150);
        alienPoint.setBounds(1130, 5, 150, 20);
        jp.add(alienPoint);

        a_pnt.setForeground(Color.white);
        a_pnt.setBounds( 1055, 10, 70, 10);
        jp.add(a_pnt);
    }
    private void setSouth() {
        velocity.setBackground(Color.BLACK);
        velocity.setForeground(Color.WHITE);
        velocity.setBounds(5, 740, 130, 20);
        jp.add(velocity);

        angle.setBackground(Color.BLACK);
        angle.setForeground(Color.WHITE);
        angle.setBounds(135,  740, 130, 20);
        jp.add(angle);

        time.setBackground(Color.BLACK);
        time.setForeground(Color.WHITE);
        time.setBounds(1210, 740, 130, 20);
        jp.add(time);
    }

    protected static int pbx=0;//mouse's x
    protected static int pby=0;//mouse's y

    public void menu_center(){//menu with buttons' functions
        menu();

        bInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jp.remove(bStart);
                jp.remove(bQuit);
                jp.remove(bInfo);
                jp.remove(bScore);
                repaint();

                info();
            }
        });
        
        bScore.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				jp.remove(bStart);
                jp.remove(bQuit);
                jp.remove(bInfo);
                jp.remove(bScore);
                repaint();
				
				JPanel scorePanel= new JPanel();
				scorePanel.setBackground(Color.BLACK);
		        scorePanel.setBounds(525, 250, 250, 250);
		        
		        JTextArea[] txScores= new JTextArea[5];
		        ArrayList<String> scores=new ArrayList<String>();
		        scores=getHighScoreList();

		        int size=5;
		        if((scores.size()<5)) {
		        	size=scores.size();
		        }
		        JTextArea txTitle= new JTextArea("Last 5 High Scores");
		        txTitle.setEditable(false);
		        txTitle.setBounds(45, 10, 160, 25);
		        txTitle.setFont(new Font("Arial", Font.PLAIN, 20));
		        txTitle.setBackground(Color.BLACK);
		        txTitle.setForeground(Color.ORANGE);
		        scorePanel.add(txTitle);
		        
		        List<String> lastFive =scores.subList((scores.size()-Math.min(scores.size(),size)), scores.size());
		        for(int i=0; i<size; i++) {
		        	txScores[i]=new JTextArea(lastFive.get(i));
		        	txScores[i].setEditable(false);
		        	
		        	txScores[i].setBounds(100, 50+(i*25), 100, 25);
		        	txScores[i].setFont(new Font("Arial", Font.PLAIN, 22));
		        	txScores[i].setBackground(Color.BLACK);
		        	txScores[i].setForeground(Color.WHITE);
		            scorePanel.add(txScores[i]);
		        	
		        }
		        JButton backMenu= new JButton("BACK TO MENU");
		        backMenu.setBackground(Color.WHITE);
		        backMenu.setForeground(Color.DARK_GRAY);
		        backMenu.setBounds(60, 190, 130, 30);
		        scorePanel.add(backMenu);
		        backMenu.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						remove(scorePanel);
						repaint();
						menu_center();
						
					}
				});
		        
		        jp.add(scorePanel);
			}
		});
        
        
        bQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        bStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jp.remove(bStart);
                jp.remove(bQuit);
                jp.remove(bInfo);
                jp.remove(bScore);
                repaint();

                levelinfo = 1;
                leveltransition();
      
                t5=new Timer(100,new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	for(int i=0; i<mClickCount; i++) {
                            

	                       if(!ifLevelEnd) {//move the bullets of spaceShip
	                    	   GuiPaintPanel.balls.get(i).x+=45;
	                           GuiPaintPanel.balls.get(i).y=(GuiPaintPanel.balls.get(i).y)+(calcShootingSlope())*45;
	                       }else {//if level is ended remove the bullets from panel
	                    	   GuiPaintPanel.balls.get(i).x=1400;
	                    	   GuiPaintPanel.balls.get(i).y=1400;
	                       }
                        
                    	}    
	                    s1.hit();
                        repaint();
                    }
                });
                t5.start();
            }
        });
    }

    Timer timeSec=new Timer(200, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (levelcounter != 0) {
                long timeEnd = System.nanoTime();
                tim = time(timeStart, timeEnd);
                time.setText("Time : " + tim);
            }
        }
    });

    static int spacecoordx;
    static int spacecoordy;
    Timer alienIconCoords = new Timer(3000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            spacecoordx = ((SpaceShip)s1).spaceX;
            spacecoordy = ((SpaceShip)s1).spaceY;

            if(levelcounter != 0) {
                for (int i = 0; i < 5; i++) {
                    ((AlienShip)alienShip[i]).seticon();
                    repaint();
                }
                alienBoss.seticon();
            }

        }
    });
    
    private String time(long start, long end) {
        long x = (end-start)/1000000000;
        int y = (int)(x % 60);
        int z = (int)(x/60);
        String s = (z) + ":" + (y % 60);
        return s;
    }

    private void info() { // the gui design of "HOW TO PLAY" button
        JPanel p= new JPanel();
        p.setBackground(Color.BLACK);
        p.setBounds(400, 100, 500, 600);


        JLabel infoTitle= new JLabel("GAME DESCRIPTION");
        infoTitle.setFont(new Font("Arial", Font.PLAIN, 22));
        infoTitle.setBounds(20, 30, 250, 50);
        infoTitle.setForeground(Color.WHITE);
        p.add(infoTitle);


        JTextArea textArea = new JTextArea("You are the Space Ship\nYour ship will appear on the left side of the screen. And your enemies on the other side."
                + "\nYou will have three levels to complete and one boss in the last level."
                + "\nIf you can succefully complete all levels you will win. Once you die you will have to \nstart from level 1."
                + "\n\n");
        textArea.setEditable(false);
        //JLabel info= new JLabel("You are the Space Ship."+"\n"+" Your ship will appear on the left side of the screen");
        textArea.setBounds(20, 70, 450, 150);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);
        p.add(textArea);


        JLabel infoTitle2= new JLabel("HOW TO PLAY");
        infoTitle2.setFont(new Font("Arial", Font.PLAIN, 22));
        infoTitle2.setBounds(20, 220, 250, 50);
        infoTitle2.setForeground(Color.WHITE);
        p.add(infoTitle2);


        JTextArea textArea2 = new JTextArea("\nPress the 'W' key to move up. \n\nPress the 'S' key to move down."
                + "\n\nMove your mouse on the screen to \nchoose your shooting angle \nand left click to shoot");
        textArea2.setEditable(false);
        textArea2.setBounds(20, 275, 250, 140);
        textArea2.setBackground(Color.BLACK);
        textArea2.setForeground(Color.WHITE);
        p.add(textArea2);

        ImageIcon wIcon= new ImageIcon("w.jpg");
        JLabel wLbl=new JLabel(wIcon);
        wLbl.setBounds(270, 275, 34, 31);
        p.add(wLbl);

        ImageIcon sIcon= new ImageIcon("s.jpg");
        JLabel sLbl=new JLabel(sIcon);
        sLbl.setBounds(270, 310, 34, 31);
        p.add(sLbl);

        ImageIcon mouseIcon= new ImageIcon("mouse.png");
        JLabel mouseLbl=new JLabel(mouseIcon);
        mouseLbl.setBounds(260,345,48,58);
        p.add(mouseLbl);

        JButton backToMenu= new JButton("MENU");
        backToMenu.setBackground(Color.WHITE);
        backToMenu.setForeground(Color.DARK_GRAY);
        backToMenu.setBounds(175, 500, 150, 40);
        p.add(backToMenu);


        jp.add(p);


        backToMenu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                jp.remove(p);
                repaint();
                menu_center();
            }
        });
    }

    public void level1() {
    	ifLevelEnd=false;
        highScore = 0;
        levelcounter=1;
        ((AlienShip)alienShip[0]).bonds(alienX, alieny1_lev1);
        ((AlienShip)alienShip[1]).bonds(alienX, alieny2_lev1);
        ((AlienShip)alienShip[2]).bonds(alienX, alieny3_lev1);
        ((AlienShip)alienShip[3]).bonds(alienX, alienX);
        ((AlienShip)alienShip[4]).bonds(alienX, alienX);
        alienMaxHP = 0;
        for (int i = 0; i < 3; i++) {
        	((AlienShip)(alienShip[i])).isDead=false;
        	alienShip[i].healthPoint = 50;
            jp.add(((AlienShip)alienShip[i]).labelUFO);
            alienMaxHP += alienShip[i].healthPoint;
            repaint();
        }
        ((AlienShip)alienShip[0]).labelUFO.setIcon(((AlienShip)alienShip[0]).ufo);
        ((AlienShip)alienShip[1]).labelUFO.setIcon(((AlienShip)alienShip[1]).shieldUFO);
        ((AlienShip)alienShip[2]).labelUFO.setIcon(((AlienShip)alienShip[2]).ufo);

        alienHP=alienMaxHP;
        s1.healthPoint = 150;
        jp.add(((SpaceShip)s1).LSpaceShip);
        ((GuiPaintPanel)jp).initializeCoords();

        ufoW=((AlienShip)alienShip[0]).ufo.getIconWidth();
        ufoH=((AlienShip)alienShip[0]).ufo.getIconHeight();


    }
    public void level2() {
    	ifLevelEnd=false; 
    	s1.healthPoint=150;
        alienHP = alienMaxHP;
        levelcounter = 2;
        ((AlienShip)alienShip[0]).bonds(alienX, alieny1_lev23);
        ((AlienShip)alienShip[1]).bonds(alienX, alieny2_lev23);
        ((AlienShip)alienShip[2]).bonds(alienX, alieny3_lev23);
        ((AlienShip)alienShip[3]).bonds(alienX, alieny4_lev23);
        ((AlienShip)alienShip[4]).bonds(alienX, alieny5_lev23);
        alienMaxHP = 0;
        for (int i = 0; i < 5; i++) {
        	((AlienShip)(alienShip[i])).isDead=false;
            alienShip[i].healthPoint = 50;
            jp.add(((AlienShip)alienShip[i]).labelUFO);
            alienMaxHP += alienShip[i].healthPoint;
            repaint();
        }
        alienHP = alienMaxHP;
        ((AlienShip)alienShip[0]).labelUFO.setIcon(((AlienShip)alienShip[0]).ufo);
        ((AlienShip)alienShip[1]).labelUFO.setIcon(((AlienShip)alienShip[1]).shieldUFO);
        ((AlienShip)alienShip[2]).labelUFO.setIcon(((AlienShip)alienShip[2]).ufo);
        ((AlienShip)alienShip[3]).labelUFO.setIcon(((AlienShip)alienShip[3]).shieldUFO);
        ((AlienShip)alienShip[4]).labelUFO.setIcon(((AlienShip)alienShip[4]).ufo);

        ((GuiPaintPanel)jp).initializeCoords();
        jp.add(((SpaceShip)s1).LSpaceShip);
       
    }
    public void level3() {
    	ifLevelEnd=false; 
    	s1.healthPoint=150;
    	alienBoss.healthPoint=150;
    	alienBoss.isDead=false;
        alienHP = alienBoss.healthPoint;;
        levelcounter = 3;
        alienBoss.coordY = 320;
        alienBoss.coordX = alienX;

        jp.add(alienBoss.labelUFO);
        alienMaxHP = alienBoss.healthPoint;

        jp.add(((SpaceShip)s1).LSpaceShip);
        ((GuiPaintPanel)jp).initializeCoords();

        bossMove = new Timer(15, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(levelcounter != 0) { ((AlienBoss)alienBoss).move(); }
            }
        });
        bossMove.start();
    }
    public void leveltransition() {
        setPage();

        timeStart = System.nanoTime();
        
        if (levelinfo == 1) {levelRestart(); level1(); }
        else if (levelinfo== 2) {levelRestart(); level2();}
        else if(levelinfo==3) {levelRestart();  level3();}
    }
    private void levelRestart() {
        for (int i = 0; i < 5; i += 1) {
            levelcounter = 0;
            ((AlienShip)alienShip[i]).labelUFO.setBounds(alienX, 1100, 100, 100);
            jp.add(((AlienShip)alienShip[i]).labelUFO);
        }
    }
    private void ifDead() {
        for (int i = 0; i < 5; i++) {
            if (alienShip[i].healthPoint <=0) {
                jp.remove(((AlienShip)alienShip[i]).labelUFO);
                ((AlienShip)alienShip[i]).isDead=true;
                repaint();
            }
        }
        if (alienBoss.healthPoint <=0) {
            jp.remove(alienBoss.labelUFO);
            alienBoss.isDead=true;
            repaint();
        }
        if(alienHP<=0) {
        	if(counter == 0) {
        		counter++;
                levelMessage();
            }
        }
        
        if(s1.healthPoint<=0) {
        	gameOver();
        }
    }

    private int counter = 0;
    private void levelMessage() {
    	ifLevelEnd=true;
        levelcounter = 0;
        JButton backMenu;
        JButton goLevel;
        JLabel level;
        JLabel hscoreLabel;
        highScore += alienMaxHP;
        if(levelinfo==3) {
            saveHighScore();
        	level = new JLabel("YOU WON !");
	        level.setFont(new Font("Arial", Font.PLAIN, 50));
	        level.setForeground(Color.YELLOW );
	        level.setBounds(553,200,400,150);
	        jp.add(level);
	        backMenu = new JButton("BACK TO MENU");
	        backMenu.setBounds(593,400,200,50);
	        backMenu.setBackground(Color.WHITE);
	        backMenu.setForeground(Color.DARK_GRAY);
	        jp.add(backMenu);
	        hscoreLabel = new JLabel("High Score : " + highScore);
            hscoreLabel.setFont(new Font("Arial", Font.PLAIN, 30));
            hscoreLabel.setForeground(Color.YELLOW );
            hscoreLabel.setBounds(580,270,400,150);
            jp.add(hscoreLabel);
            goLevel = new JButton("NEXT LEVEL");
	        
        }else {
	        level = new JLabel("Level " + levelinfo + " completed!");
	        level.setFont(new Font("Arial", Font.PLAIN, 50));
	        level.setForeground(Color.YELLOW );
	        level.setBounds(443,200,1000,150);
	        jp.add(level);
	        backMenu = new JButton("BACK TO MENU");
	        backMenu.setBounds(443,400,200,50);
	        backMenu.setBackground(Color.WHITE);
	        backMenu.setForeground(Color.DARK_GRAY);
	        jp.add(backMenu);
	        goLevel = new JButton("NEXT LEVEL");
	        goLevel.setBackground(Color.WHITE);
	        goLevel.setForeground(Color.DARK_GRAY);
	        goLevel.setBounds(653,400,200,50);
	        jp.add(goLevel);
            hscoreLabel = new JLabel("Score : " + highScore);
            hscoreLabel.setFont(new Font("Arial", Font.PLAIN, 30));
            hscoreLabel.setForeground(Color.YELLOW );
            hscoreLabel.setBounds(559,270,400,150);
            jp.add(hscoreLabel);
        }
        
        backMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                levelcounter= 0;
                alienHP = 10;
                jp.remove(goLevel);
                jp.remove(backMenu);
                jp.remove(level);
                jp.remove(hscoreLabel);
                removeSetPage();
                repaint();
                removeShip();
                menu_center();
                counter=0;
            }
        });
        
        goLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                levelcounter = 0;
                levelinfo++;
                jp.remove(goLevel);
                jp.remove(backMenu);
                jp.remove(level);
                jp.remove(hscoreLabel);
                leveltransition();
                counter=0;
            }
        });
        
    }

    private JLabel gameOverL, hscoreLabel;
    private JButton backMenu;
    
    private void gameOver() {
    	levelcounter=0;
    	s1.healthPoint=150;

    	highScore += alienMaxHP - alienHP;
    	saveHighScore();

    	alienHP = alienMaxHP;
    	gameOverL=new JLabel("GAME OVER");
        gameOverL.setFont(new Font("Arial", Font.PLAIN, 50));
        gameOverL.setForeground(Color.YELLOW );
        gameOverL.setBounds(480,200,1000,150);
        jp.add(gameOverL);
        
        backMenu= new JButton("BACK TO MENU"); 
        backMenu.setBounds(550, 400, 150, 40);
        backMenu.setBackground(Color.WHITE);
        backMenu.setForeground(Color.DARK_GRAY);
        jp.add(backMenu);
        repaint();
        GuiPaintPanel.balls.clear();
        mClickCount=0;

        if ((printHighScore() != "") && (highScore == Integer.parseInt(printHighScore()))) {
            hscoreLabel = new JLabel("High Score : " + highScore);
            hscoreLabel.setFont(new Font("Arial", Font.PLAIN, 30));
            hscoreLabel.setForeground(Color.YELLOW );
            hscoreLabel.setBounds(515,270,400,150);
            jp.add(hscoreLabel);
        }
        else {
            hscoreLabel = new JLabel("Score : " + highScore);
            hscoreLabel.setFont(new Font("Arial", Font.PLAIN, 30));
            hscoreLabel.setForeground(Color.YELLOW);
            hscoreLabel.setBounds(550, 270, 400, 150);
            jp.add(hscoreLabel);
        }

        backMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				removeShip();
				jp.remove(backMenu);
				jp.remove(gameOverL);
				jp.remove(hscoreLabel);
				removeSetPage();
				menu_center();
				repaint();
			}
		});
    	
    }


    private int highScore = 0;

    private String printHighScore() {
        String line = "";
        String temp = "";
        try {
            FileReader filereader = new FileReader("highScore.txt");
            BufferedReader bfrinp = new BufferedReader(filereader);

            while ((temp = bfrinp.readLine()) != null)
            {
                line = temp;
            }

            bfrinp.close();
            filereader.close();
        }
        catch(Exception e) {
            e.getStackTrace();
        }
        return line;
    }

    private ArrayList<String> getHighScoreList() {// gets the highscore information from the text file
   	 String lastLine = "";
        String line = "";
        ArrayList<String>highScores=new ArrayList<String>();
        
        try {
            FileReader filereader = new FileReader("highScore.txt");
            BufferedReader bfrinp = new BufferedReader(filereader);

            while ((line = bfrinp.readLine()) != null)
            {
                highScores.add(line);
            }
            System.out.println("Data in the file:" + line);

            bfrinp.close();
            filereader.close();
        }
        catch(Exception e) {
            e.getStackTrace();
        }
        return highScores;
   }
    

    private void saveHighScore() {
        try {
            if (highScore > Integer.parseInt(printHighScore())) {
                String data = Integer.toString(highScore);
                try {
                    FileWriter filereader = new FileWriter("highScore.txt", true);
                    BufferedWriter bfwr = new BufferedWriter(filereader);

                    bfwr.write(data);
                    bfwr.newLine();

                    bfwr.close();
                    filereader.close();
                }
                catch (Exception e) {
                    e.getStackTrace();
                }
            }
        }
        catch (Exception e) {
            String data = Integer.toString(highScore);
            try {
                FileWriter filereader = new FileWriter("highScore.txt", true);
                BufferedWriter bfwr = new BufferedWriter(filereader);

                bfwr.write(data);
                bfwr.newLine();

                bfwr.close();
                filereader.close();
            }
            catch (Exception ex) {
                ex.getStackTrace();
            }
        }
    }

    
    private void removeShip() {//remove ship images from panel
    	jp.remove(((SpaceShip)s1).LSpaceShip);
    	jp.remove(alienBoss.labelUFO);
    	for (int i = 0; i < 5 ; i++) {
    	    jp.remove(((AlienShip)alienShip[i]).labelUFO);
    	}
    }
    private void removeSetPage() {//removes the item from setPage panel
        jp.remove(userPoint);
        jp.remove(alienPoint);
        jp.remove(u_pnt);
        jp.remove(a_pnt);
        jp.remove(velocity);
        jp.remove(angle);
        jp.remove(time);
    }


    Timer t = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int m = 0;
            if(levelcounter == 1) { m = 3; attackControl((AlienShip[]) alienShip, m); }
            else if(levelcounter == 2) { m = 5; attackControl((AlienShip[]) alienShip, m); }
            else if(levelcounter == 3) { attackControl(alienBoss); }

            int al = alienHP * s1.maxHealthPoint / alienMaxHP;
            userPoint.setValue(s1.healthPoint);
            a_pnt.setText(alienMaxHP + " / " + alienHP);
            u_pnt.setText(s1.healthPoint + " / "+ s1.maxHealthPoint);
            alienPoint.setValue(al);


            Point mouse = MouseInfo.getPointerInfo().getLocation();
            double y = ( ((SpaceShip)s1).LSpaceShip.getIcon().getIconHeight()/2 + ((SpaceShip)s1).spaceY + 44) - mouse.y;
            double x = mouse.x - ( ((SpaceShip)s1).LSpaceShip.getIcon().getIconWidth() + 18);
            double degs = toDegrees(atan(y/x)); //tan(angle)
            angle.setText("Angle : " + degs + "\u00B0");

            ifDead();
        }
    });

    public void attackControl(AlienShip ship[], int m) {
        for (int i = 0; i < m; i++) {
            if (!(ship[i]).shieldIcon()) {
                for (int j = 0; j < m; j++) {
                    if (ship[j].healthPoint > 0) {
                        ship[j].hit();
                        repaint();
                    }
                }
                if (GuiPaintPanel.ball[i].intersects(((SpaceShip)s1).spaceX, ((SpaceShip)s1).spaceY,
                        ((SpaceShip) s1).spaceShipIcon.getIconWidth(), ((SpaceShip) s1).spaceShipIcon.getIconHeight())) {
                    GuiPaintPanel.ball[i].x = 1400;
                    GuiPaintPanel.ball[i].y = 1400;
                    s1.healthPoint -= 10;
                }
            }
            else {
                GuiPaintPanel.ball[i].x = 1400;
                GuiPaintPanel.ball[i].y = 1400;
            }
        }
    }
    public void attackControl(AlienBoss ship) {
        if (!(ship.shieldIcon())) {
            if (ship.healthPoint > 0) {
                ship.hit();
                repaint();
            }
            if (GuiPaintPanel.ball[0].intersects(((SpaceShip)s1).spaceX, ((SpaceShip)s1).spaceY,
                    ((SpaceShip) s1).spaceShipIcon.getIconWidth(), ((SpaceShip) s1).spaceShipIcon.getIconHeight())) {
                GuiPaintPanel.ball[0].x = 1400;
                GuiPaintPanel.ball[0].y = 1400;
                s1.healthPoint -= 10;
            }
        }
        else {
            GuiPaintPanel.ball[0].x = 1400;
            GuiPaintPanel.ball[0].y = 1400;
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyPressed(KeyEvent e) {
    	
        if(e.getKeyCode()==KeyEvent.VK_W) {
            ((SpaceShip)s1).moveUp();
            velocity.setText("Velocity : 20km/h");
        }
        if(e.getKeyCode()==KeyEvent.VK_S) {
            ((SpaceShip)s1).moveDown();
            velocity.setText("Velocity : 20km/h");
        }
        jp.add(((SpaceShip)s1).LSpaceShip);
        repaint();


    }

    @Override
    public void keyReleased(KeyEvent e) {
        velocity.setText("Velocity : 0km/h"); //spaceship isn't moving
    }


    static int mClickCount,ballIndex=0;

    @Override
    public void mouseClicked(MouseEvent e) {
        mClickCount++;
        GuiPaintPanel.addBall();//when clicked att a bullet to the paint panel
        Point pb = getMousePosition();//position to send balls to alienships
        pbx=pb.x;
        pby=pb.y;

        if((GuiPaintPanel.balls.size()>1)) {
            ballIndex++;
        }
        else {
            ballIndex = 0;
        }
        repaint();

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    private double slope;
    private double calcShootingSlope() {//calculates to slope to direct the spaceShip's bullets to aliens
        slope=(pby-GuiPaintPanel.balls.get(ballIndex).y)/(pbx-GuiPaintPanel.balls.get(ballIndex).x);
        return slope;
    }
}
