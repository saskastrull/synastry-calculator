package controller;

import model.AspectCalculator;
import view.mainframe.MainFrame;
import view.resultframe.ResultFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class which handles all communication between logic and interface.
 */
public class Controller {
    private ResultFrame resultFrame;
    private final MainFrame view;
    private final AspectCalculator model;

    /**
     * Class constructor assigns main frame (view) and logic to class fields.
     * @param view the graphical starting point of this program
     * @param model class containing the program's logic
     */
    public Controller(MainFrame view, AspectCalculator model) {
        this.view = view;
        this.model = model;
        this.view.addCalculateListener(new CalculateListener());
        this.view.addClearListener(new ClearListener());
    }

    /**
     * ActionListener responsible for resetting the main frame back to its original appearance.
     */
    private class ClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.clearFrame();
        }
    }

    /**
     * ActionListener responsible for performing the calculations.
     * Passes data from main frame to model and outputs the results inside a new frame.
     */
    private class CalculateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try { // destroy previous result window if it exist
                resultFrame.dispose();
            } catch (NullPointerException ignored) { }

            model.initializeStats(); // always initialize stats to 0, in case of new calculation
            resultFrame = new ResultFrame(view.getFirstPersonName(), view.getSecondPersonName(),
                    model.processCharts(view.getFirstPerson(), view.getSecondPerson()));
            resultFrame.setStats(model.getStats());
            resultFrame.addFilterListener(new FilterListener());
        }
    }

    /**
     * ActionListener responsible for filtering the resulting list of aspects.
     */
    private class FilterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            resultFrame.filterResults(resultFrame.getEntityFilterValue(), resultFrame.getAspectFilterValue());
        }
    }
}