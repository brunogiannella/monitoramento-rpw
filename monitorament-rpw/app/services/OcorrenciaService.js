(function() {

	'use strict';


	angular
		.module('ocorrencia.service', [])
		.factory('OcorrenciaService', OcorrenciaService);

	OcorrenciaService.$inject = ['$http', 'UtilsService', 'ConstantesService'];

	function OcorrenciaService($http, UtilsService, ConstantesService) {

		function cadastrarOcorrencia(ocorrenciaDTO, fncSucesso) {

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'ocorrencia',
				method : 'POST',
				data: ocorrenciaDTO,
				headers: {
					'x-acess-token': UtilsService.getToken()
				}
			}).then(
		       function(response){

		       	  if(response.data.sucess) {
		       	  	fncSucesso(response.data.data);
		       	  } else {
		       	  	UtilsService.tratarErrosHttp(response);
		       	  	return false;
		       	  }

		       }, 
		       function(response){
		       		UtilsService.tratarErrosHttp(response);
		         	return false;
		       }
		    ).catch(function() {
		    	UtilsService.tratarErrosHttp(null);
				return false;
			});
		};

		function consultarOcorrencia(idOcorrencia, fncSucesso) {

			var  data = {};

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'ocorrencia/'+idOcorrencia,
				method : 'GET',
				headers: {
					'x-acess-token': UtilsService.getToken()
				}
			}).then(
		       function(response){

		       	  if(response.data.sucess) {
		       	  	fncSucesso(response.data.data);
		       	  } else {
		       	  	UtilsService.tratarErrosHttp(response);
		       	  	return false;
		       	  }

		       }, 
		       function(response){
		       		UtilsService.tratarErrosHttp(response);
		         	return false;
		       }
		    ).catch(function() {
		    	UtilsService.tratarErrosHttp(null);
				return false;
			});
		};

		function removerOcorrencia(idOcorrencia, fncSucesso) {

			var  data = {};

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'ocorrencia/'+idOcorrencia,
				method : 'DELETE',
				headers: {
					'x-acess-token': UtilsService.getToken()
				}
			}).then(
		       function(response){

		       	  if(response.data.sucess) {
		       	  	fncSucesso(response.data.data);
		       	  } else {
		       	  	UtilsService.tratarErrosHttp(response);
		       	  	return false;
		       	  }

		       }, 
		       function(response){
		       		UtilsService.tratarErrosHttp(response);
		         	return false;
		       }
		    ).catch(function() {
		    	UtilsService.tratarErrosHttp(null);
				return false;
			});
		};

		
		function listarTiposOcorrencia(idCliente, fncSucesso) {

			var  data = {};

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'ocorrencia/cliente/'+idCliente,
				method : 'GET',
				headers: {
					'x-acess-token': UtilsService.getToken()
				}
			}).then(
		       function(response){

		       	  if(response.data.sucess) {
		       	  	fncSucesso(response.data.data);
		       	  } else {
		       	  	UtilsService.tratarErrosHttp(response);
		       	  	return false;
		       	  }

		       }, 
		       function(response){
		       		UtilsService.tratarErrosHttp(response);
		         	return false;
		       }
		    ).catch(function() {
		    	UtilsService.tratarErrosHttp(null);
				return false;
			});
		};


		return {
			cadastrarOcorrencia : cadastrarOcorrencia,
			consultarOcorrencia : consultarOcorrencia,
			removerOcorrencia : removerOcorrencia,
			listarTiposOcorrencia : listarTiposOcorrencia
		}
	}

})();