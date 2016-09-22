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
			consultarEquipamento : consultarEquipamento,
			cadastrarEquipamento : cadastrarEquipamento,
			listarEquipamentosCliente : listarEquipamentosCliente
		}
	}

})();