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
			applicationName : app.name, 
			applicationDesc : app.desc,
			applicationLaunch : app.launch,
			live : app.live
	};
	return reqObj;
}

function configRespAppObj(app) {
	var uiApp = {
			id : app.applicationId,
			name : app.applicationName, 
			desc : app.applicationDesc,
			launch : app.applicationLaunch,
			live : app.live
	};
	return uiApp;
}

function configAppForUI(app) {
	if(null == app.live) {
		app.live = false;
	}
	return app;
}