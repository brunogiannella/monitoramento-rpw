(function() {

	'use strict';


	angular
		.module('login.service', [])
		.factory('LoginService', LoginService);

	LoginService.$inject = ['$http', 'UtilsService', 'ConstantesService'];

	function LoginService($http, UtilsService, ConstantesService) {


		function realizarLogin(usuario, senha, fncSucesso) {

			var  data = { 
							"usuario": usuario, 
							"senha": senha  
						};

			$http.post(
				ConstantesService.URL.SERVIDOR + 'autenticacao/login', data).then(
			       function(response){
			        	fncSucesso(response.data.data);
			       }, 
			       function(response){
			       }
			    )
			  	.finally(function() {
				})
				.catch(function() {
				});
		};

		return {
			realizarLogin : realizarLogin
		}
	}

})();