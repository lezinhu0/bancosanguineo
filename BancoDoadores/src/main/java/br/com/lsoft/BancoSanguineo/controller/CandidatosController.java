package br.com.lsoft.BancoSanguineo.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.GsonBuilder;

import br.com.lsoft.BancoSanguineo.model.Candidato;
import br.com.lsoft.BancoSanguineo.repository.CandidatoRepository;
import br.com.lsoft.BancoSanguineo.repository.TipoSanguineoRepository;
import br.com.lsoft.BancoSanguineo.utils.BarChart;
import br.com.lsoft.BancoSanguineo.utils.BarData;
import br.com.lsoft.BancoSanguineo.utils.BarDataset;
import br.com.lsoft.BancoSanguineo.utils.DataTable;

@Controller
@RequestMapping("/candidatos")
public class CandidatosController {
	
	@Autowired
	private TipoSanguineoRepository tipoSanguineoRepository;
	
	@Autowired
	private CandidatoRepository candidatoRepository;
	
	@GetMapping("")
	public String candidatos() {
		return "candidatos";
	}
	
	@ResponseBody
	@PostMapping("")
	public DataTable relacaoCandidatos() {
		Iterable<Candidato> candidatos = candidatoRepository.findAll();
		LinkedList<Object> headers = new LinkedList<>();
		headers.add("Nome");
		headers.add("Tipo Sanguineo");
		headers.add("Cidade");
		headers.add("Estado");
		headers.add("Data Nascimento");
		headers.add("Email");
		
		
		LinkedList<LinkedList<Object>> data = new LinkedList<>();
		for (Candidato candidato : candidatos) {
			LinkedList<Object> linha = new LinkedList<>();
			linha.add(candidato.getNome());
			linha.add(candidato.getTipo_sanguineo());
			linha.add(candidato.getCidade());
			linha.add(candidato.getEstado());
			linha.add(candidato.getData_nasc());
			linha.add(candidato.getEmail());
			data.add(linha);
		}
		return new DataTable(data, headers);
	}
	
	@ResponseBody
	@PostMapping("/salvar")
	public void salvar(@RequestBody String candidatosString) {
		System.out.println("Salvando Candidatos!!");

		Candidato[] candidatos = new GsonBuilder().setDateFormat("dd/MM/yyyy").create().fromJson(candidatosString, Candidato[].class);

		for (Candidato candidato : candidatos) {
			candidato.setTipoSanguineo(tipoSanguineoRepository.findById(candidato.getTipo_sanguineo()).orElse(null));
			candidatoRepository.save(candidato);
		}

		System.out.println("Candidatos salvos com sucesso!");
	}
	
	@ResponseBody
	@GetMapping("/por-estado")
	public List<Object[]> getCandidatosPorEstado() {
		return candidatoRepository.countByEstado();
	}
	
	@ResponseBody
	@GetMapping("/grafico-doadores-por-estado")
	public BarChart getGraficoDoadoresPorEstado() {
		List<Object[]> listaEstados = candidatoRepository.countByEstado();
		
		
		BarChart chart = new BarChart();
		BarData data = new BarData();
		List<String> labels = new LinkedList<>();
		data.setLabels(labels);
		List<BarDataset> datasets = new LinkedList<>();
		BarDataset dataset = new BarDataset();
		List<Double> barData = new LinkedList<>();
		for (Object[] str : listaEstados) {
			labels.add(str[0].toString());
			barData.add(Double.parseDouble(str[1].toString()));
		}
		dataset.setData(barData);
		datasets.add(dataset);
		data.setDatasets(datasets);
		chart.setData(data);
		return chart;
	}
}
