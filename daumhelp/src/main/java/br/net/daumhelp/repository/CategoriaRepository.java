package br.net.daumhelp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.net.daumhelp.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
	//BUSCA TODAS AS CATEGORIAS ORDENADAS PELO NOME
	@Query("SELECT c FROM Categoria c WHERE c.idCategoria > 0 ORDER BY c.categoria")
	public List<Categoria> findAllOrderByName();
}
