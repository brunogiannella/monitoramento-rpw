var app = angular.module('app',['ui.router', 
                  'imprimirRelatorioMensal.controller',
                  'imprimirRelatorioClienteController.controller',

							    'constantes.service',
							    'utils.service',
							    'login.service',
                  'relatorio.service',

                  'onFinishRender-diretiva']);
 
app.config(function($stateProvider, $urlRouterProvider, $locationProvider)
{
   	// remove o # da url
   	//$locationProvider.html5Mode(true);
 
   	$urlRouterProvider.otherwise('/');

  	$stateProvider
    .state('imprimir-relatorio-cliente', {
      url: '/',
      templateUrl: 'app/views/cliente/gerarRelatorioCliente.html',
      controller: 'ImprimirRelatorioClienteController as ImprimirRelatorioClienteCrtl'
    }).state('imprimir-relatorio-mensal', {
      url: '/imprimir-relatorio-mensal',
      params: {
        relatorio : null,
        consumidor : null
      },
      templateUrl: 'app/views/admin/imprimir-relatorio-mensal.html',
      controller: 'ImprimirRelatorioMensalController as ImprimirRelatorioMensalCrtl'
    });


	    // Utilizando o HTML5 History API
	 $locationProvider.html5Mode(true);
});
