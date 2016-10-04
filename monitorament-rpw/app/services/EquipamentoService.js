(function() {

	'use strict';


	angular
		.module('equipamento.service', [])
		.factory('EquipamentoService', EquipamentoService);

	EquipamentoService.$inject = ['$http', 'UtilsService', 'ConstantesService'];

	function EquipamentoService($http, UtilsService, ConstantesService) {

		function cadastrarEquipamento(equipamentoDto, fncSucesso) {

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'equipamento',
				method : 'POST',
				data: equipamentoDto,
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

		function atualizarEquipamento(equipamentoDto, fncSucesso) {

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'equipamento',
				method : 'PUT',
				data: equipamentoDto,
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

		function consultarEquipamento(idEquipamento, fncSucesso) {

			var  data = {};

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'equipamento/' + idEquipamento,
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

		function removerEquipamento(idEquipamento, fncSucesso) {

			var  data = {};

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'equipamento/' + idEquipamento,
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

		function listarEquipamentosCliente(idCliente, fncSucesso) {

			var  data = {};

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'equipamento/cliente/' + idCliente,
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

		function consultarTiposEquipamento(fncSucesso) {

			var  data = {};

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'equipamento/tipoEquipamento',
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
			consultarEquipamento : consultarEquipamento,
			atualizarEquipamento : atualizarEquipamento,
			cadastrarEquipamento : cadastrarEquipamento,
			removerEquipamento : removerEquipamento,
			consultarTiposEquipamento : consultarTiposEquipamento,
			listarEquipamentosCliente : listarEquipamentosCliente
		}
	}

})();