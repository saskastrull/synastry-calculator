package view;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Contains values used in MainFrame, ResultFrame, as well as inside their respective components.
 */
public interface ViewConstants {
    String[] SIGNS = {"Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo", "Libra",
            "Scorpio", "Sagittarius", "Capricorn", "Aquarius", "Pisces"};
    Integer[] DEGREES = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
            11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
            21, 22, 23, 24, 25, 26, 27, 28, 29};
    String[] PLANETS = {"All", "ASC", "Sun", "Moon", "Mercury", "Venus", "Mars", "MC",
            "Jupiter", "Saturn", "Uranus", "Neptune", "Pluto"};
    String[] ASPECTS = {"All", "Conjunction", "Opposition", "Square", "Trine", "Sextile", "Quincunx"};


    Color BACKGROUND = new Color(14, 0, 38);
    Color ACCENT_COLOR = new Color(255, 242, 215);
    Color POSITIVE_SCORE = new Color(171, 255, 148);
    Color NEGATIVE_SCORE = new Color(255, 160, 174);

    int FRAME_WIDTH = 600;
    int RESULT_FRAME_WIDTH = 900;
    int FRAME_HEIGHT = 710;

    String TITLE_TEXT = "Custom Synastry Calculator";
    String STATS_TITLE = "Statistics";
    String RESULT_TEXT = "Custom Synastry Results";
    String BUTTON_TEXT_CLEAR = "Clear";
    String BUTTON_TEXT_CALCULATE = "Calculate";
    String BUTTON_TEXT_FILTER = "Filter";

    Border BORDER = new LineBorder(ViewConstants.BACKGROUND, 10);

    Dimension BUTTON_DIMENSIONS = new Dimension(140,60);

    Font TITLE_FONT = new Font(Font.MONOSPACED, Font.BOLD, 24);
    Font DEFAULT_FONT = new Font(Font.MONOSPACED, Font.PLAIN, 14);
    Font STATS_TITLE_FONT = new Font(Font.MONOSPACED, Font.BOLD, 22);
    Font STATS_FONT = new Font(Font.MONOSPACED, Font.PLAIN, 17);
    Font SCORE_FONT = new Font(Font.MONOSPACED, Font.BOLD, 17);
}
