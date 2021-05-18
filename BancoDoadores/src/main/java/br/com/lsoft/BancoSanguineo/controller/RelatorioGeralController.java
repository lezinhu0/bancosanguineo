package br.com.lsoft.BancoSanguineo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/relatorios-gerais")
public class RelatorioGeralController {
	
	@GetMapping("")
	public String relatoriosGerais() {
		return "relatoriosGerais";
	}

}
