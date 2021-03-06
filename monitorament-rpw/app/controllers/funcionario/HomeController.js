(function() {
	'use strict';

	angular
		.module('home.controller', [])
		.controller('HomeController' , HomeController);

	HomeController.$inject = ['$rootScope', '$scope', 'UtilsService', 'TurnoService', 'ClienteService', 'ChatService'];

	function HomeController($rootScope, $scope, UtilsService, TurnoService, ClienteService, ChatService) {
		this.nomeUsuario = $rootScope.usuarioLogado.nomeUsuario;
		this.irPara = UtilsService.irPara;
		this.sair = UtilsService.logout;
		var vm = this;
		vm.irParaTurnos = turnos; 
		vm.irParaOcorrencias = ocorrencias;
		vm.irParaMensagens = mensagens;
		vm.gerenciarCameras = gerenciarCameras;
		vm.consultarTurno = consultarTurno;
		vm.finalizarTurno = finalizarTurno;
		vm.quantidadeMensagensNaoLidas = $rootScope.quantidadesMensagensNaoLidas;

		function inicializar() {
			UtilsService.ativarLoading();
			var codigoUsuario = $rootScope.usuarioLogado.idUsuario;

			var funcSucessoQuantidadeMensagensNaoLidas = function(data) {
				vm.quantidadeMensagensNaoLidas = data;
			};

			ChatService.consultarQuantidadeNovasMensagensUsuario(codigoUsuario, funcSucessoQuantidadeMensagensNaoLidas);


			var funcSucesso = function(data) {
				if(data != null) {
					vm.turnos = data;

					var codigoCliente = null;
					if(data[0] != null) {
						codigoCliente = data[0].idCliente;
					} else {
						codigoCliente = $rootScope.clienteFuncionario.id;
					}
					

					if(codigoCliente == null) {
						alert("Seu usuário não está associado a nenhum cliente. Favor solicitar inclusão.");
					}

					var funcSucessoUltimos = function(data) {
						if(data != null && data.length > 0) {
							vm.turnosAnteriores = data;
						} else {
							vm.mensagemTurnosAnteriores = "Não existem turnos anteriores."
						}

					};

					TurnoService.consultarUltimosTurnosCliente(codigoCliente, funcSucessoUltimos);

					if(data.length == 0) {
						vm.mensagemTurno = "No momento você não possui nenhum turno aberto.";
					}

				}

				UtilsService.desativarLoading();
			};

			TurnoService.consultarTurnosUsuario(codigoUsuario, funcSucesso);
		}

		function consultarTurno(idTurno, editar) {
			UtilsService.irPara("consultar-turno", {idTurno:idTurno, editar:editar});
		}

		function finalizarTurno(idTurno, dataInicio) {
			UtilsService.irPara("finalizar-turno", {idTurno:idTurno, dataInicio:dataInicio});
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

		function mensagens() {
			UtilsService.irPara("chats");
		};

		function ocorrencias() {
			UtilsService.ativarLoading();
			var codigoUsuario = $rootScope.usuarioLogado.idUsuario;

			var funcSucesso = function(data) {

				if(data != null && data.length > 0) {

					UtilsService.ativarLoading();
					var codigoCliente = $rootScope.usuarioLogado.idCliente;
					$scope.codigoTurno = data[0].id;
					var funcSucesso = function(data) {
						UtilsService.irPara("cadastrar-ocorrencia", {tiposOcorrencia:data, codigoTurno:$scope.codigoTurno});
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