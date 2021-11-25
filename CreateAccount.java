import java.io.*;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class CreateAccount {
    
    private JFrame frame = new JFrame();
    private JLabel createAcc = new JLabel("Create Account");
    private JButton create;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtUserName;
    private JTextField txtPhoneNumber;
    private JPasswordField txtPass;
    
    /**
     * This constructor sets up the componets for the gui seen by the user
     */
    public CreateAccount(){


        createAcc.setFont(new Font("MV Boli", Font.BOLD, 100));


		JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();


        panel.setBackground(Color.YELLOW);
        panel1.setBackground(Color.YELLOW);


        panel1.setLayout(new GridLayout(6,6));


        panel.add(createAcc);
        panel1.add(new JLabel("Enter First Name: "));
        txtFirstName = new JTextField(20);
        panel1.add(txtFirstName);
        panel1.add(new JLabel("Enter Last Name: "));
        txtLastName = new JTextField(20);
        panel1.add(txtLastName);
        panel1.add(new JLabel("Enter Phone Number: "));
        txtPhoneNumber = new JTextField(20);
        panel1.add(txtPhoneNumber);
        panel1.add(new JLabel("Create Username: "));
        txtUserName = new JTextField(20);
        panel1.add(txtUserName);
        panel1.add(new JLabel("Create Password: "));
        txtPass = new JPasswordField(20);
        panel1.add(txtPass);
        create = new JButton("Create Account");
        create.addActionListener(new CreateActionListener());
        panel1.add(create);

		frame.add(panel, BorderLayout.NORTH);
        frame.add(panel1, BorderLayout.CENTER);
		frame.setSize(500,400);
        frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Home Screen");
		frame.setVisible(true);
    }

    private class CreateActionListener implements ActionListener{

        public void actionPerformed(ActionEvent e){
            String firstName = txtFirstName.getText();
            String lastName = txtLastName.getText();
            String phoneNumber = txtPhoneNumber.getText();
            String user = txtUserName.getText();
            String password = txtPass.getText();

            // name: ^(([A-za-z]+[\s]{1}[A-za-z]+)|([A-Za-z]+))$

            //Pattern phone = Pattern.compile("^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$", Pattern.CASE_INSENSITIVE);
            //Matcher matcher = phone.matcher(phoneNumber);
            //matcher.find();
            
            if (firstName.equals("") || lastName.equals("") || phoneNumber.equals("") || user.equals("") || password.equals("")){
                JOptionPane.showMessageDialog(null, "Please fill out informaiton in all fields", "Fill Fields", JOptionPane.ERROR_MESSAGE);
            }else{
                try{// adding the person to a request file 
                    BufferedWriter bw = new BufferedWriter(new FileWriter("Request.txt", true));
                    String ReqAcc = firstName + " " + lastName + " " + phoneNumber + " " + user +" " + password;
                    bw.write(ReqAcc + "\n");
                    bw.close();
                    JOptionPane.showMessageDialog(null, "Your request for an account has been sent and is pending.", "Request Sent", JOptionPane.INFORMATION_MESSAGE);
                }catch(IOException y){
                    // do nothing
                } 
            }

             
    
        }
    }
}


