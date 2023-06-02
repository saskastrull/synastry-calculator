package view.mainframe;

import view.ViewConstants;
import javax.swing.*;
import java.awt.*;

/**
 * Class representing an input field for a name.
 */
public class NameField extends JPanel {
    JTextField name = new JTextField("",19);

    public NameField() {
        this.setLayout(new FlowLayout());
        this.setBackground(ViewConstants.BACKGROUND);

        JLabel tag = new JLabel("Name:");
        tag.setForeground(ViewConstants.ACCENT_COLOR);
        tag.setFont(ViewConstants.DEFAULT_FONT);
        tag.setPreferredSize(new Dimension(80,20));

        name.setFont(ViewConstants.DEFAULT_FONT);
        name.setBackground(ViewConstants.ACCENT_COLOR);

        add(tag);
        add(name);
    }

    public String getPersonsName() {
        return name.getText();
    }

    public void resetName() {
        name.setText("");
    }
}
