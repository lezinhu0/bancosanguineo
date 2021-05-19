package br.com.lsoft.BancoSanguineo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.lsoft.BancoSanguineo.model.Usuario;
import br.com.lsoft.BancoSanguineo.repository.UsuarioRepository;

/**
 * MVC Controller
 *
 * @author Leandro
 */
@Controller
@RequestMapping("/usuarios/form")
public class UsuariosFormController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	/**
	 * 
	 * @author Leandro
	 */
	@GetMapping("")
	public String form(Model model, Usuario usuario) {
		model.addAttribute("usuario", usuario);
		return "usuariosForm";
	}
	
	/**
	 * 
	 * @author Leandro
	 * @param model 
	 */
	@GetMapping("/{username}")
	public String form(@PathVariable String username, Model model) {
		Usuario usuario = usuarioRepository.findById(username).orElse(null);
		model.addAttribute("usuario", usuario);
		return "usuariosForm";
	}
	
	
	/**
	 * 
	 * @author Leandro
	 */
	@PostMapping("/salvar")
	public String salvarUsuario(Usuario usuario) {
		if (!usuarioRepository.existsById(usuario.getUsername())) {
			usuario.setAuthorities("usuario");
		} else {
			usuario.setAuthorities(usuarioRepository.findById(usuario.getUsername()).orElse(null).getAuthorities());
		}
		usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
		usuarioRepository.save(usuario);
		return "redirect:/usuarios";
	}

}
