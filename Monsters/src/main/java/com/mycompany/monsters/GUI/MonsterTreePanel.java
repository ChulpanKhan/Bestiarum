 
package com.mycompany.monsters.GUI;

import com.mycompany.monsters.Monster;
import com.mycompany.monsters.MonsterController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

public class MonsterTreePanel extends JPanel {
    private final JTree tree;
    private final MonsterController controller;
    private MonsterDetailPanel detailPanel;
    private JScrollPane scrollPane = null;

    public MonsterTreePanel(MonsterController controller) {
        this.controller = controller;
        this.tree = buildTree();
        this.tree.setBackground(new Color(245, 245, 250));
        this.tree.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
        this.tree.setCellRenderer(new ColoredTreeCellRenderer());
        expandAllTreeNodes();
        setupListener();
    }

    private JTree buildTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Форматы");
        //XML
        DefaultMutableTreeNode xmlNode = new DefaultMutableTreeNode("XML");
        for (Monster m : controller.getXmlStorage().getMonsters()) {
            xmlNode.add(new DefaultMutableTreeNode(m));
        }    
        root.add(xmlNode);
        //JSON
        DefaultMutableTreeNode jsonNode = new DefaultMutableTreeNode("JSON");
        for (Monster m : controller.getJsonStorage().getMonsters()) {
            jsonNode.add(new DefaultMutableTreeNode(m));
        }    
        root.add(jsonNode);
        
        //YAML
        DefaultMutableTreeNode yamlNode = new DefaultMutableTreeNode("YAML");
        for (Monster m : controller.getYamlStorage().getMonsters()) {
            yamlNode.add(new DefaultMutableTreeNode(m));
        }    
        root.add(yamlNode);
        
        return new JTree(root);
    }


    private void setupListener() {
        tree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if (node == null) {
                detailPanel.showWelcome();
                return;
            }
            Object obj = node.getUserObject();
            if (obj instanceof Monster monster) {
                detailPanel.displayMonster(monster);
            } else {
                detailPanel.showWelcome();
            }
        });
    }

    public void setDetailPanel(MonsterDetailPanel detailPanel) {
        this.detailPanel = detailPanel;
    }

    public JTree getTree() {
        return tree;
    }
    
    public void refreshTree() {
        tree.setModel(new DefaultTreeModel((TreeNode) buildTree().getModel().getRoot()));
        expandAllTreeNodes();
    }

    private void expandAllTreeNodes() {
        for (int i = 0; i < tree.getRowCount(); i++) {
            tree.expandRow(i);
        }
    }

    public JScrollPane getScrollPane() {
        scrollPane = new JScrollPane(tree);
        scrollPane.setPreferredSize(new Dimension(200, 0));
        return scrollPane;
    }

}
