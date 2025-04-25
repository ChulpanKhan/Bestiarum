
package com.mycompany.monsters.Importer;

import com.mycompany.monsters.GUI.DialogUtils;
import com.mycompany.monsters.Importer.ImportHandler;
import com.mycompany.monsters.Monster;
import com.mycompany.monsters.MonsterStorage;
import com.mycompany.monsters.MonstersWrapper;
import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class XMLImporter implements ImportHandler{
    private ImportHandler nextHandler;
    private MonsterStorage storage;
  
    public XMLImporter(MonsterStorage storage) {
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
                JAXBContext context = JAXBContext.newInstance(MonstersWrapper.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
               MonstersWrapper monsterList = (MonstersWrapper) unmarshaller.unmarshal(file);
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
            } catch (JAXBException e) {
                DialogUtils.showErrorMessage("Ошибка JAXB: " + e.getMessage());
                System.err.println("Ошибка JAXB: " + e.getMessage());
            } catch (Exception e) {
                DialogUtils.showErrorMessage("Общая ошибка: " + e.getMessage());
                System.err.println("Общая ошибка: " + e.getMessage());
            }
        } else if (nextHandler != null) nextHandler.handle(file);
    }
    
    @Override
    public boolean canHandle(File file) {
        return file.getName().endsWith(".xml");
    }
    
}
