package br.com.lsoft.BancoSanguineo.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.lsoft.BancoSanguineo.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {


}
