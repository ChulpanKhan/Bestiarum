
package com.mycompany.monsters;

public enum FileFormat {
    XML("xml"),
    JSON("json"),
    YAML("yaml");

    private final String extension;

    FileFormat(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }
}
