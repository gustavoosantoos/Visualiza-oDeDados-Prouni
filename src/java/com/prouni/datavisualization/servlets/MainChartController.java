package com.prouni.datavisualization.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.prouni.datavisualization.bean.DataRowBean;
import com.prouni.datavisualization.dao.BeneficiadoDAO;

/**
 * Servlet implementation class MainChartController
 */
@WebServlet("/MainChartController")
public class MainChartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String universidade;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//ArrayList de Lists que será o Json de retorno
		ArrayList<List<DataRowBean>> dados = new ArrayList<>();
		
		/*Parametro que caso seja recebido, os dados deixam de ser gerais
		e se tornam específicos por universidade*/
		universidade = request.getParameter("universidade");
		
		//Lista de DataRowBean com o total de bolsas por universidade (10 com mais bolsas)
		List<DataRowBean> universidades = getListDataRowPorCampo("nome_ies");
		universidades = universidades.stream().sorted(Comparator.comparingInt(DataRowBean::getValor).reversed())
				.collect(Collectors.toList()).subList(0, 10);
		dados.add(universidades);
		
		//Lista de DataRowBean com o total de bolsas por sexo (Tratativa de dados)
		ArrayList<DataRowBean> qtdPorSexoList = getListDataRowPorCampo("sexo");
		qtdPorSexoList.forEach(u -> u.setCampo(u.getCampo().equals("M")? "Homens" : "Mulheres"));
		dados.add(qtdPorSexoList);
		
		//Demais listas de dados
		dados.add(getListDataRowPorCampo("raca_beneficiado"));
		dados.add(getListDataRowPorCampo("turno_curso"));
		dados.add(getListDataRowPorCampo("tipo_bolsa"));
		
		//Uso da biblioteca Gson para transformar List em Json
		String json = new Gson().toJson(dados);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	//Recebe um campo na tabela de dados, o DAO retorna um map de informações e o método tranforma em list de DataRowBean
	private ArrayList<DataRowBean> getListDataRowPorCampo(String campo){
		HashMap<String, Integer> map;
			if (universidade == null || campo.equals("nome_ies"))
				map = new BeneficiadoDAO().getTotalPorCampo(campo);
			else
				map = new BeneficiadoDAO().getTotalPorCampos(campo, "nome_ies", universidade);

		ArrayList<DataRowBean> listaValores = new ArrayList<>();
		map.forEach((key, value) -> listaValores.add(new DataRowBean(key, value)));
//		for(Entry<String, Integer> entry : map.entrySet()){
//			listaValores.add(new DataRowBean(entry.getKey(), entry.getValue()));
//		}
		
		
		return listaValores;
	}
	
}
