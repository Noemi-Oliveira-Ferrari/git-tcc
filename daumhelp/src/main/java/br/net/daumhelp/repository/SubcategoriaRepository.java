package br.net.daumhelp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.net.daumhelp.model.Subcategoria;

public interface SubcategoriaRepository extends JpaRepository<Subcategoria, Long> {
	
	//BUSCA SUBCATEGORIAS ORDENADAS POR NOME
	@Query("SELECT s FROM Subcategoria s WHERE s.idSubcategoria > 0 ORDER BY s.subcategoria")
	public List<Subcategoria> findAllOrderByName();
	
	//BUSCA SUBCATEGORIAS PELO ID DA CATEGORIAS ORDENADAS POR NOME
	@Query("SELECT s FROM Subcategoria s WHERE s.categoria.idCategoria = ?1 ORDER BY s.subcategoria")
	public List<Subcategoria> findByCategoria(Long id);
	
	
}
