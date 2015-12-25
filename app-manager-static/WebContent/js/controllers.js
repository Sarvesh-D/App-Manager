/**
 * JS for controllers 
 */

appModule.controller('applicationController' , function($scope,$http,$routeParams,$uibModal,appManagerServices) {
	$scope.app = {};
	$scope.applications = [];
	
	$scope.loggedUser = $routeParams.user;
	
	$scope.clear = function(form) {
		$scope.app = {};
		resetFormErrors(form);
	};
	$scope.reset = function(form) {
		$scope.app = angular.copy($scope.savedApp);
		resetFormErrors(form);
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
			var app = appManagerServices.get({app:applicationId} , function(res) {
				$scope.applications = [];
				if(null != app.applicationId) {
					$scope.noApp = false;
					var uiApp = configRespAppObj(app);
					$scope.applications.push(uiApp);
				} else {
					$scope.noApp = true;
				}
			}
			);
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
	};
	
	$scope.update = function(applicationId) {
		$uibModal.open({
			templateUrl: "html/applicationForm.html",
			controller: "updateController",
			resolve: {
				applicationId: applicationId
			}
		});
	}
	
	$scope.deleteApp = function(applicationId) {
		$uibModal.open({
			templateUrl: "deleteConfirmation.html",
			controller: "deleteController",
			resolve: {
				applicationId: applicationId
			}
		})
	}
});

appModule.controller('loginController', function($scope,$window) {
	
	$scope.login = function(userName,password) {
		if(userName === 'root' && password === 'root') {
			alert('Welcome');
			$window.location.href = "#home/"+userName;
		} else {
			alert('Invalid user');
		}
	}
	
});

appModule.controller('updateController', function($scope,$uibModalInstance,appManagerServices,applicationId) {
	$scope.app = {};
	// GET the application which needs to be updated
	appManagerServices.get({app:applicationId},function(app) {
		$scope.app = configRespAppObj(app);
	});
	// UPDATE the application
	$scope.save = function(form) {
		var reqObj = configReqAppObj(angular.copy($scope.app));
		var application = appManagerServices.update(reqObj);
		$uibModalInstance.dismiss();
		resetFormErrors(form);
	}
});

appModule.controller('deleteController', function($scope,$uibModalInstance,appManagerServices,applicationId) {
	// DELETE the app
	$scope.remove = function() {
		appManagerServices.remove({app:applicationId});
		$uibModalInstance.dismiss();
	}
	$scope.cancel = function() {
		$uibModalInstance.dismiss();
	}
});