'use strict';

app.controller('ExperimentController', ['$scope', 'DataService', 'Experiments', 'SpringDataRestAdapter', 'DATE_FORMAT',
    function ($scope, DataService, Experiments, SpringDataRestAdapter, DATE_FORMAT) {

        $scope.dateFormat = DATE_FORMAT;

        $scope.searchFilter = {
            name : null,
            description: null,
            gtStart: null,
            ltStart: null,
            gtEnd: null,
            ltEnd: null
        };

        $scope.pagination = {
            currentPage: 1,
            pageSize: 20,
            totalItems: 0
        };

        $scope.sorting = {
            column: 'start',
            isDesc: true
        };

        $scope.$watchCollection('searchFilter', function (newValue, oldValue) {
            $scope.getFilteredExperiments();
        });

        $scope.$watchCollection('sorting', function (newValue, oldValue) {
            $scope.resetPagingAndGetExperimentData()
        });

        $scope.setParentExperiment = function (experiment) {
            DataService.setParentExperiment(experiment);
        };

        $scope.resetPagingAndGetExperimentData = function () {
            $scope.pagination.currentPage = 1;
            $scope.getFilteredExperiments();
        };

        $scope.getFilteredExperiments = function () {

            var promise = Experiments.getFilteredExperiments($scope.searchFilter, $scope.pagination, $scope.sorting);

            SpringDataRestAdapter.process(promise).then(function (result) {
                $scope.pagination.totalItems = result.page.totalElements;
                $scope.pagination.currentPage = result.page.number + 1;
                $scope.experiments = result._embeddedItems;
            })
        };

        $scope.getFilteredExperiments();

    }]);