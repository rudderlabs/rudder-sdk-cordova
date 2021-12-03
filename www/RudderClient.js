var exec = require('cordova/exec');

var RudderClient = {};

RudderClient.initialize = (writeKey, config, options) => new Promise(async (resolve, reject) => {
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

    await initializeFactories(config);

    exec(() => {
        console.log("Initialized Rudder Cordova SDK Succesfully");
        resolve();
    }, (message) => {
        console.log(message);
        throw new Error(message);
    }, 'RudderSDKCordovaPlugin', 'initialize', params);
});

RudderClient.identify = (userId, traits, options) => {
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

RudderClient.group = (groupId, groupTraits, options) => {
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

RudderClient.track = (eventName, properties, options) => {
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

RudderClient.screen = (screenName, properties, options) => {
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

RudderClient.alias = (newId, options) => {
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

RudderClient.reset = () => {
    exec(null, null, 'RudderSDKCordovaPlugin', 'reset', []);
};

RudderClient.flush = () => {
    exec(null, null, 'RudderSDKCordovaPlugin', 'flush', []);
};

RudderClient.putDeviceToken = (deviceToken) => {
    if (!isValidString(deviceToken)) {
        console.log("deviceToken is Invalid, dropping putDeviceToken call");
        return;
    }

    var params = [];
    params[0] = deviceToken;

    exec(null, null, 'RudderSDKCordovaPlugin', 'putDeviceToken', params);
}

/** @deprecated use {@link RudderClient.putAdvertisingId} instead */
RudderClient.setAdvertisingId = (advertisingId) => {
    this.putAdvertisingId(advertisingId);
}

RudderClient.putAdvertisingId = (advertisingId) => {
  if (!isValidString(advertisingId)) {
        console.log("advertisingId is Invalid, dropping putAdvertisingId call");
        return;
    }

    var params = [];
    params[0] = advertisingId;

    exec(null, null, 'RudderSDKCordovaPlugin', 'putAdvertisingId', params);
}

/** @deprecated use {@link RudderClient.putAnonymousId} instead */
RudderClient.setAnonymousId = (anonymousId) => {
    this.putAnonymousId(anonymousId);
}


RudderClient.putAnonymousId = (anonymousId) => {
    if (!isValidString(anonymousId)) {
        console.log("anonymousId is Invalid, dropping putAnonymousId call");
        return;
    }

    var params = [];
    params[0] = anonymousId;

    exec(null, null, 'RudderSDKCordovaPlugin', 'putAnonymousId', params);
}

RudderClient.optOut = (optOut) => {
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

const initializeFactories = (config) => {
    if (config && config.factories) {
        if (Array.isArray(config.factories)) {
            try {
                config.factories.forEach(async (factory) => { await factory.setup() });
            }
            catch (err) {
                console.log(err);
            }
        }
        else {
            console.log("Unable to initialize factories, as an invalid value is passed")
        }
    }
}

const isValidString = (value) => {
    if (typeof value === "undefined")
        return false;
    if (value === "")
        return false;
    return typeof value === "string" || value instanceof String
}


const isOptions = (value) => {
    if (value && (value.integrations || value.externalIds)) {
        return true;
    }
    return false;
}

const isValidBoolean = (value) => {
    if (typeof value === "undefined")
        return false;
    return typeof value === "boolean" || value instanceof Boolean
}

module.exports = RudderClient;