package br.com.lsoft.BancoSanguineo.controller;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.lsoft.BancoSanguineo.model.Usuario;
import br.com.lsoft.BancoSanguineo.repository.UsuarioRepository;
import br.com.lsoft.BancoSanguineo.utils.DataTable;
import br.com.lsoft.BancoSanguineo.utils.HtmlUtils;

/**
 * MVC Controller
 *
 * @author Leandro
 */
@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	/**
	 * 
	 * @author Leandro
	 */
	@GetMapping("")
	public String usuarios() {
		return "usuarios";
	}
	
	/**
	 * 
	 * @author Leandro
	 */
	@ResponseBody
	@PostMapping("")
	public DataTable getUsuarios() {
		Iterable<Usuario> usuarios = usuarioRepository.findAll();
		DataTable table = DataTable.fromIterable(usuarios);
		if (table == null) {
			return null;
		}
		for (LinkedList<Object> linkedList : table.getData()) {
			Integer index = table.getHeaders().indexOf("Username");
			String username = (String) linkedList.get(index);
			linkedList.set(index, HtmlUtils.createLink("/usuarios/form/" + username, username));
		}
		
		return table;
	}

}
