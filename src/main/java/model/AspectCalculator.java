package model;

import java.util.*;

/**
 * Class containing the logic for calculating aspects, orbs and stats.
 * Used by Controller which passes information from MainFrame to AspectCalculator,
 * and then prints the results produced by AspectCalculator into the ResultFrame.
 */
public class AspectCalculator {

    // these are assigned values in method processCharts()
    private String firstPersonName;
    private String secondPersonName;

    // format: ASC=[0.0, 0.0], Mars=[0.0, 0.0], Neptune=[0.0, 0.0]
    private final HashMap<String, ArrayList<Double>> STATS = new HashMap<>();

    /**
     * Class constructor always initializes STATS.
     */
    public AspectCalculator() {
        initializeStats();
    }

    /**
     * Used to initialize class field STATS to a HashMap of keys and empty values.
     */
    public void initializeStats() {
        ArrayList<Double> empty = new ArrayList<>();
        empty.add(0.0); // holder for negative value, index 0
        empty.add(0.0); // holder for positive value, index 1

        this.STATS.put("ASC", empty);
        this.STATS.put("Sun", empty);
        this.STATS.put("Moon", empty);
        this.STATS.put("Mercury", empty);
        this.STATS.put("Venus", empty);
        this.STATS.put("Mars", empty);
        this.STATS.put("MC", empty);
        this.STATS.put("Jupiter", empty);
        this.STATS.put("Saturn", empty);
        this.STATS.put("Uranus", empty);
        this.STATS.put("Neptune", empty);
        this.STATS.put("Pluto", empty);
    }

    /**
     * Increments both values of an entity.
     * @param key name of entity's values to increment
     * @param posValue positive value to increment
     * @param negValue negative value to increment
     */
    private void setStats(String key, double posValue, double negValue) {
        ArrayList<Double> newValues = new ArrayList<>();

        double oldNegValue = STATS.get(key).get(0);
        double oldPosValue = STATS.get(key).get(1);

        newValues.add(oldNegValue + negValue);
        newValues.add(oldPosValue + posValue);

        STATS.put(key, newValues);
    }

    /**
     * Increments the positive value of an entity.
     * @param key name of entity's positive value to increment
     * @param posValue positive value to increment
     */
    private void setPosStat(String key, double posValue) {
        ArrayList<Double> newValues = new ArrayList<>();

        double oldNegValue = STATS.get(key).get(0);
        double oldPosValue = STATS.get(key).get(1);

        newValues.add(oldNegValue);
        newValues.add(oldPosValue + posValue);

        STATS.put(key, newValues);
    }

    /**
     * Increments the negative value of an entity.
     * @param key name of entity's negative value to increment
     * @param negValue negative value to increment
     */
    private void setNegStat(String key, double negValue) {
        ArrayList<Double> newValues = new ArrayList<>();

        double oldNegValue = STATS.get(key).get(0);
        double oldPosValue = STATS.get(key).get(1);

        newValues.add(oldNegValue + negValue);
        newValues.add(oldPosValue);

        STATS.put(key, newValues);
    }

    /**
     * Checks if given aspect is detected or not.
     * @param firstPlacement placement of entity belonging to the first person
     * @param secondPlacement placement of entity belonging to the second person
     * @param aspectDegree degree of aspect to check if it exists
     * @param allowedOrb maximum allowed alteration to aspect degree
     * @return true if method detects valid aspect based on aspectDegree
     */
    private boolean checkForAspect(int firstPlacement, int secondPlacement, int aspectDegree, int allowedOrb) {
        boolean validAspect = false;

        int lowerLimit = aspectDegree - allowedOrb;
        int upperLimit = aspectDegree + allowedOrb;

        int largerPlacement = Math.max(firstPlacement, secondPlacement);
        int smallerPlacement = Math.min(firstPlacement, secondPlacement);

        // check general
        if ((largerPlacement >= (smallerPlacement + lowerLimit)) && (largerPlacement <= (smallerPlacement + upperLimit))) {
            validAspect = true;
        }
        // if aspect is reversed
        else if ((largerPlacement >= (smallerPlacement + ((ModelConstants.ZODIAC_DEGREES - aspectDegree) - allowedOrb))) &&
                (largerPlacement <= (smallerPlacement + ((ModelConstants.ZODIAC_DEGREES - aspectDegree) + allowedOrb)))) {
            validAspect = true;
        }
        return validAspect;
    }

    /**
     * Checks the total orb of an already confirmed aspect.
     * @param firstPlacement placement of entity belonging to the first person
     * @param secondPlacement placement of entity belonging to the second person
     * @param aspectDegree degree of aspect to check orb on
     * @return total orb between two entities
     */
    private int checkOrb(int firstPlacement, int secondPlacement, int aspectDegree) {
        int largerDegree = Math.max(firstPlacement, secondPlacement);
        int smallerDegree = Math.min(firstPlacement, secondPlacement);
        int orb;

        // if aspect is reversed
        if (largerDegree - smallerDegree - aspectDegree > ModelConstants.LARGEST_ALLOWED_ORB) {
            orb = largerDegree - smallerDegree - (ModelConstants.ZODIAC_DEGREES - aspectDegree);
        }
        else { // if placement is not reversed
            orb = largerDegree - smallerDegree - aspectDegree;
        }
        return orb;
    }

    /**
     * Checks if entity is malefic or not.
     * All malefic entities are listed in ModelConstants.
     * @param entity entity to check malefic nature of
     * @return true if argument matches name of a malefic entity
     */
    private boolean maleficPlanet(String entity) {
        return Arrays.asList(ModelConstants.MALEFIC_PLANETS).contains(entity);
    }

    /**
     * Adjusts the stats of two entities involved in an aspect, based on given arguments.
     * @param orb aspect orb which alters strength of resulting entity stats
     * @param allowedOrb adds relevant context to orb when deciding stat strength
     * @param aspect decides baseStat, as well as whether stat nature is negative, positive or both
     * @param firstEntity entity belonging to the first person
     * @param secondEntity entity belonging to the second person
     */
    private void calculateStats(int orb, int allowedOrb, String aspect, String firstEntity, String secondEntity) {
        double baseStat = 0;
        int modifier = ModelConstants.MODIFIER_BASE; // default value used for wide orbs
        int statNature = 0; // 0 if negative aspect, 1 if positive aspect, 2 if mixed

        if (orb < 2) { modifier = ModelConstants.MODIFIER_TRIPLE; }
        else if (orb < (allowedOrb / 2) + 1) { modifier = ModelConstants.MODIFIER_DOUBLE; }

        switch (aspect) {
            case ModelConstants.CONJUNCTION -> {
                baseStat = ModelConstants.STAT_CONJUNCTION;
                if (maleficPlanet(firstEntity) || maleficPlanet(secondEntity)) {
                    statNature = ModelConstants.STAT_NATURE_MIXED;
                }
                else {
                    statNature = ModelConstants.STAT_NATURE_POSITIVE;
                }
            }
            case ModelConstants.OPPOSITION -> {
                baseStat = ModelConstants.STAT_OPPOSITION;
                statNature = ModelConstants.STAT_NATURE_NEGATIVE;
            }
            case ModelConstants.SQUARE -> {
                baseStat = ModelConstants.STAT_SQUARE;
                statNature = ModelConstants.STAT_NATURE_NEGATIVE;
            }
            case ModelConstants.TRINE -> {
                baseStat = ModelConstants.STAT_TRINE;
                statNature = ModelConstants.STAT_NATURE_POSITIVE;
            }
            case ModelConstants.SEXTILE -> {
                baseStat = ModelConstants.STAT_SEXTILE;
                statNature = ModelConstants.STAT_NATURE_POSITIVE;
            }
            case ModelConstants.QUINTILE, ModelConstants.BI_QUINTILE -> {
                baseStat = ModelConstants.STAT_MINOR_ASPECT;
                statNature = ModelConstants.STAT_NATURE_POSITIVE;
            }
            case ModelConstants.QUINCUNX, ModelConstants.SEMI_SQUARE, ModelConstants.SESQUI_QUADRATE, ModelConstants.SEMI_SEXTILE -> {
                baseStat = ModelConstants.STAT_MINOR_ASPECT;
                statNature = ModelConstants.STAT_NATURE_NEGATIVE;
            }
        }

        try {
            if (statNature == ModelConstants.STAT_NATURE_NEGATIVE) {
                setNegStat(firstEntity, baseStat * modifier);
                setNegStat(secondEntity, baseStat * modifier);
            }
            else if (statNature == ModelConstants.STAT_NATURE_POSITIVE) {
                setPosStat(firstEntity, baseStat * modifier);
                setPosStat(secondEntity, baseStat * modifier);
            }
            else {
                setStats(firstEntity, (baseStat * modifier) / 2, (baseStat * modifier) / 2);
                setStats(secondEntity, (baseStat * modifier) / 2, (baseStat * modifier) / 2);
            }
        }
        catch(NullPointerException ignored) { } // custom entities are always ignored
    }

    /**
     * Calculates the total degree of an entity based on its sign placement.
     * @param sign location of entity based on sign
     * @param degree specific degree inside sign
     * @return total degree of zodiac, ignoring sign
     */
    private int calculateDegrees (String sign, int degree) {
        int totalDegree = 0;
        switch (sign) {
            case "Aries" -> totalDegree = degree;
            case "Taurus" -> totalDegree = degree + 30;
            case "Gemini" -> totalDegree = degree + 60;
            case "Cancer" -> totalDegree = degree + 90;
            case "Leo" -> totalDegree = degree + 120;
            case "Virgo" -> totalDegree = degree + 150;
            case "Libra" -> totalDegree = degree + 180;
            case "Scorpio" -> totalDegree = degree + 210;
            case "Sagittarius" -> totalDegree = degree + 240;
            case "Capricorn" -> totalDegree = degree + 270;
            case "Aquarius" -> totalDegree = degree + 300;
            case "Pisces" -> totalDegree = degree + 330;
        }
        return totalDegree;
    }

    /**
     * Creates and formats a description of an aspect taking place between two entities.
     * @param firstPersonName name of first person
     * @param secondPersonName name of second person
     * @param firstEntity entity belonging to the first person
     * @param secondEntity entity belonging to the second person
     * @param aspect the aspect which is formed between the two entities
     * @param orb the size of the aspect orb
     * @return a sentence describing the aspect between two entities
     */
    private String formatText(String firstPersonName, String secondPersonName, String firstEntity, String secondEntity,
                              String aspect, int orb) {
        return firstPersonName.toUpperCase(Locale.ROOT) + "S " + firstEntity.toUpperCase(Locale.ROOT) + " " +
                aspect.toUpperCase(Locale.ROOT) + " " + secondPersonName.toUpperCase(Locale.ROOT) + "S " +
                secondEntity.toUpperCase(Locale.ROOT) + " " + orb + "° ORB";
    }

    /**
     * Uses most methods of this class in order to calculate stats and produce a text of results.
     * @param firstChart natal chart of first person
     * @param secondChart natal chart of second person
     * @return all resulting aspects in the form of a text
     */
    private ArrayList<String> calculateAspects(HashMap<String, Integer> firstChart,
                                               HashMap<String, Integer> secondChart) {
        int orb;
        String aspect;
        ArrayList<String> aspects = new ArrayList<>();

        for ( Map.Entry<String, Integer> firstPlacement : firstChart.entrySet()) {
            for ( Map.Entry<String, Integer> secondPlacement : secondChart.entrySet()) {
                // major aspects:
                if (checkForAspect(firstPlacement.getValue(), secondPlacement.getValue(),
                        ModelConstants.DEGREE_CONJUNCTION, ModelConstants.ORB_CONJUNCTION)) {
                    aspect = ModelConstants.CONJUNCTION;
                    orb = checkOrb(firstPlacement.getValue(), secondPlacement.getValue(), ModelConstants.DEGREE_CONJUNCTION);
                    calculateStats(orb, ModelConstants.ORB_CONJUNCTION, aspect, firstPlacement.getKey(), secondPlacement.getKey());
                    aspects.add(formatText(firstPersonName, secondPersonName, firstPlacement.getKey(),
                            secondPlacement.getKey(), aspect, orb));
                }
                else if (checkForAspect(firstPlacement.getValue(), secondPlacement.getValue(),
                        ModelConstants.DEGREE_OPPOSITION, ModelConstants.ORB_OPPOSITION)) {
                    aspect = ModelConstants.OPPOSITION;
                    orb = checkOrb(firstPlacement.getValue(), secondPlacement.getValue(), ModelConstants.DEGREE_OPPOSITION);
                    calculateStats(orb, ModelConstants.ORB_OPPOSITION, aspect, firstPlacement.getKey(), secondPlacement.getKey());
                    aspects.add(formatText(firstPersonName, secondPersonName, firstPlacement.getKey(),
                            secondPlacement.getKey(), aspect, orb));
                }
                else if (checkForAspect(firstPlacement.getValue(), secondPlacement.getValue(),
                        ModelConstants.DEGREE_SQUARE, ModelConstants.ORB_SQUARE)) {
                    aspect = ModelConstants.SQUARE;
                    orb = checkOrb(firstPlacement.getValue(), secondPlacement.getValue(), ModelConstants.DEGREE_SQUARE);
                    calculateStats(orb, ModelConstants.ORB_SQUARE, aspect, firstPlacement.getKey(), secondPlacement.getKey());
                    aspects.add(formatText(firstPersonName, secondPersonName, firstPlacement.getKey(),
                            secondPlacement.getKey(), aspect, orb));
                }
                else if (checkForAspect(firstPlacement.getValue(), secondPlacement.getValue(),
                        ModelConstants.DEGREE_TRINE, ModelConstants.ORB_TRINE)) {
                    aspect = ModelConstants.TRINE;
                    orb = checkOrb(firstPlacement.getValue(), secondPlacement.getValue(), ModelConstants.DEGREE_TRINE);
                    calculateStats(orb, ModelConstants.ORB_TRINE, aspect, firstPlacement.getKey(), secondPlacement.getKey());
                    aspects.add(formatText(firstPersonName, secondPersonName, firstPlacement.getKey(),
                            secondPlacement.getKey(), aspect, orb));
                }
                else if (checkForAspect(firstPlacement.getValue(), secondPlacement.getValue(),
                        ModelConstants.DEGREE_SEXTILE, ModelConstants.ORB_SEXTILE)) {
                    aspect = ModelConstants.SEXTILE;
                    orb = checkOrb(firstPlacement.getValue(), secondPlacement.getValue(), ModelConstants.DEGREE_SEXTILE);
                    calculateStats(orb, ModelConstants.ORB_SEXTILE, aspect, firstPlacement.getKey(), secondPlacement.getKey());
                    aspects.add(formatText(firstPersonName, secondPersonName, firstPlacement.getKey(),
                            secondPlacement.getKey(), aspect, orb));
                }
                // minor aspects:
                else if (checkForAspect(firstPlacement.getValue(), secondPlacement.getValue(),
                        ModelConstants.DEGREE_QUINCUNX, ModelConstants.ORB_MINOR)) {
                    aspect = ModelConstants.QUINCUNX;
                    orb = checkOrb(firstPlacement.getValue(), secondPlacement.getValue(), ModelConstants.DEGREE_QUINCUNX);
                    calculateStats(orb, ModelConstants.ORB_MINOR, aspect, firstPlacement.getKey(), secondPlacement.getKey());
                    aspects.add(formatText(firstPersonName, secondPersonName, firstPlacement.getKey(),
                            secondPlacement.getKey(), aspect, orb));
                }
                else if (checkForAspect(firstPlacement.getValue(), secondPlacement.getValue(),
                        ModelConstants.DEGREE_SESQUI_QUADRATE, ModelConstants.ORB_MINOR)) {
                    aspect = ModelConstants.SESQUI_QUADRATE;
                    orb = checkOrb(firstPlacement.getValue(), secondPlacement.getValue(), ModelConstants.DEGREE_SESQUI_QUADRATE);
                    calculateStats(orb, ModelConstants.ORB_MINOR, aspect, firstPlacement.getKey(), secondPlacement.getKey());
                    aspects.add(formatText(firstPersonName, secondPersonName, firstPlacement.getKey(),
                            secondPlacement.getKey(), aspect, orb));
                }
                else if (checkForAspect(firstPlacement.getValue(), secondPlacement.getValue(),
                        ModelConstants.DEGREE_SEMI_SQUARE, ModelConstants.ORB_MINOR)) {
                    aspect = ModelConstants.SEMI_SQUARE;
                    orb = checkOrb(firstPlacement.getValue(), secondPlacement.getValue(), ModelConstants.DEGREE_SEMI_SQUARE);
                    calculateStats(orb, ModelConstants.ORB_MINOR, aspect, firstPlacement.getKey(), secondPlacement.getKey());
                    aspects.add(formatText(firstPersonName, secondPersonName, firstPlacement.getKey(),
                            secondPlacement.getKey(), aspect, orb));
                }
                else if (checkForAspect(firstPlacement.getValue(), secondPlacement.getValue(),
                        ModelConstants.DEGREE_SEMI_SEXTILE, ModelConstants.ORB_MINOR)) {
                    aspect = ModelConstants.SEMI_SEXTILE;
                    orb = checkOrb(firstPlacement.getValue(), secondPlacement.getValue(), ModelConstants.DEGREE_SEMI_SEXTILE);
                    calculateStats(orb, ModelConstants.ORB_MINOR, aspect, firstPlacement.getKey(), secondPlacement.getKey());
                    aspects.add(formatText(firstPersonName, secondPersonName, firstPlacement.getKey(),
                            secondPlacement.getKey(), aspect, orb));
                }
                else if (checkForAspect(firstPlacement.getValue(), secondPlacement.getValue(),
                        ModelConstants.DEGREE_QUINTILE, ModelConstants.ORB_MINOR)) {
                    aspect = ModelConstants.QUINTILE;
                    orb = checkOrb(firstPlacement.getValue(), secondPlacement.getValue(), ModelConstants.DEGREE_QUINTILE);
                    calculateStats(orb, ModelConstants.ORB_MINOR, aspect, firstPlacement.getKey(), secondPlacement.getKey());
                    aspects.add(formatText(firstPersonName, secondPersonName, firstPlacement.getKey(),
                            secondPlacement.getKey(), aspect, orb));
                }
                else if (checkForAspect(firstPlacement.getValue(), secondPlacement.getValue(),
                        ModelConstants.DEGREE_BI_QUINTILE, ModelConstants.ORB_MINOR)) {
                    aspect = ModelConstants.BI_QUINTILE;
                    orb = checkOrb(firstPlacement.getValue(), secondPlacement.getValue(), ModelConstants.DEGREE_BI_QUINTILE);
                    calculateStats(orb, ModelConstants.ORB_MINOR, aspect, firstPlacement.getKey(), secondPlacement.getKey());
                    aspects.add(formatText(firstPersonName, secondPersonName, firstPlacement.getKey(),
                            secondPlacement.getKey(), aspect, orb));
                }
            }
        }
        return aspects;
    }

    /**
     * Reformats two charts in order for this class to then perform further methods.
     * This public method is called from the controller, and untangles the natal charts of two people,
     * saving their names as private class fields. This method also adds an IC point opposite to a persons MC,
     * as well as a DC point opposite to a persons ASC, before finally calling calculateAspects() to perform
     * the actual calculations.
     * @param firstChart natal chart of first person
     * @param secondChart natal chart of second person
     * @return all resulting aspects in the form of a text
     */
    public ArrayList<String> processCharts(HashMap<String, ArrayList<HashMap<String, ArrayList<String>>>> firstChart,
                                           HashMap<String, ArrayList<HashMap<String, ArrayList<String>>>> secondChart) {

        HashMap<String, Integer> firstChartDegrees = new HashMap<>();
        HashMap<String, Integer> secondChartDegrees = new HashMap<>();

        for ( String key : firstChart.keySet() ) { // key is name of person
            this.firstPersonName = key;
            for ( HashMap<String, ArrayList<String>> entity : firstChart.get(key)) { // access all entities
                for (Map.Entry<String, ArrayList<String>> placement : entity.entrySet()) {
                    firstChartDegrees.put(placement.getKey(), calculateDegrees(placement.getValue().get(0),
                              Integer.parseInt(placement.getValue().get(1)))); // add set to processed chart
                }
            }
        }

        for ( String key : secondChart.keySet() ) {
            this.secondPersonName = key;
            for ( HashMap<String, ArrayList<String>> entity : secondChart.get(key)) {
                for (Map.Entry<String, ArrayList<String>> placement : entity.entrySet()) {
                    secondChartDegrees.put(placement.getKey(), calculateDegrees(placement.getValue().get(0),
                            Integer.parseInt(placement.getValue().get(1))));
                }
            }
        }

        // add DC and IC to both hashmaps, based on ASC and MC positions
        try {
            if (firstChartDegrees.get("ASC") > 180) {
                firstChartDegrees.put("DC", firstChartDegrees.get("ASC") - 180);
            }
            else { firstChartDegrees.put("DC", firstChartDegrees.get("ASC") + 180); }

            if (firstChartDegrees.get("MC") > 180) {
                firstChartDegrees.put("IC", firstChartDegrees.get("MC") - 180);
            }
            else { firstChartDegrees.put("IC", firstChartDegrees.get("MC") + 180); }

            if (secondChartDegrees.get("ASC") > 180) {
                secondChartDegrees.put("DC", secondChartDegrees.get("ASC") - 180);
            }
            else { secondChartDegrees.put("DC", secondChartDegrees.get("ASC") + 180); }

            if (secondChartDegrees.get("MC") > 180) {
                secondChartDegrees.put("IC", secondChartDegrees.get("MC") - 180);
            }
            else { secondChartDegrees.put("IC", secondChartDegrees.get("MC") + 180); }
        } catch (NullPointerException ignored) { } // ignored for tests

        // pass to calculateAspects in order to perform the actual calculations
        return calculateAspects(firstChartDegrees, secondChartDegrees);
    }

    /**
     * Returns the final stats.
     * @return the resulting stats of all aspects
     */
    public HashMap<String, ArrayList<Double>> getStats() {
        return this.STATS;
    }
}
