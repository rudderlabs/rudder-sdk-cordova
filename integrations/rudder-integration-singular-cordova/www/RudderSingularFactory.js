var exec = require('cordova/exec');

var RudderSingularFactory = {};

RudderSingularFactory.setup = () => new Promise((resolve, _reject) => {
    exec(() => {
        console.log("Added Singular Device Mode Succesfully");
        resolve();
    }, (message) => {
        console.log(message);
        throw new Error(message);
    }, 'RudderSingularFactoryCordova', 'setup', null);
});

module.exports = RudderSingularFactory;