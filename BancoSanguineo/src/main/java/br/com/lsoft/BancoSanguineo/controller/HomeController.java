package br.com.lsoft.BancoSanguineo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author Leandro
 */
@Controller
@RequestMapping("")
public class HomeController {
	
	/**
	 * 
	 * @author Leandro
	 */
	@GetMapping("")
	public String home() {
		return "home";
	}

}
