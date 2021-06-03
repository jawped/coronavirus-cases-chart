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

 public class CSVChart {

     String[] words;
     List<String> time = new ArrayList<>();
     List<Integer> cases = new ArrayList<>();

     List<Integer> average_7_days_list = new ArrayList<>();

     List<Integer> average_7_days_cacher_list = new ArrayList<>();

     int [] proTag;
     int [] x;
     int [] diario ;
     private final Queue<Double> Dataset = new LinkedList<Double>();
     int sum7;

     public void readCsv2() throws IOException, ParseException {
         InputStream inputStream = getClass().getClassLoader().getResourceAsStream("totals.csv");
         Scanner scanner = new Scanner(inputStream);
         CSVParser csvParser = new CSVParser();

         //
         while (scanner.hasNextLine()) {
             String line = scanner.nextLine();
             if (csvParser.parseLine(line)[0].equals("germany")) {
                 words = csvParser.parseLine(line);

                 time.add(words[1]);
                 cases.add(Integer.valueOf(words[2]));

                 x = new int[cases.size()];

                 for (int i = 0; i <cases.size() ; i++) {
                    x[i]= cases.get(i);

                 }

                   proTag = new int [x.length];


                 for (int i = 0; i <x.length; i++) {
                     if (i==0){
                         proTag[0]= x[0];

                     }
                     else

                         proTag[i]= x[i]-x[i-1];


                 }




             }
         }




     }

             public void showExample () throws ParseException {
                 List<Date> xData = new ArrayList<>();
                 DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                 for (int i = 0; i < time.size(); i++) {

                     xData.add(sdf.parse(time.get(i)));
                 }

                 List<Integer> yData = new ArrayList<>();
                 for (int i = 0; i < proTag.length; i++) {


                     yData.add(proTag[i]);
                 }


                 List<Integer> yData2 = new ArrayList<>();
                 for (int i = 0; i < average_7_days_list.size(); i++) {

                     yData2.add(average_7_days_list.get(i));
                 }


                 double data[] = new double[proTag.length];
                 double movingAverage[] = new double[proTag.length];
                 int[] window = new int[7];


                 for(int i = 0; i < yData.size();i++){

                     if(Dataset.size() == window.length ) {


                         Double dataToRemove = Dataset.poll();
                         sum7 -= dataToRemove;

                     }
                     Dataset.add(Double.valueOf(yData.get(i)));
                     sum7 += yData.get(i);

                     movingAverage[i] = sum7 / Dataset.size();

                 }

                 List<Double> zData = new ArrayList<>();
                 for(int i = 0; i < movingAverage.length;i++){
                     zData.add(movingAverage[i]);
                 }





                 XYChart chart = new XYChartBuilder().width(1000).height(800).build();
                 chart.getStyler().setDatePattern("yyyy-MM-dd");
                 chart.getStyler().setDecimalPattern("");
                 chart.addSeries("Germany total cases", xData, yData);
                 chart.addSeries("Rollin Average", xData, zData);
                // chart.addSeries("7-day Rollin", xData, yData2);
                 new SwingWrapper(chart).displayChart();

             }


             public static void main (String[]args) throws IOException, ParseException {
                 CSVChart dateChart = new CSVChart();
                 dateChart.readCsv2();
                 dateChart.showExample();

             }
         }
