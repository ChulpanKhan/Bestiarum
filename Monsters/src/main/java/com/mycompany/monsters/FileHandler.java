
package com.mycompany.monsters;

import java.io.File;

public interface FileHandler {
    void setNextHandler(FileHandler nextHandler);
    void handleFile(File file);
}
