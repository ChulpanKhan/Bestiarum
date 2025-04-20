
package com.mycompany.monsters.Exporters;

import com.mycompany.monsters.Monster;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.yaml.snakeyaml.Yaml;

public class YAMLExporter {

    public void export(List<Monster> monsters, File file) {
        try (FileWriter writer = new FileWriter(file)) {
            Yaml yaml = new Yaml();
            for (Monster m : monsters) {
                yaml.dump(m, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
