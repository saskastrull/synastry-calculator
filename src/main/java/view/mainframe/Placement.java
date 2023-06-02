package view.mainframe;

import view.ViewConstants;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Class representing some information about a single placement; the entity, its sign and degree.
 */
public class Placement extends JPanel {
    protected String entity;
    protected JComboBox<String> signBox = new JComboBox<>(ViewConstants.SIGNS);
    protected JComboBox<Integer> degreeBox = new JComboBox<>(ViewConstants.DEGREES);

    public Placement(String entity) {
        this.entity = entity;
        this.setBackground(ViewConstants.BACKGROUND);
        this.setLayout(new FlowLayout());

        JLabel entityName = new JLabel(entity + ":");
        entityName.setForeground(ViewConstants.ACCENT_COLOR);

        entityName.setFont(ViewConstants.DEFAULT_FONT);
        entityName.setPreferredSize(new Dimension(80,20));

        signBox.setFont(ViewConstants.DEFAULT_FONT);
        signBox.setBackground(ViewConstants.ACCENT_COLOR);

        degreeBox.setFont(ViewConstants.DEFAULT_FONT);
        degreeBox.setBackground(ViewConstants.ACCENT_COLOR);

        add(entityName);
        add(signBox);
        add(degreeBox);
    }

    public HashMap<String, ArrayList<String>> getPlacement() {
        HashMap<String, ArrayList<String>> placement = new HashMap<>();

        ArrayList<String> values = new ArrayList<>();
        values.add(Objects.requireNonNull(signBox.getSelectedItem()).toString());
        values.add(Objects.requireNonNull(degreeBox.getSelectedItem()).toString());

        placement.put(entity, values);
        return placement;
    }

    public void resetPlacement() {
        signBox.setSelectedIndex(0);
        degreeBox.setSelectedIndex(0);
    }
}
