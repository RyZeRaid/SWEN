import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

/**
 * @author Onandi Skeen
 * Add Promoter Class allowes the use to add a promoter to the current list of promoters
 */

public class AddPromoter {

    private JLabel addprm= new JLabel("ADD MEMBER");
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JButton enter;
    private JButton done;

    private JTextField txtName;
    private JTextField txtDoB;
    private JTextField txtAge;
    private JTextField txtGen;
    private JTextField txtPosition;
    private JTextField txtpnum;
    private JTextField txtemail;
    private JTextField txthome;

    /**
     * This constructor sets up the componets for the gui seen by the user
     */
    public AddPromoter(){

        addprm.setFont(new Font("MV Boli", Font.BOLD, 50));

        panel.add(addprm);
        panel.setBackground(Color.YELLOW);
        panel1.setBackground(Color.YELLOW);

        panel1.setLayout(new GridLayout(8,2));
        panel2.setLayout(new GridLayout(1,0));

        panel1.add(new JLabel("Members Name: "));
        txtName = new JTextField(20);
        panel1.add(txtName);
        panel1.add(new JLabel("date of birth (MM/dd/yyyy): "));
        txtDoB = new JTextField(20);
        panel1.add(txtDoB);
        panel1.add(new JLabel("Age: "));
        txtAge = new JTextField(20);
        panel1.add(txtAge);
        panel1.add(new JLabel("gender: "));
        txtGen = new JTextField(20);
        panel1.add(txtGen);
        panel1.add(new JLabel("Position: "));
        txtPosition = new JTextField(20);
        panel1.add(txtPosition);
        panel1.add(new JLabel("Phone Number: "));
        txtpnum = new JTextField(20);
        panel1.add(txtpnum);
        panel1.add(new JLabel("Email Address: "));
        txtemail = new JTextField(20);
        panel1.add(txtemail);
        panel1.add(new JLabel("Home Address: "));
        txthome = new JTextField(20);
        panel1.add(txthome);
        enter = new JButton("Add Member");
        done = new JButton("Finished Adding");
        enter.addActionListener(new EnterActionListener());
        done.addActionListener(new DoneActionListener());
        panel2.add(enter);
        panel2.add(done);

        

		
        frame.add(panel, BorderLayout.NORTH);
        frame.add(panel1, BorderLayout.CENTER);
        frame.add(panel2, BorderLayout.SOUTH);
        frame.setSize(500,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Add Promoter");
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    
    /** 
     * This method generates the id number that will be given to the new promoter
     * 
     * @return String this is will become the id number of the newly entered promoter  
     */
    public String id(){
        ArrayList<String> idNumbers = new ArrayList<String>();
        int id = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader("promoter.txt"));
            File file = new File("TrashBin.txt");

            String s, t;
                    
            while((s = br.readLine()) != null){
                String[] line = s.split(" ");
                idNumbers.add(line[0]);   
            }
            br.close();

            if (file.exists()){
                BufferedReader bre = new BufferedReader(new FileReader("TrashBin.txt"));
                while((t = bre.readLine()) != null){
                    String[] lne = t.split(" ");
                    idNumbers.add(lne[0]);   
                }
                bre.close();
            }
            
            while (idNumbers.contains(id+"")){
                id++;
            }
            
        }catch(IOException e){
            //do nothing
        }

        return id+"";
    }

    public static boolean validateDate(String strDate)
   {
	if (strDate.trim().equals(""))
	{
	    return true;
	}
	else
	{
	    SimpleDateFormat sdfrmt = new SimpleDateFormat("MM/dd/yyyy");
	    sdfrmt.setLenient(false);
	    try
	    {
	        Date Date = sdfrmt.parse(strDate); 
            
	    }
	    catch (ParseException e)
	    {
	        return false;
	    }
	    return true;
	}
   }

   public static String gendercheck(String gender){

    if( gender.equals("f") || gender.equals("F") || gender.equals("female") || gender.equals("Female") || gender.equals("FEMALE") )
    {
         return "Female";
    }
    else if( gender.equals("m") ||gender.equals("M") ||gender.equals("male") || gender.equals("Male") ||gender.equals("MALE"))
    {
       return"Male";
    }
    return gender;
   }

    // When the button is pressed the user is information entered by the user will be taken and added to the file 
    private class EnterActionListener implements ActionListener{

        public void actionPerformed(ActionEvent e){
            String user = txtName.getText();
            String  Dob = txtDoB.getText();
            String Age = txtAge.getText();
            String gender = txtGen.getText();
            String position = txtPosition.getText();
            String phonenum = txtpnum.getText();
            String email = txtemail.getText();
            String homeadd = txthome.getText();  

            String[] name = user.split(" ");
            int nomatch =  0 ;

            gender = gendercheck(gender);

            System.out.println("worked");
            if(name.length > 2){// ensuring that only the first name and last name is entered
                JOptionPane.showMessageDialog(null, "Only first name and last name required", "Please Enter Right Information", JOptionPane.ERROR_MESSAGE);
            }
            else if(user.equals("") || Dob.equals("") || Age.equals("") || gender.equals("") || position.equals("") || phonenum.equals("") || email.equals("") || homeadd.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Please Enter Information for all feilds", "No Information Entered", JOptionPane.ERROR_MESSAGE);
            }
            else if(gender.equals("Female") != true && gender.equals("Male") != true)
            {
                JOptionPane.showMessageDialog(null, "Please Enter the Genders of the members as 'Male' or 'Female' "+ gender, "Please Enter the Right Information", JOptionPane.ERROR_MESSAGE);
            }
            else if(validateDate(Dob) == false){
                JOptionPane.showMessageDialog(null, "Please enter the Date of Birth in the correct format", "Please enter the right information", JOptionPane.ERROR_MESSAGE);
            }else{
                System.out.println("passed all the tests:" + gender + nomatch);

                try{ // ensures the data entered by the user is an actual number 
                    System.out.println("button pushed");
                    String message = "Press yes if " + user + " is the Memberss name \n";
                            message += "Date of birth is: "+ Dob +"\n";
                            message += "Age is:"+ Age + "gender is: "+ gender +"\n";
                            message += "Position is:"+ position +"\n";
                            message += "Phone number is:"+ phonenum +"\n";
                            message += "Members email is:"+ email +"\n";
                            message += "Members Home Address is:"+ homeadd +"\n";

                        int confirm = JOptionPane.showConfirmDialog(null, message);

                        if (confirm == 0){
                            System.out.println("confirmed");
                            String counter = id();
                            BufferedWriter bwr = new BufferedWriter(new FileWriter("promoter.txt", true));
                            String mem = counter + " " + user + " " + Age + " " + gender + " " + Dob + " " + position + " " + phonenum + " " + homeadd + " " + email;
                            bwr.write(mem + "\n");
                            System.out.println("wrote to file");
                            bwr.close();                                               
                        }
                }catch(NumberFormatException exception){
                    JOptionPane.showMessageDialog(null, "Age needs to be a number", "The Age cannot contain any letters", JOptionPane.ERROR_MESSAGE);
                }catch(IOException ex){
                    //do nothing
                }

            }
        }
    }
    //When the button connected to this action listener is pressed the user is brought back to the Promoter Screen
    private class DoneActionListener implements ActionListener{

        public void actionPerformed(ActionEvent e){
            frame.setVisible(false);
            new Promoter();
        }
    }
    


}
