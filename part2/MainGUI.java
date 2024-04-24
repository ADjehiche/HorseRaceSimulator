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
    controlPanel controlPanel;

    public MyFrame() {
        Race race = new Race(15);
        Horse horse1 = new Horse('1', "John", 0.5);
        Horse horse2 = new Horse('2', "Rafs", 0.5);
        Horse horse3 = new Horse('3', "Zans", 0.5);
        
        panel = new HorsePanel(1,1, horse1, race, "Pollimino");
        panel2 = new HorsePanel(1,2, horse2, race, "Arabian");
        panel3 = new HorsePanel(1,3, horse3, race , "Grullo");

        race.addHorse(horse1, 1);
        race.addHorse(horse2, 2);
        race.addHorse(horse3, 3);

        controlPanel = new controlPanel(panel, panel2, panel3);

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
    int PANEL_WIDTH = 1600;
    final int PANEL_HEIGHT = 500;
    private ImageIcon[] imageArray;
    private Image background;
    private Image backgroundWithSun;
    Timer timer;
    private int delay = 175, totalFrames = 5, currentFrame = 0;
    int xVelocity = 4;
    int x = 0;
    int y;
    int horseLane;
    private Horse theHorse;
    private Race race;
    String breed;

    public HorsePanel(int y, int horseLane, Horse theHorse, Race race, String breed) {
        this.theHorse = theHorse;
        this.race = race;
        this.y = y;
        this.horseLane = horseLane;
        this.breed = breed;
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        loadImages(breed);
        background = new ImageIcon("background1600.png").getImage();
        backgroundWithSun = new ImageIcon("backgroundWithSun1600.png").getImage();
        timer = new Timer(delay, this);
    }
    private void loadImages(String breed) {
        imageArray = new ImageIcon[6];
        for (int i = 0; i < imageArray.length - 1; i++) {
            imageArray[i] = new ImageIcon(breed + i + ".png");
        }
        imageArray[5] = new ImageIcon(breed + "Fallen.png");
    }
    public void changeHorseStyle(String newStyle) {
        this.breed = newStyle;
        loadImages(newStyle);
        repaint();
    }
    public Horse getHorse() {
        return theHorse;
    }
    public Race getRace(){
        return this.race;
    }
    public int getY(){
        return this.y;
    }
    public void setPanelWidth(int width) {
        this.PANEL_WIDTH = width;
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        background = new ImageIcon("background" +width+".png").getImage();
        backgroundWithSun = new ImageIcon("backgroundWithSun" +width+".png").getImage();
        if(horseLane == 1){
            getGraphics().drawImage(backgroundWithSun, 0, getY(), this.getWidth(), this.getHeight(), this);
        }else{
        getGraphics().drawImage(background, 0, getY(), this.getWidth(), this.getHeight(), this);
        }
        this.revalidate();
    }
    
    public void startAnimation() {
        timer.start();
        this.theHorse.goBackToStart();
        x =0;
    }

    public void stopAnimation() {
        timer.stop();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(horseLane == 1){
            g.drawImage(backgroundWithSun, 0, getY(), this.getWidth(), this.getHeight(), this);
        }else{
        g.drawImage(background, 0, getY(), this.getWidth(), this.getHeight(), this);
        }
        if (imageArray[currentFrame] != null || currentFrame != 5) {
            imageArray[currentFrame].paintIcon(this, g, x, 0);
        }else if(theHorse.hasFallen){
           imageArray[5].paintIcon(this, g, x, 0);
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
        race.RaceFinished(false);
        if(theHorse.hasFallen()==false){
        moveHorse();
        x = x + theHorse.getDistanceTravelled();
        }
        if(race.isFinished()){
            stopAnimation();
            currentFrame = 4;
        }
        if (x >= PANEL_WIDTH - imageArray[0].getIconWidth()) {
            getHorse().setConfidence(getHorse().getConfidence() + 0.1);
            currentFrame = 4;
            stopAnimation();
            race.RaceFinished(true);
            JOptionPane.showMessageDialog(this, "Horse " + getHorse().getName() + " is the winner");
        }else if(theHorse.hasFallen()){
            currentFrame = 5;
            stopAnimation();
            }else {
            currentFrame = (currentFrame + 1) % totalFrames;
        }
        repaint();
    }
}


class controlPanel extends JPanel {
    private JRadioButton length1000, length1300, length1600;
    private ButtonGroup lengthGroup;
    private JButton startButton;
    private JComboBox<String> horse1Breed, horse2Breed, horse3Breed;
    private JLabel horse1Stats, horse2Stats, horse3Stats;
    
    public controlPanel(HorsePanel... horses) {
        this.setPreferredSize(new Dimension(1600, 200));
        startButton = new JButton("Start");
        String[] breeds = {"Palomino", "Arabian", "Grullo"};
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        horse1Stats = new JLabel();
        horse2Stats = new JLabel();
        horse3Stats = new JLabel();

        updateHorseStats(horses);

        addComponent(horse1Stats, gbc);
        addComponent(horse2Stats, gbc);
        addComponent(horse3Stats, gbc);

        horse1Breed = new JComboBox<>(breeds);
        horse2Breed = new JComboBox<>(breeds);
        horse3Breed = new JComboBox<>(breeds);

        horse1Breed.setSelectedIndex(0);
        horse2Breed.setSelectedIndex(1);
        horse3Breed.setSelectedIndex(2);

        length1000 = new JRadioButton("1000 meters");
        length1300 = new JRadioButton("1300 meters");
        length1600 = new JRadioButton("1600 meters");
        length1600.setSelected(true); 

        lengthGroup = new ButtonGroup();
        lengthGroup.add(length1000);
        lengthGroup.add(length1300);
        lengthGroup.add(length1600);

        add(length1000);
        add(length1300);
        add(length1600);
        add(new JLabel("Horse 1 Style:"));
        add(horse1Breed);
        add(new JLabel("Horse 2 Style:"));
        add(horse2Breed);
        add(new JLabel("Horse 3 Style:"));
        add(horse3Breed);
        add(startButton);
    
        startButton.addActionListener(e -> {
            horses[0].changeHorseStyle((String)horse1Breed.getSelectedItem());
            horses[1].changeHorseStyle((String)horse2Breed.getSelectedItem());
            horses[2].changeHorseStyle((String)horse3Breed.getSelectedItem());
            int selectedLength = getSelectedTrackLength();
            updateHorseStats(horses);
            for (HorsePanel horsePanel : horses) {
                horsePanel.getRace().setRaceLength(selectedLength);
                horsePanel.setPanelWidth(selectedLength);
                horsePanel.startAnimation();
            }
        });
    }
    private int getSelectedTrackLength() {
        if (length1300.isSelected()) {
            return 1300;
        } else if (length1000.isSelected()) {
            return 1000;
        } else {
            return 1600;
        }
    }
    private void updateHorseStats(HorsePanel... horses) {
        horse1Stats.setText("Horse 1 - " + horses[0].getHorse().getName() + " Confidence: " + horses[0].getHorse().getConfidence());
        horse2Stats.setText("Horse 2 - " + horses[1].getHorse().getName() + " Confidence: " + horses[1].getHorse().getConfidence());
        horse3Stats.setText("Horse 3 - " + horses[2].getHorse().getName() + " Confidence: " + horses[2].getHorse().getConfidence());
    }
    private void addComponent(Component component, GridBagConstraints gbc) {
        this.add(component, gbc);
    }
}
    