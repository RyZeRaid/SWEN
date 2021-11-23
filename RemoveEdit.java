package project;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.print.DocFlavor.INPUT_STREAM;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * RemoveEdit Class shows the table of the current promoters in the system and allow the user to remove or edit the current promoters in the system
 */

public class RemoveEdit {
    private JFrame frame;
    private JPanel panel;
    private JPanel panel1;
    private JButton button;
    private JTable table;
    private JComboBox combo;

    private ArrayList<String[]> pList = new ArrayList<String[]>();
    private DefaultTableModel model;

    /**
     * This constructor sets up the componets for the gui seen by the user 
     */
    public RemoveEdit(){

        panel = new JPanel();
        panel1 = new JPanel();
        panel1.add(new JLabel("Press the Choose Box Beside the Promoter you would like to do a specific action to then press Enter"));
        button = new JButton("Enter");
        button.addActionListener(new ComboActionListener());
        panel.add(button);

        
        frame = new JFrame();
        frame.setTitle("Remove/Update Promoter's Information");



        String[] columnNames = { "ID", "Name", "Budget", "#Plans", "#Events", "Choose"};
        model = new DefaultTableModel(columnNames,0);

        String[] positions = {"Remove", "Update", "Choose"};
        combo = new JComboBox<String>(positions);

        table = new JTable(model);
        addToArray();
        showTable(pList);
        table.setBounds(30,40,200,300);
        TableColumn col =table.getColumnModel().getColumn(5);
        col.setCellEditor(new DefaultCellEditor(combo));
        JScrollPane sp = new JScrollPane(table);
        frame.add(panel1,BorderLayout.NORTH);
        frame.add(sp, BorderLayout.CENTER);
        frame.add(panel,BorderLayout.SOUTH);
        
        frame.setSize(650, 500);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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
            String[] item = {pData[0], pData[1], pData[2], pData[3], pData[4], "Choose"};
            pList.add(item);
        }
        br.close();

        }catch(Exception e){}
    }

    
    /** 
     * @param table This is the table currently being shown on the screen to the user 
     * @return Object[][] This is a two dimentional array which contains the data on the table that was on the screen currently 
     */
    public Object[][] getTableData (JTable table) {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
        Object[][] tableData = new Object[nRow][nCol];
        for (int i = 0 ; i < nRow ; i++)
            for (int j = 0 ; j < nCol ; j++)
                tableData[i][j] = dtm.getValueAt(i,j);
        return tableData;
    }

    private class ComboActionListener implements ActionListener{
        private ArrayList<String> newList = new ArrayList<String>();
        private ArrayList<String> idNumbers = new ArrayList<String>();
        private EditPromoter editProm;

        public void actionPerformed(ActionEvent e){

            
            for (int x = 0; x < getTableData(table).length; x++){
                newList.clear();
                if(getTableData(table)[x][5].equals("Remove")){
                    String id = getTableData(table)[x][0].toString();
                    try{// adding the person that was removed to the trash bin file 
                        BufferedWriter bw = new BufferedWriter(new FileWriter("TrashBin.txt", true));
                        String trashProm = id + " " +getTableData(table)[x][1].toString() + " " + getTableData(table)[x][2].toString() + " 0 0";
                        bw.write(trashProm + "\n");
                        bw.close();
                    }catch(IOException y){
                        // do nothing
                    }
                    try{
                        BufferedReader br = new BufferedReader(new FileReader("promoter.txt"));
                        String s;
                        
                        while((s = br.readLine()) != null){
                            String[] line = s.split(" ");
                            if (line[0].equals(id)){
                                //do nothing
                            }else{
                                newList.add(s);   
                            }

                        }
                        br.close();
                        FileWriter writer = new FileWriter("promoter.txt"); 
                            for(String str: newList) {
                                writer.write(str + System.lineSeparator());
                            }
                            writer.close();
                        
                
                        }catch(IOException exc){
                            // do nothing
                        } 
                    
                }
                else if(getTableData(table)[x][5].equals("Update")){

                    String idNum = getTableData(table)[x][0].toString();
                    idNumbers.add(idNum);               
                }
            }
            if(idNumbers.size() == 0){
                new Promoter();
            }
            else if (idNumbers.size() > 1){
                JOptionPane.showMessageDialog(null, "You can only update one Promoter at a time", "Too many Update Requests", JOptionPane.ERROR_MESSAGE);
                new Promoter();
            }else{
                for (String id : idNumbers){
                    newList.clear();
                    try{
                        BufferedReader br = new BufferedReader(new FileReader("promoter.txt"));
                        String s;
                    
                        while((s = br.readLine()) != null){
                            String[] line = s.split(" ");
                            if (line[0].equals(id)){
                                editProm = new EditPromoter(line[0], line[1], line[2]);
                            }else{
                                newList.add(s);   
                            }

                        }
                        br.close();
                        FileWriter writer = new FileWriter("promoter.txt"); 
                            for(String str: newList) {
                                writer.write(str + System.lineSeparator());
                            }
                            writer.close();
                    
            
                        }catch(Exception exc){
                            // do nothing
                        } 
                }
            }
            frame.setVisible(false);
            
        }

            
    }
    
}
