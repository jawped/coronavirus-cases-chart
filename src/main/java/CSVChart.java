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
     /*   private void readCsv() throws IOException {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("totals.csv");
            Scanner scanner = new Scanner(inputStream);
            CSVParser csvParser = new CSVParser();
            String[] words ;
            for (int i = 0; i < 10; i++) {

                String line = scanner.nextLine();
                words = csvParser.parseLine(line);
                System.out.println(Arrays.toString(words) + words.length() );

            }
        }

        } */
     String[] words;
     String[] tiempo;
     int[] casos;


     List<String> time = new ArrayList<>();
     List<Integer> cases = new ArrayList<>();

     public void readCsv2() throws IOException, ParseException {
         InputStream inputStream = getClass().getClassLoader().getResourceAsStream("totals.csv");
         Scanner scanner = new Scanner(inputStream);
         CSVParser csvParser = new CSVParser();
         try {
             //
             while (scanner.hasNextLine()) {
                 String line = scanner.nextLine();
                     if (csvParser.parseLine(line)[0].equals("germany")) {
                         words = csvParser.parseLine(line);

                         time.add(words[1]);
                         cases.add(Integer.valueOf(words[2]));
                     }
             }
         } catch (Exception e) {
             System.out.println(e);
         }

     }


     public void showExample() throws ParseException {
         List<Date> xData = new ArrayList<>();
         DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
         for (int i = 0; i < time.size(); i++) {

             xData.add(sdf.parse(time.get(i)));
         }

         List<Integer> yData = new ArrayList<>();
         for (int i = 0; i < cases.size(); i++) {

             yData.add(cases.get(i));
         }


         XYChart chart = new XYChartBuilder().width(1000).height(1600).build();
         chart.getStyler().setDatePattern("yyyy-MM-dd");
         chart.getStyler().setDecimalPattern("");
         chart.addSeries("Germany total cases", xData, yData);
         new SwingWrapper(chart).displayChart();

     }


     public static void main(String[] args) throws IOException, ParseException {
         CSVChart dateChart = new CSVChart();
         dateChart.readCsv2();
         dateChart.showExample();

     }
 }
