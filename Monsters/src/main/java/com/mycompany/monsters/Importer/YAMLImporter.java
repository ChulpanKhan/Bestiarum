
package com.mycompany.monsters.Importer;

import com.mycompany.monsters.GUI.DialogUtils;
import com.mycompany.monsters.Monster;
import com.mycompany.monsters.MonsterStorage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public class YAMLImporter implements ImportHandler{
    private ImportHandler nextHandler;
    private MonsterStorage storage;
    
    public YAMLImporter(MonsterStorage storage) {
        this.storage = storage;
    }

    @Override
    public void setNext(ImportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public boolean canHandle(File file) {
        return file.getName().endsWith(".yaml") || file.getName().endsWith(".yml");
    }

    @Override
    public void handle(File file) {
        if (canHandle(file)) {
            try (InputStream inputStream = new FileInputStream(file);) {
                Constructor constructor = new Constructor(List.class);
                Yaml yaml = new Yaml(constructor);
                List<Monster> monsters = yaml.load(inputStream);

                for (Monster m : monsters) {
                    m.setSource("yaml");
                    if (monsters.indexOf(m) < 5) {
                        m.setUniverse("ведьмак");
                    } else {
                        m.setUniverse("винкс");
                    } 
                    this.storage.add(m);
                }
            } catch (FileNotFoundException ex) {
                DialogUtils.showErrorMessage(ex.getMessage());
                Logger.getLogger("gjgjgj" + YAMLImporter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                DialogUtils.showErrorMessage(ex.getMessage());
                Logger.getLogger(YAMLImporter.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (nextHandler != null) nextHandler.handle(file);
    }

   
}
