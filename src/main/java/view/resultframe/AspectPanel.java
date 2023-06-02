package view.resultframe;

import view.ViewConstants;
import javax.swing.*;
import java.util.ArrayList;

/**
 * Class representing a panel which displays all aspects in text form.
 * Shows up on the right side inside the ResultFrame.
 */
public class AspectPanel extends JPanel {
    private final JTextArea aspectText = new JTextArea (26,52);

    public AspectPanel() {
        this.setBackground(ViewConstants.BACKGROUND);

        aspectText.setForeground(ViewConstants.BACKGROUND);
        aspectText.setBackground(ViewConstants.ACCENT_COLOR);
        aspectText.setFont(ViewConstants.DEFAULT_FONT);
        aspectText.setEditable (false);

        JScrollPane scroll = new JScrollPane ( aspectText );
        scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );

        add(scroll);
    }

    public void setResults(ArrayList<String> results) {
        aspectText.setText(""); // always clear first, in case filter functionality is used after setting results
        for (String result : results) {
            aspectText.append(result + "\n");
        }
    }
}
