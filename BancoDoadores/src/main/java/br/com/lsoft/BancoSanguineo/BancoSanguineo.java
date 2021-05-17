package br.com.lsoft.BancoSanguineo;

import java.util.LinkedList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.lsoft.BancoSanguineo.model.TipoSanguineo;
import br.com.lsoft.BancoSanguineo.repository.TipoSanguineoRepository;

@SpringBootApplication
public class BancoSanguineo {
	
	public static void main(String[] args) {
		SpringApplication.run(BancoSanguineo.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(TipoSanguineoRepository tipoSanguneoRepository) {
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
		};
	}
}
