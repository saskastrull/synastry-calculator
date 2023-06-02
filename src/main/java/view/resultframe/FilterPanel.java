package view.resultframe;

import view.ViewConstants;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class which contains the filter options of the ResultFrame.
 * Shows up on the upper left side inside the ResultFrame.
 */
public class FilterPanel extends JPanel {
    FilterCategory aspectFilter = new FilterCategory(ViewConstants.ASPECTS, "Aspects:");
    FilterCategory entityFilter = new FilterCategory(ViewConstants.PLANETS, "Entity:");
    StatisticsPanel statistics = new StatisticsPanel();

    public FilterPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(ViewConstants.BACKGROUND);

        add(entityFilter);
        add(aspectFilter);
        add(statistics);
    }

    public String getAspectValue() {
        return aspectFilter.getSelectedItem();
    }

    public String getEntityValue() {
        return entityFilter.getSelectedItem();
    }

    public void setStats(HashMap<String, ArrayList<Double>> stats) {
        statistics.setStats(stats);
    }
}
