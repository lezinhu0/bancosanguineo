package br.com.lsoft.BancoSanguineo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.lsoft.BancoSanguineo.model.Usuario;
import br.com.lsoft.BancoSanguineo.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuarios/form")
public class UsuariosFormController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("")
	public String form(Model model, Usuario usuario) {
		model.addAttribute("usuario", usuario);
		return "usuariosForm";
	}
	
	@PostMapping("/salvar")
	public String salvarUsuario(Usuario usuario) {
		if (!usuarioRepository.existsById(usuario.getUsername())) {
			usuario.setAuthorities("usuario");
		}
		usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
		usuarioRepository.save(usuario);
		return "redirect:/usuarios";
	}

}
