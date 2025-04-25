
package com.mycompany.monsters;

import com.mycompany.monsters.Exporters.*;
import com.mycompany.monsters.GUI.DialogUtils;
import com.mycompany.monsters.GUI.FileChooser;
import com.mycompany.monsters.GUI.MonsterGUI;
import com.mycompany.monsters.Importer.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MonsterController {

    private final MonsterStorage xmlStorage = new MonsterStorage();
    private final MonsterStorage jsonStorage = new MonsterStorage();
    private final MonsterStorage yamlStorage = new MonsterStorage();
    private final ImportHandler importerChain;
    private final Map<FileFormat, Exporter> exporters;

    public MonsterController() {
        ImportHandler xml = new XMLImporter(xmlStorage);
        ImportHandler json = new JSONImporter(jsonStorage);
        ImportHandler yaml = new YAMLImporter(yamlStorage);
        xml.setNext(json);
        json.setNext(yaml);
        this.importerChain = xml;
        this.exporters = Map.of(FileFormat.XML, new XMLExporter(),FileFormat.JSON, new JSONExporter(), FileFormat.YAML, new YAMLExporter() );
    } 

    public void importFiles() {
        File importFile = FileChooser.selectImportFile();
        if (importFile != null) {
            importerChain.handle(importFile); 
        }
    }

    public MonsterStorage getXmlStorage() {
        return xmlStorage;
    }
    
    public MonsterStorage getJsonStorage() {
        return jsonStorage;
    }
    
    public MonsterStorage getYamlStorage() {
        return yamlStorage;
    }
    
    public boolean isEmptyStorage(MonsterStorage storage) {
        return storage.getMonsters().isEmpty();
    }
    public void exportMonsters(List<Monster> monsters, FileFormat format) {
        if (monsters == null || monsters.isEmpty()) {
            DialogUtils.showErrorMessage("Нет монстров для экспорта" + format.getExtension());
            return;
        }

        File file = FileChooser.selectExportFile(format.getExtension());
        if (file != null) {
            try {
                exporters.get(format).export(monsters, file);
            } catch (IOException e) {
                DialogUtils.showErrorMessage("Ошибка при экспорте: " + e.getMessage());
            }
        } else System.out.println("ghjghj");
    }
}
