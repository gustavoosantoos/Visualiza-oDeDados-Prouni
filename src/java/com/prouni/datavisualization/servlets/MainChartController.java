package com.prouni.datavisualization.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
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
		
		ArrayList<List<DataRowBean>> dados = new ArrayList<>();
		
		universidade = request.getParameter("universidade");
		if (universidade.equals("null")) universidade = null;
		
		List<DataRowBean> universidades = getListDataRowPorCampo("nome_ies");
		universidades = universidades.stream().sorted(Comparator.comparingInt(DataRowBean::getValor).reversed())
				.collect(Collectors.toList()).subList(0, 10);
		dados.add(universidades);
		
		ArrayList<DataRowBean> qtdPorSexoList = getListDataRowPorCampo("sexo");
		qtdPorSexoList.forEach(u -> u.setCampo(u.getCampo().equals("M")? "Homens" : "Mulheres"));
		dados.add(qtdPorSexoList);
		
		dados.add(getListDataRowPorCampo("raca_beneficiado"));
		dados.add(getListDataRowPorCampo("turno_curso"));
		dados.add(getListDataRowPorCampo("tipo_bolsa"));
		
		String json = new Gson().toJson(dados);
		System.out.println(json);
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

	}
	
	private List<DataRowBean> getListUniversidades() {
		HashMap<String, Integer> map = new BeneficiadoDAO().getTotalPorCampo("nome_ies");
		ArrayList<DataRowBean> listaValores;
		return null;
	}

	private ArrayList<DataRowBean> getListDataRowPorCampo(String campo){
		HashMap<String, Integer> map;
			if (campo.equals("nome_ies") || universidade == null)
				map = new BeneficiadoDAO().getTotalPorCampo(campo);
			else
				map = new BeneficiadoDAO().getTotalPorCampos(campo, "nome_ies", universidade);

		ArrayList<DataRowBean> listaValores = new ArrayList<>();
		for(Entry<String, Integer> entry: map.entrySet()){
			DataRowBean bean = new DataRowBean();
			bean.setCampo(entry.getKey());
			bean.setValor(entry.getValue());
			
			listaValores.add(bean);
		}
		
		return listaValores;
	}
	
}
