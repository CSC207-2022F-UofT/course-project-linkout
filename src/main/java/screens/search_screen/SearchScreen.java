
package screens.search_screen;
import org.apache.poi.ss.usermodel.Row;

import java.util.ArrayList;

import javax.swing.JFrame;

import javax.swing.JTable;

import javax.swing.JScrollPane;

import javax.swing.table.DefaultTableModel;

import javax.swing.JLabel;

import javax.swing.JTextField;

import javax.swing.JButton;

import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;



public class SearchScreen extends JFrame {

    private JTextField txtKeyword;

    private JTable table;

    /**
     * Launch the SearchMatch UI window.
     */

    public static void main(String[] args) {
        SearchScreen frame = new SearchScreen();
        frame.setVisible(true);
    }


    public SearchScreen() {

        // Build the empty frame for UI
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(100, 100, 680, 342);

        setTitle("Search for Match");

        getContentPane().setLayout(null);

        // Create the ScrollPane
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(65, 72, 550, 159);
        getContentPane().add(scrollPane);

        // Create the Table for display the results
        table = new JTable();
        scrollPane.setViewportView(table);

        // Create the Label "Find The One : )" beside the search field
        JLabel lblSearch = new JLabel("Find The One :)");

        lblSearch.setBounds(101, 27, 124, 14);

        getContentPane().add(lblSearch);

        // Create a JTextField for Keyword Inputs
        txtKeyword = new JTextField();

        txtKeyword.setBounds(195, 24, 160, 20);

        getContentPane().add(txtKeyword);

        txtKeyword.setColumns(10);

        // Create the Button Search
        JButton btnSearch = new JButton("use_cases/Search");

        btnSearch.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg) {

                // Binding Data.
                GenerateData();
            }
        });

        btnSearch.setBounds(365, 23, 79, 23);

        getContentPane().add(btnSearch);

// Bind and PopulateData
        GenerateData();
    }

    private void GenerateData() {

        // Make the table in UI empty -- Initialization
        table.setModel(new DefaultTableModel());

        // Model for Table by adding column titles
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        model.addColumn("Age");

        model.addColumn("Gender");

        model.addColumn("Location");

        model.addColumn("Sexuality");

        model.addColumn("Height");

        model.addColumn("SelfDescription");

        model.addColumn("Weight");

        model.addColumn("Hobbies");

        model.addColumn("Username");

        model.addColumn("Like");

        model.addColumn("Review");

        model.addColumn("Report");


        try {
            // Initialized a new search
            Search matchedSearch = new Search();

            // Storing the value as a String
            String searchTexts = txtKeyword.getText();


            // Obtain an arrayList that contains al the matching users along with their corresponding profile
            ArrayList<Row> rows = matchedSearch.searchSheet(searchTexts);

            // Iterating the arraylist of matched users and updating the models with these matched users and
            // their corresponding profile
            for (int i = 0; i < rows.size(); i++) {

                model.addRow(new Object[0]);

                Row currentRow = rows.get(i);

                model.setValueAt(currentRow.getCell(0), i, 0);

                model.setValueAt(currentRow.getCell(1), i, 1);

                model.setValueAt(currentRow.getCell(2), i, 2);

                model.setValueAt(currentRow.getCell(3), i, 3);

                model.setValueAt(currentRow.getCell(4), i, 4);

                model.setValueAt(currentRow.getCell(5), i, 5);

                model.setValueAt(currentRow.getCell(6), i, 6);

                model.setValueAt(currentRow.getCell(7), i, 7);

                model.setValueAt(currentRow.getCell(8), i, 8);


                model.setValueAt(new ButtonColumnLike(table, (need Action being filled in), 9), i, 9);
                //TODO need Action being filled in
                //model.setValueAt(new ButtonColumnLike(table, (TODO need Action being filled in), 9), i, 9);

                model.setValueAt(new ButtonColumnReview(table, (need Action being filled in), 10), i, 10);
                //model.setValueAt(new ButtonColumnReview(table, (TODO need Action being filled in), 10), i, 10);

                model.setValueAt(new ButtonColumnReport(table, (need Action being filled in),11), i, 11);
                //model.setValueAt(new ButtonColumnReport(table, (TODO need Action being filled in), 11), i, 11);
            }

            file.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

