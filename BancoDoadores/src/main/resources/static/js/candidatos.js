function carregarCandidatos(file) {
	var reader = new FileReader();
	reader.addEventListener('load', function(event) {
		var candidatos = JSON.parse(event.target.result);
		
		
		$('.modal-body').html('<span>Carregando candidatos, aguarde um momento.</span><br><div class="spinner-border text-primary ml-auto mr-auto d-block mt-4 mb-5" role="status"><span class="visually-hidden"></span></div>');
		$('.modal-footer').addClass('d-none');
		$('#exampleModal *[aria-label="Close"]').addClass('d-none');
		modal = new bootstrap.Modal(document.getElementById('modal')).show();
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
				$('#modal').addClass('d-none');
				window.location.href = '/candidatos';
			},
		});
	});
	reader.readAsText(file);
}

$(document).ready(function() {
	$('html').on("dragover", function(event) {
	    event.preventDefault();  
	    event.stopPropagation();
	    if ($(this).hasClass('dragging')) {
	  		return;
  		}
	    
	    $(this).addClass('dragging');
	});
	
	$('html').on("dragleave", function(event) {
	    event.preventDefault();  
	    event.stopPropagation();
	    $(this).removeClass('dragging');
	});
	
	$('html').on("drop", function(event) {
	    event.preventDefault();  
	    event.stopPropagation();
	    
	    
	    var file = event.originalEvent.dataTransfer.files[0];
	    carregarCandidatos(file);
	});

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
	    	
			carregarCandidatos(file);
	    });
	    
	    // dispatch a click event to open the file dialog
	    inputElement.dispatchEvent(new MouseEvent("click")); 
	});
});