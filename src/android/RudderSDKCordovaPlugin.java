package com.rudderstack.analytics.cordova;

import org.apache.cordova.BuildConfig;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

public class RudderSDKCordovaPlugin extends CordovaPlugin {

  private static final String TAG = "RudderSDKCordova";
  private String writeKey;

  @Override protected void pluginInitialize() {

  }

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    if ("initialize".equals(action)) {
      initialize(args);
      return true;
    } else if ("identify".equals(action)) {
      identify(args);
      return true;
    } else if ("group".equals(action)) {
      group(args);
      return true;
    } else if ("track".equals(action)) {
      track(args);
      return true;
    } else if ("screen".equals(action)) {
      screen(args);
      return true;
    } else if ("alias".equals(action)) {
      alias(args);
      return true;
    } else if ("reset".equals(action)) {
      reset();
      return true;
    } else if ("flush".equals(action)) {
      flush();
      return true;
    } else if ("enable".equals(action)) {
      enable();
      return true;
    } else if ("disable".equals(action)) {
      disable();
      return true;
    }
    return false;
  }

  private void initialize(JSONArray args) {
    // To be implemented
  }

  private void identify(JSONArray args) {
    // To be implemented
  }

  private void group(JSONArray args) {
    // To be implemented
  }

  private void track(JSONArray args) {
    // To be implemented
  }

  private void screen(JSONArray args) {
    // To be implemented
  }

  private void alias(JSONArray args) {
    // To be implemented
  }

  private void reset() {
    // To be implemented
  }

  private void flush() {
    // To be implemented
  }

  private void enable() {
    // To be implemented
  }

  private void disable() {
    // To be implemented
  }

}