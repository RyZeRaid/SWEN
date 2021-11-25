

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
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
import java.awt.event.ActionEvent;

/**
 *  Edit Promoter Class allows the user to change the information for a specific promoter that they have already added 
 */

public class EditPromoter {

    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JButton enter;

    private JTextField txtName;
    private JTextField txtBudget;

    private String name;
    private String budget;
    private String id;


    /**
     * 
     * This constructor sets the gui the user will see to edit the data of the promoter
     * @param id This is the id number of the promoter that is going to be edited 
     * @param name This is the name of the promoter that is going to be edited 
     * @param budget This is the budget of the promoter that is going to be edited 
     */
    public EditPromoter(String id, String name, String budget){

        this.name = name;
        this.budget = budget;
        this.id = id;


        panel.setLayout(new GridLayout(2,2));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String message = "Please enter the information for " + name + " with budget " + budget + 
                                ". If that specific item is to stay the same leave the field blank";

        panel.add(new JLabel(message));
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
        enter = new JButton("Edit Promoter");
        enter.addActionListener(new EnterActionListener());
        panel2.add(enter);

        

        frame.add(panel, BorderLayout.NORTH);
        frame.add(panel1, BorderLayout.CENTER);
        frame.add(panel2, BorderLayout.SOUTH);
        frame.setSize(500,400);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Edit Promoter");
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private class EnterActionListener implements ActionListener{

        public void actionPerformed(ActionEvent e){
            String user = txtName.getText();
            String budget1 = txtBudget.getText();

            String[] name1 = user.split(" ");

            if(name1.length > 1){
                JOptionPane.showMessageDialog(null, "Only first name required", "Please Enter Right Information", JOptionPane.ERROR_MESSAGE);
            }else{
                try{
                    if(user.equals("")){
                        user = name;
                    }
                    if(budget1.equals("")){
                        budget1 = budget;
                    }

                    double money = Double.parseDouble(budget1); // to enure that what is entered by the user is a number

                    String message = "Press yes if " + user + " is the Promoters name and " + budget1 + " is the Budget";

                    int confirm = JOptionPane.showConfirmDialog(null, message);

                    if (confirm == 0){
                        BufferedWriter bw = new BufferedWriter(new FileWriter("promoter.txt", true));
                        String newProm = id +" " + user + " " + budget1 + " 0 0";
                        bw.write(newProm + "\n");
                        bw.close();
                        frame.setVisible(false);
                        new Promoter();
                    }
                }catch(NumberFormatException exception){
                    JOptionPane.showMessageDialog(null, "Budget needs to be a number", "The budget cannot contain any letters", JOptionPane.ERROR_MESSAGE);
                }catch(IOException ex){
                    //do nothing
                }
            }
        }
    }
    
}
