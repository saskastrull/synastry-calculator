package view.resultframe;

import view.ViewConstants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/**
 * Class representing all entity stats shown in the ResultFrame.
 * Shows up on the lower left side inside the ResultFrame.
 */
public class StatisticsPanel extends JPanel {
    EntityStats ascStats = new EntityStats("ASC");
    EntityStats sunStats = new EntityStats("Sun");
    EntityStats moonStats = new EntityStats("Moon");
    EntityStats mercuryStats = new EntityStats("Mercury");
    EntityStats venusStats = new EntityStats("Venus");
    EntityStats marsStats = new EntityStats("Mars");
    EntityStats jupiterStats = new EntityStats("Jupiter");
    EntityStats saturnStats = new EntityStats("Saturn");
    EntityStats uranusStats = new EntityStats("Uranus");
    EntityStats neptuneStats = new EntityStats("Neptune");
    EntityStats plutoStats = new EntityStats("Pluto");

    public StatisticsPanel() {
        this.setPreferredSize(new Dimension(350,420));
        this.setBackground(ViewConstants.BACKGROUND);

        JLabel title = new JLabel(ViewConstants.STATS_TITLE.toUpperCase(Locale.ROOT), SwingConstants.CENTER);
        title.setForeground(ViewConstants.ACCENT_COLOR);
        title.setFont(ViewConstants.STATS_TITLE_FONT);
        title.setPreferredSize(new Dimension(340,40));

        add(title);

        add(ascStats);
        add(sunStats);
        add(moonStats);
        add(mercuryStats);
        add(venusStats);
        add(marsStats);
        add(jupiterStats);
        add(saturnStats);
        add(uranusStats);
        add(neptuneStats);
        add(plutoStats);
    }

    public void setStats(HashMap<String, ArrayList<Double>> stats) {
        ascStats.setStats(stats.get("ASC").get(1), stats.get("ASC").get(0));
        sunStats.setStats(stats.get("Sun").get(1), stats.get("Sun").get(0));
        moonStats.setStats(stats.get("Moon").get(1), stats.get("Moon").get(0));
        mercuryStats.setStats(stats.get("Mercury").get(1), stats.get("Mercury").get(0));
        venusStats.setStats(stats.get("Venus").get(1), stats.get("Venus").get(0));
        marsStats.setStats(stats.get("Mars").get(1), stats.get("Mars").get(0));
        jupiterStats.setStats(stats.get("Jupiter").get(1), stats.get("Jupiter").get(0));
        saturnStats.setStats(stats.get("Saturn").get(1), stats.get("Saturn").get(0));
        uranusStats.setStats(stats.get("Uranus").get(1), stats.get("Uranus").get(0));
        neptuneStats.setStats(stats.get("Neptune").get(1), stats.get("Neptune").get(0));
        plutoStats.setStats(stats.get("Pluto").get(1), stats.get("Pluto").get(0));
    }
}
