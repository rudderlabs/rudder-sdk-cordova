var exec = require('cordova/exec');

var RudderClient = {};

RudderClient.initialize = function (writeKey, config, options) {
    if (!writeKey || !writeKey instanceof String) {
        console.log("WriteKey is Invalid, Aborting");
        return;
    }
    if (!config instanceof Object) {
        config = {};
    }
    if (!options instanceof Object) {
        options = {};
    }

    var params = [];
    params[0] = writeKey;
    params[1] = config;
    params[2] = options;
    
    exec(null, null, 'RudderSDKCordovaPlugin', 'initialize', params);
}

RudderClient.identify = function (userId, traits, options) {

    var params = [];
    params[0] = userId;
    params[1] = traits;
    params[2] = options;

    exec(null, null, 'RudderSDKCordovaPlugin', 'identify', params);
};

RudderClient.group = function (groupId, groupTraits, options) {

    var params = [];
    params[0] = groupId;
    params[1] = groupTraits;
    params[2] = options;

    exec(null, null, 'RudderSDKCordovaPlugin', 'group', params);
};

RudderClient.track = function (eventName, properties, options) {

    var params = [];
    params[0] = eventName;
    params[1] = properties;
    params[2] = options;

    exec(null, null, 'RudderSDKCordovaPlugin', 'track', params);
};

RudderClient.screen = function (screenName, properties, options) {

    var params = [];
    params[0] = screenName;
    params[1] = properties;
    params[2] = options;

    exec(null, null, 'RudderSDKCordovaPlugin', 'screen', params);
};

RudderClient.alias = function (newId, options) {

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

RudderClient.optOut = function (optOut) {
    
    var params = [];
    params[0] = optOut;

    exec(null, null, 'RudderSDKCordovaPlugin', 'optOut', params);
};

RudderClient.putDeviceToken = function (deviceToken) {

    var params = [];
    params[0] = deviceToken;

    exec(null, null, 'RudderSDKCordovaPlugin', 'putDeviceToken', params);
}

RudderClient.setAdvertisingId = function(advertisingId) {
    var params = [];
    params[0] = advertisingId;

    exec(null, null, 'RudderSDKCordovaPlugin', 'setAdvertisingId', params);
}

RudderClient.setAnonymousId = function(anonymousId) {
    var params = [];
    params[0] = anonymousId;

    exec(null, null, 'RudderSDKCordovaPlugin', 'setAnonymousId', params);
}

RudderClient.getRudderContext = function(callback) {
    exec(callback, null, 'RudderSDKCordovaPlugin', 'getRudderContext', params);
}

module.exports = RudderClient;