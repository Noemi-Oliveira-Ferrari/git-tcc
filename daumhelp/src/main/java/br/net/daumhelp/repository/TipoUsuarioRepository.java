package br.net.daumhelp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.net.daumhelp.model.TipoUsuario;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long> {

}
