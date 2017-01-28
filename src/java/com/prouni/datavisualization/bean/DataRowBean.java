package com.prouni.datavisualization.bean;

public class DataRowBean {
	private String campo;
	private int valor;
	
	public DataRowBean(){
		super();
	}
	
	public DataRowBean(String campo, int valor){
		this.campo = campo;
		this.valor = valor;
	}
	
	public String getCampo() {
		return campo;
	}
	public void setCampo(String nome) {
		this.campo = nome;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int quantidade) {
		this.valor = quantidade;
	}
	
	
}
