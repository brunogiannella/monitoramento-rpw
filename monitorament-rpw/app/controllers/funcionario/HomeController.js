(function() {
	'use strict';

	angular
		.module('home.controller', [])
		.controller('HomeController' , HomeController);

	HomeController.$inject = ['$rootScope', 'UtilsService', 'TurnoService'];

	function HomeController($rootScope, UtilsService, TurnoService) {
		this.nomeUsuario = $rootScope.usuarioLogado.nomeUsuario;
		this.irPara = UtilsService.irPara;
		this.sair = UtilsService.logout;
		var vm = this;
		vm.irParaTurnos = turnos; 
		vm.irParaOcorrencias = ocorrencias;

		function inicializar() {
			UtilsService.ativarLoading();
			var codigoUsuario = $rootScope.usuarioLogado.idUsuario;

			var funcSucesso = function(data) {
				if(data != null && data.length > 0) {
					vm.turnos = data;
				} else {
					vm.mensagemTurno = "No momento você não possui nenhum turno aberto."
				}

				UtilsService.desativarLoading();
			};

			TurnoService.consultarTurnosUsuario(codigoUsuario, funcSucesso);
		}

		function turnos() {
			UtilsService.ativarLoading();
			var codigoUsuario = $rootScope.usuarioLogado.idUsuario;

			var funcSucesso = function(data) {

				if(data != null && data.length > 0) {
					alert("No momento seu usuário possui um turno aberto, é necessário fecha-lo antes de abrir novo turno.")
				} else {
					UtilsService.irPara("cadastrar-turno");
				}
				
				UtilsService.desativarLoading();
			};

			TurnoService.consultarTurnosUsuario(codigoUsuario, funcSucesso);
		};

		function ocorrencias() {
			UtilsService.ativarLoading();
			var codigoUsuario = $rootScope.usuarioLogado.idUsuario;

			var funcSucesso = function(data) {

				if(data != null && data.length > 0) {
					UtilsService.irPara("cadastrar-ocorrencia");
				} else {
					alert("No momento seu usuário não possui um turno aberto. Para cadastrar ocorrências abra um novo turno.")
				}
				
				UtilsService.desativarLoading();
			};

			TurnoService.consultarTurnosUsuario(codigoUsuario, funcSucesso);
		};

		inicializar();

	}

})();