package view.resultframe;

import view.ViewConstants;
import javax.swing.*;
import java.awt.*;

/**
 * Class which displays the positive and negative values for an entity.
 */
public class EntityStats extends JPanel {
    protected String entity;
    JLabel positiveStat = new JLabel("0");
    JLabel negativeStat = new JLabel("0");

    public EntityStats(String entity) {
        this.entity = entity;
        this.setBackground(ViewConstants.BACKGROUND);
        this.setLayout(new FlowLayout());

        JLabel entityName = new JLabel(entity + ":");
        entityName.setForeground(ViewConstants.ACCENT_COLOR);
        entityName.setFont(ViewConstants.STATS_FONT);
        entityName.setPreferredSize(new Dimension(100,20));

        positiveStat.setForeground(ViewConstants.POSITIVE_SCORE);
        positiveStat.setFont(ViewConstants.SCORE_FONT);
        positiveStat.setPreferredSize(new Dimension(100,20));

        negativeStat.setForeground(ViewConstants.NEGATIVE_SCORE);
        negativeStat.setFont(ViewConstants.SCORE_FONT);
        negativeStat.setPreferredSize(new Dimension(65,20));

        add(entityName);
        add(positiveStat);
        add(negativeStat);
    }

    public void setStats(double positiveStat, double negativeStat) {
        this.positiveStat.setText(Double.toString(positiveStat));
        this.negativeStat.setText(Double.toString(negativeStat));
    }
}
