package br.net.daumhelp.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.net.daumhelp.dto.ClienteDTO;
import br.net.daumhelp.dto.ProfissionalDTO;
import br.net.daumhelp.dto.repository.ClienteDTORepository;
import br.net.daumhelp.dto.repository.ProfissionalDTORepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	ClienteDTORepository clienteRepository;

	@Autowired
	ProfissionalDTORepository profissionalRepository;
	

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		ClienteDTO cliente = clienteRepository.findByEmail(email);
		ProfissionalDTO profissional = profissionalRepository.findByEmail(email);
		
		if (cliente != null) {
			User user = new User(cliente.getEmail(), cliente.getIdCliente().toString(), new ArrayList<>());

			return user;
		} else if (profissional != null) {
			User user = new User(profissional.getEmail(), profissional.getIdProfissional().toString(), new ArrayList<>());

			return user;
			
		}
				
		throw new UsernameNotFoundException("Usuário com e-mail "+email+" não foi encontrado.");
	}
}