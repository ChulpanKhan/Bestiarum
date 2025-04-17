
package com.mycompany.monsters;

public class Monster {
    private String name;
    private String description;
    private int dangerLevel;
    private String habitat;
    private String firstMention;
    private String immunities;
    private String activity;
    private int heightInMetres;
    private int weightInKg;
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
    public String getImmunities() {
        return immunities;
    }
    public String getActivity() {
        return activity;
    }
    public int getHeightInMetres() {
        return heightInMetres;
    }
    public int getWeightInKg() {
        return weightInKg;
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
    public void setImmunities(String immunities) {
        this.immunities = immunities;
    }
    public void setActivity(String activity) {
        this.activity = activity;
    }
    public void setHeightInMetres(int heightInMetres) {
        this.heightInMetres = heightInMetres;
    }
    public void setWeightInKg(int weightInKg) {
        this.weightInKg = weightInKg;
    }
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
    public void setSource(String source) {
        this.source = source;
    }
}
