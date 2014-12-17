var thesisModule = angular.module('visualiserApp', ['visualiserServices']);

thesisModule.controller('mainController', function ($scope, RestApiService) {
    $scope.showNodesTable = false;

    RestApiService.load()
        .then(function (resource) {
            return resource.$get('experiments', {'page': 0, 'size': 10, 'sort': null});
        })
        .then(function (resource) {
            return resource.$get('experiments');
        })
        .then(function (experiments) {
            $scope.experiments = experiments;
        });

    $scope.getNodesData = function (experiment) {
        experiment.$get('experiment2NodeList', {'projection': 'embedded'})
            .then(function (resource) {
                return resource.$get('experiment2Nodes');
            })
            .then(function (experiment2Nodes) {
                $scope.experiment2Nodes = experiment2Nodes;
                $scope.showNodesTable = true;
            });
    };
});