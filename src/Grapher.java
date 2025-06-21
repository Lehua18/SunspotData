import com.rinearn.graph3d.RinearnGraph3D;
import org.knowm.xchart.*;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;


public class Grapher {
    public Grapher(double[] x, double[] y, double[] z) {
        RinearnGraph3D graph = new RinearnGraph3D();
    }
    public Grapher(double[] x, double[] y){
        XYChart graph = QuickChart.getChart("name", "x","y","x(y)",x,y);
//        graph.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Scatter);
        new SwingWrapper(graph).displayChart();
    }
}
