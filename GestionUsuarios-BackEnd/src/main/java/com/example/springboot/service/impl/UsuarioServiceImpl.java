package com.example.springboot.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.entity.Usuario;
import com.example.springboot.properties.ErrorProperties;
import com.example.springboot.repository.IUsuarioRepository;
import com.example.springboot.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Autowired
	ErrorProperties errorProperties;

	@Override
	public Map<String, Object> getUsuarios() {
		return usuarioRepository.getUsuarios();
	}

	@Override
	public Map<String, Object> getUsuario(String login) {
		if (login == null || login == "") {
			Map<String, Object> map = new HashMap<>();
			map.put("mensaje", "El nombre de usuario recibido esta vacio o es nulo");
			map.put("usuario", null);
			map.put("codigo", errorProperties.getErrorQuery());
			
			return map;
		} else {
			return usuarioRepository.getUsuario(login);
		}
	}
	
	@Override
	public Map<String, Object> login(String login, String password)
	{
		if (login == null || login == "" || password == null || password == "") {
			Map<String, Object> map = new HashMap<>();
			map.put("mensaje", "El nombre de usuario o la contraseña recebidos estan vacios");
			map.put("usuario", null);
			map.put("codigo", errorProperties.getErrorQuery());
			
			return map;
		} else {
			return usuarioRepository.login(login, password);
		}
	}

	@Override
	@Transactional
	public Map<String, Object> createUsuario(Usuario usuario) {
		if (usuario == null) {
			Map<String, Object> map = new HashMap<>();
			map.put("mensaje", "El usuario recibido para creación tiene un valor invalido");
			map.put("usuario", null);
			map.put("codigo", errorProperties.getNoData());
			
			return map;
		} else if (usuario.login == null || usuario.login == "" || usuario.password == null || usuario.password == ""
				|| usuario.nombre == null || usuario.nombre == "" || usuario.cliente == null) {
			Map<String, Object> map = new HashMap<>();
			map.put("mensaje", "Alguno de los datos necesarios para la creacción del usuario no existen o no se encontraron");
			map.put("usuario", null);
			map.put("codigo", errorProperties.getErrorQuery());
			
			return map;
		} else {
			return usuarioRepository.createUsuario(usuario);
		}
	}

	@Override
	@Transactional
	public Map<String, Object> modifyUsuario(Usuario usuario) {
		if (usuario == null) {
			Map<String, Object> map = new HashMap<>();
			map.put("mensaje", "El usuario recibido para modificación tiene un valor invalido");
			map.put("usuario", null);
			map.put("codigo", errorProperties.getNoData());
			
			return map;
		} else if (usuario.login == null || usuario.login == "") {
			Map<String, Object> map = new HashMap<>();
			map.put("mensaje", "Alguno de los datos necesarios para la modificación del usuario no existen o no se encontraron");
			map.put("usuario", null);
			map.put("codigo", errorProperties.getErrorQuery());
			
			return map;
		} else {
			return usuarioRepository.modifyUsuario(usuario);
		}
	}
}
