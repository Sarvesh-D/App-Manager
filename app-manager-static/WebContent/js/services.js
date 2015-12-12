/**
 * 
 */
var appModuleServices = angular.module('appModuleServices',['ngResource']);

appModuleServices.factory('appManagerServices' , function($resource) {
	return $resource('http://localhost:8081/app-manager-services/applications/:app');
});