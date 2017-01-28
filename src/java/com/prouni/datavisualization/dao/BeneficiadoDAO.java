package com.prouni.datavisualization.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import com.prouni.datavisualization.factory.ConnectionFactory;

public class BeneficiadoDAO {
	
	private String query;
	
	public int getTotalBeneficiados(){
		query = "select count(ID_BENEFICIADO) from beneficiados";
		return getCountByQuery(query);
	}
	
	public int getTotalPorCursoEspecifico(String curso){
		query = "select count(id_beneficiado) from beneficiados where nome_curso = '" + curso + "'";
		return getCountByQuery(query);
	}
	
	public HashMap<String, Integer> getTotalPorCampo(String campo){
		query = "select "+campo+", count("+campo+") from beneficiados group by "+campo;
		return  getMapByQuery(query);
	}
	
	public HashMap<String, Integer> getTotalPorCampos(String campo, String campoWhere, String valueWhere){
		query = "select "+campo+", count("+campo+") from beneficiados where "+campoWhere+" = '"+valueWhere+"' group by "+campo;
		return getMapByQuery(query);
	}
	
	private int getCountByQuery(String query) {
		int count = 0;
		try(Connection conn = ConnectionFactory.getConnection(); 
				Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query)){
			if(rs.next())
				count = rs.getInt(1);
		} catch (Exception e) {
			e.getMessage();
		}
		
		return count;
	}

	private HashMap<String, Integer> getMapByQuery(String query) {
		HashMap<String, Integer> map = new HashMap<>();
		try(Connection conn = ConnectionFactory.getConnection();
				Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query)){
			while(rs.next()){
				map.put(rs.getString(1), rs.getInt(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
}
