package view.resultframe;
import view.ViewConstants;

import javax.swing.*;
import java.awt.*;

/**
 * Class which displays a title and a combo box.
 */
public class FilterCategory extends JPanel {
    JComboBox<String> optionBox;

    public FilterCategory(String[] categoryList, String title) {
        this.setLayout(new FlowLayout());
        this.setBackground(ViewConstants.BACKGROUND);
        this.setPreferredSize(new Dimension(340,25));

        JLabel filterLabel = new JLabel(title);
        filterLabel.setForeground(ViewConstants.ACCENT_COLOR);
        filterLabel.setFont(ViewConstants.DEFAULT_FONT);
        filterLabel.setPreferredSize(new Dimension(80,30));

        this.optionBox = new JComboBox<>(categoryList);

        optionBox.setFont(ViewConstants.DEFAULT_FONT);
        optionBox.setBackground(ViewConstants.ACCENT_COLOR);
        optionBox.setPreferredSize(new Dimension(150,30));

        add(filterLabel);
        add(optionBox);
    }

    public String getSelectedItem() {
        return (String) optionBox.getSelectedItem();
    }
}
