package br.com.lsoft.BancoSanguineo.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.lsoft.BancoSanguineo.model.Candidato;
import br.com.lsoft.BancoSanguineo.repository.CandidatoRepository;

@Controller
@RequestMapping("/report")
public class ReportController {
	
	@Autowired
	private CandidatoRepository candidatoRepository;
	
	@GetMapping("")
	public String report() {
		Iterable<Candidato> candidatos = candidatoRepository.findAll();
		
		HashMap<String, List<BigDecimal>> map = new HashMap<>();
		
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
			
			map.get(key).add(candidato.getICM());
		}
		
		
		HashMap<String, BigDecimal> mediaImcs = new HashMap<>();
		Iterator<Entry<String, List<BigDecimal>>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, List<BigDecimal>> entry = iter.next();
			String key = entry.getKey();
			List<BigDecimal> imcs = entry.getValue();
			BigDecimal somaImcs = BigDecimal.ZERO;
			for (BigDecimal imc : imcs) {
				somaImcs = somaImcs.add(imc);
			}
			mediaImcs.put(key, somaImcs.divide(BigDecimal.valueOf(imcs.size()), 2, RoundingMode.HALF_EVEN));
		}
		
		List<Object> candidatosObesos = candidatoRepository.findObesosPorSexo();
		
		List<Object> mediaPorTipoSanguineo = candidatoRepository.findMediaPorTipoSanguineo();
		
		List<Object> doadoresDisponiveis = candidatoRepository.findDoadoresDisponiveis();
		
		List<Candidato> doadoresDisponiveisPorTipoSanguineo = candidatoRepository.findDoadoresDisponiveisPorTipoSanguineo("O-");
		
		return "report";
	}

}
