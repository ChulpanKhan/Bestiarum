
package com.mycompany.monsters;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class Recipe {
    private String type;
    @XmlElementWrapper(name = "ingredients")
    @XmlElement(name = "entry")
    private Map<String, Integer> ingredients = new HashMap(); //РІРѕР·РјРѕР¶РЅРѕ С…СЌС€РјСЌРї РёРјСЏ-РєРѕР»РёС‡РµСЃС‚РІРѕ
    private String preparationTime;
    private String effectiveness;   
    
    //getters
    public String getType() {
        return type;
    }
    public Map<String, Integer> getIngredients() {
        return ingredients;
    }
    public String getPreparationTime() {
        return preparationTime;
    }
    public String getEffectiveness() {
        return effectiveness;
    }
    
    //setters
    public void setType(String type) {
        this.type = type;
    }
    public void setIngredients(Map<String, Integer> ingredients) {
        this.ingredients = ingredients;
    }
    public void setPreparationTime(String preparationTime) {
        this.preparationTime = preparationTime;
    }
    public void setEffectiveness(String effectiveness) {
        this.effectiveness = effectiveness;
    }
}
