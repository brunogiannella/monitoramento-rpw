(function() {

	'use strict';


	angular
		.module('cliente.service', [])
		.factory('ClienteService', ClienteService);

	ClienteService.$inject = ['$http', 'UtilsService', 'ConstantesService'];

	function ClienteService($http, UtilsService, ConstantesService) {

		function consultarClientes(fncSucesso) {

			var  data = {};

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'cliente',
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

		function atualizarCliente(clienteDto, fncSucesso) {

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'cliente',
				method : 'PUT',
				data: clienteDto,
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

		function consultarCliente(id, fncSucesso) {

			var  data = {};

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'cliente/'+id,
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

		function removerCliente(id, fncSucesso) {

			var  data = {};

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'cliente/'+id,
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

		function associarTipoOcorrencia(associarTipoOcorrenciaDTO, fncSucesso) {

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'cliente/associar-tipo-ocorrencia',
				method : 'POST',
				data: associarTipoOcorrenciaDTO,
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

		function associarTipoOcorrenciaPersonalizada(associarTipoOcorrenciaDTO, fncSucesso) {

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'cliente/associar-tipo-ocorrencia/personalizada',
				method : 'POST',
				data: associarTipoOcorrenciaDTO,
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


		function desassociarTipoOcorrencia(associarTipoOcorrenciaDTO, fncSucesso) {

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'cliente/desassociar-tipo-ocorrencia',
				method : 'POST',
				data: associarTipoOcorrenciaDTO,
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

		function desassociarTipoOcorrenciaPersonalizada(associarTipoOcorrenciaDTO, fncSucesso) {

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'cliente/desassociar-tipo-ocorrencia/personalizada',
				method : 'POST',
				data: associarTipoOcorrenciaDTO,
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
			cadastrarCliente : cadastrarCliente,
			atualizarCliente : atualizarCliente,
			consultarCliente : consultarCliente,
			consultarClientes : consultarClientes,
			removerCliente : removerCliente,
			associarTipoOcorrenciaPersonalizada : associarTipoOcorrenciaPersonalizada,
			associarTipoOcorrencia : associarTipoOcorrencia,
			desassociarTipoOcorrencia : desassociarTipoOcorrencia,
			desassociarTipoOcorrenciaPersonalizada : desassociarTipoOcorrenciaPersonalizada
		}
	}

})();