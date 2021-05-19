package br.com.lsoft.BancoSanguineo.utils;

public class BarChart {

	private final String type = "bar";
	private BarData data;
	
	public BarChart(BarData data) {
		this.data = data;
	}

	public BarData getData() {
		return data;
	}

	public void setData(BarData data) {
		this.data = data;
	}

	public String getType() {
		return type;
	}

}
