/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.monsters;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MyHuawei
 */
public class MonsterStorage {
    private List<Monster> monsters = new ArrayList<>();
    
    public List<Monster> getMonsters() {
        return monsters;
    }
    
    public void add(Monster monster) {
        monsters.add(monster); 
    }
}
