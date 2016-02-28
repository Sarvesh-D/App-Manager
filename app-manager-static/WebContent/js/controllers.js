/**
 * JS for controllers 
 */

appModule.controller('applicationController' , function($scope,$rootScope,$http,$uibModal,$window,appManagerServices,auditServices) {
	$scope.app = {};
	$scope.applications = [];
	
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
	
	$scope.doMap = function() {
		$window.location.href = '#map/';
	}
	
	$scope.getAudit = function(applicationId) {
		if(null != applicationId) {
			var appRevisions = auditServices.query({appId:applicationId} , function(res) {
				$scope.applications = [];
				$scope.noApp = false;
				angular.forEach(appRevisions,function(appRevision) {
					var uiApp = configRespAppObj(appRevision);
					$scope.applications.push(uiApp);
				});
			}
			);
		} else {
			alert('Enter Application ID');
		}
	};
		
});

appModule.controller('loginController', function($scope,$window,$rootScope,userLoginServices) {
	
	$scope.login = function(user) {
		if(user.password === 'root') {
			alert('Welcome');
			$rootScope.loggedUser = user.userName;
			var reqObj = {
					'userName': user.userName 
			}
			userLoginServices.save(reqObj);
			$window.location.href = "#home/"+user.userName;
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

appModule.controller('appMapController', function($scope,$rootScope,$window,appManagerServices,userManagerServices,userAppMappingServices) {
	$scope.init = function() {
		// load apps
		var apps = appManagerServices.query(function() {
			$scope.applications = [];
			angular.forEach(apps,function(app) {
				var uiApp = configRespAppObj(app);
				$scope.applications.push(uiApp);
			});
		});
		
		// load users
		var users = userManagerServices.query(function() {
			$scope.users = [];
			angular.forEach(users,function(user) {
				var uiUser = configRespUserObj(user);
				$scope.users.push(uiUser);
			});
		});
	}
	
	
	$scope.mapAppToUsers = function(appUsersMap) {
		console.log(appUsersMap);
		// UPDATE each user
		var reqObj = {
				'applicationId':appUsersMap.application,
				'users':appUsersMap.users
		}
		userAppMappingServices.update(reqObj);
		$scope.appsMappedSucessfully = true;
	}
	
	$scope.back = function() {
		$window.location.href = "#home/"+$rootScope.loggedUser;
	}
});