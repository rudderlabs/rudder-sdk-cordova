var exec = require('cordova/exec');

var RudderAppCenterFactory = {};

RudderAppCenterFactory.setup = () => new Promise((resolve, reject) => {
    exec(() => {
        console.log("Added AppCenter Device Mode Succesfully");
        resolve();
    }, (message) => {
        console.log(message);
        throw new Error(message);
    }, 'RudderAppCenterFactoryCordova', 'setup', null);
});

module.exports = RudderAppCenterFactory;