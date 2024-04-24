import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainGUI
{
    public static void main(String[] args){
        new MyFrame();
    }
}

 class MyFrame extends JFrame {
    HorsePanel panel;
    HorsePanel panel2;
    HorsePanel panel3;
    boolean finished = false;
    settingsPanel controlPanel;

    public MyFrame() {
        Race race = new Race(15);
        Horse horse1 = new Horse('1', "John", 0.5);
        Horse horse2 = new Horse('2', "Rafs", 0.5);
        Horse horse3 = new Horse('3', "Zans", 0.5);
        
        panel = new HorsePanel(1,1, horse1);
        panel2 = new HorsePanel(1,2, horse2);
        panel3 = new HorsePanel(1,3, horse3);

        race.addHorse(horse1, 1);
        race.addHorse(horse2, 2);
        race.addHorse(horse3, 3);

        controlPanel = new settingsPanel(panel, panel2, panel3);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel centerPanel = new JPanel(new GridLayout(3, 1));
        centerPanel.add(panel);
        centerPanel.add(panel2);
        centerPanel.add(panel3);

        add(centerPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        pack();
        setVisible(true);

        horse1.goBackToStart();
        horse2.goBackToStart();
        horse3.goBackToStart();

    }
}
class HorsePanel extends JPanel implements ActionListener {
    final int PANEL_WIDTH = 1600;
    final int PANEL_HEIGHT = 500;
    private ImageIcon[] imageArray;
    private Image background;
    private Image backgroundWithSun;
    private Image fallenHorse;
    Timer timer;
    private int delay = 175, totalFrames = 5, currentFrame = 0;
    int xVelocity = 4;
    int x = 0;
    int y ;
    int horseLane;
    private Horse theHorse;

    public HorsePanel(int y, int horseLane, Horse theHorse) {
        this.theHorse = theHorse;
        this.y = y;
        this.horseLane = horseLane;
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        background = new ImageIcon("background.png").getImage();
        backgroundWithSun = new ImageIcon("backgroundWithSun.png").getImage();
        fallenHorse = new ImageIcon("frameFallen.png").getImage();
        imageArray = new ImageIcon[totalFrames];
        for (int i = 0; i < imageArray.length; i++) {
            imageArray[i] = new ImageIcon("frame" + i + ".png");
        }
        timer = new Timer(delay, this);
    }

    public void startAnimation() {
        timer.start();
    }

    public void stopAnimation() {
        timer.stop();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(horseLane == 1){
            g.drawImage(backgroundWithSun, 0, this.y, this.getWidth(), this.getHeight(), this);
        }else{
        g.drawImage(background, 0, this.y, this.getWidth(), this.getHeight(), this);
        }
        if (imageArray[currentFrame] != null) {
            imageArray[currentFrame].paintIcon(this, g, x, 0);
        }else if(theHorse.hasFallen){
           g.drawImage(fallenHorse, x, this.y, null);
        }
    }
    public void moveHorse()
    {
        
        if  (!theHorse.hasFallen())
        {
            if (Math.random() < theHorse.getConfidence())
            {
               theHorse.moveForward();
            }
            
            if (Math.random() < (0.1*theHorse.getConfidence()*theHorse.getConfidence()))
            {
                theHorse.fall();
                if(theHorse.getConfidence()>0.0){
                    theHorse.setConfidence(theHorse.getConfidence()-0.1);
                }else{
                    theHorse.setConfidence(0.1);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moveHorse();
        x = x + theHorse.getDistanceTravelled();
        if (x >= PANEL_WIDTH - imageArray[0].getIconWidth()) {
            x = 0;
            currentFrame = 4;
            stopAnimation();
        } else {
            currentFrame = (currentFrame + 1) % totalFrames;
        }
        repaint();
    }
}


class settingsPanel extends JPanel {
    
    public settingsPanel(HorsePanel... horses) {
        this.setPreferredSize(new Dimension(1600, 100));
        JButton startButton = new JButton("Start");
    
        startButton.addActionListener(e -> {
            for (HorsePanel horse : horses) {
                horse.startAnimation();
            }
        });
    
        this.add(startButton);
    }
    }