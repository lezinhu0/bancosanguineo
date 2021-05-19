package br.com.lsoft.BancoSanguineo.controller;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.lsoft.BancoSanguineo.model.Usuario;
import br.com.lsoft.BancoSanguineo.repository.UsuarioRepository;
import br.com.lsoft.BancoSanguineo.utils.DataTable;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("")
	public String usuarios() {
		return "usuarios";
	}
	
	@ResponseBody
	@PostMapping("")
	public DataTable getUsuarios() {
		Iterable<Usuario> usuarios = usuarioRepository.findAll();
		for (Iterator<Usuario> iterator = usuarios.iterator(); iterator.hasNext();) {
			Usuario usuario = iterator.next();
		}
		return DataTable.fromIterable(usuarios);
	}

}
