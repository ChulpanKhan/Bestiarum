
package com.mycompany.monsters.Exporters;

import com.mycompany.monsters.Monster;
import java.io.File;
import java.io.IOException;
import java.util.List;

public interface Exporter {
    void export(List<Monster> monsters, File file) throws IOException;
}
