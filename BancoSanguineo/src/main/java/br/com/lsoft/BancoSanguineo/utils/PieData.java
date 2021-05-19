package br.com.lsoft.BancoSanguineo.utils;

import java.util.List;

public class PieData {
	
	private List<String> labels;
	private List<PieDataset> datasets;
	
	public PieData(List<String> labels, List<PieDataset> datasets) {
		this.labels = labels;
		this.datasets = datasets;
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	public List<PieDataset> getDatasets() {
		return datasets;
	}

	public void setDatasets(List<PieDataset> datasets) {
		this.datasets = datasets;
	}


}
