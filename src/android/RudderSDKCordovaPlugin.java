package com.rudderstack.analytics.cordova;

import com.rudderstack.android.sdk.core.RudderClient;
import com.rudderstack.android.sdk.core.RudderConfig;
import com.rudderstack.android.sdk.core.RudderIntegration;
import com.rudderstack.android.sdk.core.RudderOption;
import com.rudderstack.android.sdk.core.RudderProperty;
import com.rudderstack.android.sdk.core.RudderTraits;
import com.rudderstack.android.sdk.core.RudderLogger;


import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RudderSDKCordovaPlugin extends CordovaPlugin {

    private RudderClient rudderClient = null;
    private RudderConfig rudderConfig = null;
    private int noOfActivities;
    protected ExecutorService executor = null;

    private List<Runnable> runnableTasks = new ArrayList<>();
    private boolean execServiceStarted = false;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        executor = Executors.newSingleThreadExecutor();
    }

    @Override
    public boolean execute(
            String action,
            JSONArray args,
            CallbackContext callbackContext
    ) {
        switch (action) {
            case "initialize":
                initialize(args, callbackContext);
                return true;
            case "identify":
                identify(args);
                return true;
            case "group":
                group(args);
                return true;
            case "track":
                track(args);
                return true;
            case "screen":
                screen(args);
                return true;
            case "alias":
                alias(args);
                return true;
            case "reset":
                reset();
                return true;
            case "flush":
                flush();
                return true;
            case "putDeviceToken":
                putDeviceToken(args);
                return true;
            case "putAdvertisingId":
                putAdvertisingId(args);
                return true;
            case "putAnonymousId":
                putAnonymousId(args);
                return true;
            case "optOut":
                optOut(args);
                return true;
            default:
                return false;
        }
    }

    private void initialize(JSONArray args, CallbackContext callbackContext) {

        cordova
                .getThreadPool()
                .execute(
                        () -> {
                            String writeKey = Utils.optArgString(args, 0);
                            rudderConfig = Utils.getRudderConfig(args.optJSONObject(1));
                            RudderOption options = Utils.getRudderOption(args.optJSONObject(2));
                            rudderClient =
                                    RudderClient.getInstance(
                                            cordova.getActivity(),
                                            writeKey,
                                            rudderConfig,
                                            options
                                    );
                            if (RudderClient.getInstance() != null) {
                                for (Runnable runnableTask : runnableTasks) {
                                    executor.execute(runnableTask);
                                }
                                execServiceStarted = true;
                                callbackContext.success();
                                return;
                            }
                            callbackContext.error("Failed to Initialize Rudder Cordova SDK");
                        });
    }

    private void identify(JSONArray args) {
        if (rudderClient == null) {
            RudderLogger.logWarn("Dropping the Identify call as SDK is not initialized yet");
            return;
        }
        executor.execute(
                () -> {
                    String userId = Utils.optArgString(args, 0);
                    RudderTraits traits = Utils.getRudderTraits(args.optJSONObject(1));
                    RudderOption options = Utils.getRudderOption(args.optJSONObject(2));
                    rudderClient.identify(userId, traits, options);
                }
        );
    }

    private void group(JSONArray args) {
        if (rudderClient == null) {
            RudderLogger.logWarn("Dropping the Group call as SDK is not initialized yet");
            return;
        }
        executor.execute(
                () -> {
                    String groupId = Utils.optArgString(args, 0);
                    RudderTraits groupTraits = Utils.getRudderTraits(
                            args.optJSONObject(1)
                    );
                    RudderOption options = Utils.getRudderOption(args.optJSONObject(2));
                    rudderClient.group(groupId, groupTraits, options);
                }
        );
    }

    private void track(JSONArray args) {
        if (rudderClient == null) {
            RudderLogger.logWarn("Dropping the Track call as SDK is not initialized yet");
            return;
        }
        executor.execute(
                () -> {
                    String eventName = Utils.optArgString(args, 0);
                    RudderProperty eventProperties = Utils.getRudderProperty(
                            args.optJSONObject(1)
                    );
                    RudderOption options = Utils.getRudderOption(args.optJSONObject(2));
                    rudderClient.track(eventName, eventProperties, options);
                }
        );
    }

    private void screen(JSONArray args) {
        if (rudderClient == null) {
            RudderLogger.logWarn("Dropping the Screen call as SDK is not initialized yet");
            return;
        }
        executor.execute(
                () -> {
                    String screenName = Utils.optArgString(args, 0);
                    RudderProperty screenProperties = Utils.getRudderProperty(
                            args.optJSONObject(1)
                    );
                    RudderOption options = Utils.getRudderOption(args.optJSONObject(2));
                    rudderClient.screen(screenName, screenProperties, options);
                }
        );
    }

    private void alias(JSONArray args) {
        if (rudderClient == null) {
            RudderLogger.logWarn("Dropping the Alias call as SDK is not initialized yet");
            return;
        }
        executor.execute(
                () -> {
                    String newUserId = Utils.optArgString(args, 0);
                    RudderOption options = Utils.getRudderOption(args.optJSONObject(1));
                    rudderClient.alias(newUserId, options);
                }
        );
    }

    private void reset() {
        if (rudderClient == null) {
            RudderLogger.logWarn("Dropping the Reset call as SDK is not initialized yet");
            return;
        }
        executor.execute(
                () -> rudderClient.reset()
        );

    }

    private void flush() {
        if (rudderClient == null) {
            RudderLogger.logWarn("Dropping the Flush call as SDK is not initialized yet");
            return;
        }
        executor.execute(
                () -> rudderClient.flush()
        );
    }


    private void putDeviceToken(JSONArray args) {
        executor.execute(
                () -> RudderClient.putDeviceToken(Utils.optArgString(args, 0))
        );
    }

    private void putAdvertisingId(JSONArray args) {
        executor.execute(
                () -> RudderClient.putAdvertisingId(Utils.optArgString(args, 0)));
    }

    private void putAnonymousId(JSONArray args) {

        executor.execute(
                () -> RudderClient.putAnonymousId(Utils.optArgString(args, 0)));
    }


    @Override
    public void onStart() {
        Runnable runnableTask = () -> {
            if (rudderConfig.isTrackLifecycleEvents()) {
                noOfActivities += 1;
                if (noOfActivities == 1) {
                    // no previous activity present. Application Opened
                    rudderClient.track("Application Opened");
                }
            }
        };
        if (rudderClient == null && !execServiceStarted) {
            runnableTasks.add(runnableTask);
            return;
        }
        executor.execute(runnableTask);
    }

    @Override
    public void onStop() {
        Runnable runnableTask = () -> {
            if (rudderConfig.isTrackLifecycleEvents()) {
                noOfActivities -= 1;
                if (noOfActivities == 0) {
                    rudderClient.track("Application Backgrounded");
                }
            }
        };
        if (rudderClient == null && !execServiceStarted) {
            runnableTasks.add(runnableTask);
            return;
        }
        executor.execute(runnableTask);
    }

    private void optOut(JSONArray args) {
        if (rudderClient == null) {
            RudderLogger.logWarn("Dropping the optOut call as SDK is not initialized yet");
            return;
        }
        executor.execute(
                () -> rudderClient.optOut(args.optBoolean(0))
        );
     }
}
