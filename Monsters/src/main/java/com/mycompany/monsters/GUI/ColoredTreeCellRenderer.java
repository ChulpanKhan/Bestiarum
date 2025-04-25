/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.monsters.GUI;

import com.mycompany.monsters.Monster;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author MyHuawei
 */
public class ColoredTreeCellRenderer extends DefaultTreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel,
            boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        setBackgroundNonSelectionColor(new Color(245, 245, 250));
        setBackgroundSelectionColor(new Color(220, 220, 255));
//        setTextSelectionColor(DARK_BROWN);
//        setTextNonSelectionColor(DARK_BROWN);
        return this;        
    }
}
