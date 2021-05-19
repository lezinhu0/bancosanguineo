package br.com.lsoft.BancoSanguineo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author Leandro
 */
@Controller
@RequestMapping("/relatorios-gerais")
public class RelatorioGeralController {
	
	/**
	 * 
	 * @author Leandro
	 */
	@GetMapping("")
	public String relatoriosGerais() {
		return "relatoriosGerais";
	}

}
