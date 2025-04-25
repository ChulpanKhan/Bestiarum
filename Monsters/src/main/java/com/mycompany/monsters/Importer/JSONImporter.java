
package com.mycompany.monsters.Importer;

import com.mycompany.monsters.Importer.ImportHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.monsters.GUI.DialogUtils;
import com.mycompany.monsters.Monster;
import com.mycompany.monsters.MonsterStorage;
import com.mycompany.monsters.MonstersWrapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JSONImporter implements ImportHandler{
    private ImportHandler nextHandler;
    private MonsterStorage storage;

    public JSONImporter(MonsterStorage storage) {
        this.storage = storage;
    }

    @Override
    public void setNext(ImportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handle(File file) {
        if (canHandle(file)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                MonstersWrapper monsterList = mapper.readValue(file, MonstersWrapper.class);
                List<Monster> monsters = monsterList.getMonsters();
               for (Monster m : monsters) {
                    m.setSource("xml");
                    if (monsters.indexOf(m) < 5) {
                        m.setUniverse("ведьмак");
                    } else {
                        m.setUniverse("винкс");
                    } 
                    this.storage.add(m);
                }
            } catch (IOException ex) {
                DialogUtils.showErrorMessage(ex.getMessage());
                Logger.getLogger(JSONImporter.class.getName()).log(Level.SEVERE, null, ex);
            } 
        } else if (nextHandler != null) nextHandler.handle(file);
    }

    @Override
    public boolean canHandle(File file) {
        return file.getName().endsWith(".json");
    }
    
}
