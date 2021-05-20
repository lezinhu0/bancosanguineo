var table;

$(document).ready(function() {
	$('.tipo-sanguineo').click(function() {
		$('.tipo-sanguineo').removeClass('selected');
		$(this).addClass('selected');
		
		var tipoSanguineo = $(this).find('.tipo-sanguineo-text').attr('data-value');
		
		$.post('', {tipoSanguineo: tipoSanguineo}, function(dataTable) {
			if (dataTable.headers == null) {
				return;
			}
			
			var headers = [];
			
			dataTable.headers.forEach(function(titulo) {
				headers.push({ title: titulo, class: "nowrap" });
			});
			
			if (table == null) {
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
			} else {
			   table.clear().draw();
			   table.rows.add(dataTable.data);
			   table.columns.adjust().draw();
			}
		});
	});
});