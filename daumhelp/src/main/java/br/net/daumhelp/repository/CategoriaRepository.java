package br.net.daumhelp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.net.daumhelp.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
