package main.java.com.controlleur;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import main.java.com.domaine.AdresseIp;
import main.java.com.domaine.Test;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@ManagedBean
public class ChartView implements Serializable {

	private PieChartModel pieModel1;
	
	private BarChartModel barModel;
	
	ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"ApplicationContext.xml");

	Test springDao2 = (Test) ctx.getBean("test", Test.class);

	@PostConstruct
	public void init() {
		createPieModels();
		 createBarModels();
	}

	public PieChartModel getPieModel1() {
		return pieModel1;
	}

	

	private void createPieModels() {
		createPieModel1();
		
	}

	private void createPieModel1() {
		pieModel1 = new PieChartModel();
		List<AdresseIp> lista = springDao2.getUtilisateurctr()
				.getService_list();
		for (AdresseIp a : lista) {
			pieModel1.set(a.adresseIpaffilie,
					a.commissionAffilie(a.adresseIpaffilie));

		}

		 pieModel1.setTitle("Etat des commissions ");
	        pieModel1.setLegendPosition("e");
	        pieModel1.setFill(false);
	        pieModel1.setShowDataLabels(true);
	        pieModel1.setDiameter(150);
		
	}
	
	  public BarChartModel getBarModel() {
	        return barModel;
	    }
	     
	  
	 
	    private BarChartModel initBarModel() {
	        BarChartModel model = new BarChartModel();
	        
	        
	        
	        
	 
	        ChartSeries boys = new ChartSeries();
	        boys.setLabel("ali");
	        boys.set("2011", 120);
	        boys.set("2012", 100);
	        boys.set("2013", 44);
	        boys.set("2014", 150);
	        boys.set("2015", 25);
	 
	        ChartSeries girls = new ChartSeries();
	        girls.setLabel("Salah");
	        girls.set("2011", 52);
	        girls.set("2012", 60);
	        girls.set("2013", 110);
	        girls.set("2014", 135);
	        girls.set("2015", 120);
	 
	        model.addSeries(boys);
	        model.addSeries(girls);
	         
	        return model;
	    }
	     
	    private void createBarModels() {
	        createBarModel();
	       
	    }
	     
	    private void createBarModel() {
	        barModel = initBarModel();
	         
	        barModel.setTitle("Bar Chart");
	        barModel.setLegendPosition("ne");
	         
	        Axis xAxis = barModel.getAxis(AxisType.X);
	        xAxis.setLabel("Gender");
	         
	        Axis yAxis = barModel.getAxis(AxisType.Y);
	        yAxis.setLabel("Births");
	        yAxis.setMin(0);
	        yAxis.setMax(200);
	    }
	     
	   


}