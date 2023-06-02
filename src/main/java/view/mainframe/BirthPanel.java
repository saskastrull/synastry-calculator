package view.mainframe;

import view.ViewConstants;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Panel used in MainView for inputting a single birth chart.
 * Consists of all the smaller components found in the view folder.
 */
public class BirthPanel extends JPanel {
    NameField nameField = new NameField(); // owner of chart
    Placement ascendant = new Placement("ASC");
    Placement sun = new Placement("Sun");
    Placement moon = new Placement("Moon");
    Placement mercury = new Placement("Mercury");
    Placement venus = new Placement("Venus");
    Placement mars = new Placement("Mars");
    Placement midheaven = new Placement("MC");
    Placement jupiter = new Placement("Jupiter");
    Placement saturn = new Placement("Saturn");
    Placement uranus = new Placement("Uranus");
    Placement neptune = new Placement("Neptune");
    Placement pluto = new Placement("Pluto");
    CustomPlacement custom = new CustomPlacement();

    BirthPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(ViewConstants.BACKGROUND);

        add(nameField);
        add(ascendant);
        add(sun);
        add(moon);
        add(mercury);
        add(venus);
        add(mars);
        add(midheaven);
        add(jupiter);
        add(saturn);
        add(uranus);
        add(neptune);
        add(pluto);
        add(custom);
    }

    public HashMap<String, ArrayList<HashMap<String, ArrayList<String>>>> getPlacements() {
        HashMap<String, ArrayList<HashMap<String, ArrayList<String>>>> personsPlacements = new HashMap<>();
        ArrayList<HashMap<String, ArrayList<String>>> placements = new ArrayList<>();

        placements.add(ascendant.getPlacement());
        placements.add(sun.getPlacement());
        placements.add(moon.getPlacement());
        placements.add(mercury.getPlacement());
        placements.add(venus.getPlacement());
        placements.add(mars.getPlacement());
        placements.add(midheaven.getPlacement());
        placements.add(jupiter.getPlacement());
        placements.add(saturn.getPlacement());
        placements.add(uranus.getPlacement());
        placements.add(neptune.getPlacement());
        placements.add(pluto.getPlacement());
        placements.add(custom.getPlacement());

        personsPlacements.put(getPersonsName(), placements);

        return personsPlacements;
    }

    public String getPersonsName() {
        return nameField.getPersonsName();
    }

    public void resetBirthPanel() {
        nameField.resetName();
        ascendant.resetPlacement();
        sun.resetPlacement();
        moon.resetPlacement();
        mercury.resetPlacement();
        venus.resetPlacement();
        mars.resetPlacement();
        midheaven.resetPlacement();
        jupiter.resetPlacement();
        saturn.resetPlacement();
        uranus.resetPlacement();
        neptune.resetPlacement();
        pluto.resetPlacement();
        custom.resetCustomPlacement();
    }
}
