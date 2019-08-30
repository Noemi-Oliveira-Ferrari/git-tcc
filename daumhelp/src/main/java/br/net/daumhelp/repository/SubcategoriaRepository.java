package br.net.daumhelp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.net.daumhelp.model.Subcategoria;

public interface SubcategoriaRepository extends JpaRepository<Subcategoria, Long> {
	@Query("SELECT s FROM Subcategoria s WHERE s.categoria.idCategoria = ?1")
	public List<Subcategoria> findByCategoria(Long id);

}
