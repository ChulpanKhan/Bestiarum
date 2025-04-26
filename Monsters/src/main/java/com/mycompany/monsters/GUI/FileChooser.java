
package com.mycompany.monsters.GUI;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooser {
        
    public static File selectImportFile() {
        JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.dir")));
        fileChooser.setDialogTitle("Выберите файл");
        fileChooser.setMultiSelectionEnabled(true);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Поддерживаемые форматы (XML, JSON, YAML)",
                "xml", "json", "yml", "yaml");
        fileChooser.setFileFilter(filter);

        int userSelection = fileChooser.showOpenDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            DialogUtils.showSuccessDialog("Файл успещно импортирован");
            return fileChooser.getSelectedFile();
        }
        return null;
    }
    
    public static File selectExportFile(String suffix) {
        JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.dir")));
        fileChooser.setDialogTitle("Выберите место для экспорта файла");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Files", suffix));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            if (!filePath.endsWith(suffix)) {
                selectedFile = new File(filePath + "."+suffix);
            }
            DialogUtils.showSuccessDialog("Файл успещно экспортирован\n"+selectedFile.getAbsolutePath());
            return selectedFile;
        }
        return null;
    }

}
