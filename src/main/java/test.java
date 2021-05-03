import com.opencsv.CSVParser;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class test {

     public String [] a ={"19141231","19151231","19161201"} ;



    private void showExample() throws ParseException {

        List<Date> xData = new ArrayList<>();
        DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        for( int i = 0 ; i<a.length; i++){

            xData.add(sdf.parse(a[i]));

        }





        List<Integer> berlinNumbers = new ArrayList<>();
        berlinNumbers.add(1945684);
        berlinNumbers.add(1835094);
        berlinNumbers.add(1771491);




        XYChart chart = new XYChartBuilder().width(800).height(600).build();
        chart.getStyler().setDatePattern("yyyy-MM-dd");
        chart.addSeries("Berlin", xData, berlinNumbers);
        new SwingWrapper(chart).displayChart();
    }


    public static void main(String[] args)throws ParseException {
        test x = new test();
        x.showExample();
    }


}
