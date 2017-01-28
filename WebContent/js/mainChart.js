// Load the Visualization API and the corechart package.
var dadosMainChart;
	var dadosMainSexoChart;
	var dadosMainRacaChart;
	var dadosMainTurnoCursoChart;
	var dadosMainTipoBolsaChart;

$(document).ready(function load(){

	$(window).resize(function(){
		doMainChart(dadosMainChart, dadosMainSexoChart, dadosMainRacaChart, dadosMainTurnoCursoChart, dadosMainTipoBolsaChart);
		console.log('Resize handler working');
	});
	
	$.ajax({
		method: "get",
		url: "MainChartController",
		cache: false,
        async: false,
        data: {universidade: "null"}
	}).done(function(responseJson){
		setData(responseJson);
		$("#labelUniversidade").text("GERAL");
	});
	
	
});
function setData(responseJson){
	dadosMainChart = new Array(responseJson[0].length);
	for(var i = 0; i < responseJson[0].length; i++){
		dadosMainChart[i] = new Array(2);
		dadosMainChart[i][0] = responseJson[0][i].campo;
		dadosMainChart[i][1] = responseJson[0][i].valor;
	}
	
	dadosMainSexoChart = new Array(responseJson[1].length + 1);
	dadosMainRacaChart = new Array(responseJson[2].length + 1);
	dadosMainTurnoCursoChart = new Array(responseJson[3].length + 1);
	dadosMainTipoBolsaChart = new Array(responseJson[4].length + 1);
	
	for(var i = 0; i < responseJson[1].length; i++){
		dadosMainSexoChart[i] = new Array(2);
		dadosMainSexoChart[i][0] = responseJson[1][i].campo;
		dadosMainSexoChart[i][1] = responseJson[1][i].valor;
		console.log(responseJson[1][i].campo);
		console.log(responseJson[1][i].valor);
	}
	var sexoMeta = dadosMainSexoChart.length - 1;
	dadosMainSexoChart[sexoMeta] = new Array(3);
	dadosMainSexoChart[sexoMeta] = ["Total de bolsas por sexo.", "mainChartSexo"];

	
	for(var i = 0; i < responseJson[2].length; i++){
		dadosMainRacaChart[i] = new Array(2);
		dadosMainRacaChart[i][0] = responseJson[2][i].campo;
		dadosMainRacaChart[i][1] = responseJson[2][i].valor;
	}
	var racaMeta = dadosMainRacaChart.length - 1;
	dadosMainRacaChart[racaMeta] = new Array(3);
	dadosMainRacaChart[racaMeta] = ["Total de bolsas por raca.", "mainChartRaca", "Raça"];

	
	for(var i = 0; i < responseJson[3].length; i++){
		dadosMainTurnoCursoChart[i] = new Array(2);
		dadosMainTurnoCursoChart[i][0] = responseJson[3][i].campo;
		dadosMainTurnoCursoChart[i][1] = responseJson[3][i].valor;
	}
	var turnoCursoMeta = dadosMainTurnoCursoChart.length -1;
	dadosMainTurnoCursoChart[turnoCursoMeta] = new Array(3);
	dadosMainTurnoCursoChart[turnoCursoMeta] = ["Total de bolsas por turno", "mainChartTurnoCurso", "Turno"];

	
	for(var i = 0; i < responseJson[4].length; i++){
		dadosMainTipoBolsaChart[i] = new Array(2);
		dadosMainTipoBolsaChart[i][0] = responseJson[4][i].campo;
		dadosMainTipoBolsaChart[i][1] = responseJson[4][i].valor;
	}
	var tipoBolsaMeta = dadosMainTipoBolsaChart.length - 1;
	dadosMainTipoBolsaChart[tipoBolsaMeta] = new Array(3);
	dadosMainTipoBolsaChart[tipoBolsaMeta] = ["Total de bolsas por tipo de bolsa.", "mainChartTipoBolsa", "Tipo"];

	doMainChart(dadosMainChart, [dadosMainSexoChart, dadosMainRacaChart, dadosMainTurnoCursoChart, dadosMainTipoBolsaChart]);
}

function doMainChart(dados, dadosPieChart){
	google.charts.load('current', {
		'packages' : [ 'bar', 'corechart' ]
	});

	google.charts.setOnLoadCallback(drawChart);

	function drawChart() {
		//Gera gráfico principal de bolsas por universidade
		var data = new google.visualization.DataTable();
		data.addColumn('string', 'Universidade');
		data.addColumn('number', 'Bolsas');
		data.addRows(dados);

		var optionsMain = {
			title : 'Total de bolsas por universidade',
			chartArea: {width: '40%'},
			legend: {position: "none"},
			height: 350,
			chartArea:{
				width: '100%',
				height: '100%'
			},
			bars: 'horizontal'
		};

		var chart = new google.charts.Bar(document
				.getElementById('mainChart'));
		chart.draw(data, optionsMain);
		
		google.visualization.events.addListener(chart, 'select', function(){
			var selection = chart.getSelection();
			var linha = selection[0].row;
			var coluna = selection[0].column;
			var v_universidade = dados[linha][coluna - 1];
			var confereUniversidade = $("#labelUniversidade").text();
			if (v_universidade != confereUniversidade) {
				$.ajax({
					method: "get",
					url: "MainChartController",
					cache: false,
					async: false,
					data: {universidade: v_universidade}
				}).done(function (responseJson){
					setData(responseJson);
					$("#labelUniversidade").text(v_universidade);
					
				});
			}else{
				confirm('Você já está na visualização desta universidade!');
			}
		});
		

		//Gera gráficos de torta por dados
		for (var i = 0; i < dadosPieChart.length; i++) {
			var dadosAtuais = dadosPieChart[i];
			var options = {
					title: dadosAtuais[dadosAtuais.length -1][0],
					width: 400,
					height: 300,
					chartArea:{
						width: '90%',
						height:'80%'
					},
					colors: ['#FF3333', '#3333FF', '#66FF66', '#f3b49f', '#006633']
			};
			
			var dadosRows = new Array(dadosAtuais.length - 1);
			for(var a = 0; a < dadosAtuais.length - 1; a++){
				dadosRows[a] = dadosAtuais[a];
			}
			
			var data = new google.visualization.DataTable();
	    	data.addColumn('string', dadosAtuais[dadosAtuais.length - 1][2]);
	    	data.addColumn('number', 'Bolsas');
	    	data.addRows(dadosRows);
	    	
	    	var chartPie = new google.visualization.PieChart(document.getElementById(dadosAtuais[dadosAtuais.length -1][1]));
	    	chartPie.draw(data, options);
	    	
		};
	}
}

