package view;

import javax.swing.*;

/**
 * A custom button class, used both in MainFrame and ResultFrame.
 */
public class CustomButton extends JButton {
    public CustomButton(String buttonText) {
        super(buttonText);

        this.setPreferredSize(ViewConstants.BUTTON_DIMENSIONS);
        this.setFont(ViewConstants.DEFAULT_FONT);
        this.setBackground(ViewConstants.ACCENT_COLOR);
    }
}
