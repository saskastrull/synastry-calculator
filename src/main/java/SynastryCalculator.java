import controller.Controller;
import model.AspectCalculator;
import view.mainframe.MainFrame;

/**
 * Program starting point!
 */
public class SynastryCalculator {
    /**
     * Constructor creates view and model and passes them to the controller.
     */
    public static void main(final String[] args) {
        MainFrame view = new MainFrame();
        AspectCalculator model = new AspectCalculator();
        new Controller(view, model);
    }
}
