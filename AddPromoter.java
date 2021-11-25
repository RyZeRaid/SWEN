

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

    private JLabel addprm= new JLabel("ADD PROMOTER");
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JButton enter;
    private JButton done;

    private JTextField txtName;
    private JTextField txtBudget;

    /**
     * This constructor sets up the componets for the gui seen by the user
     */
    public AddPromoter(){

        addprm.setFont(new Font("MV Boli", Font.BOLD, 50));

        panel.add(addprm);
        panel.setBackground(Color.YELLOW);
        panel1.setBackground(Color.YELLOW);

        panel1.setLayout(new GridLayout(2,2));
        panel2.setLayout(new GridLayout(1,0));

        panel1.add(new JLabel("Promoter First Name: "));
        txtName = new JTextField(20);
        panel1.add(txtName);
        panel1.add(new JLabel("Budget: "));
        txtBudget = new JTextField(20);
        panel1.add(txtBudget);
        enter = new JButton("Add Promoter");
        done = new JButton("Finished Adding");
        enter.addActionListener(new EnterActionListener());
        done.addActionListener(new DoneActionListener());
        panel2.add(enter);
        panel2.add(done);

        

		
        frame.add(panel, BorderLayout.NORTH);
        frame.add(panel1, BorderLayout.CENTER);
        frame.add(panel2, BorderLayout.SOUTH);
        frame.setSize(500,400);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
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


    // When the button is pressed the user is information entered by the user will be taken and added to the file 
    private class EnterActionListener implements ActionListener{

        public void actionPerformed(ActionEvent e){
            String user = txtName.getText();
            String budget = txtBudget.getText();

            String[] name = user.split(" ");

            if(name.length > 1){// ensuring that only the first name is entered
                JOptionPane.showMessageDialog(null, "Only first name required", "Please Enter Right Information", JOptionPane.ERROR_MESSAGE);
            }

            else if(user.equals("") || budget.equals("")){
                JOptionPane.showMessageDialog(null, "Please Enter Information", "No Information Entered", JOptionPane.ERROR_MESSAGE);
            }else{

                try{ // ensures the data entered by the user is an actual number 
                    double money = Double.parseDouble(budget);
                    String message = "Press yes if " + user + " is the Promoters name and " + budget + " is the Budget";

                    int confirm = JOptionPane.showConfirmDialog(null, message);

                    if (confirm == 0){
                        String counter = id();
                        BufferedWriter bwr = new BufferedWriter(new FileWriter("promoter.txt", true));
                        String prom = counter + " " + user + " " + budget + " 0 0";
                        bwr.write(prom + "\n");
                        bwr.close();                                               
                    }
                }catch(NumberFormatException exception){
                    JOptionPane.showMessageDialog(null, "Budget needs to be a number", "The budget cannot contain any letters", JOptionPane.ERROR_MESSAGE);
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
