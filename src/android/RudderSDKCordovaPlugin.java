package com.rudderstack.analytics.cordova;

import com.rudderstack.android.sdk.core.RudderClient;
import com.rudderstack.android.sdk.core.RudderConfig;
import com.rudderstack.android.sdk.core.RudderOption;
import com.rudderstack.android.sdk.core.RudderProperty;
import com.rudderstack.android.sdk.core.RudderTraits;
import com.rudderstack.android.sdk.core.RudderLogger;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

public class RudderSDKCordovaPlugin extends CordovaPlugin {

    private static RudderClient rudderClient = null;

    @Override
    public boolean execute(
            String action,
            JSONArray args,
            CallbackContext callbackContext
    )
            throws JSONException {
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
        } else if ("optOut".equals(action)) {
            optOut(args);
            return true;
        } else if ("putDeviceToken".equals(action)) {
            putDeviceToken(args);
            return true;
        } else if ("setAdvertisingId".equals(action)) {
            setAdvertisingId(args);
            return true;
        } else if ("setAnonymousId".equals(action)) {
            setAnonymousId(args);
            return true;
        }
        return false;
    }

    private void initialize(JSONArray args) {
        RudderLogger.logInfo("Initializing Rudder Cordova SDK");
        String writeKey = Utils.optArgString(args, 0);
        RudderConfig config = Utils.getRudderConfig(args.optJSONObject(1));
        RudderOption options = Utils.getRudderOption(args.optJSONObject(2));
        rudderClient =
                RudderClient.getInstance(
                        cordova.getActivity(),
                        writeKey,
                        config,
                        options
                );
        RudderLogger.logInfo("Initialized Rudder Cordova SDK");
    }

    private void identify(JSONArray args) {
        if (rudderClient == null) {
            RudderLogger.logWarn("Dropping the Identify call as SDK is not initialized yet");
            return;
        }
        cordova
                .getThreadPool()
                .execute(
                        new Runnable() {
                            public void run() {
                                String userId = Utils.optArgString(args, 0);
                                RudderTraits traits = Utils.getRudderTraits(args.optJSONObject(1));
                                RudderOption options = Utils.getRudderOption(args.optJSONObject(2));
                                rudderClient.identify(userId, traits, options);
                            }
                        }
                );
    }

    private void group(JSONArray args) {
        if (rudderClient == null) {
            RudderLogger.logWarn("Dropping the Group call as SDK is not initialized yet");
            return;
        }
        cordova
                .getThreadPool()
                .execute(
                        new Runnable() {
                            public void run() {
                                String groupId = Utils.optArgString(args, 0);
                                RudderTraits groupTraits = Utils.getRudderTraits(
                                        args.optJSONObject(1)
                                );
                                RudderOption options = Utils.getRudderOption(args.optJSONObject(2));
                                rudderClient.group(groupId, groupTraits, options);
                            }
                        }
                );
    }

    private void track(JSONArray args) {
        if (rudderClient == null) {
            RudderLogger.logWarn("Dropping the Track call as SDK is not initialized yet");
            return;
        }
        cordova
                .getThreadPool()
                .execute(
                        new Runnable() {
                            public void run() {
                                String eventName = Utils.optArgString(args, 0);
                                RudderProperty eventProperties = Utils.getRudderProperty(
                                        args.optJSONObject(1)
                                );
                                RudderOption options = Utils.getRudderOption(args.optJSONObject(2));
                                rudderClient.track(eventName, eventProperties, options);
                            }
                        }
                );
    }

    private void screen(JSONArray args) {
        if (rudderClient == null) {
            RudderLogger.logWarn("Dropping the Screen call as SDK is not initialized yet");
            return;
        }
        cordova
                .getThreadPool()
                .execute(
                        new Runnable() {
                            public void run() {
                                String screenName = Utils.optArgString(args, 0);
                                RudderProperty screenProperties = Utils.getRudderProperty(
                                        args.optJSONObject(1)
                                );
                                RudderOption options = Utils.getRudderOption(args.optJSONObject(2));
                                rudderClient.screen(screenName, screenProperties, options);
                            }
                        }
                );
    }

    private void alias(JSONArray args) {
        if (rudderClient == null) {
            RudderLogger.logWarn("Dropping the Alias call as SDK is not initialized yet");
            return;
        }
        cordova
                .getThreadPool()
                .execute(
                        new Runnable() {
                            public void run() {
                                String newUserId = Utils.optArgString(args, 0);
                                RudderOption options = Utils.getRudderOption(args.optJSONObject(1));
                                rudderClient.alias(newUserId, options);
                            }
                        }
                );
    }

    private void reset() {
        if (rudderClient == null) {
            RudderLogger.logWarn("Dropping the Reset call as SDK is not initialized yet");
            return;
        }
        cordova
                .getThreadPool()
                .execute(
                        new Runnable() {
                            public void run() {
                                rudderClient.reset();
                            }
                        }
                );

    }

    private void flush() {
        if (rudderClient == null) {
            RudderLogger.logWarn("Dropping the Flush call as SDK is not initialized yet");
            return;
        }
        cordova
                .getThreadPool()
                .execute(
                        new Runnable() {
                            public void run() {
                                rudderClient.flush();
                            }
                        }
                );
    }

    private void optOut(JSONArray args) {
        if (rudderClient == null) {
            RudderLogger.logWarn("Dropping the optOut call as SDK is not initialized yet");
            return;
        }
        cordova
                .getThreadPool()
                .execute(
                        new Runnable() {
                            public void run() {
                                rudderClient.optOut(args.optBoolean(0));
                            }
                        }
                );
    }

    private void putDeviceToken(JSONArray args) {
        if (rudderClient == null) {
            RudderLogger.logWarn("Dropping the putDeviceToken call as SDK is not initialized yet");
            return;
        }
        cordova
                .getThreadPool()
                .execute(
                        new Runnable() {
                            public void run() {
                                rudderClient.putDeviceToken(Utils.optArgString(args, 0));
                            }
                        }
                );
    }

    private void setAdvertisingId(JSONArray args) {
        RudderClient.updateWithAdvertisingId(Utils.optArgString(args, 0));
    }

    private void setAnonymousId(JSONArray args) {
        RudderClient.setAnonymousId(Utils.optArgString(args, 0));
    }
}
