(function() {

	'use strict';


	angular
		.module('cliente.service', [])
		.factory('ClienteService', ClienteService);

	ClienteService.$inject = ['$http', 'UtilsService', 'ConstantesService'];

	function ClienteService($http, UtilsService, ConstantesService) {

		function cadastrarCliente(clienteDto, fncSucesso) {

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'cliente',
				method : 'POST',
				data: clienteDto,
				headers: {
					'x-acess-token': UtilsService.getToken()
				}
			}).then(
		       function(response){

		       	  if(response.data.sucess) {
		       	  	fncSucesso(response.data.data);
		       	  } else {
		       	  	return false;
		       	  }

		       }, 
		       function(response){
		         return false;
		       }
		    ).catch(function() {
				return false;
			});
		};

		return {
			cadastrarCliente : cadastrarCliente
		}
	}

})();