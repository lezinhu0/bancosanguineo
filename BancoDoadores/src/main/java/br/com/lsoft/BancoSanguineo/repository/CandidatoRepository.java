package br.com.lsoft.BancoSanguineo.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.lsoft.BancoSanguineo.model.Candidato;

public interface CandidatoRepository extends CrudRepository<Candidato, String>{

	@Query("select c.estado, count(c.nome) from Candidato c group by c.estado order by 1")
	public List<Object[]> countByEstado();

	@Query(nativeQuery = true, value = "select sexo, obesos, total, obesos/total * 100 as representacao from (select c.sexo, sum(case when (c.peso / c.altura / c.altura) > 30 then 1 else 0 end) as obesos, count(sexo) as total from candidato c where upper(sexo) = upper(:sexo) group by sexo) as resultset")
	public List<Object[]> findObesosPorSexo(@Param("sexo") String sexo);

	@Query(nativeQuery = true, value = "select tipo_sanguineo, avg(idade) from (select tipo_sanguineo, floor(datediff(curdate(), c.data_nasc) / 365) as idade from candidato c) as resultset group by tipo_sanguineo order by 1 desc")
	public List<Object[]> findMediaPorTipoSanguineo();

	@Query(nativeQuery = true, 
			value = "select distinct\n"
			+		"	c.tipo_sanguineo as 'tipo_sanguineo_receptor', cdp.*\n"
			+ 		"	from Candidato c\n"
			+ 		"	join tipo_sanguineo_doa_para dp on dp.doa_para_codigo = c.tipo_sanguineo\n"
			+ 		"	join Candidato cdp on cdp.tipo_sanguineo = dp.tipo_sanguineo_codigo and floor(datediff(curdate(), cdp.data_nasc) / 365) > 16 and floor(datediff(curdate(), cdp.data_nasc) / 365) <= 69 and cdp.peso > 50")
	public List<Map<String, Object>> findDoadoresDisponiveis();

	@Query(nativeQuery = true, 
			value = "select distinct\n"
			+		"	cdp.*\n"
			+ 		"	from Candidato c\n"
			+ 		"	join tipo_sanguineo_doa_para dp on dp.doa_para_codigo = c.tipo_sanguineo\n"
			+ 		"	join Candidato cdp on cdp.tipo_sanguineo = dp.tipo_sanguineo_codigo and floor(datediff(curdate(), cdp.data_nasc) / 365) > 16 and floor(datediff(curdate(), cdp.data_nasc) / 365) <= 69 and cdp.peso > 50"
			+		"	where c.tipo_sanguineo = :tipoSanguineo")
	public List<Candidato> findDoadoresDisponiveisPorTipoSanguineo(@Param("tipoSanguineo") String tipoSanguineo);


}
