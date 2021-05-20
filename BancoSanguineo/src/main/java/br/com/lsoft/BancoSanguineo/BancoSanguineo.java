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
	public CommandLineRunner commandLineRunner(TipoSanguineoRepository tipoSanguineoRepository) {
		return args -> {
			LinkedList<TipoSanguineo> tiposSanguineos = new LinkedList<>();
			tiposSanguineos.add(new TipoSanguineo("A+", "A POSITIVO"));
			tiposSanguineos.add(new TipoSanguineo("A-", "A NEGATIVO"));
			tiposSanguineos.add(new TipoSanguineo("B+", "A POSITIVO"));
			tiposSanguineos.add(new TipoSanguineo("B-", "B NEGATIVO"));
			tiposSanguineos.add(new TipoSanguineo("AB+", "AB POSITIVO"));
			tiposSanguineos.add(new TipoSanguineo("AB-", "AB NEGATIVO"));
			tiposSanguineos.add(new TipoSanguineo("O+", "O POSITIVO"));
			tiposSanguineos.add(new TipoSanguineo("O-", "O NEGATIVO"));
			tipoSanguineoRepository.saveAll(tiposSanguineos);

			LinkedList<TipoSanguineo> doaPara = new LinkedList<>();
			TipoSanguineo tipoSanguineo = tipoSanguineoRepository.findById("A+").orElse(null);
			doaPara.add(new TipoSanguineo("AB+"));
			doaPara.add(new TipoSanguineo("A+"));
			tipoSanguineo.setDoaPara(doaPara);
			tipoSanguineoRepository.save(tipoSanguineo);

			doaPara = new LinkedList<>();
			tipoSanguineo = tipoSanguineoRepository.findById("A-").orElse(null);
			doaPara.add(new TipoSanguineo("A+"));
			doaPara.add(new TipoSanguineo("A-"));
			doaPara.add(new TipoSanguineo("AB+"));
			doaPara.add(new TipoSanguineo("AB-"));
			tipoSanguineo.setDoaPara(doaPara);
			tipoSanguineoRepository.save(tipoSanguineo);

			doaPara = new LinkedList<>();
			tipoSanguineo = tipoSanguineoRepository.findById("B+").orElse(null);
			doaPara.add(new TipoSanguineo("B+"));
			doaPara.add(new TipoSanguineo("AB+"));
			tipoSanguineo.setDoaPara(doaPara);
			tipoSanguineoRepository.save(tipoSanguineo);

			doaPara = new LinkedList<>();
			tipoSanguineo = tipoSanguineoRepository.findById("B-").orElse(null);
			doaPara.add(new TipoSanguineo("B+"));
			doaPara.add(new TipoSanguineo("B-"));
			doaPara.add(new TipoSanguineo("AB+"));
			doaPara.add(new TipoSanguineo("AB-"));
			tipoSanguineo.setDoaPara(doaPara);
			tipoSanguineoRepository.save(tipoSanguineo);

			doaPara = new LinkedList<>();
			tipoSanguineo = tipoSanguineoRepository.findById("AB+").orElse(null);
			doaPara.add(new TipoSanguineo("AB+"));
			tipoSanguineo.setDoaPara(doaPara);
			tipoSanguineoRepository.save(tipoSanguineo);

			doaPara = new LinkedList<>();
			tipoSanguineo = tipoSanguineoRepository.findById("AB-").orElse(null);
			doaPara.add(new TipoSanguineo("AB+"));
			doaPara.add(new TipoSanguineo("AB-"));
			tipoSanguineo.setDoaPara(doaPara);
			tipoSanguineoRepository.save(tipoSanguineo);

			doaPara = new LinkedList<>();
			tipoSanguineo = tipoSanguineoRepository.findById("O+").orElse(null);
			doaPara.add(new TipoSanguineo("A+"));
			doaPara.add(new TipoSanguineo("B+"));
			doaPara.add(new TipoSanguineo("O+"));
			doaPara.add(new TipoSanguineo("AB+"));
			tipoSanguineo.setDoaPara(doaPara);
			tipoSanguineoRepository.save(tipoSanguineo);

			doaPara = new LinkedList<>();
			tipoSanguineo = tipoSanguineoRepository.findById("O-").orElse(null);
			doaPara.add(new TipoSanguineo("A+"));
			doaPara.add(new TipoSanguineo("B+"));
			doaPara.add(new TipoSanguineo("O+"));
			doaPara.add(new TipoSanguineo("AB+"));
			doaPara.add(new TipoSanguineo("A-"));
			doaPara.add(new TipoSanguineo("B-"));
			doaPara.add(new TipoSanguineo("O-"));
			doaPara.add(new TipoSanguineo("AB-"));
			tipoSanguineo.setDoaPara(doaPara);
			tipoSanguineoRepository.save(tipoSanguineo);
		};
	}
}
