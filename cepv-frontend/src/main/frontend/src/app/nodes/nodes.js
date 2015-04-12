'use strict';

app.controller('NodeController', ['$scope', 'DataService', 'Nodes', 'DATE_FORMAT',
    function ($scope, DataService, Nodes, DATE_FORMAT) {

        $scope.dateFormat = DATE_FORMAT;

        $scope.parentExperiment = DataService.getParentExperiment();

        $scope.nodesSearchFilter = {
            externalId: null,
            name: null,
            description: null,
            gtAdditionTime: null,
            ltAdditionTime: null,
            gtRemovalTime: null,
            ltRemovalTime: null
        };

        $scope.nodesPagination = {
            currentPage: 1,
            pageSize: 20,
            totalItems: 0
        };

        $scope.nodesSorting = {
            column: 'additionTime',
            isDesc: true
        };

        $scope.$watchCollection('nodesSearchFilter', function (newValue, oldValue) {
            $scope.getFilteredExperiment2NodeRelations();
        });

        $scope.$watchCollection('nodesSorting', function (newValue, oldValue) {
            resetPagingAndGetFilteredExperiment2NodeRelations();
        });

        $scope.nodeConnectionsSearchFilter = {
            firstNodeExternalId: null,
            secondNodeExternalId: null,
            firstNodeName: null,
            secondNodeName: null,
            gtConnectionTime: null,
            ltConnectionTime: null,
            gtDisconnectionTime: null,
            ltDisconnectionTime: null
        };

        $scope.nodeConnectionsPagination = {
            currentPage: 1,
            pageSize: 20,
            totalItems: 0
        };

        $scope.nodeConnectionsSorting = {
            column: 'connectionTime',
            isDesc: true
        };

        $scope.$watchCollection('nodeConnectionsSearchFilter', function (newValue, oldValue) {
            $scope.getFilteredNodeConnections();
        });

        $scope.$watchCollection('nodeConnectionsSorting', function (newValue, oldValue) {
            resetPagingAndGetFilteredNodeConnections();
        });

        $scope.nodeConnectionsGraphSearchFilter = {
            gtConnectionTime: null,
            ltConnectionTime: null,
            gtDisconnectionTime: null,
            ltDisconnectionTime: null
        };

        $scope.$watchCollection('nodeConnectionsGraphSearchFilter', function (newValue, oldValue) {
            $scope.getFilteredNodeConnectionsForVisualisation();
        });

        function resetPagingAndGetFilteredExperiment2NodeRelations() {
            $scope.nodesPagination.currentPage = 1;
            $scope.getFilteredExperiment2NodeRelations();
        }

        function resetPagingAndGetFilteredNodeConnections() {
            $scope.nodeConnectionsPagination.currentPage = 1;
            $scope.getFilteredNodeConnections();
        }

        $scope.getFilteredExperiment2NodeRelations = function () {

            var resource = Nodes.getFilteredExperiment2NodeRelations($scope.parentExperiment, $scope.nodesSearchFilter,
                $scope.nodesPagination, $scope.nodesSorting);

            resource.get(function (result) {
                $scope.nodesPagination.totalItems = result.page.totalElements;
                $scope.nodesPagination.currentPage = result.page.number + 1;
                $scope.experiment2Nodes = (result._embedded === undefined ? result._embedded : result._embedded.experiment2Nodes);
            })
        };

        $scope.getFilteredNodeConnections = function () {

            var resource = Nodes.getFilteredNodeConnections($scope.parentExperiment, $scope.nodeConnectionsSearchFilter,
                $scope.nodeConnectionsPagination, $scope.nodeConnectionsSorting);

            resource.get(function (result) {
                $scope.nodeConnectionsPagination.totalItems = result.page.totalElements;
                $scope.nodeConnectionsPagination.currentPage = result.page.number + 1;
                $scope.node2Nodes = (result._embedded === undefined ? result._embedded : result._embedded.node2Nodes);
            })
        };

        $scope.getFilteredNodeConnectionsForVisualisation = function () {

            var resource = Nodes.getFilteredNodeConnections($scope.parentExperiment, $scope.nodeConnectionsGraphSearchFilter,
                {currentPage: 1, pageSize: 500}, {column: 'connectionTime', isDesc: true});

            resource.get(function (result) {
                $scope.nodeConnectionsGraphData = (result._embedded === undefined ? result._embedded : result._embedded.node2Nodes);
            })
        };

        $scope.setParentNode = function (experiment2node) {
            DataService.setParentNode(experiment2node);
        };

        $scope.getFilteredExperiment2NodeRelations();
        $scope.getFilteredNodeConnections();
        $scope.getFilteredNodeConnectionsForVisualisation();
    }]);
