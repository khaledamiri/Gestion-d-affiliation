package main.java.com.domaine;

public class Suggestion {

	public int idsug;
	public String name;
	public String email;
	public String msg;
	public String date;
	public String situation;

	public Suggestion() {
	}

	public Suggestion(int idsug, String name, String email, String msg,
			String date, String situation) {
		super();
		this.idsug = idsug;
		this.name = name;
		this.email = email;
		this.msg = msg;
		this.date = date;
		this.situation = situation;
	}

	public int getIdsug() {
		return idsug;
	}

	public void setIdsug(int idsug) {
		this.idsug = idsug;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSituation() {
		return situation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}

}
