
package com.mycompany.monsters;

import com.mycompany.monsters.GUI.FileChooser;
import com.mycompany.monsters.Importer.*;
import java.io.File;

public class MonsterController {

    private final MonsterStorage xmlRepo = new MonsterStorage();
    private final MonsterStorage jsonRepo = new MonsterStorage();
    private final MonsterStorage yamlRepo = new MonsterStorage();
    private final ImportHandler importerChain;

    public MonsterController() {
        ImportHandler xml = new XMLImporter();
        ImportHandler json = new JSONImporter();
        ImportHandler yaml = new YAMLImporter();
        xml.setNext(json);
        json.setNext(yaml);
        this.importerChain = xml;
    }
    
    public void importFiles() {
        File importFile = FileChooser.selectImportFile();
        if (importFile != null) {
            String fileName = importFile.getName().toLowerCase();
            if (fileName.endsWith(".xml")) importerChain.handle(importFile, xmlRepo);
            else if (fileName.endsWith(".json")) importerChain.handle(importFile, jsonRepo);
            else importerChain.handle(importFile, yamlRepo);
        }    
    }
}
