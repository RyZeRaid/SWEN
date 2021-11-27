

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.print.DocFlavor.INPUT_STREAM;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Promoter Class shows the table of the current promoters in the system
 */
public class Promoter {
    private JFrame frame;
    private JPanel panel;
    private JButton add;
    private JButton editRemove;
    private JButton trash;
    private JButton sortName;
    private JButton sortBudget;
    private JButton exit;
    private JTable table;

    private ArrayList<String[]> pList = new ArrayList<String[]>();
    private DefaultTableModel model;

    /**
     * This constructor sets up the componets for the gui seen by the user 
     */
    public Promoter(){

        add = new JButton("Add Promoter");
        add.addActionListener(new AddActionListener());
        editRemove = new JButton("Edit/Remove User");
        editRemove.addActionListener(new EditActionListener());
        trash = new JButton("Trash Bin");
        trash.addActionListener(new TrashActionListener());
        sortName = new JButton("Sort by Name");
        sortName.addActionListener(new SortNameButtonListener());
        sortBudget = new JButton("Sort by Budget");
        sortBudget.addActionListener(new SortBudgetButtonListener());
        exit = new JButton("Exit App");
        exit.setBackground(Color.RED);
        exit.setForeground(Color.WHITE);
        exit.addActionListener(new ExitButtonListener());

        panel = new JPanel();
        panel.add(add);
        panel.add(editRemove);
        panel.add(trash);
        panel.add(sortName);
        panel.add(sortBudget);
        panel.add(exit);

       
        frame = new JFrame();
        frame.setTitle("Current Members");



        String[] columnNames = { "ID", "Name", "Date of Birth", "Age", "Gender", "Position", "Phone Number", "Home Address", "Email Address"};
        model = new DefaultTableModel(columnNames,0);


        table = new JTable(model);
        addToArray();
        showTable(pList);
        table.setBounds(30,40,200,300);
        table.setEnabled(false);
        JScrollPane sp = new JScrollPane(table);
        frame.add(sp, BorderLayout.NORTH);
        frame.add(panel);

        frame.setSize(650, 529);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private class EditActionListener implements ActionListener{
        private RemoveEdit prom;
        public void actionPerformed(ActionEvent e){
            prom = new RemoveEdit();
            frame.setVisible(false);
        }
    }

    private class AddActionListener implements ActionListener{
        private AddPromoter addProm;
        public void actionPerformed(ActionEvent e){
            addProm = new AddPromoter();
            frame.setVisible(false);
        }
    }

    
    /** 
     * This method allows the number of promoters to be added to the Jtable and shown to the user
     * @param pList2 This is an array containing the current promoters on the system
     */
    private void showTable(ArrayList<String[]> pList2) {
        if (pList2.size()>0){
            for (int x = 0; x<pList2.size();x++)
                model.addRow(pList2.get(x));
        }
    }

    /**
     * This method takes the data from the file containing the promoter's information and add it to a list
     */
    public void addToArray(){
        try{
        BufferedReader br = new BufferedReader(new FileReader("promoter.txt"));
        String s;
        
        while((s = br.readLine()) != null){
            String[] pData = s.split(" ");
            String[] item = {pData[0], pData[1], pData[2], pData[3], pData[4], pData[5], pData[6], pData[7], pData[8], pData[9]};
            pList.add(item);
        }
        br.close();

        }catch(IOException e){
            //do nothing
        }
    }

    private class SortNameButtonListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e){

            model.setRowCount(0);
            Collections.sort(pList, new SortName());
            showTable(pList);
        }
    }

    private class SortBudgetButtonListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e){

            model.setRowCount(0);
            Collections.sort(pList, new SortBudget());
            showTable(pList);
        }
    }

    private class TrashActionListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e){
            new Trash();
            frame.setVisible(false);
        }
    }

    private class ExitButtonListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    }
}