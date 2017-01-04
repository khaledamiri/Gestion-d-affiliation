package main.java.com.domaine;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;

public class Bannierecss extends Banniere  {

	private int idbanncss;
	private String background_color;

	private String width;
	private String height;

	private String color;
	private String text_align;

	private String line_height;

	private String margin_left;
	private String text_decoration;
	private String text_transform;
	private String font_style;
	private String font_family;
	private String idBann;
	
	
	

	public Bannierecss() {
		super();
	}

	public Bannierecss(int idbanncss, String background_color, String width,
			String height, String color, String text_align, String line_height,
			String margin_left, String text_decoration, String text_transform,
			String font_style, String font_family, String idBann) {
		super();
		this.idbanncss = idbanncss;
		this.background_color = background_color;
		this.width = width;
		this.height = height;
		this.color = color;
		this.text_align = text_align;
		this.line_height = line_height;
		this.margin_left = margin_left;
		this.text_decoration = text_decoration;
		this.text_transform = text_transform;
		this.font_style = font_style;
		this.font_family = font_family;
		this.idBann = idBann;
	}

	@Override
	public String toString() {
		return "Bannierecss [idbanncss=" + idbanncss + ", background_color="
				+ background_color + ", width=" + width + ", height=" + height
				+ ", color=" + color + ", text_align=" + text_align
				+ ", line_height=" + line_height + ", margin_left="
				+ margin_left + ", text_decoration=" + text_decoration
				+ ", text_transform=" + text_transform + ", font_style="
				+ font_style + ", font_family=" + font_family + ", idBann="
				+ idBann + "]";
	}

	public String getBackground_color() {
		return background_color;
	}

	public void setBackground_color(String background_color) {
		this.background_color = background_color;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getText_align() {
		return text_align;
	}

	public void setText_align(String text_align) {
		this.text_align = text_align;
	}

	public String getLine_height() {
		return line_height;
	}

	public void setLine_height(String line_height) {
		this.line_height = line_height;
	}

	public String getMargin_left() {
		return margin_left;
	}

	public void setMargin_left(String margin_left) {
		this.margin_left = margin_left;
	}

	public String getText_decoration() {
		return text_decoration;
	}

	public void setText_decoration(String text_decoration) {
		this.text_decoration = text_decoration;
	}

	public String getText_transform() {
		return text_transform;
	}

	public void setText_transform(String text_transform) {
		this.text_transform = text_transform;
	}

	public String getFont_style() {
		return font_style;
	}

	public void setFont_style(String font_style) {
		this.font_style = font_style;
	}

	public String getFont_family() {
		return font_family;
	}

	public void setFont_family(String font_family) {
		this.font_family = font_family;
	}

	public int getIdbanncss() {
		return idbanncss;
	}

	public void setIdbanncss(int idbanncss) {
		this.idbanncss = idbanncss;
	}

	public String getIdBann() {
		return idBann;
	}

	public void setIdBann(String idBann) {
		this.idBann = idBann;
	}
	
	//CSS
		private Map<String, String> ItemsBanner = new LinkedHashMap<String, String>();
		public String selectbanner;
		
		public Map<String, String> getItemsBanner() {
			ApplicationContext ctx = new ClassPathXmlApplicationContext(
					"ApplicationContext.xml");

			Test springDao2 = (Test) ctx.getBean("test", Test.class);
			
			try {
				List<Banniere> list = springDao2.getBanndao().findAll();
				Iterator<Banniere> iter1 = list.iterator();

				while (iter1.hasNext()) {
					Banniere bann = (Banniere) iter1.next();
					String valeur = bann.getDESIGN_BANN();
					String cle = bann.getDESIGN_BANN();

					ItemsBanner.put(valeur, cle);
					// System.out.println(cle);

				}

			} catch (DataAccessException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return ItemsBanner;
		}
		
		public void setItemsBanner(Map<String, String> itemsBanner) {
			ItemsBanner = itemsBanner;
		}

		public String getSelectbanner() {
			return selectbanner;
		}

		public void setSelectbanner(String selectbanner) {
			this.selectbanner = selectbanner;
		

		}

}
