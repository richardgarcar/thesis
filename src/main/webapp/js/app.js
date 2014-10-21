var thesisModule = angular.module('thesisApp', ['angular-hal']);

thesisModule.factory('ThesisApi', function (halClient) {

    return {
        'load': function () {
            return halClient.$get('api/');
        }
    };
});

thesisModule.controller('thesisController', function ($scope, ThesisApi) {
    ThesisApi
        .load()
        .then(function (resource) {
            return resource.$get('experiments', {'page': 0, 'size': 10, 'sort': null});
        })
        .then(function (resource) {
            return resource.$get('experiments');
        })
        .then(function (experiments) {
            $scope.experiments = experiments;
        });
});