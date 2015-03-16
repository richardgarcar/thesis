var services = angular.module('visualiserServices', ['angular-hal']);

services.factory('RestApiService', function (halClient) {

    return {
        'getRoot': function () {
            return halClient.$get('api/');
        }
    };
});

services.factory('DataService', function () {

    var data = {
        parentExperiment: null,
        parentNode: null
    }

    return {
        'getParentExperiment': function () {
            return data.parentExperiment;
        },
        'setParentExperiment': function (parentExperiment) {
            data.parentExperiment = parentExperiment;
        },
        'getParentNode': function () {
            return data.parentNode;
        },
        'setParentNode': function (parentNode) {
            data.parentNode = parentNode;
        }
    }
});
