(function() {
	'use strict';

	angular
		.module('login.controller', [])
		.controller('LoginController' , LoginController);

	LoginController.$inject = ['$scope', '$rootScope', 'LoginService', 'UtilsService', 'UsuarioService', 'TipoOcorrenciaService', 'ClienteService', 'CameraService', 'IndicadoresService', 'TurnoService', 'OcorrenciaService', 'ChatService'];

	function LoginController($scope, $rootScope, LoginService, UtilsService, UsuarioService, TipoOcorrenciaService, ClienteService, CameraService, IndicadoresService, TurnoService, OcorrenciaService, ChatService) {

		this.usuario = null;
		this.senha = null;
		this.realizarLogin = realizarLogin;

		function realizarLogin() {
			UtilsService.ativarLoading();
			$scope.erro = false;
			$scope.mensagemErro = "";

			var funcSucesso = function(data) {

				if(data != null) {
					$rootScope.usuarioLogado = data;

					if($rootScope.usuarioLogado.tipoUsuario == "ADMINISTRADOR") {
						UtilsService.irPara('home-administrador');
						carregarDominiosAdmin();
					} else if($rootScope.usuarioLogado.tipoUsuario == "SUPERVIDOR") {
						UtilsService.irPara('home-supervisor');
					} else if($rootScope.usuarioLogado.tipoUsuario == "FUNCIONARIO") {
						UtilsService.irPara('home-funcionario');
						carregarDominiosFuncionario();
					} else if($rootScope.usuarioLogado.tipoUsuario == "CLIENTE") {
						UtilsService.irPara('home-cliente');
						carregarDominiosCliente();
					}
				} else {
					$scope.erro = true;
					$scope.mensagemErro = "Usuário ou senha inválidos";
				}

				UtilsService.desativarLoading();
				
			};

			LoginService.realizarLogin(this.usuario, this.senha, funcSucesso);
		}

		function carregarDominiosFuncionario() {
			$rootScope.dominios = {};

			var funcSucessoClienteFuncionario = function(data) {
				$rootScope.clienteFuncionario = data;
			};

			ClienteService.consultarCliente($rootScope.usuarioLogado.idCliente, funcSucessoClienteFuncionario);

			var funcSucessoCondicoesClimaticas = function(data) {
				$rootScope.dominios.condicoesClimaticas = data;
			};

			TurnoService.consultarCondicoesClimaticas(funcSucessoCondicoesClimaticas);

			var funcSucessoTempo = function(data) {
				$rootScope.dominios.tempo = data;
			};

			TurnoService.consultarTempo(funcSucessoTempo);

			var funcSucessoPeriodos = function(data) {
				$rootScope.dominios.periodos = data;
			};

			TurnoService.consultarPeriodos(funcSucessoPeriodos);

			var funcSucessoInformantesOcorrencias = function(data) {
				$rootScope.dominios.informantesOcorrencias = data;
			};

			OcorrenciaService.consultarInformantesOcorrencias(funcSucessoInformantesOcorrencias);
		
			var funcSucessoToMensagens = function(data) {
				$rootScope.dominios.toMensagens = data;
			};

			UsuarioService.consultarUsuariosTipoUsuario($rootScope.usuarioLogado.tipoUsuario, funcSucessoToMensagens);

		}

		function carregarDominiosCliente() {
			$rootScope.dominios = {};
			$rootScope.indicadores = {};

			var d = new Date();
			var mesRetorno = d.getMonth();
			var mesAtual = mesRetorno + 1;
			var anoAtual = d.getFullYear();

			$rootScope.dominios.descricaoMesAtual = UtilsService.getMonth(mesAtual);
			$rootScope.dominios.mesAtual = mesAtual;
			$rootScope.dominios.anoAtual = anoAtual;

			var funcSucessoClienteFuncionario = function(data) {
				$rootScope.cliente = data;
			};

			ClienteService.consultarCliente($rootScope.usuarioLogado.idCliente, funcSucessoClienteFuncionario);

			var funcSucessoQuantidadeTurnosClientesMes = function(data) {
				$rootScope.indicadores.quantidadeTurnos = data;
			};

			IndicadoresService.consultaIndicadoresTurnosClientesMes($rootScope.usuarioLogado.idCliente, mesAtual, anoAtual, funcSucessoQuantidadeTurnosClientesMes);

			var funcSucessoQuantidadeOcorrenciasClientesMes = function(data) {
				$rootScope.indicadores.quantidadeOcorrencias = data;
			};

			IndicadoresService.consultaIndicadoresOcorrenciasClientesMes($rootScope.usuarioLogado.idCliente, mesAtual, anoAtual, funcSucessoQuantidadeOcorrenciasClientesMes);

		}

		function carregarDominiosAdmin() {

			$rootScope.dominios = {};
			$rootScope.indicadores = {};

			var funcSucessoTiposUsuario = function(data) {
				$rootScope.dominios.tiposUsuarioConsulta = data;
			};

			UsuarioService.consultarTiposUsuario(funcSucessoTiposUsuario);


			var funcSucessoTiposCampoOcorrencia = function(data) {
				$rootScope.dominios.tiposCampoOcorrenciaConsulta = data;
			};

			TipoOcorrenciaService.listarCamposOcorrencia(funcSucessoTiposCampoOcorrencia);

			var funcSucessoClientes = function(data) {
				$rootScope.dominios.clienteConsulta = data;
			};

			ClienteService.consultarClientes(funcSucessoClientes);

			TipoOcorrenciaService.listarCamposOcorrencia(funcSucessoTiposCampoOcorrencia);

			var funcSucessoTiposOcorrencia = function(data) {
				$rootScope.dominios.tipoOcorrenciasConsulta = data;
			};

			TipoOcorrenciaService.listarTiposOcorrencia(funcSucessoTiposOcorrencia);

			var funcSucessoTiposOcorrenciaPersonalizada = function(data) {
				$rootScope.dominios.tipoOcorrenciasPersonalizadasConsulta = data;
			};

			TipoOcorrenciaService.listarTiposOcorrenciasPersonalizadas(funcSucessoTiposOcorrenciaPersonalizada);

			var funcSucessoTiposCamera = function(data) {
				$rootScope.dominios.tipoCamerasConsulta = data;
			};

			CameraService.listarTiposCamera(funcSucessoTiposCamera);

			var funcSucessoQuantidadeClientes = function(data) {
				$rootScope.indicadores.quantidadeClientes = data;
			};

			IndicadoresService.consultarQuantidadeClientes(funcSucessoQuantidadeClientes);

			var funcSucessoQuantidadeTurnosPendentes = function(data) {
				$rootScope.indicadores.quantidadeTurnosPendentes = data;
			};

			IndicadoresService.consultarQuantidadeTurnosPendentes(funcSucessoQuantidadeTurnosPendentes);

			var funcSucessoQuantidadeTurnosAndamento = function(data) {
				$rootScope.indicadores.quantidadeTurnosAndamento = data;
			};

			IndicadoresService.consultarQuantidadeTurnosAndamento(funcSucessoQuantidadeTurnosAndamento);

			var funcSucessoQuantidadeUsuarios = function(data) {
				$rootScope.indicadores.quantidadeUsuarios = data;
			};

			IndicadoresService.consultarQuantidadeUsuarios(funcSucessoQuantidadeUsuarios);

			var funcSucessoQuantidadeTiposOcorrencia = function(data) {
				$rootScope.indicadores.quantidadeTiposOcorrencia = data;
			};

			IndicadoresService.consultarQuantidadeTiposOcorrencias(funcSucessoQuantidadeTiposOcorrencia);

			var funcSucessoQuantidadeTiposOcorrenciaPersonalizadas = function(data) {
				$rootScope.indicadores.quantidadeTiposOcorrenciaPersonalizadas = data;
			};

			IndicadoresService.consultarQuantidadeTiposOcorrenciaPersonalizadas(funcSucessoQuantidadeTiposOcorrenciaPersonalizadas);
		
			var funcSucessoToMensagens = function(data) {
				$rootScope.dominios.toMensagens = data;
			};

			UsuarioService.consultarUsuariosTipoUsuario($rootScope.usuarioLogado.tipoUsuario, funcSucessoToMensagens);
		}

	}

})();