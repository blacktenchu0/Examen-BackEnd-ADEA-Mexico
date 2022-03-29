package com.example.springboot.repository.impl;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.example.springboot.entity.Usuario;
import com.example.springboot.repository.IUsuarioRepository;
import com.example.springboot.properties.ErrorProperties;

@Repository
public class UsuarioRepositoryImpl implements IUsuarioRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	ErrorProperties errorProperties;

	private final Logger logger = LogManager.getRootLogger();

	@Override
	public Map<String, Object> getUsuarios() {
		Map<String, Object> map = new HashMap<>();
		List<Usuario> usuarios = new ArrayList<>();

		Query query = entityManager.createNativeQuery(
				"SELECT LOGIN, " + "NOMBRE, " + "CLIENTE, " + "EMAIL, " + "FECHAALTA, " + "FECHABAJA, " + "STATUS, "
						+ "INTENTOS, " + "FECHAREVOCADO, " + "FECHA_VIGENCIA, " + "NO_ACCESO, " + "APELLIDO_PATERNO, "
						+ "APELLIDO_MATERNO, " + "AREA, " + "FECHAMODIFICACION " + "FROM Usuario");

		List<Object[]> select = (List<Object[]>) query.getResultList();

		if (select == null || select.isEmpty()) {
			map.put("mensaje", "No se encontraron datos de usuarios registrados");
			map.put("usuarios", null);
			map.put("codigo", errorProperties.getNoData());

			return map;
		} else {
			for (Object queryResponse : select) {
				Usuario usuario = new Usuario();
				Object[] row = (Object[]) queryResponse;

				String[] rowString = new String[row.length];

				logger.info("*** row.length *** - " + row.length);

				for (int i = 0; i < row.length; i++) {
					logger.info("*** row[i] *** - " + row[i]);
					if (row[i] != null) {
						rowString[i] = row[i].toString();
					} else {
						rowString[i] = null;
					}
				}

				DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");

				usuario.login = rowString[0].toString();
				usuario.nombre = rowString[1].toString();
				usuario.cliente = Float.parseFloat(rowString[2].toString());
				if (rowString[3].toString() != null && rowString[3].toString() != "") {
					usuario.email = rowString[3].toString();
				}
				try {
					usuario.fechaAlta = dateFormat
							.format(new SimpleDateFormat("dd/MM/yyyy").parse(rowString[4].toString()));
				} catch (ParseException e) {
				}
				if (rowString[5] != null) {
					try {
						usuario.fechaBaja = dateFormat
								.format(new SimpleDateFormat("dd/MM/yyyy").parse(rowString[5].toString()));
					} catch (ParseException e) {
					}
				}
				usuario.status = rowString[6].toString().charAt(0);
				usuario.intentos = Float.parseFloat(rowString[7].toString());
				if (rowString[8] != null) {
					try {
						usuario.fechaRevocados = dateFormat
								.format(new SimpleDateFormat("dd/MM/yyyy").parse(rowString[8].toString()));
					} catch (ParseException e) {
					}
				}
				if (rowString[9] != null) {
					try {
						usuario.fechaVigencia = dateFormat
								.format(new SimpleDateFormat("dd/MM/yyyy").parse(rowString[9].toString()));
					} catch (ParseException e) {
					}
				}
				if (rowString[10] != null && rowString[10].toString() != "") {
					usuario.noAcceso = Integer.parseInt(rowString[10].toString());
				}
				if (rowString[11] != null && rowString[11].toString() != "") {
					usuario.apellidoPaterno = rowString[11].toString();
				}
				if (rowString[12] != null && rowString[12].toString() != "") {
					usuario.apellidoMaterno = rowString[12].toString();
				}

				if (rowString[13] != null && rowString[13].toString() != "") {
					usuario.area = Long.parseLong(rowString[13].toString());
				}

				try {
					usuario.fechaModificacion = dateFormat
							.format(new SimpleDateFormat("dd/MM/yyyy").parse(rowString[14].toString()));
				} catch (ParseException e) {
				}

				usuarios.add(usuario);
			}

			map.put("mensaje", "Se han obtenido los datos de los usuarios con éxito");
			map.put("usuarios", usuarios);
			map.put("codigo", errorProperties.getOk());

			return map;
		}
	}

	@Override
	public Map<String, Object> getUsuario(String login) {
		Map<String, Object> map = new HashMap<>();
		Usuario usuario = new Usuario();

		Query query = entityManager.createNativeQuery("SELECT LOGIN, " + "NOMBRE, " + "CLIENTE, " + "EMAIL, "
				+ "FECHAALTA, " + "FECHABAJA, " + "STATUS, " + "INTENTOS, " + "FECHAREVOCADO, " + "FECHA_VIGENCIA, "
				+ "NO_ACCESO, " + "APELLIDO_PATERNO, " + "APELLIDO_MATERNO, " + "AREA, " + "FECHAMODIFICACION "
				+ "FROM Usuario " + "WHERE LOGIN = ?1");
		query.setParameter(1, login);
		Object queryResponse = query.getResultList().get(0);

		if (query.getResultList() == null || query.getResultList().isEmpty()) {
			map.put("mensaje", "No se encontraron datos del usuario que se intentó consultar");
			map.put("usuario", null);
			map.put("codigo", errorProperties.getNoData());

			return map;
		} else {
			Object[] row = (Object[]) queryResponse;

			String[] rowString = new String[row.length];

			logger.info("*** row.length *** - " + row.length);

			for (int i = 0; i < row.length; i++) {
				logger.info("*** row[i] *** - " + row[i]);
				if (row[i] != null) {
					rowString[i] = row[i].toString();
				} else {
					rowString[i] = null;
				}
			}

			DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");

			usuario.login = rowString[0].toString();
			usuario.nombre = rowString[1].toString();
			usuario.cliente = Float.parseFloat(rowString[2].toString());
			if (rowString[3].toString() != null && rowString[3].toString() != "") {
				usuario.email = rowString[3].toString();
			}
			try {
				usuario.fechaAlta = dateFormat
						.format(new SimpleDateFormat("dd/MM/yyyy").parse(rowString[4].toString()));
			} catch (ParseException e) {
			}
			if (rowString[5] != null) {
				try {
					usuario.fechaBaja = dateFormat
							.format(new SimpleDateFormat("dd/MM/yyyy").parse(rowString[5].toString()));
				} catch (ParseException e) {
				}
			}
			usuario.status = rowString[6].toString().charAt(0);
			usuario.intentos = Float.parseFloat(rowString[7].toString());
			if (rowString[8] != null) {
				try {
					usuario.fechaRevocados = dateFormat
							.format(new SimpleDateFormat("dd/MM/yyyy").parse(rowString[8].toString()));
				} catch (ParseException e) {
				}
			}
			if (rowString[9] != null) {
				try {
					usuario.fechaVigencia = dateFormat
							.format(new SimpleDateFormat("dd/MM/yyyy").parse(rowString[9].toString()));
				} catch (ParseException e) {
				}
			}
			if (rowString[10] != null && rowString[10].toString() != "") {
				usuario.noAcceso = Integer.parseInt(rowString[10].toString());
			}
			if (rowString[11] != null && rowString[11].toString() != "") {
				usuario.apellidoPaterno = rowString[11].toString();
			}
			if (rowString[12] != null && rowString[12].toString() != "") {
				usuario.apellidoMaterno = rowString[12].toString();
			}

			if (rowString[13] != null && rowString[13].toString() != "") {
				usuario.area = Long.parseLong(rowString[13].toString());
			}

			try {
				usuario.fechaModificacion = dateFormat
						.format(new SimpleDateFormat("dd/MM/yyyy").parse(rowString[14].toString()));
			} catch (ParseException e) {
			}

			map.put("mensaje", "Se han obtenido los datos de los usuarios con éxito");
			map.put("usuario", usuario);
			map.put("codigo", errorProperties.getOk());

			return map;
		}
	}

	@Override
	public Map<String, Object> login(String login, String password) {
		Map<String, Object> map = new HashMap<>();
		Query query = entityManager.createNativeQuery(
				"SELECT LOGIN FROM usuario " + "WHERE login = '" + login + "' AND password = '" + Base64.getEncoder().encodeToString(password.getBytes(StandardCharsets.UTF_8)) + "'");

		List<Object[]> result = (List<Object[]>) query.getResultList();
		if (result == null || result.isEmpty()) {
			map.put("mensaje", "No se encontraron datos del usuario que se intentó consultar");
			map.put("usuario", null);
			map.put("codigo", errorProperties.getNoData());

			return map;
		} else {
			logger.info("*** El usuario fue verificado éxitosamente ***");
			map.put("mensaje", "El usuario fue verificado éxitosamente");
			map.put("usuario", getUsuario(login).get("usuario"));
			map.put("codigo", errorProperties.getOk());

			return map;
		}
	}

	@Override
	public Map<String, Object> createUsuario(Usuario usuario) {
		Map<String, Object> map = new HashMap<>();

		String sql = "INSERT INTO Usuario (";
		sql += "LOGIN, ";
		sql += "PASSWORD, ";
		sql += "NOMBRE, ";
		sql += "CLIENTE, ";
		if (usuario.email != null && usuario.email != "")
			sql += "EMAIL, ";
		sql += "INTENTOS, ";
		if (usuario.apellidoPaterno != null && usuario.apellidoPaterno != "")
			sql += "APELLIDO_PATERNO, ";
		if (usuario.apellidoMaterno != null && usuario.apellidoMaterno != "")
			sql += "APELLIDO_MATERNO, ";
		if (usuario.area != null)
			sql += "AREA, ";
		sql = sql.substring(0, sql.length() - 2);
		sql += ") VALUES (?1, ?2, ?3, ?4, ";
		if (usuario.email != null && usuario.email != "")
			sql += "?5, ";
		sql += "?6, ";
		if (usuario.apellidoPaterno != null && usuario.apellidoPaterno != "")
			sql += "?7, ";
		if (usuario.apellidoMaterno != null && usuario.apellidoMaterno != "")
			sql += "?8, ";
		if (usuario.area != null)
			sql += "?9, ";
		sql = sql.substring(0, sql.length() - 2);
		sql += ")";

		Query query = entityManager.createNativeQuery(sql);

		logger.info("*** sql *** - " + sql);

		query.setParameter(1, usuario.login);
		query.setParameter(2, Base64.getEncoder().encodeToString(usuario.password.getBytes(StandardCharsets.UTF_8)));
		query.setParameter(3, usuario.nombre);
		query.setParameter(4, usuario.cliente);
		if (usuario.email != null && usuario.email != "")
			query.setParameter(5, usuario.email);
		query.setParameter(6, usuario.intentos);
		if (usuario.apellidoPaterno != null && usuario.apellidoPaterno != "")
			query.setParameter(7, usuario.apellidoPaterno);
		if (usuario.apellidoMaterno != null && usuario.apellidoMaterno != "")
			query.setParameter(8, usuario.apellidoMaterno);
		if (usuario.area != null)
			query.setParameter(9, usuario.area);

		if (query.executeUpdate() > 0) {

			map.put("mensaje", "Se ha creado el usuario con éxito");
			map.put("usuario", null);
			map.put("codigo", errorProperties.getOk());

			return map;
		} else {
			map.put("mensaje", "Ha habido un error en la base de datos al momento de crear el usuario");
			map.put("usuario", null);
			map.put("codigo", errorProperties.getErrorDb());

			return map;
		}
	}

	@Override
	public Map<String, Object> modifyUsuario(Usuario usuario) {
		Map<String, Object> map = new HashMap<>();
		String dateS = (new SimpleDateFormat("dd/MM/yyyy hh:mm:ss")).format(new Date());

		Date date;
		try {
			date = (new SimpleDateFormat("dd/MM/yyyy hh:mm:ss")).parse(dateS);
		} catch (ParseException e1) {
			date = null;
		}

		if (date != null) {

			String sql = "UPDATE Usuario SET ";
			Integer count = 1;
			if (usuario.password != null && usuario.password != "") {
				sql += "PASSWORD = ?" + count + ", ";
				count++;
			}
			if (usuario.nombre != null && usuario.nombre != "") {
				sql += "NOMBRE = ?" + count + ", ";
				count++;
			}
			if (usuario.cliente != null) {
				sql += "CLIENTE = ?" + count + ", ";
				count++;
			}
			if (usuario.email != null && usuario.email != "") {
				sql += "EMAIL = ?" + count + ", ";
				count++;
			}
			if (usuario.fechaBaja != null && usuario.fechaBaja != "") {
				sql += "FECHABAJA = ?" + count + ", ";
				count++;
			}
			if (usuario.status != null) {
				sql += "STATUS = ?" + count + ", ";
				count++;
			}
			if (usuario.intentos != null) {
				sql += "INTENTOS = ?" + count + ", ";
				count++;
			}
			if (usuario.fechaRevocados != null && usuario.fechaRevocados != "") {
				sql += "FECHAREVOCADO = ?" + count + ", ";
				count++;
			}
			if (usuario.fechaVigencia != null && usuario.fechaVigencia != "") {
				sql += "FECHA_VIGENCIA = ?" + count + ", ";
				count++;
			}
			if (usuario.noAcceso != null) {
				sql += "NO_ACCESO = ?" + count + ", ";
				count++;
			}
			if (usuario.apellidoPaterno != null && usuario.apellidoPaterno != "") {
				sql += "APELLIDO_PATERNO = ?" + count + ", ";
				count++;
			}
			if (usuario.apellidoMaterno != null && usuario.apellidoMaterno != "") {
				sql += "APELLIDO_MATERNO = ?" + count + ", ";
				count++;
			}
			if (usuario.area != null) {
				sql += "AREA = ?13, ";
				count++;
			}
			sql += "FECHAMODIFICACION = ?" + count + " " + "WHERE login = '" + usuario.login + "'";

			Query query = entityManager.createNativeQuery(sql);
			count = 1;

			if (usuario.password != null && usuario.apellidoPaterno != "") {
				query.setParameter(count,
						Base64.getEncoder().encodeToString(usuario.password.getBytes(StandardCharsets.UTF_8)));
				count++;
			}
			if (usuario.nombre != null && usuario.apellidoPaterno != "") {
				query.setParameter(count, usuario.nombre);
				count++;
			}
			if (usuario.cliente != null) {
				query.setParameter(count, usuario.cliente);
				count++;
			}
			if (usuario.email != null && usuario.email != "") {
				query.setParameter(count, usuario.email);
				count++;
			}
			if (usuario.fechaBaja != null && usuario.fechaBaja != "") {
				try {
					query.setParameter(count, (new SimpleDateFormat("dd/MM/yyyy hh:mm:ss")).parse(usuario.fechaBaja));
				} catch (ParseException e) {}
				count++;
			}
			if (usuario.status != null) {
				query.setParameter(count, usuario.status);
				count++;
			}
			if (usuario.intentos != null) {
				query.setParameter(count, usuario.intentos);
				count++;
			}
			if (usuario.fechaRevocados != null && usuario.fechaRevocados != "") {
				try {
					query.setParameter(count, (new SimpleDateFormat("dd/MM/yyyy hh:mm:ss")).parse(usuario.fechaRevocados));
				} catch (ParseException e) {}
				count++;
			}
			if (usuario.fechaVigencia != null && usuario.fechaVigencia != "") {
				try {
					query.setParameter(count, (new SimpleDateFormat("dd/MM/yyyy hh:mm:ss")).parse(usuario.fechaVigencia));
				} catch (ParseException e) {}
				count++;
			}
			if (usuario.noAcceso != null) {
				query.setParameter(count, usuario.noAcceso);
				count++;
			}
			if (usuario.apellidoPaterno != null && usuario.apellidoPaterno != "") {
				query.setParameter(count, usuario.apellidoPaterno);
				count++;
			}
			if (usuario.apellidoMaterno != null && usuario.apellidoMaterno != "") {
				query.setParameter(count, usuario.apellidoMaterno);
				count++;
			}
			if (usuario.area != null) {
				query.setParameter(count, usuario.area);
				count++;
			}
			query.setParameter(count, date);

			try {
				query.executeUpdate();

				map.put("mensaje", "Se ha modificado el usuario con éxito");
				map.put("usuario", null);
				map.put("codigo", errorProperties.getOk());

				return map;
			} catch (Exception e) {
				map.put("mensaje", "Ha habido un error en la base de datos al momento de crear el usuario");
				map.put("usuario", null);
				map.put("codigo", errorProperties.getErrorDb());

				return map;
			}
		} else {
			map.put("mensaje", "Ha habido un error al recuperar la fecha actual al momento de crear el usuario");
			map.put("usuario", null);
			map.put("codigo", errorProperties.getErrorDate());

			return map;
		}

	}

}
