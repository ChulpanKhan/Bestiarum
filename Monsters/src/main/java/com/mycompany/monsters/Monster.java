
package com.mycompany.monsters;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "monster")
@XmlAccessorType(XmlAccessType.FIELD)
public class Monster {
    private String name;
    private String description;
    private int dangerLevel;
    private String habitat;
    private String firstMention;
    private String vulnerabilities;
    private String immunities;
    private String activity;
    private String height;
    private String weight;
    private Recipe recipe;
    private String source;
    
    //getters
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getDangerLevel() {
        return dangerLevel;
    }
    public String getHabitat() {
        return habitat;
    }
    public String getFirstMention() {
        return firstMention;
    }
    public String getVulnerabilities() {
        return vulnerabilities;
    }
    public String getImmunities() {
        return immunities;
    }
    public String getActivity() {
        return activity;
    }
    public String getHeight() {
        return height;
    }
    public String getWeight() {
        return weight;
    }
    public Recipe getRecipe() {
        return recipe;
    }
    public String getSource() {
        return source;
    }
     
    //setters  
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setDangerLevel(int dangerLevel) {
        this.dangerLevel = dangerLevel;
    }
    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }
    public void setFirstMention(String firstMention) {
        this.firstMention = firstMention;
    }
    public void setVulnerabilities(String vulnerabilities) {
        this.vulnerabilities = vulnerabilities;
    }
    public void setImmunities(String immunities) {
        this.immunities = immunities;
    }
    public void setActivity(String activity) {
        this.activity = activity;
    }
    public void setHeight(String height) {
        this.height= height;
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
    public void setSource(String source) {
        this.source = source;
    }
}
