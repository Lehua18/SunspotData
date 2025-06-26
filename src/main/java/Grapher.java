//3D Graph and stylers
import com.rinearn.graph3d.RinearnGraph3D;

//2D Graph and Stylers
import org.knowm.xchart.*;
import org.knowm.xchart.style.*;
import org.knowm.xchart.style.colors.*;
import org.knowm.xchart.style.lines.SeriesLines;


//General Imports
import java.awt.*;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;

public class Grapher {
    private ArrayList<Double> coeff;

    //3D Grapher
    public Grapher(double[] x, double[] y, double[] z) {
        RinearnGraph3D graph = new RinearnGraph3D();
    }

    //2D Grapher
    public Grapher(double[] x, double[] y){
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
//
//        //Curve fitting
//        Collection<WeightedObservedPoint> points = new ArrayList<>();
//        for(int k = 0; k<xTruncData.length; k++){
//            points.add(new WeightedObservedPoint(1, xTruncData[k], yTruncData[k]));
//        }
//        PolynomialCurveFitter fitter = PolynomialCurveFitter.create(15);
////        fitter = fitter.getO
//        double[] coefficients = fitter.fit(points);

        //Creates a new array list with approximated values
        ArrayList<Double> xApprox = new ArrayList<>();
        ArrayList<Double> yApprox = new ArrayList<>();
        int degree = 2;
        double center = xTruncData[xTruncData.length/2];
        for(double d = startDate; d<endDate; d+= 0.08){
            //Nullify any rounding errors
            d*=100;
            d= Math.round(d);
            d/=100.0;
            xApprox.add(d);
            //System.out.println("d: "+d);
            
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
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideSE);
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
        XYSeries futureApproxSeries = chart.addSeries("Predicted Sunspot Data", xAfterApprox, taylorApproxOuter(degree,xTruncData,yTruncData,center,startDate,endDate));

        //Style individual series
        dataSeries.setLineColor(XChartSeriesColors.BLUE);
        dataSeries.setLineStyle(SeriesLines.NONE);
        futureSeries.setLineColor(XChartSeriesColors.GREEN);
        futureSeries.setLineStyle(SeriesLines.NONE);
        approxSeries.setLineStyle(SeriesLines.SOLID);
        approxSeries.setLineColor(XChartSeriesColors.PURPLE);
        approxSeries.setSmooth(true);
        futureApproxSeries.setLineColor(XChartSeriesColors.RED);
        futureApproxSeries.setLineStyle(SeriesLines.SOLID);
        futureApproxSeries.setSmooth(true);

        //display chart
        new SwingWrapper<XYChart>(chart).displayChart();
    }

    //Get coefficients of least squares approx method
    public double squaresApprox(double x, double[] coeff){
        double output = 0;
        for(int b = coeff.length-1; b>=0; b--){
            output+= coeff[b]*Math.pow(x,b);
        }
        return output;
    }

    public ArrayList<Double> taylorApproxOuter(int iterations, double[] xData, double[] yData, double center, double startDate, double endDate) {
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
        coeff = new ArrayList<Double>();
        coeff.add(newYData[centerIndex]);
        double last = taylorApproxCoeff(iterations,newXdata,newYData,center);
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

    public double taylorApproxCoeff(int iterations, double[] xData, double[] yData, double center){


        System.out.print(iterations+" xdata: [");
        for(double x : xData){
            System.out.print(x+", ");
        }
        System.out.println("]");
        System.out.print("\t"+iterations+" ydata: [");
        for(double x : yData){
            System.out.print(x+", ");
        }
        System.out.println("]");
        if(iterations == 1){
            System.out.println("check");
            coeff.add((yData[1] - yData[0])/(xData[1] - xData[0]));
            return  (yData[1] - yData[0])/(xData[1] - xData[0]);
        }else{
            System.out.println("notcheck");
            int centIndex = centerIndex(xData,center);
            double next = ((taylorApproxCoeff(iterations - 1, arrayShortener(xData,centIndex, true),
                            arrayShortener(yData, centIndex, true), center) -
                    taylorApproxCoeff(iterations - 1, arrayShortener(xData,centIndex, false),
                            arrayShortener(yData, centIndex, false), center))/
                    (((sum(arrayShortener(xData,centIndex,true)))/(iterations)) -
                    ((sum(arrayShortener(xData,centIndex,false)))/(iterations))))
            ;
            coeff.add(next);
            return next;

        }
    }


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
