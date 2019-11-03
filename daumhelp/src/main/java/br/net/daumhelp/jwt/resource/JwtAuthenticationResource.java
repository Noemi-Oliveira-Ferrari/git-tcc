package br.net.daumhelp.jwt.resource;

import javax.annotation.processing.SupportedOptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.net.daumhelp.config.JwtTokenUtill;
import br.net.daumhelp.jwt.model.JWTRequest;
import br.net.daumhelp.jwt.model.JWTResponse;
import br.net.daumhelp.model.Cliente;
import br.net.daumhelp.model.Profissional;
import br.net.daumhelp.repository.ClienteRepository;
import br.net.daumhelp.repository.ProfissionalRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@SupportedOptions(value = { "eventBusIndex", "verbose" })
public class JwtAuthenticationResource {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtill jwtTokenUtil;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ProfissionalRepository profissionalRepository;
	

	@PostMapping("/login/cliente")
	public ResponseEntity<?> createAuthenticationTokenCliente(@RequestBody JWTRequest authenticationRequest)
			throws Exception {
		final Cliente cliente = clienteRepository.findUserLogin(
				authenticationRequest.getEmail(),
				authenticationRequest.getPassword());

		if (cliente != null) {
			final String token = jwtTokenUtil.generateTokenCliente(cliente);
			return ResponseEntity.ok(new JWTResponse(token));
		}

		return ResponseEntity.ok("{\"error\": \"Usuario não cadastrado\"}");
	}

	@PostMapping("/login/profissional")
	public ResponseEntity<?> createAuthenticationTokenProfissional(@RequestBody JWTRequest authenticationRequest)
			throws Exception {
		final Profissional profissional = profissionalRepository.findUserLogin(
				authenticationRequest.getEmail(), 
				authenticationRequest.getPassword());

		if (profissional != null) {
			final String token = jwtTokenUtil.generateTokenProfissional(profissional);
			return ResponseEntity.ok(new JWTResponse(token));
		}

		return ResponseEntity.ok("{\"error\": \"Usuario não cadastrado\"}");
	}

	
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}