/**
 * 
 */
var appModuleServices = angular.module('appModuleServices',['ngResource']);

appModuleServices.factory('appManagerServices' , function($resource) {
	return $resource('http://localhost:8081/app-manager-services/applications/:app',{},
			{
				update: {
					method:'PUT'
				}
			});
});

appModuleServices.factory('userManagerServices' , function($resource) {
	return $resource('http://localhost:8081/app-manager-services/users/:user',{},
			{
				update: {
					method:'PUT'
				}
			});
});

appModuleServices.factory('userAppMappingServices' , function($resource) {
	return $resource('http://localhost:8081/app-manager-services/app/users',{},
			{
				update: {
					method:'PUT'
				}
			});
});

appModuleServices.factory('userLoginServices' , function($resource) {
	return $resource('http://localhost:8081/app-manager-services/login',{},
			{
				update: {
					method:'PUT'
				}
			});
});

appModuleServices.factory('auditServices' , function($resource) {
	return $resource('http://localhost:8081/app-manager-services/audit/:appId',{},
			{
				update: {
					method:'PUT'
				}
			});
});