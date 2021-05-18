package br.com.lsoft.BancoSanguineo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import br.com.lsoft.BancoSanguineo.model.Candidato;
import br.com.lsoft.BancoSanguineo.repository.CandidatoRepository;
import br.com.lsoft.BancoSanguineo.repository.TipoSanguineoRepository;

@Controller
@RequestMapping("/candidatos")
public class CandidatosController {
	
	@Autowired
	private TipoSanguineoRepository tipoSanguineoRepository;
	
	@Autowired
	private CandidatoRepository candidatoRepository;
	
	@ResponseBody
	@PostMapping("/salvar")
	public String salvar(@RequestBody String candidatosString) {
		System.out.println("Salvando Candidatos!!");
		
		 Candidato[] candidatos = new Gson().fromJson(candidatosString, Candidato[].class);
		 
		 for (Candidato candidato : candidatos) {
			 candidato.setTipoSanguineo(tipoSanguineoRepository.findById(candidato.getTipo_sanguineo()).orElse(null));
			 candidatoRepository.save(candidato);
		}
		 
		 System.out.println("Candidatos salvos com sucesso!");
		 
		 return "response";
	}
}
