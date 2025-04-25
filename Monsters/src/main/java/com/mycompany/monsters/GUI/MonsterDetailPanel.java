
package com.mycompany.monsters.GUI;

import com.mycompany.monsters.Monster;
import com.mycompany.monsters.Recipe;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MonsterDetailPanel extends JPanel {
    private final JPanel editorPanel;
    private JLabel welcomeLabel;
    private Color backgroundColor;
    private Font font;
    private final Font witcherFont = FontLoader.loadFont("/fonts/MasonChronicles.ttf", 16f);;
    private final Font winxFont =  FontLoader.loadFont("/fonts/magicwixscriptrus.otf", 16f);

    public MonsterDetailPanel() {
        setLayout(new BorderLayout());

        editorPanel = new JPanel();
        editorPanel.setLayout(new BoxLayout(editorPanel, BoxLayout.Y_AXIS));

        //add(welcomeLabel, BorderLayout.CENTER);
        add(new JScrollPane(editorPanel), BorderLayout.CENTER);
        showWelcome(); 
        editorPanel.setVisible(true);
    }

    void showWelcome() {
        editorPanel.removeAll();
        String htmlText = "<html><div style='text-align: center; '>"
                + "<h2>База данных магических существ: от монстров Ведьмака до фей Winx</h2>"
                + "<p><i>Геральт из Ривии, исследуя новый портал между мирами,</i></p>"
                + "<p><i>обнаружил неизвестные ему ранее виды существ —</i></p>"
                + "<p><i>фей из измерения Магикс. </i></p>"
                + "<p><i>В отличие от агрессивных чудовищ, с которыми он привык сражаться,</i></p>"
                + "<p><i>эти создания используют магию гармонии и стихий. </i></p>"
                + "<p><i>Однако, как и любая сила, их дар имеет слабости. </i></p>"
                + "<p><i>Каталогизируя их способности и уязвимости, Ведьмак сможет понять,</i></p>"
                + "<p><i>представляют ли они угрозу… или, возможно, </i></p>"
                + "<p><i>станут союзниками в грядущей войне с Дикой Охотой.</i></p>"
                + "</div></html>";
        welcomeLabel = new JLabel(htmlText, SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Palatino Linotype", Font.PLAIN, 16));
        welcomeLabel.setForeground(Color.BLACK);
        editorPanel.add(welcomeLabel);
        editorPanel.setBackground(new Color(220, 220, 255));
        //welcomeLabel.setVisible(true);
        
        revalidate();
        repaint();
    }
 

    void displayMonster(Monster monster) {
        editorPanel.removeAll();

        boolean isWinx = monster.getUniverse()!= null && monster.getUniverse().contains("винкс");

        font = isWinx ? winxFont : witcherFont;

        backgroundColor = isWinx ? new Color(255, 230, 240) : new Color(220, 220, 220);
        Color buttonColor = isWinx ? new Color(255, 182, 193) : new Color(180, 180, 180);

        setBackground(backgroundColor);
        editorPanel.setBackground(backgroundColor);
        welcomeLabel.setVisible(false);
        editorPanel.setVisible(true);

        JTextField name = createStyledField(monster.getName());
        JTextField desc = createStyledField(monster.getDescription());
        JTextField level = createStyledField(String.valueOf(monster.getDangerLevel()));
        JTextField habitat = createStyledField(monster.getHabitat());
        JTextField mention = createStyledField(monster.getFirstMention());
        JTextField vulnerabilities = createStyledField(monster.getVulnerabilities());
        JTextField activity = createStyledField(monster.getActivity());
        JTextField height = createStyledField(monster.getHeight());
        JTextField weight = createStyledField(monster.getWeight());
        JTextField immArea = createStyledField(String.join(", ", monster.getImmunities()));

        addField("Имя:", name);
        addField("Описание:", desc);
        addField("Опасность:", level);
        addField("Среда обитания:", habitat);
        addField("Первое упоминание:", mention);
        addField("Уязвимость:", vulnerabilities);
        addField("Активность:", activity);
        addField("Рост:", height);
        addField("Вес:", weight);
        addField("Иммунитет:", immArea);

        Recipe recipe = monster.getRecipe();
        JTextField recipeType = createStyledField(recipe.getType());
        JTextField prepTime = createStyledField(recipe.getPreparationTime());
        JTextField effectiveness = createStyledField(recipe.getEffectiveness());
        JTextArea ingredientArea = new JTextArea();
        ingredientArea.setFont(font);
        ingredientArea.setBackground(backgroundColor);
        ingredientArea.setText(recipe.getIngredients().entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .reduce((a, b) -> a + "\n" + b).orElse(""));
        ingredientArea.setLineWrap(true);
        ingredientArea.setWrapStyleWord(true);

        addField("Рецепт:", recipeType);
        addField("Время приготовления:", prepTime);
        addField("Эффективность:", effectiveness);
        addField("Ингредиенты (имя: кол-во):", new JScrollPane(ingredientArea));

        JButton save = new JButton("Сохранить изменения");
        save.setFont(font);
        save.setFocusPainted(false);
        save.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        save.addActionListener(e -> {
            monster.setName(name.getText());
            monster.setDescription(desc.getText());
            monster.setDangerLevel(Integer.parseInt(level.getText()));
            monster.setHabitat(habitat.getText());
            monster.setFirstMention(mention.getText());
            monster.setActivity(activity.getText());
            monster.setHeight(height.getText());
            monster.setWeight(weight.getText());

            recipe.setType(recipeType.getText());
            recipe.setPreparationTime(prepTime.getText());
            recipe.setEffectiveness(effectiveness.getText());

            Map<String, Integer> ingredients = new HashMap<>();
            boolean hasError = false;

            for (String line : ingredientArea.getText().split("\\n")) {
                if (!line.trim().isEmpty() && line.contains(":")) {
                    String[] parts = line.split(":", 2);
                    try {
                        ingredients.put(parts[0].trim(), Integer.valueOf(parts[1].trim()));
                    } catch (NumberFormatException ex) {
                        hasError = true;
                    }
                } else if (!line.trim().isEmpty()) {
                    hasError = true;
                }
            }

            if (hasError) {
                DialogUtils.showErrorMessage("Некоторые строки ингредиентов были введены неверно. Используйте формат: имя:число");
            } else {
                DialogUtils.showSuccessDialog("Данные успешно сохранены!");
                recipe.setIngredients(ingredients);
            }

        });

        editorPanel.add(Box.createVerticalStrut(10));
        editorPanel.add(save);

        revalidate();
        repaint();
    }

    private JTextField createStyledField(String text) {
        JTextField field = new JTextField(text);
        field.setFont(font);
        field.setBackground(backgroundColor);
        return field;
    }

    private void addField(String label, Component input) {
        JPanel row = new JPanel(new BorderLayout(5, 5));
        JLabel lbl = new JLabel(label);
        lbl.setFont(font);
        lbl.setBackground(backgroundColor);
        lbl.setOpaque(true);
        row.add(lbl, BorderLayout.WEST);
        row.add(input, BorderLayout.CENTER);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, input.getPreferredSize().height + 10));
        row.setBackground(backgroundColor);
        editorPanel.add(row);
    }
}
