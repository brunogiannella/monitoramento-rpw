(function() {
	'use strict';

	angular
		.module('removerChat.controller', [])
		.controller('RemoverChatController' , RemoverChatController);

	RemoverChatController.$inject = ['$rootScope', '$scope', '$stateParams', 'ChatService', 'UtilsService'];

	function RemoverChatController($rootScope, $scope, $stateParams, ChatService, UtilsService) {

		

		function inicializar() {
			vm.chat = $stateParams.chat;
			vm.idCliente = $rootScope.usuarioLogado.idUsuario;
		};

		function removerChat() {
			var funcSucesso = function(data) {
				alert("Chat removido com sucesso");
				UtilsService.irPara("chats");
			};

			ChatService.removerChat(vm.chat.id, vm.idCliente, funcSucesso);			
		};

		function inicio() {
			if($rootScope.usuarioLogado.tipoUsuario == "ADMINISTRADOR") {
				UtilsService.irPara('home-administrador');
			} else if($rootScope.usuarioLogado.tipoUsuario == "SUPERVIDOR") {
				UtilsService.irPara('home-supervisor');
			} else if($rootScope.usuarioLogado.tipoUsuario == "FUNCIONARIO") {
				UtilsService.irPara('home-funcionario');
			}
		}

		function voltar() {
			UtilsService.irPara("chats");
		};

		var vm = this;
		vm.voltar = voltar;
		vm.inicio = inicio;
		vm.removerChat = removerChat;
		$scope.sair = UtilsService.logout;	

		inicializar();

	}

})();