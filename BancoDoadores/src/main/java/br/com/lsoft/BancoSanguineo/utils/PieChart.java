package br.com.lsoft.BancoSanguineo.utils;

public class PieChart {
	
	private String type = "pie";
	private PieData data;
	
	public PieChart(PieData data) {
		this.data = data;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public PieData getData() {
		return data;
	}

	public void setData(PieData data) {
		this.data = data;
	}

}
