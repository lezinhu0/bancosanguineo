package br.com.lsoft.BancoSanguineo.controller;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

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
import br.com.lsoft.BancoSanguineo.utils.PieChart;
import br.com.lsoft.BancoSanguineo.utils.PieData;
import br.com.lsoft.BancoSanguineo.utils.PieDataset;

/**
 * 
 * @author Leandro
 */
@Controller
@RequestMapping("/candidatos")
public class CandidatosController {
	
	@Autowired
	private TipoSanguineoRepository tipoSanguineoRepository;
	
	@Autowired
	private CandidatoRepository candidatoRepository;
	
	/**
	 * 
	 * @author Leandro
	 */
	@GetMapping("")
	public String candidatos() {
		return "candidatos";
	}
	
	
	/**
	 * Gets all candidates
	 * 
	 * @return {@link DataTable} 
	 * @author Leandro
	 */
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
	
	/**
	 * Saves candidates
	 * 
	 * @param candidatosString needs to be a Json Collection of {@link Candidato}
	 * @author Leandro
	 */
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
	
	
	/**
	 * Get amount of candidates in each state
	 * 
	 * @author Leandro
	 */
	@ResponseBody
	@GetMapping("/por-estado")
	public List<Object[]> getCandidatosPorEstado() {
		return candidatoRepository.countByEstado();
	}
	
	/**
	 * Gets a ChartJS java integration {@link BarChart} from each state 
	 * 
	 * @author Leandro
	 */
	@ResponseBody
	@GetMapping("/grafico-candidatos-por-estado")
	public BarChart getGraficoCandidatosPorEstado() {
		List<Object[]> listaEstados = candidatoRepository.countByEstado();
		List<String> labels = new LinkedList<>();
		List<BarDataset> datasets = new LinkedList<>();
		BarDataset dataset = new BarDataset();
		List<Double> barData = new LinkedList<>();
		for (Object[] str : listaEstados) {
			labels.add(str[0].toString());
			barData.add(Double.parseDouble(str[1].toString()));
		}
		dataset.setData(barData);
		datasets.add(dataset);
		BarData data = new BarData(labels, datasets);
		return new BarChart(data);
	}
	
	/**
	 * Gets a ChartJS java integration {@link BarChart} with average IMC between candidates ages 
	 * 
	 * @author Leandro
	 */
	@ResponseBody
	@GetMapping("/grafico-media-imc-idade")
	public BarChart getGraficoMediaICMPorIdade() {
		TreeMap<String, List<BigDecimal>> map = new TreeMap<>();
		Iterable<Candidato> candidatos = candidatoRepository.findAll();
		for (Candidato candidato : candidatos) {
			long idade = candidato.getIdade();
			int idadeBase = 0;
			if (idade % 10 == 0) {
				idadeBase = (int) ((idade / 10) * 10) - 10;
			} else { 
				idadeBase = (int) (idade / 10) * 10;
			}
			String key = "";
			if (idadeBase == 0) {
				key = idadeBase + " - " + (idadeBase + 10);
			} else {
				key = (idadeBase + 1) + " - " + (idadeBase + 10);
			}
			if (map.get(key) == null) {
				map.put(key, new LinkedList<BigDecimal>());
			}
			map.get(key).add(candidato.getIMC());
		}
		List<String> labels = new LinkedList<>();
		List<BarDataset> datasets = new LinkedList<>();
		BarDataset dataset = new BarDataset();
		List<Double> barData = new LinkedList<>();
		Iterator<Entry<String, List<BigDecimal>>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, List<BigDecimal>> entry = iter.next();
			String key = entry.getKey();
			List<BigDecimal> imcs = entry.getValue();
			BigDecimal somaImcs = BigDecimal.ZERO;
			for (BigDecimal imc : imcs) {
				somaImcs = somaImcs.add(imc);
			}
			labels.add(key);
			barData.add(Double.parseDouble(somaImcs.divide(BigDecimal.valueOf(imcs.size()), 2, RoundingMode.HALF_EVEN).toString()));
		}
		dataset.setData(barData);
		datasets.add(dataset);
		BarData data = new BarData(labels, datasets);
		return new BarChart(data);
	}
	
	/**
	 * Gets a {@link DataTable} with average IMC between candidates ages 
	 * 
	 * @author Leandro
	 */
	@ResponseBody
	@PostMapping("/tabela-media-imc-idade")
	public DataTable getTabelaMediaIMCPorIdade() {
		TreeMap<String, List<BigDecimal>> map = new TreeMap<>();
		Iterable<Candidato> candidatos = candidatoRepository.findAll();
		for (Candidato candidato : candidatos) {
			long idade = candidato.getIdade();
			int idadeBase = 0;
			if (idade % 10 == 0) {
				idadeBase = (int) ((idade / 10) * 10) - 10;
			} else { 
				idadeBase = (int) (idade / 10) * 10;
			}
			String key = "";
			if (idadeBase == 0) {
				key = idadeBase + " - " + (idadeBase + 10);
			} else {
				key = (idadeBase + 1) + " - " + (idadeBase + 10);
			}
			if (map.get(key) == null) {
				map.put(key, new LinkedList<BigDecimal>());
			}
			map.get(key).add(candidato.getIMC());
		}
		
		LinkedList<Object> headers = new LinkedList<>();
		headers.add("Faixa Etária");
		headers.add("Média IMC");
		
		LinkedList<LinkedList<Object>> data = new LinkedList<>();
		Iterator<Entry<String, List<BigDecimal>>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, List<BigDecimal>> entry = iter.next();
			String key = entry.getKey();
			List<BigDecimal> imcs = entry.getValue();
			BigDecimal somaImcs = BigDecimal.ZERO;
			for (BigDecimal imc : imcs) {
				somaImcs = somaImcs.add(imc);
			}
			LinkedList<Object> linha = new LinkedList<>();
			linha.add(key);
			linha.add(Double.parseDouble(somaImcs.divide(BigDecimal.valueOf(imcs.size()), 2, RoundingMode.HALF_EVEN).toString()));
			data.add(linha);
		}
		
		return new DataTable(data, headers);
	}
	
	/**
	 * Gets a {@link PieChart} with index of obese candidates 
	 * 
	 * @param sexo {@link String} to filter candidates between male and female
	 * @author Leandro
	 */
	@ResponseBody
	@GetMapping("/indice-obesidade")
	public PieChart getGraficoIndiceObesidade(String sexo) {
		List<Object[]> candidatosObesos = candidatoRepository.findObesosPorSexo(sexo);
		List<Double> data = new LinkedList<>();
		for (Object[] object : candidatosObesos) {
			data.add(Double.parseDouble(object[1].toString()));
			data.add(Double.parseDouble(object[2].toString()));
		}
		List<PieDataset> datasets = new LinkedList<>();
		PieDataset pieDataset = new PieDataset("Serie", data);
		List<String> backgroundColors = new LinkedList<String>();
		backgroundColors.add("rgb(175, 50, 100)");
		backgroundColors.add("rgb(50, 150, 150)");
		pieDataset.setBackgroundColor(backgroundColors);
		datasets.add(pieDataset);
		List<String> labels = new LinkedList<>();
		labels.add("Obesos");
		labels.add("Não Obesos");
		PieData pieData = new PieData(labels, datasets);
		return new PieChart(pieData);
	}
	
	/**
	 * Gets a {@link BarChart} with age average between candidates by blood type 
	 * 
	 * @author Leandro
	 */
	@ResponseBody
	@GetMapping("/grafico-media-idade-tipo-sanguineo")
	public BarChart getGraficoMediaIdadeTipoSanguineo() {
		List<Object[]> mediaPorTipoSanguineo = candidatoRepository.findMediaPorTipoSanguineo();		
		List<String> labels = new LinkedList<>();
		List<BarDataset> datasets = new LinkedList<>();
		BarDataset dataset = new BarDataset();
		List<Double> barData = new LinkedList<>();
		for (Object[] object : mediaPorTipoSanguineo) {
			labels.add(object[0].toString());
			double doubleValue = Double.parseDouble(object[1].toString());
			barData.add(Math.round(doubleValue) * 1.0);
		}
		dataset.setData(barData);
		datasets.add(dataset);
		BarData data = new BarData(labels, datasets);
		return new BarChart(data);
	}
	
	/**
	 * Gets a {@link DataTable} with with age average between candidates by blood type 
	 * 
	 * @author Leandro
	 */
	@ResponseBody
	@PostMapping("/tabela-media-idade-tipo-sanguineo")
	public DataTable getTabelaMediaIdadeTipoSanguineo() {
		List<Object[]> mediaPorTipoSanguineo = candidatoRepository.findMediaPorTipoSanguineo();
		LinkedList<Object> headers = new LinkedList<>();
		headers.add("Tipo Sanguineo");
		headers.add("Média de Idade");
		LinkedList<LinkedList<Object>> data = new LinkedList<>();
		for (Object[] object : mediaPorTipoSanguineo) {
			LinkedList<Object> linha = new LinkedList<>();
			linha.add(object[0]);
			linha.add(Math.round(Double.parseDouble(object[1].toString())));
			data.add(linha);
		}
		return new DataTable(data, headers);
	}
	
	/**
	 * Gets a {@link BarChart} to represent the amount of candidates that can donate blood grouped by blood type
	 * 
	 * @author Leandro
	 */
	@ResponseBody
	@GetMapping("/grafico-doadores-por-tipo-sanguineo")
	public BarChart getGraficoDoadoresPorTipoSanguineo() {
		List<Map<String, Object>> doadoresDisponiveis = candidatoRepository.findDoadoresDisponiveis();
		
		TreeMap<String, Double> treeMap = new TreeMap<>();
		for (Map<String, Object> map : doadoresDisponiveis) {
			String key = "tipo_sanguineo_receptor";
			if (treeMap.get(map.get(key)) == null) {
				treeMap.put(map.get(key).toString(), 1.0);
			} else {
				treeMap.put(map.get(key).toString(), treeMap.get(map.get(key).toString()) + 1);
			}
		}
		
		
		List<String> labels = new LinkedList<>();
		BarDataset dataset = new BarDataset();
		Iterator<Entry<String, Double>> iter = treeMap.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, Double> entry = iter.next();
			labels.add(entry.getKey());
			dataset.getData().add(entry.getValue());
		}
		
		List<BarDataset> datasets = new LinkedList<>();
		datasets.add(dataset);
		BarData data = new BarData(labels, datasets);
		return new BarChart(data);
	}
}
