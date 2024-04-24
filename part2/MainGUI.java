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
    settingsPanel controlPanel;

    public MyFrame() {
        panel = new HorsePanel(1,1);
        panel2 = new HorsePanel(1,2);
        panel3 = new HorsePanel(1,3);
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
    }
}
class HorsePanel extends JPanel implements ActionListener {
    final int PANEL_WIDTH = 1600;
    final int PANEL_HEIGHT = 500;
    private ImageIcon[] imageArray;
    private Image background;
    private Image backgroundWithSun;
    Timer timer;
    private int delay = 175, totalFrames = 5, currentFrame = 0;
    int xVelocity = 4;
    int x = 0;
    int y ;
    int horseLane;

    public HorsePanel(int y, int horseLane) {
        this.y = y;
        this.horseLane = horseLane;
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        background = new ImageIcon("background.png").getImage();
        backgroundWithSun = new ImageIcon("backgroundWithSun.png").getImage();
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
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        x += xVelocity;
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