var exec = require('cordova/exec');

var RudderClient = {};

RudderClient.initialize = (writeKey, config, options) => new Promise((resolve, reject) => {
    if (!isValidString(writeKey)) {
        console.log('WriteKey is Invalid, Aborting SDK Initialization');
        throw new Error('WriteKey is Invalid, Aborting SDK Initialization');
    }

    if (isOptions(config)) {
        options = config;
        config = null;
    }

    if (!isOptions(options)) {
        console.log('Options is invalid, setting it to null');
        options = null;
    }

    var params = [];
    params[0] = writeKey;
    params[1] = config;
    params[2] = options;

    console.log("Initializing Rudder Cordova SDK");

    exec(() => {
        console.log("Initialized Rudder Cordova SDK Succesfully");
        RudderClient.track("Application Opened");
        resolve();
    }, (message) => {
        console.log(message);
        throw new Error(message);
    }, 'RudderSDKCordovaPlugin', 'initialize', params);
});

RudderClient.identify = function (userId, traits, options) {
    if (!isValidString(userId)) {
        console.log("userId is Invalid, dropping identify call");
        return;
    }

    if (isOptions(traits)) {
        options = traits;
        traits = null;
    }

    if (!isOptions(options)) {
        console.log('Options is invalid, setting it to null');
        options = null;
    }

    var params = [];
    params[0] = userId;
    params[1] = traits;
    params[2] = options;

    exec(null, null, 'RudderSDKCordovaPlugin', 'identify', params);
};

RudderClient.group = function (groupId, groupTraits, options) {
    if (!isValidString(groupId)) {
        console.log("groupId is Invalid, dropping group call");
        return;
    }

    if (isOptions(groupTraits)) {
        options = groupTraits;
        groupTraits = null;
    }

    if (!isOptions(options)) {
        console.log('Options is invalid, setting it to null');
        options = null;
    }

    var params = [];
    params[0] = groupId;
    params[1] = groupTraits;
    params[2] = options;

    exec(null, null, 'RudderSDKCordovaPlugin', 'group', params);
};

RudderClient.track = function (eventName, properties, options) {
    if (!isValidString(eventName)) {
        console.log("eventName is Invalid, dropping track call");
        return;
    }

    if (isOptions(properties)) {
        options = properties;
        properties = null;
    }

    if (!isOptions(options)) {
        console.log('Options is invalid, setting it to null');
        options = null;
    }

    var params = [];
    params[0] = eventName;
    params[1] = properties;
    params[2] = options;

    exec(null, null, 'RudderSDKCordovaPlugin', 'track', params);
};

RudderClient.screen = function (screenName, properties, options) {
    if (!isValidString(screenName)) {
        console.log("screenName is Invalid, dropping screen call");
        return;
    }

    if (isOptions(properties)) {
        options = properties;
        properties = null;
    }

    if (!isOptions(options)) {
        console.log('Options is invalid, setting it to null');
        options = null;
    }

    var params = [];
    params[0] = screenName;
    params[1] = properties;
    params[2] = options;

    exec(null, null, 'RudderSDKCordovaPlugin', 'screen', params);
};

RudderClient.alias = function (newId, options) {
    if (!isValidString(newId)) {
        console.log("newId is Invalid, dropping alias call");
        return;
    }

    if (!isOptions(options)) {
        console.log('Options is invalid, setting it to null');
        options = null;
    }

    var params = [];
    params[0] = newId;
    params[1] = options;

    exec(null, null, 'RudderSDKCordovaPlugin', 'alias', params);
};

RudderClient.reset = function () {
    exec(null, null, 'RudderSDKCordovaPlugin', 'reset', []);
};

RudderClient.flush = function () {
    exec(null, null, 'RudderSDKCordovaPlugin', 'flush', []);
};

RudderClient.putDeviceToken = function (deviceToken) {
    if (!isValidString(deviceToken)) {
        console.log("deviceToken is Invalid, dropping putDeviceToken call");
        return;
    }

    var params = [];
    params[0] = deviceToken;

    exec(null, null, 'RudderSDKCordovaPlugin', 'putDeviceToken', params);
}

RudderClient.setAdvertisingId = function (advertisingId) {
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

RudderClient.optOut = function (optOut) {

    if (!isValidBoolean(optOut)) {
        console.log("newId is Invalid, dropping alias call");
        return;
    }

    var params = [];
    params[0] = optOut;

    exec(null, null, 'RudderSDKCordovaPlugin', 'optOut', params);
}

RudderClient.LogLevel = {
    VERBOSE: 5,
    DEBUG: 4,
    INFO: 3,
    WARN: 2,
    ERROR: 1,
    NONE: 0
}

const isValidString = function (value) {
    if (typeof value === "undefined")
        return false;
    if (value === "")
        return false;
    return typeof value === "string" || value instanceof String
}

const isOptions = function (value) {
    if (value && (value.integrations || value.externalIds)) {
        return true;
    }
    return false;
}

const isValidBoolean = function (value) {
    if (typeof value === "undefined")
        return false;
    return typeof value === "boolean" || value instanceof Boolean
}

module.exports = RudderClient;