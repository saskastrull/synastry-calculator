package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

// format of firstExampleChart and secondExampleChart: {Name = [{ASC=[Aries, 0]}, {Sun=[Aries, 0]}, ...

public class AspectCalculatorTest {
    AspectCalculator calculator = new AspectCalculator();
    ArrayList<String> expectedResult = new ArrayList<>();

    HashMap<String, ArrayList<HashMap<String, ArrayList<String>>>> firstExampleChart = new HashMap<>();
    HashMap<String, ArrayList<HashMap<String, ArrayList<String>>>> secondExampleChart = new HashMap<>();

    public AspectCalculatorTest() {
        ArrayList<String> placementAries = new ArrayList<>();
        placementAries.add(0, "Aries");
        placementAries.add(1, "0");

        ArrayList<String> placementCancer = new ArrayList<>();
        placementCancer.add(0, "Cancer");
        placementCancer.add(1, "0");

        ArrayList<String> placementGemini = new ArrayList<>();
        placementGemini.add(0, "Gemini");
        placementGemini.add(1, "0");

        ArrayList<String> placementLeo = new ArrayList<>();
        placementLeo.add(0, "Leo");
        placementLeo.add(1, "0");

        ArrayList<String> placementLibra = new ArrayList<>();
        placementLibra.add(0, "Libra");
        placementLibra.add(1, "0");

        HashMap<String, ArrayList<String>> placementSun = new HashMap<>();
        placementSun.put("Sun", placementAries);

        HashMap<String, ArrayList<String>> placementMoon = new HashMap<>();
        placementMoon.put("Moon", placementCancer);

        HashMap<String, ArrayList<String>> placementMercury = new HashMap<>();
        placementMercury.put("Mercury", placementGemini);

        HashMap<String, ArrayList<String>> placementVenus = new HashMap<>();
        placementVenus.put("Venus", placementLeo);

        HashMap<String, ArrayList<String>> placementMars = new HashMap<>();
        placementMars.put("Mars", placementLibra);

        ArrayList<HashMap<String, ArrayList<String>>> firstExamplePlacements = new ArrayList<>();
        firstExamplePlacements.add(placementSun);
        firstExamplePlacements.add(placementMoon);
        firstExamplePlacements.add(placementMercury);
        firstExamplePlacements.add(placementVenus);
        firstExamplePlacements.add(placementMars);

        ArrayList<HashMap<String, ArrayList<String>>> secondExamplePlacements = new ArrayList<>();
        secondExamplePlacements.add(placementSun);

        firstExampleChart.put("Naruto", firstExamplePlacements);
        secondExampleChart.put("Sasuke", secondExamplePlacements);

        expectedResult.add("NARUTOS MARS OPPOSITION SASUKES SUN 0° ORB");
        expectedResult.add("NARUTOS MOON SQUARE SASUKES SUN 0° ORB");
        expectedResult.add("NARUTOS VENUS TRINE SASUKES SUN 0° ORB");
        expectedResult.add("NARUTOS SUN CONJUNCTION SASUKES SUN 0° ORB");
        expectedResult.add("NARUTOS MERCURY SEXTILE SASUKES SUN 0° ORB");
    }

    @Test
    void processChartsTest() {
        assertEquals(expectedResult, calculator.processCharts(firstExampleChart, secondExampleChart));
    }
}