var table;

$(document).ready(function() {
	$('.tipo-sanguineo').click(function() {
		if (table != null) {
			table.destroy();
		}
		
		var tipoSanguineo = $(this).find('.tipo-sanguineo-text').attr('data-value');
		
		$.post('', {tipoSanguineo: tipoSanguineo}, function(dataTable) {
			if (dataTable.headers == null) {
				return;
			}
			
			var headers = [];
			
			dataTable.headers.forEach(function(titulo) {
				headers.push({ title: titulo, class: "nowrap" });
			});
			
			
			table = $('#tabelaCandidatos').DataTable({
				data: dataTable.data,
				columns: headers,
				iDisplayLength: 10,
		    	dom: 'Blfrpti',
		        buttons: [
		            'copyHtml5',
		            'excelHtml5',
		            'csv',
		        ],
		        initComplete: function() {
		        	$('.table-responsive').next().toggleClass('d-none');
		        },
			});	
		});
	});
});