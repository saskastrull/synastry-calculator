package view.mainframe;

import view.ViewConstants;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Class representing some information about a single placement; the custom entity, its sign and degree.
 */
public class CustomPlacement extends JPanel {
    protected JComboBox<String> signBox = new JComboBox<>(ViewConstants.SIGNS);
    protected JComboBox<Integer> degreeBox = new JComboBox<>(ViewConstants.DEGREES);
    protected JTextField nameField = new JTextField("Custom");

    public CustomPlacement() {
        this.setBackground(ViewConstants.BACKGROUND);

        nameField.setFont(ViewConstants.DEFAULT_FONT);
        nameField.setPreferredSize(new Dimension(80,20));
        nameField.setBackground(ViewConstants.ACCENT_COLOR);

        signBox.setFont(ViewConstants.DEFAULT_FONT);
        signBox.setBackground(ViewConstants.ACCENT_COLOR);

        degreeBox.setFont(ViewConstants.DEFAULT_FONT);
        degreeBox.setBackground(ViewConstants.ACCENT_COLOR);

        this.setLayout(new FlowLayout());

        add(nameField);
        add(signBox);
        add(degreeBox);
    }

    public HashMap<String, ArrayList<String>> getPlacement() {
        HashMap<String, ArrayList<String>> placement = new HashMap<>();

        ArrayList<String> values = new ArrayList<>();
        values.add(Objects.requireNonNull(signBox.getSelectedItem()).toString());
        values.add(Objects.requireNonNull(degreeBox.getSelectedItem()).toString());

        placement.put(nameField.getText(), values);
        return placement;
    }

    public void resetCustomPlacement() {
        signBox.setSelectedIndex(0);
        degreeBox.setSelectedIndex(0);
        nameField.setText("Custom");
    }
}
