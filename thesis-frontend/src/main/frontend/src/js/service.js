var services = angular.module('visualiserServices', ['angular-hal']);

services.factory('RestApiService', function (halClient) {

    return {
        'load': function () {
            return halClient.$get('api/');
        }
    };
});
