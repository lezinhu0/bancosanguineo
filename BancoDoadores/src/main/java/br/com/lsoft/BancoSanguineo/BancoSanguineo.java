package br.com.lsoft.BancoSanguineo;

import java.util.LinkedList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.lsoft.BancoSanguineo.model.TipoSanguineo;
import br.com.lsoft.BancoSanguineo.model.Usuario;
import br.com.lsoft.BancoSanguineo.repository.TipoSanguineoRepository;
import br.com.lsoft.BancoSanguineo.repository.UsuarioRepository;

@SpringBootApplication(exclude = { org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class })
public class BancoSanguineo {
	
	public static void main(String[] args) {
		SpringApplication.run(BancoSanguineo.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(TipoSanguineoRepository tipoSanguneoRepository, UsuarioRepository usuarioRepository) {
		return args -> {
			TipoSanguineo tipoSanguineo = new TipoSanguineo();
			tipoSanguineo.setCodigo("A+");
			tipoSanguineo.setDescricao("A Positivo");
			tipoSanguneoRepository.save(tipoSanguineo);
			
			tipoSanguineo = new TipoSanguineo();
			tipoSanguineo.setCodigo("A-");
			tipoSanguineo.setDescricao("A Positivo");
			tipoSanguneoRepository.save(tipoSanguineo);
			
			tipoSanguineo = new TipoSanguineo();
			tipoSanguineo.setCodigo("B+");
			tipoSanguineo.setDescricao("A Positivo");
			tipoSanguneoRepository.save(tipoSanguineo);
			
			tipoSanguineo = new TipoSanguineo();
			tipoSanguineo.setCodigo("B-");
			tipoSanguineo.setDescricao("A Positivo");
			tipoSanguneoRepository.save(tipoSanguineo);
			
			tipoSanguineo = new TipoSanguineo();
			tipoSanguineo.setCodigo("AB+");
			tipoSanguineo.setDescricao("A Positivo");
			tipoSanguneoRepository.save(tipoSanguineo);
			
			tipoSanguineo = new TipoSanguineo();
			tipoSanguineo.setCodigo("AB-");
			tipoSanguineo.setDescricao("A Positivo");
			tipoSanguneoRepository.save(tipoSanguineo);
			
			tipoSanguineo = new TipoSanguineo();
			tipoSanguineo.setCodigo("O+");
			tipoSanguineo.setDescricao("A Positivo");
			tipoSanguneoRepository.save(tipoSanguineo);
			
			tipoSanguineo = new TipoSanguineo();
			tipoSanguineo.setCodigo("O-");
			tipoSanguineo.setDescricao("A Positivo");
			tipoSanguneoRepository.save(tipoSanguineo);

			
			LinkedList<TipoSanguineo> doaPara = new LinkedList<>();
			tipoSanguineo = tipoSanguneoRepository.findById("A+").orElse(null);
			doaPara.add(new TipoSanguineo("AB+"));
			doaPara.add(new TipoSanguineo("A+"));
			tipoSanguineo.setDoaPara(doaPara);
			tipoSanguneoRepository.save(tipoSanguineo);
			
			doaPara = new LinkedList<>();
			tipoSanguineo = tipoSanguneoRepository.findById("A-").orElse(null);
			doaPara.add(new TipoSanguineo("A+"));
			doaPara.add(new TipoSanguineo("A-"));
			doaPara.add(new TipoSanguineo("AB+"));
			doaPara.add(new TipoSanguineo("AB-"));
			tipoSanguineo.setDoaPara(doaPara);
			tipoSanguneoRepository.save(tipoSanguineo);
			
			doaPara = new LinkedList<>();
			tipoSanguineo = tipoSanguneoRepository.findById("B+").orElse(null);
			doaPara.add(new TipoSanguineo("B+"));
			doaPara.add(new TipoSanguineo("AB+"));
			tipoSanguineo.setDoaPara(doaPara);
			tipoSanguneoRepository.save(tipoSanguineo);
			
			doaPara = new LinkedList<>();
			tipoSanguineo = tipoSanguneoRepository.findById("B-").orElse(null);
			doaPara.add(new TipoSanguineo("B+"));
			doaPara.add(new TipoSanguineo("B-"));
			doaPara.add(new TipoSanguineo("AB+"));
			doaPara.add(new TipoSanguineo("AB-"));
			tipoSanguineo.setDoaPara(doaPara);
			tipoSanguneoRepository.save(tipoSanguineo);
			
			doaPara = new LinkedList<>();
			tipoSanguineo = tipoSanguneoRepository.findById("AB+").orElse(null);
			doaPara.add(new TipoSanguineo("AB+"));
			tipoSanguineo.setDoaPara(doaPara);
			tipoSanguneoRepository.save(tipoSanguineo);
			
			doaPara = new LinkedList<>();
			tipoSanguineo = tipoSanguneoRepository.findById("AB-").orElse(null);
			doaPara.add(new TipoSanguineo("AB+"));
			doaPara.add(new TipoSanguineo("AB-"));
			tipoSanguineo.setDoaPara(doaPara);
			tipoSanguneoRepository.save(tipoSanguineo);
			
			doaPara = new LinkedList<>();
			tipoSanguineo = tipoSanguneoRepository.findById("O+").orElse(null);
			doaPara.add(new TipoSanguineo("A+"));
			doaPara.add(new TipoSanguineo("B+"));
			doaPara.add(new TipoSanguineo("O+"));
			doaPara.add(new TipoSanguineo("AB+"));
			tipoSanguineo.setDoaPara(doaPara);
			tipoSanguneoRepository.save(tipoSanguineo);
			
			doaPara = new LinkedList<>();
			tipoSanguineo = tipoSanguneoRepository.findById("O-").orElse(null);
			doaPara.add(new TipoSanguineo("A+"));
			doaPara.add(new TipoSanguineo("B+"));
			doaPara.add(new TipoSanguineo("O+"));
			doaPara.add(new TipoSanguineo("AB+"));
			doaPara.add(new TipoSanguineo("A-"));
			doaPara.add(new TipoSanguineo("B-"));
			doaPara.add(new TipoSanguineo("O-"));
			doaPara.add(new TipoSanguineo("AB-"));
			tipoSanguineo.setDoaPara(doaPara);
			tipoSanguneoRepository.save(tipoSanguineo);
		};
	}
}
