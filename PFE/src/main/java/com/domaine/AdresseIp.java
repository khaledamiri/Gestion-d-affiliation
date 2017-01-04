package main.java.com.domaine;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AdresseIp {

	public int idadresseIp;
	public String adresseIpaffilie;
	public String adresseIpClient;
	public String designBanniere;
	public String date;

	public AdresseIp() {
	}

	public AdresseIp(int idadresseIp, String adresseIpaffilie,
			String adresseIpClient, String designBanniere, String date) {
		super();
		this.idadresseIp = idadresseIp;
		this.adresseIpaffilie = adresseIpaffilie;
		this.adresseIpClient = adresseIpClient;
		this.designBanniere = designBanniere;
		this.date = date;
	}

	@Override
	public String toString() {
		return "AdresseIp [idadresseIp=" + idadresseIp + ", adresseIpaffilie="
				+ adresseIpaffilie + ", adresseIpClient=" + adresseIpClient
				+ ", designBanniere=" + designBanniere + ", date=" + date + "]";
	}

	public int getIdadresseIp() {
		return idadresseIp;
	}

	public void setIdadresseIp(int idadresseIp) {
		this.idadresseIp = idadresseIp;
	}

	public String getAdresseIpaffilie() {
		return adresseIpaffilie;
	}

	public void setAdresseIpaffilie(String adresseIpaffilie) {
		this.adresseIpaffilie = adresseIpaffilie;
	}

	public String getAdresseIpClient() {
		return adresseIpClient;
	}

	public void setAdresseIpClient(String adresseIpClient) {
		this.adresseIpClient = adresseIpClient;
	}

	public String getDesignBanniere() {
		return designBanniere;
	}

	public void setDesignBanniere(String designBanniere) {
		this.designBanniere = designBanniere;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	ApplicationContext ctx = new ClassPathXmlApplicationContext(
			"ApplicationContext.xml");

	Test springDao2 = (Test) ctx.getBean("test", Test.class);

	/**
	 * @param affilieIp
	 * @return commisiion selon nombre des clients qui clique sur sa banniere 
	 */
	public Integer commissionAffilie(String affilieIp) {
		try {

			Integer number = springDao2.getUtilisateurdao().getCommission(
					affilieIp);

			System.out.println(number);

			return number;

		} catch (Exception e) {
			e.printStackTrace();

			return 0;

		}

	}

}
