app.controller('NodeController', ['$scope', 'RestApiService', 'DataService',
    function ($scope, RestApiService, DataService) {

        var dateFilter = 'date:\'dd.MM.yyyy HH:mm:ss\'';

        $scope.parentExperiment = DataService.getParentExperiment();

        $scope.myViewModel = {
            setParentNode: function (experiment2node) {
                DataService.setParentNode(experiment2node);
            }
        };

        $scope.nodeGridOptions = {
            pagingPageSizes: [25, 50, 75, 100],
            pagingPageSize: 25,
            columnDefs: [
                {field: 'nodeName', displayName: 'Name', enableHiding: false},
                {field: 'nodeDescription', displayName: 'Description', enableHiding: false},
                {field: 'additionTime', displayName: 'Addition time', enableHiding: false, cellFilter: dateFilter},
                {field: 'removalTime', displayName: 'Removal time', enableHiding: false, cellFilter: dateFilter},
                {
                    name: 'action',
                    displayName: 'Action',
                    enableSorting: false,
                    enableColumnMenu: false,
                    width: 120,
                    cellTemplate: '<a ui-sref="queries"><button class="btn btn-info" ng-click="getExternalScopes().setParentNode(row.entity)">Queries</button></a>'
                }
            ]
        };

        $scope.nodesConnectionGridOptions = {
            pagingPageSizes: [25, 50, 75, 100],
            pagingPageSize: 25,
            columnDefs: [
                {field: 'firstNode.name', displayName: 'First node name', enableHiding: false},
                {field: 'firstNode.description', displayName: 'First node description', enableHiding: false},
                {field: 'secondNode.name', displayName: 'Second node name', enableHiding: false},
                {field: 'secondNode.description', displayName: 'Second node description', enableHiding: false},
                {field: 'connectionTime', displayName: 'Connection time', enableHiding: false, cellFilter: dateFilter},
                {
                    field: 'disconnectionTime',
                    displayName: 'Disconnection time',
                    enableHiding: false,
                    cellFilter: dateFilter
                }
            ]
        };

        var getNodesData = function (experiment) {
            experiment.$get('experiment2NodeList', {'projection': 'with_node_data'})
                .then(function (resource) {
                    return resource.$get('experiment2Nodes');
                })
                .then(function (experiment2Nodes) {
                    $scope.nodeGridOptions.data = experiment2Nodes;
                });

            //experiment.$get('node2NodeList', {'projection': 'with_nodes_data'})
            //    .then(function (resource) {
            //        return resource.$get('node2Nodes');
            //    })
            //    .then(function (node2Nodes) {
            //        $scope.nodesConnectionGridOptions.data = node2Nodes;
            //    });
        };

        getNodesData($scope.parentExperiment);
    }]);