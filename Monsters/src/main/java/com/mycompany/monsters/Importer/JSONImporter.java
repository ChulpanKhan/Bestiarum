
package com.mycompany.monsters.Importer;

import com.mycompany.monsters.Importer.ImportHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.monsters.Monster;
import com.mycompany.monsters.MonsterStorage;
import com.mycompany.monsters.MonstersWrapper;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JSONImporter implements ImportHandler{
    private ImportHandler nextHandler;
    
    @Override
    public void setNext(ImportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
    @Override
     public void handle(File file, MonsterStorage storage) {
        if (canHandle(file)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                MonstersWrapper monsterList = mapper.readValue(file, MonstersWrapper.class);
                for (Monster m : monsterList.getMonsters()) {
                    m.setSource("json");
                    storage.add(m);
                }
            } catch (IOException ex) {
                Logger.getLogger(JSONImporter.class.getName()).log(Level.SEVERE, null, ex);
            } 
        } else if (nextHandler != null) nextHandler.handle(file, storage);
    }

    @Override
    public boolean canHandle(File file) {
        return file.getName().endsWith(".json");
    }
    
}
