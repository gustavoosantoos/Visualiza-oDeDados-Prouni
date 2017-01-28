package com.prouni.datavisualization.bean;

import java.time.LocalDate;

public class BeneficiadoBean {
	
	private int id_beneficiado;
	private String nome_ies;
	private String tipo_bolsa;
	private String modalidade;
	private String nome_curso;
	private String turno;
	private String sexo;
	private String raca;
	private LocalDate dt_nascimento;
	private boolean pcd;
	
	public int getId_beneficiado() {
		return id_beneficiado;
	}
	public String getNome_ies() {
		return nome_ies;
	}
	public void setNome_ies(String nome_ies) {
		this.nome_ies = nome_ies;
	}
	public String getTipo_bolsa() {
		return tipo_bolsa;
	}
	public void setTipo_bolsa(String tipo_bolsa) {
		this.tipo_bolsa = tipo_bolsa;
	}
	public String getModalidade() {
		return modalidade;
	}
	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}
	public String getNome_curso() {
		return nome_curso;
	}
	public void setNome_curso(String nome_curso) {
		this.nome_curso = nome_curso;
	}
	public String getTurno() {
		return turno;
	}
	public void setTurno(String turno) {
		this.turno = turno;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getRaca() {
		return raca;
	}
	public void setRaca(String raca) {
		this.raca = raca;
	}
	public LocalDate getDt_nascimento() {
		return dt_nascimento;
	}
	public void setDt_nascimento(LocalDate dt_nascimento) {
		this.dt_nascimento = dt_nascimento;
	}
	public boolean isPcd() {
		return pcd;
	}
	public void setPcd(boolean pcd) {
		this.pcd = pcd;
	}
	
}
