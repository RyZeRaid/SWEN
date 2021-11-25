
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JProgressBar;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Welcome is a class that shows the user a intro to before the app opens
 */

public class Welcome {
    
    private ImageIcon image = new ImageIcon("giphy.gif");
    private JFrame frame = new JFrame();
    private JLabel label = new JLabel();
    private JPanel panel = new JPanel();
    private JButton button = new JButton("Press to Continue");
    private JProgressBar bar = new JProgressBar();
    private LoginScreen loginScreen;

    
    /**
     * This constructor sets up the componets for the gui seen by the user 
     */
    public Welcome(){

        bar.setStringPainted(true);

        button.setBounds(165, 10, 150, 50);
        button.setBackground(Color.YELLOW);
        button.setVisible(false);

        label.setIcon(image);
        label.add(button);

		panel.setLayout(new GridLayout(0,1));
        panel.setBackground(Color.WHITE);
        panel.add(label);
        

		
        frame.add(bar, BorderLayout.NORTH);
        frame.add(panel);
        frame.setSize(500,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Welcome");
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        fill();

        button.setVisible(true);
        button.addActionListener(new StartActionListener());

    }

     private class StartActionListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e){
            frame.setVisible(false);
            loginScreen = new LoginScreen();
        }
    }

    /**
     * This method fills the progress bar to simulate loading
     */
    public void fill(){
        int counter = 0;

        while(counter <=100){

            bar.setValue(counter);
            try{
                Thread.sleep(40);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            counter +=1;
        }
    }

    
}
