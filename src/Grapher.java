//3D Graph and stylers
import com.rinearn.graph3d.RinearnGraph3D;

//2D Graph and Stylers
import org.knowm.xchart.*;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.*;
import org.knowm.xchart.SwingWrapper.*;
import org.knowm.xchart.style.AxesChartStyler;
import org.knowm.xchart.style.colors.*;
import org.knowm.xchart.style.lines.SeriesLines;
import org.knowm.xchart.style.markers.SeriesMarkers;

//Math Stuff
import org.apache.commons.math4.legacy.fitting.*;
import org.apache.commons.math4.legacy.optim.*;
import org.apache.commons.math4.legacy.fitting.PolynomialCurveFitter;
import org.apache.commons.math4.legacy.fitting.WeightedObservedPoint;
import org.apache.commons.math4.legacy.fitting.AbstractCurveFitter.*;

//General Imports
import java.awt.*;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

public class Grapher {
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
        int startIndex = -1;
        int endIndex = -1;
        boolean foundStart = false;
        boolean foundEnd = false;
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
        }

        //Create new arrays with only data in the time range
        double[] xTruncData = new double[endIndex-startIndex+1];
        double[] yTruncData = new double[endIndex-startIndex+1];
        int count = 0;
        for(int j = startIndex; j<=endIndex; j++){
            xTruncData[count] = x[j];
            yTruncData[count] = y[j];
            count++;
        }

        //Curve fitting
        Collection<WeightedObservedPoint> points = new ArrayList<>();
        for(int k = 0; k<xTruncData.length; k++){
            points.add(new WeightedObservedPoint(1, xTruncData[k], yTruncData[k]));
        }
        PolynomialCurveFitter fitter = PolynomialCurveFitter.create(10);
//        fitter = fitter.getO
        double[] coefficients = fitter.fit(points);
        
        ArrayList<Double> xApprox = new ArrayList<>();
        ArrayList<Double> yApprox = new ArrayList<>();
        for(double d = startDate; d<endDate; d+= 0.01){
            xApprox.add(d);
            yApprox.add(taylorApprox(d, coefficients));
            
        }
        

        //Create 2D graph
        XYChart chart = new XYChartBuilder().width(800).height(600).title("Sunspot Number Over Time").xAxisTitle("Time (years)").yAxisTitle("Sunspot Number").build();
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
        chart.getStyler().setXAxisMax(endDate);
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
        XYSeries dataSeries = chart.addSeries("Sunspot Data", xTruncData, yTruncData);
        XYSeries approxSeries = chart.addSeries("Approx Sunspot Data", xApprox, yApprox);
        dataSeries.setLineColor(XChartSeriesColors.BLUE);
        approxSeries.setLineColor(XChartSeriesColors.PURPLE);
        approxSeries.setLineStyle(SeriesLines.SOLID);
        approxSeries.setSmooth(true);
        dataSeries.setLineStyle(SeriesLines.NONE);
        new SwingWrapper<XYChart>(chart).displayChart();
    }
    
    public double taylorApprox(double x, double[] coeff){
        double output = 0;
        for(int b = coeff.length-1; b>=0; b--){
            output+= coeff[b]*Math.pow(x,b);
        }
        return output;
    }
}
