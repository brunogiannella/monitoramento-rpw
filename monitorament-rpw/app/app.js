var app = angular.module('app',['ui.router', 
							    'login.controller',
                  'home.controller',
                  'homeAdmin.controller',
                  'cadastroUsuarioController.controller',

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
    }).state('home-funcionario', {
      url: '/home',
      templateUrl: 'app/views/home-funcionario.html',
      controller: 'HomeController as HomeCrtl'
    }).state('home-administrador', {
      url: '/home-admin',
      templateUrl: 'app/views/home-admin.html',
      controller: 'HomeAdminController as HomeCrtl'
    }).state('cadastrar-usuario', {
      url: '/cadastrar-usuario',
      templateUrl: 'app/views/cadastro-usuario.html',
      controller: 'CadastroUsuarioController as CadastroUsuarioCrtl'
    });

	    // Utilizando o HTML5 History API
	$locationProvider.html5Mode(true);
});
