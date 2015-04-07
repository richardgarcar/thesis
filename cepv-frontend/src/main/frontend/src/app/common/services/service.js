var services = angular.module('visualiserServices', ['ngResource', 'spring-data-rest']);

services.factory('Experiments', ['$http', function ExperimentsFactory($http) {

    return {
        'getFilteredExperiments': function (searchFilter, pagination, sorting) {
            return $http.get('api/experiments/searchByFilter', {
                params: {
                    name: searchFilter.name,
                    description: searchFilter.description,
                    gtStart: searchFilter.gtStart,
                    ltStart: searchFilter.ltStart,
                    gtEnd: searchFilter.gtEnd,
                    ltEnd: searchFilter.ltEnd,
                    page: pagination.currentPage - 1,
                    size: pagination.pageSize,
                    sort: sorting.column + ',' + (sorting.isDesc ? 'desc' : 'asc')
                }
            });
        }
    };
}]);

services.factory('Nodes', function NodesFactory() {

    return {
        'getFilteredExperiment2NodeRelations': function (experiment, searchFilter, pagination, sorting) {
            var filteredExperiment2NodeRelationsResourceObject = {
                "name": "filteredExperiment2NodeRelations",
                "parameters": {
                    nodeExternalId: searchFilter.externalId,
                    nodeName: searchFilter.name,
                    nodeDescription: searchFilter.description,
                    gtAdditionTime: searchFilter.gtAdditionTime,
                    ltAdditionTime: searchFilter.ltAdditionTime,
                    gtRemovalTime: searchFilter.gtRemovalTime,
                    ltRemovalTime: searchFilter.ltRemovalTime,
                    page: pagination.currentPage - 1,
                    size: pagination.pageSize,
                    sort: sorting.column + ',' + (sorting.isDesc ? 'desc' : 'asc')
                }
            };

            return experiment._resources(filteredExperiment2NodeRelationsResourceObject);
        },

        'getFilteredNodeConnections': function (experiment, searchFilter, pagination, sorting) {
            var filteredNodeConnectionsResourceObject = {
                "name": "filteredNodeConnections",
                "parameters": {
                    firstNodeExternalId: searchFilter.firstNodeExternalId,
                    secondNodeExternalId: searchFilter.secondNodeExternalId,
                    firstNodeName: searchFilter.firstNodeName,
                    secondNodeName: searchFilter.secondNodeName,
                    gtConnectionTime: searchFilter.gtConnectionTime,
                    ltConnectionTime: searchFilter.ltConnectionTime,
                    gtDisconnectionTime: searchFilter.gtDisconnectionTime,
                    ltDisconnectionTime: searchFilter.ltDisconnectionTime,
                    page: pagination.currentPage - 1,
                    size: pagination.pageSize,
                    sort: sorting.column + ',' + (sorting.isDesc ? 'desc' : 'asc')
                }
            };

            return experiment._resources(filteredNodeConnectionsResourceObject);
        }
    };
});

services.factory('Queries', ['$http', function QueriesFactory($http) {

    return {
        'getFilteredQueries': function (experimentId, nodeExternalId, searchFilter, pagination) {
            return $http.get('api/experiments/' + experimentId + '/nodes/' + nodeExternalId + '/queries/searchByFilter', {
                params: {
                    gtDeploymentTime: searchFilter.gtDeployment,
                    ltDeploymentTime: searchFilter.ltDeployment,
                    page: pagination.currentPage - 1,
                    size: pagination.pageSize,
                    sort: 'deploymentTime,desc'
                }
            });
        },

        'getQueryExecutions': function (query, pagination) {
            var queryExecutionsResourceObject = {
                "name": "queryExecutions",
                "parameters": {
                    page: pagination.currentPage - 1,
                    size: pagination.pageSize,
                    sort: 'executionTime,desc'
                }
            };

            return query._resources(queryExecutionsResourceObject);
        }
    };
}]);

services.factory('DataService', function () {

    var data = {
        parentExperiment: null,
        parentNode: null,
        parentQuery: null
    };

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
        },
        'getParentQuery': function () {
            return data.parentQuery;
        },
        'setParentQuery': function (parentQuery) {
            data.parentQuery = parentQuery;
        }
    }
});
