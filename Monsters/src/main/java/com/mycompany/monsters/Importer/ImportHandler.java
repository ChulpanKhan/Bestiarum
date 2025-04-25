
package com.mycompany.monsters.Importer;

import com.mycompany.monsters.MonsterStorage;
import java.io.File;

public interface ImportHandler {
    void setNext(ImportHandler nextHandler);
    void handle(File file);
    boolean canHandle(File file);
}
