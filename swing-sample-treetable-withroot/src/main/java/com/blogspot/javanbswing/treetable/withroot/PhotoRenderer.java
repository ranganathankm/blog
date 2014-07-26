package com.blogspot.javanbswing.treetable.withroot;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author ranga
 */
public class PhotoRenderer extends JLabel
                           implements TableCellRenderer {
    public Component getTableCellRendererComponent(
                            JTable table, Object photo,
                            boolean isSelected, boolean hasFocus,
                            int row, int column) {
        if(photo != null) {
            ImageIcon imageIcon = new ImageIcon(getClass().getResource("/images/" + photo));
            setIcon(imageIcon);
        }
        else {
            setIcon(null);
        }
        return this;
    }
}