var app = angular.module('app',['ui.router', 
							    'login.controller',
                  'home.controller',
                  'consultarOcorrenciasController.controller',
                  'cadastrarOcorrenciaController.controller',
                  'iniciarNovoTurnoController.controller',
                  'gerenciarCamerasController.controller',
                  'homeAdmin.controller',
                  'cadastroUsuarioController.controller',
                  'cadastroClienteController.controller',
                  'cadastrarTipoOcorrenciaController.controller',

							    'constantes.service',
							    'utils.service',
							    'login.service',
                  'turno.service',
                  'cliente.service',
                  'tipoOcorrencia.service',
                  'usuario.service',
                  'camera.service']);
 
app.config(function($stateProvider, $urlRouterProvider, $locationProvider)
{
   	// remove o # da url
   	$locationProvider.html5Mode(true);
 
   	$urlRouterProvider.otherwise('/');

  	$stateProvider
    .state('login', {
      url: '/',
      templateUrl: 'app/views/login.html',
      controller: 'LoginController as LoginCrtl'
    })
    .state('home-funcionario', {
      url: '/home',
      templateUrl: 'app/views/funcionario/home-funcionario.html',
      controller: 'HomeController as HomeCrtl'
    })
    .state('cadastrar-turno', {
      url: '/cadastrar-turno',
      templateUrl: 'app/views/funcionario/cadastrar-turno.html',
      controller: 'IniciarNovoTurnoController as IniciarNovoTurnoCrtl'
    })
    .state('cadastrar-ocorrencia', {
      url: '/cadastrar-ocorrencia',
      templateUrl: 'app/views/funcionario/cadastrar-ocorrencia.html',
      controller: 'CadastrarOcorrenciaController as CadastrarOcorrenciaCrtl'
    })
    .state('gerenciar-cameras', {
      url: '/gerenciar-cameras',
      templateUrl: 'app/views/funcionario/gerenciar-cameras.html',
      controller: 'GerenciarCamerasController as GerenciarCamerasCrtl'
    })
    .state('consultar-ocorrencias-funcionario', {
      url: '/consultar-ocorrencias-funcionario',
      templateUrl: 'app/views/funcionario/consultar-ocorrencia.html',
      controller: 'ConsultarOcorrenciasController as ConsultarOcorrenciasCrtl'
    })
    .state('home-administrador', {
      url: '/home-admin',
      templateUrl: 'app/views/admin/home-admin.html',
      controller: 'HomeAdminController as HomeCrtl'
    })
    .state('cadastrar-usuario', {
      url: '/cadastrar-usuario',
      templateUrl: 'app/views/admin/cadastro-usuario.html',
      controller: 'CadastroUsuarioController as CadastroUsuarioCrtl'
    })
    .state('cadastrar-cliente', {
      url: '/cadastrar-cliente',
      templateUrl: 'app/views/admin/cadastro-cliente.html',
      controller: 'CadastroClienteController as CadastroClienteCrtl'
    })
    .state('cadastrar-tipo-ocorrencias', {
      url: '/cadastrar-tipo-ocorrencias',
      templateUrl: 'app/views/admin/cadastrar-tipo-ocorrencias.html',
      controller: 'CadastrarTipoOcorrenciaController as CadastrarTipoOcorrenciaCrtl'
    });

	    // Utilizando o HTML5 History API
	$locationProvider.html5Mode(true);
});
