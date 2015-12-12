/**
 * JS for controllers 
 */

appModule.controller('applicationController' , function($scope,$http,$routeParams,$window,appManagerServices) {
	$scope.app = {};
	$scope.savedApp = {};
	$scope.applications = [];
	
	//$scope.loggedUser = $window.sessionStorage.getItem('userName');
	$scope.loggedUser = $routeParams.user;
	
	$scope.clear = function(form) {
		$scope.app = {};
		$scope.savedApp = {};
		resetFormErrors(form);
	};
	$scope.reset = function(form) {
		$scope.app = angular.copy($scope.savedApp);
		resetFormErrors(form);
	};
	$scope.update = function() {
		$scope.savedApp = angular.copy($scope.app);
	};
	
	/**
	 * ANGULAR RESTFUL CALLS
	 * 
	 */
	$scope.getApps = function() {
		var apps = appManagerServices.query(function() {
			$scope.applications = [];
			$scope.noApp = false;
			angular.forEach(apps,function(app) {
				var uiApp = configRespAppObj(app);
				$scope.applications.push(uiApp);
			});

		});
	};
	
	$scope.getApp = function(applicationId) {
		if(null != applicationId) {
			var app = appManagerServices.get({app:$scope.applicationId} , function() {
				$scope.applications = [];
				if(null != app.applicationId) {
					$scope.noApp = false;
					var uiApp = configRespAppObj(app);
					$scope.applications.push(uiApp);
				} else {
					$scope.noApp = true;
				}
			});
		} else {
			alert('Enter Application ID');
		}
	};
	
	$scope.save = function(form) {
		var reqObj = configReqAppObj(angular.copy($scope.app));
		var application = appManagerServices.save(reqObj);
		$scope.applications.push(configAppForUI(angular.copy($scope.app)));
		$scope.app = {};
		resetFormErrors(form);
	}
});

appModule.controller('loginController', function($scope,$http,$window) {
	
	$scope.login = function(userName,password) {
		if(userName === 'root' && password === 'root') {
			alert('Welcome');
			//$window.sessionStorage.setItem('userName', userName);
			$window.location.href = "#home/"+userName;
		} else {
			alert('Invalid user');
		}
	}
	
});