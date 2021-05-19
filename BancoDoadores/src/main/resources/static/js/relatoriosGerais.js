$(document).ready(function() {
    $('canvas').after('<div class="spinner"></div>');
    
    $.get('/candidatos/grafico-candidatos-por-estado', function (chartData) {
        var ctx = document.getElementById('graficoDoadoresPorEstado').getContext('2d');
        var chart = new Chart(ctx, chartData);
        chart.options.scales.yAxes[0].ticks.beginAtZero = true;
        chart.options.onClick = function (evt, item) {
        };
        chart.options.legend.display = false;
        chart.update();
    });
    
	var candidatosPorEstado = new Map();
	$.get('/candidatos/por-estado', function(response) {
		var maximo = 0;
		var minimo;
		$(response).each(function() {
			candidatosPorEstado.set(this[0], this[1]);
			if (this[1] > maximo) {
				maximo = this[1];
			}
			if (minimo == null) {
				minimo = this[1];
			} else {
				if (this[1] < minimo) {
					minimo = this[1];
				}
			}
		});

		$('#escala-mapa .labels .min').html(minimo);
		$('#escala-mapa .labels .max').html(maximo);
		$(response).each(function() {
			$('#svg-map #' + this[0] + ' path').css('fill', 'rgba(0, 0, ' + 255 * (this[1] / maximo) + ', 1)');
		});
	});
	
    $('.estado-mapa').hover(
		function (e) {
			$('#label-mapa .titulo').html(this.id);
			$('#label-mapa .texto').html(candidatosPorEstado.get(this.id));
			
			$('#label-mapa').removeClass('d-none');
			
			$('#label-mapa').css('left', e.clientX + 10);
			$('#label-mapa').css('top', e.clientY + 10);
		}, 
		function () {
	        $('#label-mapa').addClass('d-none');
		}
	);
	
	
	$.get('/candidatos/grafico-media-imc-idade', function (chartData) {
		var ctx = document.getElementById('graficoImcPorIdade').getContext('2d');
		var chart = new Chart(ctx, chartData);
		chart.options.scales.yAxes[0].ticks.beginAtZero = true;
		chart.options.legend.display = false;
		chart.update();
	});
	
	$.post('/candidatos/tabela-media-imc-idade', function(dataTable) {
		if (dataTable.headers == null) {
			return;
		}
		
		var headers = [];
		
		dataTable.headers.forEach(function(titulo) {
			headers.push({ title: titulo });
		});
		
		var table = $('#tabelaImcPorIdade').DataTable({
			data: dataTable.data,
			columns: headers,
			iDisplayLength: 10,
	    	dom: 'fpt',
	        buttons: [
	            'copyHtml5',
	            'excelHtml5',
	            'csv',
	        ],
		});	
	});
	
	$.get('/candidatos/indice-obesidade?sexo=Masculino', function (data) {
		var ctx = document.getElementById('graficoIndiceObesidadeHomens').getContext('2d');
		var chart = new Chart(ctx, data);
		var indice = data.data.datasets[0].data[0] / data.data.datasets[0].data[1];
		indice = Math.round(indice * 1000) / 10 + '%';
		$('#indice-homens-obesos').html(indice);
	});
	
	$.get('/candidatos/indice-obesidade?sexo=Feminino', function (data) {
		var ctx = document.getElementById('graficoIndiceObesidadeMulheres').getContext('2d');
		var chart = new Chart(ctx, data);
		var indice = data.data.datasets[0].data[0] / data.data.datasets[0].data[1];
		indice = Math.round(indice * 1000) / 10 + '%';
		$('#indice-mulheres-obesas').html(indice);
	});
	
	$.get('/candidatos/grafico-media-idade-tipo-sanguineo', function (chartData) {
		var ctx = document.getElementById('graficoIdadeXTipoSanguineo').getContext('2d');
		var chart = new Chart(ctx, chartData);
		chart.options.scales.yAxes[0].ticks.beginAtZero = true;
		chart.options.legend.display = false;
		chart.update();
	});
	
	$.post('/candidatos/tabela-media-idade-tipo-sanguineo', function(dataTable) {
		if (dataTable.headers == null) {
			return;
		}
		
		var headers = [];
		
		dataTable.headers.forEach(function(titulo) {
			headers.push({ title: titulo });
		});
		
		var table = $('#tabelaIdadeXTipoSanguineo').DataTable({
			data: dataTable.data,
			columns: headers,
			iDisplayLength: 10,
	    	dom: 'fpt',
	        buttons: [
	            'copyHtml5',
	            'excelHtml5',
	            'csv',
	        ],
		});	
	});
	
	
	$.post('/candidatos/tabela-doadores-tipo-sanguineo', function(dataTable) {
		if (dataTable.headers == null) {
			return;
		}
		
		var headers = [];
		
		dataTable.headers.forEach(function(titulo) {
			headers.push({ title: titulo, class: 'nowrap' });
		});
		
		var table = $('#tabelaDoadoresPorTipoSanguineoAnalitico').DataTable({
			data: dataTable.data,
			columns: headers,
			iDisplayLength: 10,
	    	dom: 'Blfpt',
	        buttons: [
	            'copyHtml5',
	            'excelHtml5',
	            'csv',
	        ],
	        aaSorting: [
	        	[2, 'desc'],
	        ],
		});	
	});
	
	
	$.get('/candidatos/grafico-doadores-por-tipo-sanguineo', function (chartData) {
		var ctx = document.getElementById('graficoDoadoresPorTipoSanguineoResumido').getContext('2d');
		var chart = new Chart(ctx, chartData);
		chart.options.scales.yAxes[0].ticks.beginAtZero = true;
		chart.options.legend.display = false;
		chart.update();
	});
});