/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.monsters;

/**
 *
 * @author MyHuawei
 */
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
