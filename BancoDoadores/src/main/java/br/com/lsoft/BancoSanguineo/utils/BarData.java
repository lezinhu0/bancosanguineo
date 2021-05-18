package br.com.lsoft.BancoSanguineo.utils;

import java.util.List;

public class BarData {
	private List<String> labels;
	private List<BarDataset> datasets;

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	public List<BarDataset> getDatasets() {
		return datasets;
	}

	public void setDatasets(List<BarDataset> datasets) {
		this.datasets = datasets;
	}

}
