package br.com.lsoft.BancoSanguineo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.lsoft.BancoSanguineo.model.Candidato;
import br.com.lsoft.BancoSanguineo.model.TipoSanguineo;
import br.com.lsoft.BancoSanguineo.repository.CandidatoRepository;
import br.com.lsoft.BancoSanguineo.repository.TipoSanguineoRepository;
import br.com.lsoft.BancoSanguineo.utils.DataTable;

/**
 * 
 * @author Leandro
 */
@Controller
@RequestMapping("/processamento/doadores-disponiveis")
public class ProcessamentoDoadoresDisponiveisController {
	
	@Autowired
	private CandidatoRepository candidatoRepository;
	
	@Autowired
	private TipoSanguineoRepository tipoSanguineoRepository;

	/**
	 * 
	 * @author Leandro
	 */
	@GetMapping("")
	public String doadoresDisponíveis(Model model) {
		Iterable<TipoSanguineo> tiposSanguineos = tipoSanguineoRepository.findAll();
		model.addAttribute("tiposSanguineos", tiposSanguineos);
		return "processamentoDoadoresDisponiveis";
	}
	
	
	/**
	 * 
	 * @author Leandro
	 */
	@ResponseBody
	@PostMapping("")
	public DataTable processamentoDoadoresDisponiveis(String tipoSanguineo) {
		Iterable<Candidato> doadoresDisponiveisPorTipoSanguineo = candidatoRepository.findDoadoresDisponiveisPorTipoSanguineo(tipoSanguineo);
		return DataTable.fromIterable(doadoresDisponiveisPorTipoSanguineo);
	}

}
