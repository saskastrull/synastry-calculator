package view.mainframe;

import view.ViewConstants;
import view.CustomButton;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/**
 * Class representing the graphical starting point of this program.
 */
public class MainFrame extends JFrame {
    private final BirthPanel firstBirthPanel = new BirthPanel();
    private final BirthPanel secondBirthPanel = new BirthPanel();

    protected JButton clearButton = new CustomButton(ViewConstants.BUTTON_TEXT_CLEAR);
    protected JButton calculateButton = new CustomButton(ViewConstants.BUTTON_TEXT_CALCULATE);

    /**
     * Class constructor which sets up the layout of this frame.
     */
    public MainFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(ViewConstants.FRAME_WIDTH,ViewConstants.FRAME_HEIGHT);
        this.setResizable(false);
        this.setTitle(ViewConstants.TITLE_TEXT.toUpperCase(Locale.ROOT));
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(ViewConstants.BACKGROUND);

        JLabel title = new JLabel(ViewConstants.TITLE_TEXT.toUpperCase(Locale.ROOT), SwingConstants.CENTER);
        title.setFont(ViewConstants.TITLE_FONT);
        title.setForeground(ViewConstants.ACCENT_COLOR);

        // BUTTON AREA
        JPanel buttonArea = new JPanel();
        buttonArea.setBackground(ViewConstants.BACKGROUND);
        buttonArea.setLayout(new FlowLayout());
        buttonArea.add(calculateButton);
        buttonArea.add(clearButton);

        // ADDING BORDERS
        title.setBorder(ViewConstants.BORDER);
        firstBirthPanel.setBorder(ViewConstants.BORDER);
        secondBirthPanel.setBorder(ViewConstants.BORDER);
        clearButton.setBorder(ViewConstants.BORDER);
        calculateButton.setBorder(ViewConstants.BORDER);

        // ADDING COMPONENTS TO FRAME
        add(title, BorderLayout.NORTH);
        add(buttonArea, BorderLayout.SOUTH);
        add(firstBirthPanel, BorderLayout.WEST);
        add(secondBirthPanel, BorderLayout.EAST);

        this.setVisible(true);
    }

    /**
     * Method which returns the name of the first chart owner.
     * @return name of the first chart owner
     */
    public String getFirstPersonName() {
        return firstBirthPanel.getPersonsName();
    }

    /**
     * Method which returns the name of the second chart owner.
     * @return name of the second chart owner
     */
    public String getSecondPersonName() {
        return secondBirthPanel.getPersonsName();
    }

    /**
     * Method which resets the frame to its original state.
     */
    public void clearFrame() {
        firstBirthPanel.resetBirthPanel();
        secondBirthPanel.resetBirthPanel();
    }

    /**
     * Method which returns the birth chart of the first person.
     * @return a HashMap containing all entity positions of the first person
     */
    public HashMap<String, ArrayList<HashMap<String, ArrayList<String>>>> getFirstPerson() {
        return firstBirthPanel.getPlacements();
    }

    /**
     * Method which returns the birth chart of the second person.
     * @return a HashMap containing all entity positions of the second person
     */
    public HashMap<String, ArrayList<HashMap<String, ArrayList<String>>>> getSecondPerson() {
        return secondBirthPanel.getPlacements();
    }

    /**
     * Method used in Controller to add functionality to clearButton.
     * @param listenOnButton ActionListener passed in through Controller
     */
    public void addClearListener(ActionListener listenOnButton) {
        clearButton.addActionListener(listenOnButton);
    }

    /**
     * Method used in Controller to add functionality to calculateButton.
     * @param listenOnButton ActionListener passed in through Controller
     */
    public void addCalculateListener(ActionListener listenOnButton) {
        calculateButton.addActionListener(listenOnButton);
    }
}
