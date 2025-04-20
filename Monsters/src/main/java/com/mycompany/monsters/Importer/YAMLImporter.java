
package com.mycompany.monsters.Importer;

import com.mycompany.monsters.Importer.ImportHandler;
import com.mycompany.monsters.Monster;
import com.mycompany.monsters.MonsterStorage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public class YAMLImporter implements ImportHandler{
    private ImportHandler nextHandler;
    
    @Override
    public void setNext(ImportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public boolean canHandle(File file) {
        return file.getName().endsWith(".yaml") || file.getName().endsWith(".yml");
    }

    @Override
    public void handle(File file, MonsterStorage storage) {
        if (canHandle(file)) {
            try (InputStream inputStream = new FileInputStream(file);) {
                Constructor constructor = new Constructor(Monster.class);
//                TypeDescription monsterDescription = new TypeDescription(Monster.class);
//                monsterDescription.addPropertyParameters("recipe", Recipe.class);
//                constructor.addTypeDescription(monsterDescription);

                Yaml yaml = new Yaml(constructor);
                Iterable<Object> data = yaml.loadAll(inputStream);
                for (Object obj : data) {
                    if (obj instanceof Monster m) {
                        m.setSource("yaml");
                        storage.add(m);
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger("gjgjgj" + YAMLImporter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(YAMLImporter.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (nextHandler != null) nextHandler.handle(file, storage);
    }

   
}
