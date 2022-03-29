package com.example.springboot.repository;

import java.util.Map;

import com.example.springboot.entity.Usuario;

public interface IUsuarioRepository {

	public Map<String, Object> getUsuarios();

	public Map<String, Object> getUsuario(String login);

	public Map<String, Object> login(String login, String password);

	public Map<String, Object> createUsuario(Usuario usuario);

	public Map<String, Object> modifyUsuario(Usuario usuario);
}
