package br.com.lsoft.BancoSanguineo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.lsoft.BancoSanguineo.model.TipoSanguineo;

@SpringBootApplication
public class BancoSanguineo {
	
	public static void main(String[] args) {
		SpringApplication.run(BancoSanguineo.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			TipoSanguineo tipoSanguineo = new TipoSanguineo();
			tipoSanguineo.setCodigo("A+");
			tipoSanguineo.setDescricao("A Positivo");
			
		};
	}

}
