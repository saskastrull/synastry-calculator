package model;

/**
 * Contains values used in AspectCalculator.
 */
public interface ModelConstants {
    int ZODIAC_DEGREES = 360;
    int LARGEST_ALLOWED_ORB = 10;

    int MODIFIER_BASE = 1;
    int MODIFIER_DOUBLE = 2;
    int MODIFIER_TRIPLE = 3;

    int STAT_NATURE_NEGATIVE = 0;
    int STAT_NATURE_POSITIVE = 1;
    int STAT_NATURE_MIXED = 2;

    // stats for each aspect
    double STAT_CONJUNCTION = 5.0;
    double STAT_OPPOSITION = 4.5;
    double STAT_TRINE = 4;
    double STAT_SQUARE = 4;
    double STAT_SEXTILE = 3.0;
    double STAT_MINOR_ASPECT = 2.0;

    // aspect degrees
    int DEGREE_CONJUNCTION = 0;
    int DEGREE_OPPOSITION = 180;
    int DEGREE_SQUARE = 90;
    int DEGREE_TRINE = 120;
    int DEGREE_SEXTILE = 60;
    int DEGREE_QUINCUNX = 150;
    int DEGREE_SESQUI_QUADRATE = 135;
    int DEGREE_SEMI_SQUARE = 45;
    int DEGREE_SEMI_SEXTILE = 30;
    int DEGREE_QUINTILE = 72;
    int DEGREE_BI_QUINTILE = 144;

    // max allowed orbs for each aspect
    int ORB_CONJUNCTION = 10;
    int ORB_OPPOSITION = 10;
    int ORB_SQUARE = 6;
    int ORB_TRINE = 6;
    int ORB_SEXTILE = 4;
    int ORB_MINOR = 2;

    String[] MALEFIC_PLANETS = {"Mars", "Saturn", "Uranus", "Neptune", "Pluto"};

    String CONJUNCTION = "conjunction";
    String OPPOSITION = "opposition";
    String SQUARE = "square";
    String TRINE = "trine";
    String SEXTILE = "sextile";
    String QUINCUNX = "quincunx";
    String SESQUI_QUADRATE = "sesqui-quadrate";
    String SEMI_SQUARE = "semi-square";
    String SEMI_SEXTILE = "semi-sextile";
    String QUINTILE = "quintile";
    String BI_QUINTILE = "bi-quintile";
}
