package com.example.springboot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:error.properties")
@ConfigurationProperties
public class ErrorProperties {

	private String ok;
	private String errorDb;
	private String errorEmail;
	private String errorSftp;
	private String errorInternal;
	private String errorAccesar;
	private String errorCast;
	private String errorSql;
	private String errorConnection;
	private String timeOut;
	private String errorQuery;
	private String errorSave;
	private String errorUpdate;
	private String errorExists;
	private String errorDelete;
	private String noData;
	private String errorDataIncomplet;
	private String errorOpcion;
	private String errorQuantityExceeded;
	private String errorNoExists;
	private String errorDate;

	public String getOk() {
		return ok;
	}

	public void setOk(String ok) {
		this.ok = ok;
	}

	public String getErrorDb() {
		return errorDb;
	}

	public void setErrorDb(String errorDb) {
		this.errorDb = errorDb;
	}

	public String getErrorEmail() {
		return errorEmail;
	}

	public void setErrorEmail(String errorEmail) {
		this.errorEmail = errorEmail;
	}

	public String getErrorSftp() {
		return errorSftp;
	}

	public void setErrorSftp(String errorSftp) {
		this.errorSftp = errorSftp;
	}

	public String getErrorInternal() {
		return errorInternal;
	}

	public void setErrorInternal(String errorInternal) {
		this.errorInternal = errorInternal;
	}

	public String getErrorAccesar() {
		return errorAccesar;
	}

	public void setErrorAccesar(String errorAccesar) {
		this.errorAccesar = errorAccesar;
	}

	public String getErrorCast() {
		return errorCast;
	}

	public void setErrorCast(String errorCast) {
		this.errorCast = errorCast;
	}

	public String getErrorSql() {
		return errorSql;
	}

	public void setErrorSql(String errorSql) {
		this.errorSql = errorSql;
	}

	public String getErrorConnection() {
		return errorConnection;
	}

	public void setErrorConnection(String errorConnection) {
		this.errorConnection = errorConnection;
	}

	public String getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(String timeOut) {
		this.timeOut = timeOut;
	}

	public String getErrorQuery() {
		return errorQuery;
	}

	public void setErrorQuery(String errorQuery) {
		this.errorQuery = errorQuery;
	}

	public String getErrorSave() {
		return errorSave;
	}

	public void setErrorSave(String errorSave) {
		this.errorSave = errorSave;
	}

	public String getErrorUpdate() {
		return errorUpdate;
	}

	public void setErrorUpdate(String errorUpdate) {
		this.errorUpdate = errorUpdate;
	}

	public String getErrorDelete() {
		return errorDelete;
	}

	public void setErrorDelete(String errorDelete) {
		this.errorDelete = errorDelete;
	}

	public String getNoData() {
		return noData;
	}

	public void setNoData(String noData) {
		this.noData = noData;
	}

	public String getErrorDataIncomplet() {
		return errorDataIncomplet;
	}

	public void setErrorDataIncomplet(String errorDataIncomplet) {
		this.errorDataIncomplet = errorDataIncomplet;
	}

	public String getErrorOpcion() {
		return errorOpcion;
	}

	public void setErrorOpcion(String errorOpcion) {
		this.errorOpcion = errorOpcion;
	}

	public String getErrorQuantityExceeded() {
		return errorQuantityExceeded;
	}

	public void setErrorQuantityExceeded(String errorQuantityExceeded) {
		this.errorQuantityExceeded = errorQuantityExceeded;
	}

	public String getErrorExists() {
		return errorExists;
	}

	public void setErrorExists(String errorExists) {
		this.errorExists = errorExists;
	}

	public String getErrorNoExists() {
		return errorNoExists;
	}

	public void setErrorNoExists(String errorNoExists) {
		this.errorNoExists = errorNoExists;
	}

	public String getErrorDate() {
		return errorDate;
	}

	public void setErrorDate(String errorDate) {
		this.errorDate = errorDate;
	}

	
}
