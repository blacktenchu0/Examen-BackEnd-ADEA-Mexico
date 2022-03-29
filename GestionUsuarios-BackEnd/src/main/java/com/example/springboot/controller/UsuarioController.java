package com.example.springboot.controller;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.entity.Usuario;
import com.example.springboot.service.IUsuarioService;

@RestController
@RequestMapping(value = "api/usuario")
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;
	
	private final Logger logger = LogManager.getRootLogger();

	@GetMapping("/getUsuarios")
	public ResponseEntity<?> getUsuarios() {
		Map<String, Object> usuarios = usuarioService.getUsuarios();
		ResponseEntity<Map<String, Object>> respuesta = new ResponseEntity<Map<String, Object>>(usuarios, HttpStatus.OK);

		return respuesta;
	}

	@GetMapping("/getUsuario/{login}")
	public ResponseEntity<?> getUsuario(@PathVariable String login) {
		Map<String, Object> usuario = usuarioService.getUsuario(login);

		ResponseEntity<Map<String, Object>> respuesta = new ResponseEntity<Map<String, Object>>(usuario, HttpStatus.OK);

		return respuesta;
	}
	
	@GetMapping("/login/{login}/{password}")
	public ResponseEntity<?> login(@PathVariable String login, @PathVariable String password) {
		logger.info("*** Controller: login ***");
		Map<String, Object> usuario = usuarioService.login(login, password);

		ResponseEntity<Map<String, Object>> respuesta = new ResponseEntity<Map<String, Object>>(usuario, HttpStatus.CREATED);

		return respuesta;
	}

	@PostMapping("/createUsuario")
	public ResponseEntity<?> createUsuario(@RequestBody Usuario usuarioFront) {
		Map<String, Object> usuario = usuarioService.createUsuario(usuarioFront);

		ResponseEntity<Map<String, Object>> respuesta = new ResponseEntity<Map<String, Object>>(usuario, HttpStatus.CREATED);

		return respuesta;
	}

	@PutMapping("/modifyUsuario")
	public ResponseEntity<?> modifyUsuario(@RequestBody Usuario usuarioFront) {
		Map<String, Object> usuario = usuarioService.modifyUsuario(usuarioFront);

		ResponseEntity<Map<String, Object>> respuesta = new ResponseEntity<Map<String, Object>>(usuario, HttpStatus.CREATED);

		return respuesta;
	}
}
