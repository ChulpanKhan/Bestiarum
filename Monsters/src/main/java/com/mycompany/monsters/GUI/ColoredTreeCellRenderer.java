
package com.mycompany.monsters.GUI;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

public class ColoredTreeCellRenderer extends DefaultTreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel,
            boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        setBackgroundNonSelectionColor(new Color(245, 245, 250));
        setBackgroundSelectionColor(new Color(220, 220, 255));
        return this;        
    }
}
