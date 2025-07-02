
//2D Graph and Stylers
import org.knowm.xchart.*;
import org.knowm.xchart.style.*;
import org.knowm.xchart.style.colors.*;
import org.knowm.xchart.style.lines.SeriesLines;
import org.knowm.xchart.style.markers.Marker;
import org.knowm.xchart.style.markers.SeriesMarkers;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;


//General Imports
import java.awt.*;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Grapher {
    private ArrayList<Double> coeff;
    private Map<String, Boolean> vars = new HashMap<>();

    //2D Grapher
    public Grapher(double[] x, double[] y) throws InterruptedException{
        //Get endpoints
        Scanner scan = new Scanner(System.in);
        System.out.println("Please choose a starting year");
        double startDate = Double.parseDouble(scan.nextLine());
        System.out.println("Please choose an ending year");
        double endDate = Double.parseDouble(scan.nextLine());
        System.out.println("Please enter a ending year for the approximation");
        double endApproxDate = Double.parseDouble(scan.nextLine());

        //find approx index of endpoints
        int startIndex = -1;
        int endIndex = -1;
        int endApproxIndex = -1;
        boolean foundStart = false;
        boolean foundEnd = false;
        boolean foundEndApprox = false;
        for(int i = 0; i<x.length; i++){
            if(x[i] >= startDate && !foundStart){
                startIndex = i;
                System.out.println("Start: "+startIndex);
                foundStart = true;
            }
            if (x[i] >= endDate && !foundEnd){
                endIndex = i-1;
                System.out.println("End: "+endIndex);
                foundEnd = true;
            }
            if (x[i] >= endApproxDate && !foundEndApprox){
                endApproxIndex = i-1;
                System.out.println("EndApprox: "+endApproxIndex);
                foundEndApprox = true;

            }
        }

        //Create new arrays with data in each time range
        double[] xTruncData = new double[endIndex-startIndex+1];
        double[] yTruncData = new double[endIndex-startIndex+1];
        int count = 0;
        for(int j = startIndex; j<=endIndex; j++){
            xTruncData[count] = x[j];
            yTruncData[count] = y[j];
            count++;
        }
        int endOfApproxDataIndex;
        if(endApproxIndex == -1){
            endOfApproxDataIndex = x.length;
        }else{
            endOfApproxDataIndex = endApproxIndex;
        }
        double[] xAfterData = new double[endOfApproxDataIndex-endIndex+1];
        double[] yAfterData = new double[endOfApproxDataIndex-endIndex+1];
        count = 0;
        for(int l = endIndex; l<=endOfApproxDataIndex; l++){
            xAfterData[count] = x[l];
            yAfterData[count] = y[l];
            count++;
        }


        //Creates a new array list with approximated values
        ArrayList<Double> xApprox = new ArrayList<>();
        System.out.println("What degree would you like the Taylor approximation to be?");
        int degree = Integer.parseInt(scan.nextLine());
        double center = xTruncData[xTruncData.length/2];
        for(double d = startDate; d<endDate; d+= 0.08){
            //Nullify any rounding errors
            d*=100;
            d= Math.round(d);
            d/=100.0;

            xApprox.add(d);
            
        }
        ArrayList<Double> xAfterApprox = new ArrayList<>();
        for(double f = endDate; f<endApproxDate; f+= 0.08){
            //Nullify rounding error
            f*=100;
            f = Math.round(f);
            f/=100.0;

            xAfterApprox.add(f);
        }
        

        //Create 2D graph
        XYChart chart = new XYChartBuilder().width(800).height(600).title("Sunspot Number Over Time").xAxisTitle("Year").yAxisTitle("Sunspot Number").build();

        // Customize Chart
        chart.getStyler().setPlotBackgroundColor(Color.WHITE);
        chart.getStyler().setPlotGridLinesVisible(true);
        chart.getStyler().setPlotGridLinesColor(Color.GRAY);
        chart.getStyler().setChartBackgroundColor(Color.WHITE);
        chart.getStyler().setLegendBackgroundColor(Color.WHITE);
        chart.getStyler().setLegendBorderColor(Color.BLACK);
        chart.getStyler().setChartFontColor(Color.BLACK);
        chart.getStyler().setChartTitleBoxVisible(false);
        chart.getStyler().setChartTitleBoxBorderColor(null);
        chart.getStyler().setAxisTickPadding(0);
        chart.getStyler().setXAxisMin(startDate);
        chart.getStyler().setXAxisMax(endApproxDate);
        chart.getStyler().setYAxisMax(300.0);
        chart.getStyler().setYAxisMin(0.0);
        chart.getStyler().setAxisTickMarkLength(15);
        chart.getStyler().setPlotMargin(20);
        chart.getStyler().setChartTitleFont(new Font(Font.SERIF, Font.BOLD, 24));
        chart.getStyler().setLegendFont(new Font(Font.SERIF, Font.PLAIN, 18));
        chart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideE);
        chart.getStyler().setLegendSeriesLineLength(12);
        chart.getStyler().setAxisTitleFont(new Font(Font.SANS_SERIF, Font.ITALIC, 18));
        chart.getStyler().setAxisTickLabelsFont(new Font(Font.SERIF, Font.PLAIN, 11));
        chart.getStyler().setDatePattern("MM-dd");
        chart.getStyler().setDecimalPattern("#0");
        chart.getStyler().setLocale(Locale.ENGLISH);

        //Add data to graph
        XYSeries dataSeries = chart.addSeries("Sunspot Data", xTruncData, yTruncData);
        XYSeries approxSeries = chart.addSeries("Approx Sunspot Data", xApprox, taylorApproxOuter(degree,xTruncData,yTruncData,center,startDate,endDate));
        XYSeries futureSeries = chart.addSeries("After Sunspot Data", xAfterData,yAfterData);
        XYSeries futureApproxSeries = chart.addSeries("Predicted Sunspot Data", xAfterApprox, taylorApproxOuter(degree,xTruncData,yTruncData,center,endDate,endApproxDate));

        //Style individual series
        dataSeries.setLineColor(XChartSeriesColors.BLUE);
        dataSeries.setLineStyle(SeriesLines.NONE);
        dataSeries.setMarker(SeriesMarkers.CIRCLE);
        futureSeries.setLineColor(XChartSeriesColors.GREEN);
        futureSeries.setLineStyle(SeriesLines.NONE);
        futureSeries.setMarker(SeriesMarkers.PLUS);
        approxSeries.setLineStyle(SeriesLines.SOLID);
        approxSeries.setLineColor(XChartSeriesColors.YELLOW);
        approxSeries.setSmooth(true);
        approxSeries.setMarker(SeriesMarkers.NONE);
        futureApproxSeries.setLineColor(XChartSeriesColors.GREEN);
        futureApproxSeries.setLineStyle(SeriesLines.SOLID);
        futureApproxSeries.setSmooth(true);
        futureApproxSeries.setMarker(SeriesMarkers.NONE);
        try {
            BitmapEncoder.saveBitmap(chart, getTime(), BitmapFormat.PNG);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        System.exit(0);

        //display chart
     //   new SwingWrapper<XYChart>(chart).displayChart();
    }

    public ArrayList<Double> taylorApproxOuter(int iterations, double[] xData, double[] yData, double center, double startDate, double endDate) throws InterruptedException {
        double total = 0;
       System.out.println("Center: "+center);
        int centerIndex = centerIndex(xData, center);
        double[] newXdata = new double[iterations + 1];
        double[] newYData = new double[iterations + 1];
        int count = 0;
        if (iterations % 2 == 0) {
            int diff = iterations / 2;
            for (int i = centerIndex - diff; i <= centerIndex + diff; i++) {
                newXdata[count] = xData[i];
                newYData[count] = yData[i];
                count++;
            }
        } else {
            int diff = iterations / 2;
            for (int i = centerIndex - diff; i <= centerIndex + diff + 1; i++) {
                newXdata[count] = xData[i];
                newYData[count] = yData[i];
                count++;
            }
        }

         centerIndex = centerIndex(newXdata, center);
        if(coeff == null) {
            coeff = new ArrayList<Double>();
            coeff.add(newYData[centerIndex]);
            double last = taylorApproxCoeff(iterations, newXdata, newYData, center);
        }

        System.out.print("FINAL COEFFICIENTS: [");
        for(double co : coeff){
            System.out.print(co+", ");
        }
        System.out.println("]");
        ArrayList<Double> points = new ArrayList<>();
        for(double num = startDate; num<endDate; num+=0.08) {
            //Nullify rounding error
            num*=100;
            num= Math.round(num);
            num/=100.0;

            total = taylorApproxSum(num,center);
            System.out.println("Total for "+num+": "+ total);
            points.add(total);
        }
        System.out.println("Total points: "+ points.size());

        return points;

    }

    public double taylorApproxCoeff(int iterations, double[] xData, double[] yData, double center) throws InterruptedException {
        //base case
        if(iterations == 1){
            //Checks that no other coefficient of iteration 1 has been added
            if(vars.get(""+iterations) == null || !vars.get("1")) {
                //adds as coefficient (slope formula)
                coeff.add((yData[1] - yData[0])/(xData[1] - xData[0]));
                //Notes that a coefficient of iteration 1 has been added
                vars.put("1", true);
            }
            //Slope formula
            return  (yData[1] - yData[0])/(xData[1] - xData[0]);

        //recursive case
        }else{
            int centIndex = centerIndex(xData,center);

            //Creates new flag for next iteration if one has not already been created
            if(vars.get(""+iterations) == null || !vars.get(""+iterations)) {
                vars.put("" + (iterations - 1), false);
            }

            //Recursive function (derived from slope formula)
            double next = ((taylorApproxCoeff(iterations - 1, arrayShortener(xData,centIndex, true),
                            arrayShortener(yData, centIndex, true), center) -
                    taylorApproxCoeff(iterations - 1, arrayShortener(xData,centIndex, false),
                            arrayShortener(yData, centIndex, false), center))/
                    (((sum(arrayShortener(xData,centIndex,true)))/(iterations)) -
                    ((sum(arrayShortener(xData,centIndex,false)))/(iterations))))
            ;

            //Checks that no other coefficient of iteration 'iteration' has been added
            if(vars.get(""+iterations) == null || !vars.get(""+iterations)) {
                System.out.println("Iteration "+iterations+": "+next);
                coeff.add(next);
                vars.put(""+iterations, true);
            }
            return next;

        }
    }


    //Finds y value based on taylor approximation
    public double taylorApproxSum(double num, double center){
        double total = 0;
        double difference = num-center;
        for(int i = 0; i<coeff.size(); i++){
            total+= ((coeff.get(i)*Math.pow(difference,i))/factorial(i));
        }
        return total;
    }

    //Shortens array by 1 index depending on placement
    public double[] arrayShortener(double[] oldArr, int centerIndex, boolean first){
        double[] newArr = new double[oldArr.length -1];
        if (first) {
            for(int i = 0; i<newArr.length; i++) {
                newArr[i] = oldArr[i];
            }

        } else {
            for (int i = 0; i< newArr.length; i++){
                newArr[i] = oldArr[i+1];
            }
        }
        return newArr;
    }

    //Gets the index of the 'center' of an array
    public int centerIndex(double[] arr, double center){
        int centerIndex = -1;
        for(int j = 0; j< arr.length; j++){
            if(arr[j] == center){
                centerIndex = j;
            }
        }
        return centerIndex;
    }

    //Adds all elements in an array
    public double sum(double[] arr){
        double total = 0;
        for (double v : arr) {
            total += v;
        }
        return total;
    }

    //Takes the factorial of a number
    public int factorial(int num){
        int total = 1;
        if(num != 0 && num != 1) {
            for (int i = num; i > 0; i--) {
                total *= i;
            }
        }
        return total;
    }
}
