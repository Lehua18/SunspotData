import com.rinearn.graph3d.RinearnGraph3D;
import org.knowm.xchart.*;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import java.util.Scanner;
import org.knowm.xchart.style.*;
import org.knowm.xchart.SwingWrapper.*;
import org.knowm.xchart.style.Styler.*;


public class Grapher {
    public Grapher(double[] x, double[] y, double[] z) {
        RinearnGraph3D graph = new RinearnGraph3D();
    }
    public Grapher(double[] x, double[] y){
        XYChart graph = QuickChart.getChart("name", "x","y","x(y)",x,y);
//        graph.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Scatter);
        Styler styler = graph.getStyler();
        styler.setXAxisTitleColor()
        new SwingWrapper(graph).displayChart();
    }
}
