var exec = require('cordova/exec');

var RudderAppsflyerFactory = {};

RudderAppsflyerFactory.setup = () => new Promise((resolve, _reject) => {
    exec(() => {
        console.log("Added Appsflyer Device Mode Succesfully");
        resolve();
    }, (message) => {
        console.log(message);
        throw new Error(message);
    }, 'RudderAppsflyerFactoryCordova', 'setup', null);
});

module.exports = RudderAppsflyerFactory;