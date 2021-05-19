package br.com.lsoft.BancoSanguineo;

import java.util.LinkedList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import br.com.lsoft.BancoSanguineo.model.TipoSanguineo;
import br.com.lsoft.BancoSanguineo.repository.TipoSanguineoRepository;

@SpringBootApplication(exclude = { org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class })
public class BancoSanguineo extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BancoSanguineo.class, args);
	}

	/**
	 * Runs on start
	 *
	 * @author Leandro
	 */
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
