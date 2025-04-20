
package com.mycompany.monsters.Importer;

import com.mycompany.monsters.Importer.ImportHandler;
import com.mycompany.monsters.Monster;
import com.mycompany.monsters.MonsterStorage;
import com.mycompany.monsters.MonstersWrapper;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class XMLImporter implements ImportHandler{
    private ImportHandler nextHandler;
    @Override
    public void setNext(ImportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handle(File file, MonsterStorage storage) {
        if (canHandle(file)) {
            try{
               JAXBContext context = JAXBContext.newInstance(MonstersWrapper.class);
               Unmarshaller unmarshaller = context.createUnmarshaller();
               MonstersWrapper monsterList = (MonstersWrapper) unmarshaller.unmarshal(file);
               for (Monster m : monsterList.getMonsters()) {
                    m.setSource("xml");
                    storage.add(m);
                }
            } catch (JAXBException e) {
                System.err.println("Ошибка JAXB: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Общая ошибка: " + e.getMessage());
            }
        } else if (nextHandler != null) nextHandler.handle(file, storage);
    }
    
    @Override
    public boolean canHandle(File file) {
        return file.getName().endsWith(".xml");
    }
    
}
