/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.monsters.GUI;

import com.mycompany.monsters.MonsterController;
import javax.swing.JFrame;

public class MonsterView extends JFrame{
    private final MonsterController controller;
//    private final MonsterTreePanel treePanel;
//    private final MonsterDetailPanel detailPanel;
    
    public MonsterView(MonsterController controller) {
        super("Bestiary Viewer");
        this.controller = controller;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLayout(new BorderLayout());

        treePanel = new MonsterTreePanel(controller);
        detailPanel = new MonsterDetailPanel();

        treePanel.setDetailPanel(detailPanel);

        add(new JScrollPane(treePanel.getTree()), BorderLayout.WEST);
        add(detailPanel, BorderLayout.CENTER);
        add(buildButtonPanel(), BorderLayout.SOUTH);

        setVisible(true);
    }

}
