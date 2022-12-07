package use_cases.search_use_case.search_screen;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

/**
 *  The ButtonColumnReview class provides a renderer and an editor that appears as a
 *  JButton for Review Column.
 */
public class ButtonColumnReview extends AbstractCellEditor
        implements TableCellRenderer, TableCellEditor, ActionListener, MouseListener
{
    private JTable table;
    private Action action;
    private int mnemonic;
    private Border originalBorder;
    private Border focusBorder;

    private JButton renderButton;
    private JButton editButton;
    private Object editorValue;
    private boolean isButtonColumnEditor;

    /**
     *  Create the ButtonColumn to be used as a renderer and editor. The
     *  renderer and editor will automatically be installed on the TableColumn
     *  of the specified column.
     *
     *  @param table the table containing the button renderer/editor
    //     *  @param action the Action to be invoked when the button is invoked
     *  @param column the column to which the button renderer/editor is added
     */
//    public ButtonColumn(JTable table, Action action, int column)
    public ButtonColumnReview(JTable table,Action action, int column){
        this.table = table;
        this.action = action;

        renderButton = new JButton("Like");
        editButton = new JButton();
        editButton.setFocusPainted(false);
        editButton.addActionListener(this);
        originalBorder = editButton.getBorder();
        setFocusBorder(new LineBorder(Color.BLACK));

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(column).setCellRenderer(this);
        columnModel.getColumn(column).setCellEditor(this);
        table.addMouseListener(this);
    }

    public void setFocusBorder(Border focusBorder)
    {
        this.focusBorder = focusBorder;
        editButton.setBorder( focusBorder );
    }

    @Override
    public Component getTableCellEditorComponent(
            JTable table, Object value, boolean isSelected, int row, int column)
    {
        editButton.setText("Already Reviewed");
        editButton.setIcon(null);
        this.editorValue = value;
        return editButton;
    }

    @Override
    public Object getCellEditorValue() {return editorValue;}


//  Implement TableCellRenderer interface

    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        if (isSelected)
        {
            renderButton.setForeground(table.getSelectionForeground());
            renderButton.setBackground(table.getSelectionBackground());
        }
        else
        {
            renderButton.setForeground(table.getForeground());
            renderButton.setBackground(UIManager.getColor("Button.background"));
        }

        if (hasFocus)
        {
            renderButton.setBorder(focusBorder);
        }
        else
        {
            renderButton.setBorder(originalBorder);
        }

        renderButton.setText("Review");
        renderButton.setIcon(null);

        return renderButton;
    }

//  Implement ActionListener interface
//  The button has been pressed. Stop editing and invoke the custom Action

    public void actionPerformed(ActionEvent e)
    {
        int row = table.convertRowIndexToModel(table.getEditingRow() );
        fireEditingStopped();

        //  Invoke the Action

        ActionEvent event = new ActionEvent(
                table,
                ActionEvent.ACTION_PERFORMED,
                "" + row);
        action.actionPerformed(event);
    }

//  Implement MouseListener interface
//  When the mouse is pressed the editor is invoked.

    public void mousePressed(MouseEvent e)
    {
        if (table.isEditing()
                &&  table.getCellEditor() == this)
            isButtonColumnEditor = true;
    }

    public void mouseReleased(MouseEvent e)
    {
        if (isButtonColumnEditor
                &&  table.isEditing())
            table.getCellEditor().stopCellEditing();

        isButtonColumnEditor = false;
    }

    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
