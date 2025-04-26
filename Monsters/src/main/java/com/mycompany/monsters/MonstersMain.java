
package com.mycompany.monsters;

import com.mycompany.monsters.GUI.MonsterGUI;

public class MonstersMain {

    public static void main(String[] args) {
//        //YAMLImporter imp = new YAMLImporter();
//        //XMLImporter imp = new XMLImporter();
//        JSONImporter imp = new JSONImporter();
//        //File file =  new File("C:/Users/MyHuawei/Desktop/новый 2.yaml");
//        //File file =  new File("C:/Users/MyHuawei/Documents/GitHub/Bestiarum/Monsters.yaml");
//        //File file =  new File("C:/Users/MyHuawei/Documents/GitHub/Bestiarum/Monsters.xml");
//        File file =  new File("C:/Users/MyHuawei/Documents/GitHub/Bestiarum/Monsters.json");
//        
//        
//        MonsterStorage xmlRepo = new MonsterStorage();
//        imp.handle(file, xmlRepo);
//        for (Monster m : xmlRepo.getMonsters()) {
//            System.out.println(m.getRecipe().getIngredients()+m.getSource());
//        }

            MonsterController controller = new MonsterController();
            new MonsterGUI(controller);

    }
}
