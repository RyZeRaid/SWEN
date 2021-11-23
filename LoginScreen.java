package project;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The login Screen class creates the Gui to allow the user to login
 *Login Screen allows the use to enter a user name and password to enter the application
 */

public class LoginScreen  {

    private JFrame frame = new JFrame();
    private JLabel login = new JLabel("Login");
    private JButton enter;
    private JTextField txtName;
    private JPasswordField txtPass;
    
    /**
     * This constructor sets up the componets for the gui seen by the user
     */
    public LoginScreen(){


        login.setFont(new Font("MV Boli", Font.BOLD, 100));


		JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();

        panel.setBackground(Color.YELLOW);
        panel1.setBackground(Color.YELLOW);
        panel2.setBackground(Color.YELLOW);

        panel1.setLayout(new GridLayout(3,4));
        panel2.setLayout(new GridLayout(3,0));

        panel.add(login);
        panel1.add(new JLabel("Username: "));
        txtName = new JTextField(20);
        panel1.add(txtName);
        panel1.add(new JLabel("Password: "));
        txtPass = new JPasswordField(20);
        panel1.add(txtPass);
        enter = new JButton("Login");
        enter.addActionListener(new LoginActionListener());
        panel1.add(enter);
        panel2.add(new JLabel("This Login System is a Prototype and does not actually work"));
        panel2.add(new JLabel("The Default Username is: Tester12"));
        panel2.add(new JLabel("The Default Password is: Password12"));

		frame.add(panel, BorderLayout.NORTH);
        frame.add(panel1, BorderLayout.CENTER);
        frame.add(panel2, BorderLayout.SOUTH);
		frame.setSize(500,400);
        frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Home Screen");
		frame.setVisible(true);
    }

    private class LoginActionListener implements ActionListener{

        public void actionPerformed(ActionEvent e){
            String user = txtName.getText();
            String password = txtPass.getText();
            
            if (user.equals("Tester12") && password.equals("Password12")){
                frame.setVisible(false);
                new Promoter();
            }else{
                JOptionPane.showMessageDialog(null, "Incorrect Username or Password", "Wrong Information", JOptionPane.ERROR_MESSAGE);
            }   

        }
    }

}
