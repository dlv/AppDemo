package br.com.prado.api;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.prado.api.entities.Empresa;
import br.com.prado.api.repositories.EmpresaRepository;

@SpringBootApplication
public class AppDemoApplication {

	private static final Logger LOG = Logger.getLogger(AppDemoApplication.class.getName());
	
	@Value("${paginacao.qtd_por_pagina}")
	private int qtdPorPagina;

	@Autowired
	private EmpresaRepository empresaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(AppDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			LOG.info("Quantidade de elemento por página: " + this.qtdPorPagina);

			Empresa empresa = new Empresa();
			empresa.setRazaoSocial("Prado IT");
			empresa.setCnpj("76441333000118");

			this.empresaRepository.save(empresa);

			List<Empresa> empresas = this.empresaRepository.findAll();
			empresas.forEach(emp -> LOG.info(emp.toString()));

			Optional<Empresa> empresaDb = this.empresaRepository.findById(1L);
			LOG.info("Empresa por ID: "+ empresaDb.get().toString());

			empresaDb.get().setRazaoSocial("Prado WEB");
			this.empresaRepository.save(empresaDb.get());

			Empresa empresaCnpj = this.empresaRepository.findByCnpj("76441333000118");
			LOG.info("Empresa por ID: "+ empresaCnpj);

			this.empresaRepository.deleteById(1L);
			empresas = this.empresaRepository.findAll();
			LOG.info("Empresas: "+ empresas.size());
		};
	}

}
