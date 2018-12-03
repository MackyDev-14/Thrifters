package application;

public class modelTable {
	String dt, tm, am, sh, act;
	
	public modelTable(String dt, String tm, String am, String sh, String act) {
		this.dt = dt;
		this.tm = tm;
		this.am = am;
		this.sh = sh;
		this.act = act;
	}

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}

	public String getTm() {
		return tm;
	}

	public void setTm(String tm) {
		this.tm = tm;
	}

	public String getAm() {
		return am;
	}

	public void setAm(String am) {
		this.am = am;
	}

	public String getSh() {
		return sh;
	}

	public void setSh(String sh) {
		this.sh = sh;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}
}
