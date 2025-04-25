
package com.mycompany.monsters.Exporters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mycompany.monsters.Monster;
import com.mycompany.monsters.MonstersWrapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class JSONExporter implements Exporter {

    @Override
    public void export(List<Monster> monsters, File file) throws IOException{

            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            MonstersWrapper wrapper = new MonstersWrapper();
            wrapper.setMonsters(monsters);
            mapper.writeValue(file, wrapper);


    }
}