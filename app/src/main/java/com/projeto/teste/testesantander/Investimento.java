package com.projeto.teste.testesantander;

import org.json.JSONObject;

public class Investimento /*implements Serializable*/ {
	
	private JSONObject telaJson;
	private String titulo;
	private String nomeFundo;
	private String oQueE;
	private String definicao;
	private String tituloRisco;
	private int risco;
	private String tituloInfo;
    private JSONObject moreInfoJson;
	private JSONObject monthJson;
	private JSONObject yearJson;
	private JSONObject v12monthsJson;

	public JSONObject getTelaJson() { return telaJson; }
	public void setTelaJson(JSONObject telaJson) {
		this.telaJson = telaJson;
	}

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getNomeFundo() { return nomeFundo; }
	public void setNomeFundo(String nomeFundo) {
		this.nomeFundo = nomeFundo;
	}

	public String getOQueE() {
		return oQueE;
	}
	public void setOQueE(String oQueE) {
		this.oQueE = oQueE;
	}

	public String getDefinicao() {
		return definicao;
	}
	public void setDefinicao(String definicao) {
		this.definicao = definicao;
	}

	public String gettituloRisco() { return tituloRisco; }
	public void settituloRisco(String tituloRisco) {
		this.tituloRisco = tituloRisco;
	}

	public int getrisco() { return risco; }
	public void setrisco(int risco) {
		this.risco = risco;
	}

	public String gettituloInfo() {	return tituloInfo; }
	public void settituloInfo(String tituloInfo) {
		this.tituloInfo = tituloInfo;
	}

	public JSONObject getmoreInfoJson() { return moreInfoJson; }
	public void setmoreInfoJson(JSONObject moreInfoJson) {
		this.moreInfoJson = moreInfoJson;
	}

	public void setmonthJson(JSONObject monthJson) {
		this.monthJson = monthJson;
	}
	public JSONObject getmonthJson() { return monthJson; }

	public void setyearJson(JSONObject yearJson) {
		this.yearJson = yearJson;
	}
	public JSONObject getyearJson() { return yearJson; }

	public void set12monthsJson(JSONObject v12monthsJson) {
		this.v12monthsJson = v12monthsJson;
	}
	public JSONObject get12monthsJson() { return v12monthsJson; }

	@Override
	public String toString() {return  titulo;
	}
}

