import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DemographicExample {
    /*
    https://de.wikipedia.org/wiki/Einwohnerentwicklung_von_Berlin
    31. Dezember 1914 	1 945 684
    31. Dezember 1915 	1.835.094
    1. Dezember 1916 	1.771.491
    5. Dezember 1917 	1.744.085
    31. Dezember 1918 	1.748.000
    8. Oktober 1919 	1.902.508
    31. Dezember 1919 	1.928.432
     */
    private void showExample() throws ParseException {
        List<Date> xData = new ArrayList<>();
        DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        xData.add(sdf.parse("19141231"));
        xData.add(sdf.parse("19151231"));
        xData.add(sdf.parse("19161201"));
        xData.add(sdf.parse("19171205"));
        xData.add(sdf.parse("19181231"));
        xData.add(sdf.parse("19191008"));
        xData.add(sdf.parse("19191231"));

        List<Integer> berlinNumbers = new ArrayList<>();
        berlinNumbers.add(1945684);
        berlinNumbers.add(1835094);
        berlinNumbers.add(1771491);
        berlinNumbers.add(1744085);
        berlinNumbers.add(1748000);
        berlinNumbers.add(1902508);
        berlinNumbers.add(1928432);

        /*
        https://de.wikipedia.org/wiki/Einwohnerentwicklung_von_Hamburg
        31. Dezember 1913 	1.031.480
        1. Dezember 1916 	876.833
        5. Dezember 1917 	846.055
        8. Oktober 1919  	985.779
        31. Dezember 1919 	1.004.427
         */
        List<Date> hamburgDates = new ArrayList<>();
        hamburgDates.add(sdf.parse("19131231"));
        hamburgDates.add(sdf.parse("19161201"));
        hamburgDates.add(sdf.parse("19171205"));
        hamburgDates.add(sdf.parse("19191008"));
        hamburgDates.add(sdf.parse("19191231"));
        List<Integer> hamburgNumbers = new ArrayList<>();
        hamburgNumbers.add(1031480);
        hamburgNumbers.add(876833);
        hamburgNumbers.add(846055);
        hamburgNumbers.add(985779);
        hamburgNumbers.add(1004427);

        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        chart.getStyler().setDatePattern("yyyy-MM-dd");
        chart.addSeries("Berlin", xData, berlinNumbers);
        chart.addSeries("Hamburg", hamburgDates, hamburgNumbers);
        new SwingWrapper(chart).displayChart();
    }







    public static void main(String[] args) throws ParseException {
        DemographicExample demographicExample = new DemographicExample();
        demographicExample.showExample();
    }
}
