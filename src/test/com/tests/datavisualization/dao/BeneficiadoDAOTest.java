package com.tests.datavisualization.dao;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.prouni.datavisualization.dao.BeneficiadoDAO;

public class BeneficiadoDAOTest {
	
	private BeneficiadoDAO dao;
	
	@Before
	public void inicializaDAO(){
		this.dao = new BeneficiadoDAO();
	}
	
	@Test
	public void getTotalBeneficiadosTest() {
		int resultado = dao.getTotalBeneficiados();
		assertEquals(4366, resultado);
	}
	
	@Test
	public void getTotalPorUniversidadeTest(){
		Map<String, Integer> totalPorUniversidade = dao.getTotalPorCampo("nome_ies");
		System.out.println(totalPorUniversidade);
		assertNotEquals(true, totalPorUniversidade.isEmpty());
	}
	
	@Test 
	public void getTotalPorCursoTest(){
		Map<String, Integer> totalPorCurso = dao.getTotalPorCampo("nome_curso");
		System.out.println(totalPorCurso);
		assertNotEquals(true, totalPorCurso.isEmpty());
	}
	
	@Test
	public void getTotalPorTipoBolsaTest(){
		Map<String, Integer> totalPorTipoBolsa = dao.getTotalPorCampo("tipo_bolsa");
		System.out.println(totalPorTipoBolsa);
		assertNotEquals(true, totalPorTipoBolsa.isEmpty());
	}
	
	@Test
	public void getTotalPorSexo(){
		Map<String, Integer> totalPorSexo = dao.getTotalPorCampo("sexo");
		System.out.println(totalPorSexo);
		assertNotEquals(true, totalPorSexo.isEmpty());
	}
	
	@Test
	public void getTotalPorSexoECurso(){
		Map<String, Integer> totalPorSexoECurso = dao.getTotalPorCampos("sexo", "nome_curso", "Sistemas de Informação");
		System.out.println(totalPorSexoECurso);
		assertNotEquals(null, totalPorSexoECurso);
	} 
	
	@Test
	public void getTotalPorUniversidadeETurno(){
		Map<String,Integer> totalPorUniversidadeETurno = dao.getTotalPorCampos("turno_curso", "nome_ies", "Universidade Positivo");
		System.out.println(totalPorUniversidadeETurno);
		assertNotEquals(null, totalPorUniversidadeETurno);
	}
	
	@Test 
	public void getTotalPorCursoEspecificoTest(){
		int count = dao.getTotalPorCursoEspecifico("Sistemas de Informação");
		assertEquals(11, count);
	}
	
	@Test
	public void getTotalPorCursoERaca(){
		Map<String, Integer> totalPorRacaECurso = dao.getTotalPorCampos("raca_beneficiado", "nome_curso", "Administração");
		System.out.println(totalPorRacaECurso);
		assertNotEquals(null, totalPorRacaECurso);
	}

}
