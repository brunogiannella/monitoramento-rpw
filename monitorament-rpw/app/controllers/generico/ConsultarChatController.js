(function() {
	'use strict';

	angular
		.module('consultarChat.controller', [])
		.controller('ConsultarChatController' , ConsultarChatController);

	ConsultarChatController.$inject = ['$rootScope', '$scope', '$stateParams', 'ChatService', 'UtilsService'];

	function ConsultarChatController($rootScope, $scope, $stateParams, ChatService, UtilsService) {

		var vm = this;
		$scope.sair = UtilsService.logout;
		vm.voltar = voltar;
		vm.inicio = inicio;	
		vm.enviarMensagem = enviarMensagem

		function inicializar() {
			vm.mensagemDto = {};
			vm.mensagemDto.idChat = $stateParams.idChat;
			vm.mensagemDto.codigoUsuarioFrom = $rootScope.usuarioLogado.idUsuario;

			var funcSucesso = function(data) {
				if(data != null) {
					vm.mensagensChat = data;
				}
			};

			ChatService.consultarChat($stateParams.idChat, $rootScope.usuarioLogado.idUsuario, funcSucesso);
		}

		function enviarMensagem() {
			var funcSucesso = function(data) {
				inicializar();
			};

			ChatService.enviarMensagem(vm.mensagemDto, funcSucesso);
		};

		function voltar() {
			UtilsService.irPara('chats');
		};

		function inicio() {
			if($rootScope.usuarioLogado.tipoUsuario == "ADMINISTRADOR") {
				UtilsService.irPara('home-administrador');
			} else if($rootScope.usuarioLogado.tipoUsuario == "SUPERVIDOR") {
				UtilsService.irPara('home-supervisor');
			} else if($rootScope.usuarioLogado.tipoUsuario == "FUNCIONARIO") {
				UtilsService.irPara('home-funcionario');
			}
		};
		
		inicializar();

	}

})();