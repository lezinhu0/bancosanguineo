$(document).ready(function() {
		$('#password, #repeatPassword').keyup(function() {
			var senha = $('#password').val();
			var repetirSenha = $('#repeatPassword').val();
			
			if (senha != repetirSenha) {
				$('#password, #repeatPassword').removeClass('border-success');
				$('#password, #repeatPassword').addClass('border-danger');
				$('#coincidir-senhas').html('As senhas não coincidem!');
			} else {
				$('#password, #repeatPassword').removeClass('border-danger');
				$('#password, #repeatPassword').addClass('border-success');
				$('#coincidir-senhas').html('');
			}
		});
});