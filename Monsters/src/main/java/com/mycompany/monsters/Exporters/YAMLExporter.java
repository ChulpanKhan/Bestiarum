    
package com.mycompany.monsters.Exporters;

import com.mycompany.monsters.Monster;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.yaml.snakeyaml.Yaml;

public class YAMLExporter implements Exporter{

    @Override
    public void export(List<Monster> monsters, File file) throws IOException{
        Yaml yaml = new Yaml();
        try (FileWriter writer = new FileWriter(file)) {
            yaml.dump(monsters, writer);       
        }
    }

}
