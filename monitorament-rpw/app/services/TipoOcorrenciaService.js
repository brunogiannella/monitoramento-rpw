(function() {

	'use strict';


	angular
		.module('tipoOcorrencia.service', [])
		.factory('TipoOcorrenciaService', TipoOcorrenciaService);

	TipoOcorrenciaService.$inject = ['$http', 'UtilsService', 'ConstantesService'];

	function TipoOcorrenciaService($http, UtilsService, ConstantesService) {

		function cadastrarTipoOcorrencia(tipoOcorrenciaDto, fncSucesso) {

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'tipoOcorrencia',
				method : 'POST',
				data: tipoOcorrenciaDto,
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

		function consultarTipoOcorrencia(idTipoOcorrencia, fncSucesso) {

			var  data = {};

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'tipoOcorrencia/'+idTipoOcorrencia,
				method : 'GET',
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

		function removerTipoOcorrencia(idTipoOcorrencia, fncSucesso) {

			var  data = {};

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'tipoOcorrencia/'+idTipoOcorrencia,
				method : 'DELETE',
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

		function listarTiposOcorrencia(fncSucesso) {

			var  data = {};

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'tipoOcorrencia',
				method : 'GET',
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

		function listarCamposOcorrencia(fncSucesso) {

			var  data = {};

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'tipoCamposOcorrencia',
				method : 'GET',
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
			cadastrarTipoOcorrencia : cadastrarTipoOcorrencia,
			consultarTipoOcorrencia : consultarTipoOcorrencia,
			removerTipoOcorrencia : removerTipoOcorrencia,
			listarTiposOcorrencia : listarTiposOcorrencia,
			listarCamposOcorrencia : listarCamposOcorrencia
		}
	}

})();