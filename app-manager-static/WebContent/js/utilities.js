/**
 * JS for common utility functions 
 */

function resetFormErrors(form) {
	if (form) {
		form.$setPristine();
		form.$setUntouched();
	}
}

function configReqAppObj(app) {
	var reqObj = {
			applicationId : app.id,
			applicationName : app.name, 
			applicationDesc : app.desc,
			applicationLaunch : app.launch,
			live : app.live,
			users : app.users
	};
	return reqObj;
}

function configRespAppObj(app) {
	var uiApp = {
			id : app.applicationId,
			name : app.applicationName, 
			desc : app.applicationDesc,
			launch : new Date(app.applicationLaunch),
			live : app.live
	};
	return uiApp;
}

function configRespUserObj(user) {
	var uiUser = {
			userId : user.userId,
			userName : user.userName, 
	};
	return uiUser;
}

function configAppForUI(app) {
	if(null == app.live) {
		app.live = false;
	}
	return app;
}