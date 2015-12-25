/**
 * JS for modules 
 */
var appModule = angular.module('appModule',['ngRoute','appModuleServices','ui.bootstrap']);

appModule.config(function($routeProvider) {
	$routeProvider.
		when('/', {
			templateUrl: "html/login.html",
			controller: "loginController"
		}).
		when('/home/:user', {
			templateUrl: "html/home.html",
			controller: "applicationController"
		});
});