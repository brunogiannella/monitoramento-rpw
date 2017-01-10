(function() {

	'use strict';


	angular
		.module('turno.service', [])
		.factory('TurnoService', TurnoService);

	TurnoService.$inject = ['$http', 'UtilsService', 'ConstantesService'];

	function TurnoService($http, UtilsService, ConstantesService) {

		function iniciarTurno(turnoDto, fncSucesso) {

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'turno',
				method : 'POST',
				data: turnoDto,
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


		function fecharTurno(turnoDto, fncSucesso) {

			var  data = {};

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'turno/finalizar',
				method : 'POST',
				data: turnoDto,
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

		function consultarTurno(idTurno, fncSucesso) {

			var  data = {};

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'turno/'+idTurno,
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

		function consultarDetalheTurno(idTurno, fncSucesso) {

			var  data = {};

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'turno/'+idTurno+'/detalhe',
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

		function consultarTurnosUsuario(idUsuario, fncSucesso) {

			var  data = {};

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'turno/usuario/'+idUsuario,
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

		function consultarUltimosTurnosCliente(idCliente, fncSucesso) {

			var  data = {};

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'turno/cliente/'+idCliente+"/ultimos",
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

		function consultarCondicoesClimaticas(fncSucesso) {

			var  data = {};

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'condicoesClimaticas',
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

		function consultarPeriodos(fncSucesso) {

			var  data = {};

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'periodo',
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

		function consultarTempo(fncSucesso) {

			var  data = {};

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'tempo',
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

		function consultarTurnosPendentes(fncSucesso) {

			var  data = {};

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'turno/pendentes',
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

		function consultarUltimosDezTurnos(idCliente, fncSucesso) {

			var  data = {};

			$http({	
				url : ConstantesService.URL.SERVIDOR + '/turno/clientes/'+ idCliente +'/ultimos/10',
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

		function aprovarTurno(idTurno, fncSucesso) {

			var  data = {};

			$http({	
				url : ConstantesService.URL.SERVIDOR + 'turno/'+idTurno+'/aprovar',
				method : 'PUT',
				data: {},
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
			iniciarTurno : iniciarTurno,
			fecharTurno : fecharTurno,
			consultarTurno : consultarTurno,
			consultarDetalheTurno : consultarDetalheTurno,
			consultarTurnosUsuario : consultarTurnosUsuario,
			consultarCondicoesClimaticas : consultarCondicoesClimaticas,
			consultarPeriodos : consultarPeriodos,
			consultarTempo : consultarTempo,
			consultarUltimosTurnosCliente : consultarUltimosTurnosCliente,
			consultarTurnosPendentes : consultarTurnosPendentes,
			consultarUltimosDezTurnos : consultarUltimosDezTurnos,
			aprovarTurno : aprovarTurno
		}
	}

})();