package view.resultframe;

import view.CustomButton;
import view.ViewConstants;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/**
 * Class representing the frame which shows all results.
 * Appears after pressing "calculate" from the MainFrame.
 */
public class ResultFrame extends JFrame {
    ArrayList<String> results;

    private final FilterPanel filterPanel = new FilterPanel();
    private final AspectPanel aspectPanel = new AspectPanel();

    protected JButton filterButton = new CustomButton(ViewConstants.BUTTON_TEXT_FILTER);

    /**
     * Constructor is called through the Controller, which gets the relevant arguments from MainFrame.
     * @param firstName name of owner of first birth chart
     * @param secondName name of owner of second birth chart
     * @param results list containing all aspects made between the planets
     */
    public ResultFrame(String firstName, String secondName, ArrayList<String> results) {
        this.results = results;
        aspectPanel.setResults(results);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(ViewConstants.RESULT_FRAME_WIDTH,ViewConstants.FRAME_HEIGHT);
        this.setResizable(false);
        this.setTitle(ViewConstants.RESULT_TEXT.toUpperCase(Locale.ROOT));
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(ViewConstants.BACKGROUND);

        JLabel title = new JLabel(firstName.toUpperCase(Locale.ROOT) + " AND " +
                secondName.toUpperCase(Locale.ROOT), SwingConstants.CENTER);
        title.setFont(ViewConstants.TITLE_FONT);
        title.setForeground(ViewConstants.ACCENT_COLOR);

        // BUTTON AREA
        JPanel buttonArea = new JPanel();
        buttonArea.setBackground(ViewConstants.BACKGROUND);
        buttonArea.setLayout(new FlowLayout());
        buttonArea.add(filterButton);

        // ADDING BORDERS
        title.setBorder(ViewConstants.BORDER);
        filterPanel.setBorder(ViewConstants.BORDER);
        aspectPanel.setBorder(ViewConstants.BORDER);
        filterButton.setBorder(ViewConstants.BORDER);

        // ADDING COMPONENTS TO FRAME
        add(title, BorderLayout.NORTH);
        add(buttonArea, BorderLayout.SOUTH);
        add(filterPanel, BorderLayout.WEST);
        add(aspectPanel, BorderLayout.EAST);

        this.setVisible(true);
    }

    /**
     * Method used in controller to set stats inside this frame.
     * @param stats stat results to display in ResultFrame
     */
    public void setStats(HashMap<String, ArrayList<Double>> stats) {
        filterPanel.setStats(stats);
    }

    /**
     * Method used for filtering the list of aspects displayed in ResultFrame.
     * @param entity which entity's aspects to display
     * @param aspect which aspect to display
     */
    public void filterResults(String entity, String aspect) {
        ArrayList<String> filteredResults = new ArrayList<>();

        String searchEntity;
        String searchAspect;

        if (entity.equals("All")) { searchEntity = " "; }
        else { searchEntity = entity.toUpperCase(Locale.ROOT); }

        if (aspect.equals("All")) { searchAspect = " "; }
        else { searchAspect = aspect.toUpperCase(Locale.ROOT); }

        for (String result : results) {
            if (result.contains(searchEntity) && result.contains(searchAspect)) {
                filteredResults.add(result);
            }
        }
        aspectPanel.setResults(filteredResults);
    }

    /**
     * Returns the selected value of the aspect combo box.
     * @return currently selected aspect as String
     */
    public String getAspectFilterValue() {
        return filterPanel.getAspectValue();
    }

    /**
     * Returns the selected value of the entity combo box.
     * @return current selected entity as String
     */
    public String getEntityFilterValue() {
        return filterPanel.getEntityValue();
    }

    /**
     * Method used in Controller to add functionality to filterButton.
     * @param listenOnButton ActionListener passed in through Controller
     */
    public void addFilterListener(ActionListener listenOnButton) {
        filterButton.addActionListener(listenOnButton);
    }
}
