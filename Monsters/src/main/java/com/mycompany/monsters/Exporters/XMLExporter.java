
package com.mycompany.monsters.Exporters;

import com.mycompany.monsters.Monster;
import com.mycompany.monsters.MonstersWrapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class XMLExporter implements Exporter{
    @Override
    public void export(List<Monster> monsters, File file) throws IOException{
        try {
            JAXBContext context = JAXBContext.newInstance(MonstersWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            MonstersWrapper wrapper = new MonstersWrapper();
            wrapper.setMonsters(monsters);
            marshaller.marshal(wrapper, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
