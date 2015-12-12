/**
 * JS for modules 
 */
var appModule = angular.module('appModule',['ngRoute','appModuleServices']);

appModule.config(function($routeProvider) {
	$routeProvider.
		when('/home/:user', {
			templateUrl: "html/applicationForm.html",
			controller: "applicationController"
		}).
		otherwise({
			templateUrl: "html/login.html",
			controller: "loginController"
		});
});