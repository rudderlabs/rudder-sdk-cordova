package com.rudderstack.analytics.cordova.factories;

import com.rudderstack.analytics.cordova.RudderSDKCordovaPlugin;
import com.rudderstack.android.integration.singular.SingularIntegrationFactory;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;

public class RudderSingularFactoryCordova extends CordovaPlugin {

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }

    @Override
    public boolean execute(
            String action,
            JSONArray args,
            CallbackContext callbackContext
    ) {
        switch (action) {
            case "setup":
                setup(callbackContext);
                return true;
            default:
                return false;
        }
    }

    private void setup(CallbackContext callbackContext) {
        RudderSDKCordovaPlugin.addFactory(SingularIntegrationFactory.FACTORY);
        callbackContext.success();
    }
}