$(document).ready(function() {
	$('.table-responsive').after('<div class="spinner"></div>');
	
	$.post('', function(dataTable) {
		if (dataTable.headers == null) {
			return;
		}
		
		var headers = [];
		
		dataTable.headers.forEach(function(titulo) {
			headers.push({ title: titulo });
		});
		
		var table = $('#tabelaCandidatos').DataTable({
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
	
	$('#filechoser-btn').click(function() {
	    // Create an input element
	    var inputElement = document.createElement("input");
	
	    // Set its type to file
	    inputElement.type = "file";
	
	    // Set accept to the file types you want the user to select. 
	    // Include both the file extension and the mime type
	    inputElement.accept = '.json';
	
	    // set onchange event to call callback when user has selected file
	    inputElement.addEventListener("change", function(e) {
	    	var file = e.target.files[0];
	    	
	    	var reader = new FileReader();
	    	reader.addEventListener('load', function(event) {
	    		var candidatos = JSON.parse(event.target.result);
	    		$.ajax({
	    			url: '/candidatos/salvar',
	    			type: 'POST',
					dataType: 'text',
				    headers: {
				        'Accept': 'application/json',
				        'Content-Type': 'application/json'
				    },
	    			data: JSON.stringify(candidatos),
	    			success: function(response) {
	    				window.location.href = '/candidatos';
	    			},
	    		});
	    	});
	    	reader.readAsText(file);
	    });
	    
	    // dispatch a click event to open the file dialog
	    inputElement.dispatchEvent(new MouseEvent("click")); 
	});
});