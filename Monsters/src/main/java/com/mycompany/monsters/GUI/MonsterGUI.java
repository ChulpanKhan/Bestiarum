
package com.mycompany.monsters.GUI;

import com.mycompany.monsters.Exporters.*;
import com.mycompany.monsters.FileFormat;
import com.mycompany.monsters.Monster;
import com.mycompany.monsters.MonsterController;
import com.mycompany.monsters.MonsterStorage;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.PopupMenu;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MonsterGUI extends JFrame{
    private final MonsterController controller;
    private final MonsterTreePanel treePanel;
    private final MonsterDetailPanel detailPanel;
    
    public MonsterGUI(MonsterController controller) {
        super("Bestiary Viewer");
        this.controller = controller;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 500);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        treePanel = new MonsterTreePanel(controller);
        detailPanel = new MonsterDetailPanel();

        treePanel.setDetailPanel(detailPanel);

        //add(new JScrollPane(treePanel.getTree()), BorderLayout.WEST);
        add(treePanel.getScrollPane(), BorderLayout.WEST);

        //add(treePanel.getScrollPane(), BorderLayout.WEST);
        add(detailPanel, BorderLayout.CENTER);
        add(buildButtonPanel(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private Component buildButtonPanel() {
        JPanel panel = new JPanel();

        JButton importBtn = new JButton("Импортировать");
        importBtn.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
        importBtn.addActionListener(e -> {
            controller.importFiles(); //если что искать ошибку тут
            treePanel.refreshTree();
        });

        JButton exportBtn = new JButton("Экспортировать");
        exportBtn.addActionListener(e -> showExportDialog());
        exportBtn.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
        
        panel.add(importBtn);
        panel.add(exportBtn);

        return panel;
    }


    
    private void showExportDialog() {
        JDialog dialog = new JDialog(this, "Выберите формат для экспорта", true);
        dialog.setLayout(new GridLayout(0, 1));

        dialog.add(createXmlExportButton());
        dialog.add(createJsonExportButton());
        dialog.add(createYamlExportButton());
        JButton exit = new JButton("Закрыть");
        exit.addActionListener(e -> dialog.dispose());
        dialog.add(exit);

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private JButton createXmlExportButton() {
        JButton button = new JButton("XML");
        button.addActionListener(e -> {
            List<Monster> monsters = controller.getXmlStorage().getMonsters();
            controller.exportMonsters(monsters, FileFormat.XML);
        });
        return button;
    }

    private JButton createJsonExportButton() {
        JButton button = new JButton("JSON");
        button.addActionListener(e -> {
            List<Monster> monsters = controller.getJsonStorage().getMonsters();
            controller.exportMonsters(monsters, FileFormat.JSON);
        });
        return button;
    }

    private JButton createYamlExportButton() {
        JButton button = new JButton("YAML");
        button.addActionListener(e -> {
            List<Monster> monsters = controller.getYamlStorage().getMonsters();
            controller.exportMonsters(monsters, FileFormat.YAML);
        });
        return button;
    }


}
