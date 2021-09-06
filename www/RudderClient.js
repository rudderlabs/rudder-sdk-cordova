var exec = require('cordova/exec');

var RudderClient = {};

RudderClient.initialize = function() {
    exec(null, null, 'RudderSDKCordovaPlugin', 'initialize', null);
}

RudderClient.identify = function() {
    exec(null, null, 'RudderSDKCordovaPlugin', 'identify', null);
};

RudderClient.group = function() {
    exec(null, null, 'RudderSDKCordovaPlugin', 'group', null);
};

RudderClient.track = function() {
    exec(null, null, 'RudderSDKCordovaPlugin', 'track', null);
};

RudderClient.screen = function() {
    exec(null, null, 'RudderSDKCordovaPlugin', 'screen', null);
};

RudderClient.alias = function() {
    exec(null, null, 'RudderSDKCordovaPlugin', 'alias', null);
};

RudderClient.reset = function() {
    exec(null, null, 'RudderSDKCordovaPlugin', 'reset', []);
};

RudderClient.flush = function() {
    exec(null, null, 'RudderSDKCordovaPlugin', 'flush', []);
};

RudderClient.enable = function() {
    exec(null, null, 'RudderSDKCordovaPlugin', 'enable', []);
};

RudderClient.disable = function() {
    exec(null, null, 'RudderSDKCordovaPlugin', 'disable', []);
};

module.exports = RudderClient;