var exec = require('cordova/exec');

var RudderClient = {};

RudderClient.loaded = false;

// should promisify this call 
RudderClient.initialize = (writeKey, config, options) => new Promise((resolve, reject) => {
    if ((typeof writeKey === "undefined") || !writeKey instanceof String) {
        console.log("WriteKey is Invalid, Aborting");
        reject();
        return;
    }

    if (isOptions(config)) {
        options = config;
        config = null;
    }

    var params = [];
    params[0] = writeKey;
    params[1] = config;
    params[2] = options;

    console.log("Initializing Rudder Cordova SDK");

    exec(() => {
        RudderClient.loaded = true;
        console.log("Initialized Rudder Cordova SDK Succesfully");
        resolve();
    }, (message) => {
        RudderClient.loaded = false;
        console.log(message);
        reject(message);
    }, 'RudderSDKCordovaPlugin', 'initialize', params);
});

RudderClient.identify = function (userId, traits, options) {
    if (!RudderClient.loaded) {
        console.log("SDK is not initialized yet, dropping identify call");
        return;
    }

    if (!isValidString(userId)) {
        console.log("userId is Invalid, dropping identify call");
        return;
    }

    if (isOptions(traits)) {
        options = traits;
        traits = null;
    }

    var params = [];
    params[0] = userId;
    params[1] = traits;
    params[2] = options;

    exec(null, null, 'RudderSDKCordovaPlugin', 'identify', params);
};

RudderClient.group = function (groupId, groupTraits, options) {
    if (!RudderClient.loaded) {
        console.log("SDK is not initialized yet, dropping group call");
        return;
    }

    if (!isValidString(groupId)) {
        console.log("groupId is Invalid, dropping group call");
        return;
    }

    if (isOptions(groupTraits)) {
        options = groupTraits;
        groupTraits = null;
    }

    var params = [];
    params[0] = groupId;
    params[1] = groupTraits;
    params[2] = options;

    exec(null, null, 'RudderSDKCordovaPlugin', 'group', params);
};

RudderClient.track = function (eventName, properties, options) {
    if (!RudderClient.loaded) {
        console.log("SDK is not initialized yet, dropping track call");
        return;
    }

    if (!isValidString(eventName)) {
        console.log("eventName is Invalid, dropping track call");
        return;
    }

    if (isOptions(properties)) {
        options = properties;
        properties = null;
    }

    var params = [];
    params[0] = eventName;
    params[1] = properties;
    params[2] = options;

    exec(null, null, 'RudderSDKCordovaPlugin', 'track', params);
};

RudderClient.screen = function (screenName, properties, options) {
    if (!RudderClient.loaded) {
        console.log("SDK is not initialized yet, dropping screen call");
        return;
    }

    if (!isValidString(screenName)) {
        console.log("screenName is Invalid, dropping screen call");
        return;
    }

    if (isOptions(properties)) {
        options = properties;
        properties = null;
    }

    var params = [];
    params[0] = screenName;
    params[1] = properties;
    params[2] = options;

    exec(null, null, 'RudderSDKCordovaPlugin', 'screen', params);
};

RudderClient.alias = function (newId, options) {
    if (!RudderClient.loaded) {
        console.log("SDK is not initialized yet, dropping alias call");
        return;
    }

    if (!isValidString(newId)) {
        console.log("newId is Invalid, dropping alias call");
        return;
    }

    var params = [];
    params[0] = newId;
    params[1] = options;

    exec(null, null, 'RudderSDKCordovaPlugin', 'alias', params);
};

RudderClient.reset = function () {
    if (!RudderClient.loaded) {
        console.log("SDK is not initialized yet, dropping reset call");
        return;
    }

    exec(null, null, 'RudderSDKCordovaPlugin', 'reset', []);
};

RudderClient.flush = function () {
    if (!RudderClient.loaded) {
        console.log("SDK is not initialized yet, dropping flush call");
        return;
    }

    exec(null, null, 'RudderSDKCordovaPlugin', 'flush', []);
};

RudderClient.optOut = function (optOut) {
    if (!RudderClient.loaded) {
        console.log("SDK is not initialized yet, dropping optOut call");
        return;
    }

    if (!isValidBoolean(optOut)) {
        console.log("newId is Invalid, dropping alias call");
        return;
    }

    var params = [];
    params[0] = optOut;

    exec(null, null, 'RudderSDKCordovaPlugin', 'optOut', params);
};

RudderClient.putDeviceToken = function (deviceToken) {
    if (!RudderClient.loaded) {
        console.log("SDK is not initialized yet, dropping putDeviceToken call");
        return;
    }

    if (!isValidString(deviceToken)) {
        console.log("deviceToken is Invalid, dropping putDeviceToken call");
        return;
    }

    var params = [];
    params[0] = deviceToken;

    exec(null, null, 'RudderSDKCordovaPlugin', 'putDeviceToken', params);
}

RudderClient.setAdvertisingId = function (advertisingId) {
    if (device.platform === "iOS") {
        if (!RudderClient.loaded) {
            console.log("SDK is not initialized yet, dropping setAdvertisingId call");
            return;
        }
    }

    if (!isValidString(advertisingId)) {
        console.log("advertisingId is Invalid, dropping setAdvertisingId call");
        return;
    }

    var params = [];
    params[0] = advertisingId;

    exec(null, null, 'RudderSDKCordovaPlugin', 'setAdvertisingId', params);
}

RudderClient.setAnonymousId = function (anonymousId) {
    if (!isValidString(anonymousId)) {
        console.log("anonymousId is Invalid, dropping setAnonymousId call");
        return;
    }

    var params = [];
    params[0] = anonymousId;

    exec(null, null, 'RudderSDKCordovaPlugin', 'setAnonymousId', params);
}

// Should promisify this as well
RudderClient.getRudderContext = () => new Promise((resolve, reject) => {
    if (!RudderClient.loaded) {
        console.log("SDK is not initialized yet, dropping getRudderContext call");
        return;
    }

    exec((context) => {
        console.log("Retrieved Rudder Context Succesfully");
        resolve(context);
    }, (message) => {
        console.log(message);
        reject(message);
    }, 'RudderSDKCordovaPlugin', 'getRudderContext', null);
});

const isValidString = function (value) {
    if (typeof value === "undefined")
        return false;
    return typeof value === "string" || value instanceof String
}

const isValidBoolean = function (value) {
    if (typeof value === "undefined")
        return false;
    return typeof value === "boolean" || value instanceof Boolean
}

const isOptions = function (value) {
    if (value && (value.integrations || value.externalIds)) {
        return true;
    }
    return false;
}

module.exports = RudderClient;