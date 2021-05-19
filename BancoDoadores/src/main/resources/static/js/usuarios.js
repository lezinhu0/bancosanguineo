$(document).ready(function() {
	$.post('', function(dataTable) {
		if (dataTable.headers == null) {
			return;
		}
		var headers = [];
		dataTable.headers.forEach(function(titulo) {
			headers.push({ title: titulo });
		});
		
		var table = $('#tabela-usuarios').DataTable({
			data: dataTable.data,
			columns: headers,
			iDisplayLength: 10,
	    	dom: 'Blfrpti',
	        buttons: [
	            'copyHtml5',
	            'excelHtml5',
	            'csv',
	        ],
		});	
	}); 
});