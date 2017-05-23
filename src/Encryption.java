import java.io.File;
import java.text.NumberFormat;
import java.util.Scanner;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Encryption {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter secret word to be encrypted:\n");
		String str = sc.nextLine();
		int n = str.length();
		int count=1,j;
		int[] ASCII = new int[256];
		for(int i=1;i<256;count++)
		{	j=1;
			while(j<=count && i<256)
			{
				ASCII[i++] = count;
				j++;
			}
		}
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		
		String[] coordinates = new String[n];
		int xcoord,ycoord,occurence;
		for(int i=0;i<n;i++)
		{
			XYSeries DATA = new XYSeries( String.valueOf(i+1) );
			int temp = str.charAt(i);
			occurence = 1;
			// char stored in int represents ASCII value of the char
			xcoord = ASCII[temp];
			for(int k=temp;k>=0;k--)
			{
				if(ASCII[k-1] == xcoord)
				{
					occurence++;
				}
				else
				{
					break;
				}
			}
			xcoord--;
			ycoord = occurence;
			if(i % 8 == 2 || i % 8 == 3)
			{
				xcoord *= -1;
			}
			else if(i % 8 == 4 || i % 8 == 5)
			{
				xcoord *= -1;
				ycoord *= -1;
			}
			else if(i % 8 == 6 || i % 8 == 7)
			{
				ycoord *= -1;
			}
			coordinates[i] = "["+xcoord+","+ycoord+"]"+","+(i+1);
			
			DATA.add(xcoord,ycoord);
            dataset.addSeries(DATA);
           
		}
		 dataset.getSeries();

         JFreeChart xylineChart = ChartFactory.createXYLineChart(
            "", 
            "X-axis",
            "Y-axis", 
            dataset,
            PlotOrientation.VERTICAL, 
            false, true, false);
         
         XYPlot plot = xylineChart.getXYPlot();
         XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
         plot.setRenderer(renderer);
         
         NumberFormat format = NumberFormat.getNumberInstance();
         format.setMaximumFractionDigits(2); // etc.
         XYItemLabelGenerator generator =
             new StandardXYItemLabelGenerator("{0}", format, format);
         renderer.setBaseItemLabelGenerator(generator);
         renderer.setBaseItemLabelsVisible(true);
           
         int width = 640;   /* Width of the image */
         int height = 480;  /* Height of the image */ 
         File XYChart = new File( "XYLineChart.jpeg" ); 
         ChartUtilities.saveChartAsJPEG( XYChart, xylineChart, width, height);
		System.out.println("Jpeg file created successfully");
		
	}
}
