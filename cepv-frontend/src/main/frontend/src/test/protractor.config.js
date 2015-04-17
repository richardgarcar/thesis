exports.config = {

    specs: ['./src/test/e2e/*Spec.js'],

    capabilities: {
        'browserName': 'firefox'
    },

    jasmineNodeOpts: {
        showColors: true,
        defaultTimeoutInterval: 30000
    }
};