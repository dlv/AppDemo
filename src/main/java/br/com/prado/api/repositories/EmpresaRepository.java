package br.com.prado.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.prado.api.entities.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>{

	Empresa findByCnpj(String cnpj);
}
