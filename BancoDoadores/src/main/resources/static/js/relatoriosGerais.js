$(document).ready(function() {
    $('canvas').after('<div class="spinner"></div>');
    
    $.get('/candidatos/grafico-doadores-por-estado', function (chartData) {
        var ctx = document.getElementById('graficoDoadoresPorEstado').getContext('2d');
        var chart = new Chart(ctx, chartData);
        chart.options.scales.yAxes[0].ticks.beginAtZero = true;
        chart.options.onClick = function (evt, item) {
        };
        chart.update();
        $('#graficoDoadoresPorEstado').next().toggleClass('d-none');
    });
    
	var candidatosPorEstado = new Map();
	$(document).ready(function() {
		$.get('/candidatos/por-estado', function(response) {
			var maximo = 0;
			$(response).each(function() {
				candidatosPorEstado.set(this[0], this[1]);
				if (this[1] > maximo) {
					maximo = this[1];
				}
			});
			$(response).each(function() {
				$('#svg-map #' + this[0] + ' path').css('fill', 'rgba(0, 0, ' + 255 * (this[1] / maximo) + ', 1)');
			});
		});
		
		var intervalId;
	    $('.estado-mapa').hover(
			function (e) {
				$('#label-mapa .titulo').html(this.id);
				$('#label-mapa .texto').html(candidatosPorEstado.get(this.id));
				
				$('#label-mapa').removeClass('d-none');
				
				$('#label-mapa').css('left', e.clientX);
				$('#label-mapa').css('top', e.clientY);
				
				console.log('rodando');
			}, 
			function () {
		        clearInterval(intervalId);
		        $('#label-mapa').addClass('d-none');
			}
		);
	});
});