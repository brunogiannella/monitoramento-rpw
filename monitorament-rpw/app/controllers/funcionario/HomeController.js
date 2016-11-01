(function() {
	'use strict';

	angular
		.module('home.controller', [])
		.controller('HomeController' , HomeController);

	HomeController.$inject = ['$rootScope', 'UtilsService', 'TurnoService', 'ClienteService'];

	function HomeController($rootScope, UtilsService, TurnoService, ClienteService) {
		this.nomeUsuario = $rootScope.usuarioLogado.nomeUsuario;
		this.irPara = UtilsService.irPara;
		this.sair = UtilsService.logout;
		var vm = this;
		vm.irParaTurnos = turnos; 
		vm.irParaOcorrencias = ocorrencias;
		vm.gerenciarCameras = gerenciarCameras;

		function inicializar() {
			UtilsService.ativarLoading();
			var codigoUsuario = $rootScope.usuarioLogado.idUsuario;

			var funcSucesso = function(data) {
				if(data != null && data.length > 0) {
					vm.turnos = data;
					vm.turnosAnteriores == [];
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

					UtilsService.ativarLoading();
					var codigoCliente = $rootScope.usuarioLogado.idCliente;

					var funcSucesso = function(data) {
						UtilsService.irPara("cadastrar-ocorrencia", {tiposOcorrencia:data});
					};

					ClienteService.consultarTiposOcorrenciaCliente(codigoCliente, funcSucesso);

				} else {
					alert("No momento seu usuário não possui um turno aberto. Para cadastrar ocorrências abra um novo turno.")
				}
				
				UtilsService.desativarLoading();
			};

			TurnoService.consultarTurnosUsuario(codigoUsuario, funcSucesso);
		};

		function gerenciarCameras() {
			UtilsService.ativarLoading();
			var codigoUsuario = $rootScope.usuarioLogado.idUsuario;

			var funcSucesso = function(data) {

				if(data != null && data.length > 0) {

					UtilsService.irPara("gerenciar-cameras", {idTurno: data[0].id});
				} else {
					alert("No momento seu usuário não possui um turno aberto. Para cadastrar uma avaliação da câmera abra um novo turno.")
				}
				
				UtilsService.desativarLoading();
			};

			TurnoService.consultarTurnosUsuario(codigoUsuario, funcSucesso);
		};

		inicializar();

	}

})();