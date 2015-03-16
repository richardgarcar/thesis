app.controller('NodeController', ['$scope', 'RestApiService', 'DataService',
    function ($scope, RestApiService, DataService) {

        var dateFilter = 'date:\'dd.MM.yyyy HH:mm:ss:sss\'';

        var nodesPagingOptions = {
            pageNumber: 1,
            pageSize: 25,
            sort: 'additionTime,desc'
        };

        var nodesSearchOptions = {
            name: null,
            description: null,
            gtAdditionTime: null,
            ltAdditionTime: null,
            gtRemovalTime: null,
            ltRemovalTime: null
        };

        var nodeConnnectionsPagingOptions = {
            pageNumber: 1,
            pageSize: 25,
            sort: 'connectionTime,desc'
        };

        var nodeConnectionsSearchOptions = {
            firstNodeName: null,
            secondNodeName: null,
            firstNodeDescription: null,
            secondNodeDescription: null,
            gtConnectionTime: null,
            ltConnectionTime: null,
            gtDisconnectionTime: null,
            ltDisconnectionTime: null
        };

        $scope.parentExperiment = DataService.getParentExperiment();

        $scope.myViewModel = {
            setParentNode: function (experiment2node) {
                DataService.setParentNode(experiment2node);
            }
        };

        $scope.nodesGridOptions = {
            pagingPageSizes: [25, 50, 75, 100],
            enableFiltering: true,
            useExternalFiltering: true,
            useExternalPaging: true,
            useExternalSorting: true,
            columnDefs: [
                {field: 'embeddedNode.name', displayName: 'Name', enableHiding: false},
                {field: 'embeddedNode.description', displayName: 'Description', enableHiding: false},
                {field: 'additionTime', displayName: 'Addition time', enableHiding: false, cellFilter: dateFilter,
                    filters: [
                        {placeholder: 'greater than'},
                        {placeholder: 'less than'}
                    ]},
                {field: 'removalTime', displayName: 'Removal time', enableHiding: false, cellFilter: dateFilter,
                    filters: [
                        {placeholder: 'greater than'},
                        {placeholder: 'less than'}
                    ]},
                {
                    name: 'actions',
                    displayName: 'Actions',
                    enableFiltering: false,
                    enableSorting: false,
                    enableColumnMenu: false,
                    width: 120,
                    cellTemplate: '<a ui-sref="queries"><button class="btn btn-info" ng-click="getExternalScopes().setParentNode(row.entity)">Queries</button></a>'
                }
            ],
            onRegisterApi: function (gridApi) {
                $scope.gridApi = gridApi;

                $scope.gridApi.core.on.filterChanged( $scope, function() {
                    var grid = this.grid;
                    nodesSearchOptions.name = grid.columns[0].filters[0].term;
                    nodesSearchOptions.description = grid.columns[1].filters[0].term;
                    nodesSearchOptions.gtAdditionTime = grid.columns[2].filters[0].term;
                    nodesSearchOptions.ltAdditionTime = grid.columns[2].filters[1].term;
                    nodesSearchOptions.gtRemovalTime = grid.columns[3].filters[0].term;
                    nodesSearchOptions.ltRemovalTime = grid.columns[3].filters[1].term;

                    getNodesData($scope.parentExperiment);
                });

                $scope.gridApi.core.on.sortChanged($scope, function (grid, sortColumns) {
                    if (sortColumns.length == 0) {
                        nodesPagingOptions.sort = null;
                    } else {
                        nodesPagingOptions.sort = sortColumns[0].field + "," + sortColumns[0].sort.direction;
                    }
                    getNodesData($scope.parentExperiment);
                });

                gridApi.paging.on.pagingChanged($scope, function (newPage, pageSize) {
                    nodesPagingOptions.pageNumber = newPage;
                    nodesPagingOptions.pageSize = pageSize;
                    getNodesData($scope.parentExperiment);
                });
            }
        };

        $scope.nodeConnectionsGridOptions = {
            pagingPageSizes: [25, 50, 75, 100],
            enableFiltering: true,
            useExternalFiltering: true,
            useExternalPaging: true,
            useExternalSorting: true,
            columnDefs: [
                {field: 'embeddedFirstNode.name', displayName: 'First node name', enableHiding: false},
                {field: 'embeddedFirstNode.description', displayName: 'First node description', enableHiding: false},
                {field: 'embeddedSecondNode.name', displayName: 'Second node name', enableHiding: false},
                {field: 'embeddedSecondNode.description', displayName: 'Second node description', enableHiding: false},
                {field: 'connectionTime', displayName: 'Connection time', enableHiding: false, cellFilter: dateFilter,
                    filters: [
                        {placeholder: 'greater than'},
                        {placeholder: 'less than'}
                    ]},
                {field: 'disconnectionTime', displayName: 'Disconnection time', enableHiding: false, cellFilter: dateFilter,
                    filters: [
                        {placeholder: 'greater than'},
                        {placeholder: 'less than'}
                    ]}
            ],
            onRegisterApi: function (gridApi) {
                $scope.gridApi = gridApi;

                $scope.gridApi.core.on.filterChanged( $scope, function() {
                    var grid = this.grid;

                    nodeConnectionsSearchOptions.firstNodeName = grid.columns[0].filters[0].term;
                    nodeConnectionsSearchOptions.secondNodeName = grid.columns[1].filters[0].term;
                    nodeConnectionsSearchOptions.firstNodeDescription = grid.columns[2].filters[0].term;
                    nodeConnectionsSearchOptions.secondNodeDescription = grid.columns[3].filters[0].term;
                    nodeConnectionsSearchOptions.gtConnectionTime = grid.columns[4].filters[0].term;
                    nodeConnectionsSearchOptions.ltConnectionTime = grid.columns[4].filters[1].term;
                    nodeConnectionsSearchOptions.gtDisconnectionTime = grid.columns[5].filters[0].term;
                    nodeConnectionsSearchOptions.ltDisconnectionTime = grid.columns[5].filters[1].term;

                    getNodeConnectionsData($scope.parentExperiment);
                });

                $scope.gridApi.core.on.sortChanged($scope, function (grid, sortColumns) {
                    if (sortColumns.length == 0) {
                        nodeConnnectionsPagingOptions.sort = null;
                    } else {
                        nodeConnnectionsPagingOptions.sort = sortColumns[0].field + "," + sortColumns[0].sort.direction;
                    }
                    getNodeConnectionsData($scope.parentExperiment);
                });

                gridApi.paging.on.pagingChanged($scope, function (newPage, pageSize) {
                    nodeConnnectionsPagingOptions.pageNumber = newPage;
                    nodeConnnectionsPagingOptions.pageSize = pageSize;
                    getNodeConnectionsData($scope.parentExperiment);
                });
            }
        };

        var getNodesData = function (experiment) {
            experiment.$get('filteredExperiment2NodeRelations', {
                'page': nodesPagingOptions.pageNumber - 1,
                'size': nodesPagingOptions.pageSize,
                'sort': nodesPagingOptions.sort,
                'nodeName': nodesSearchOptions.name,
                'nodeDescription': nodesSearchOptions.description,
                'gtAdditionTime': nodesSearchOptions.gtAdditionTime,
                'ltAdditionTime': nodesSearchOptions.ltAdditionTime,
                'gtRemovalTime': nodesSearchOptions.gtRemovalTime,
                'ltRemovalTime': nodesSearchOptions.ltRemovalTime
            })
                .then(function (resource) {
                    $scope.nodesGridOptions.data = [];
                    $scope.nodesGridOptions.totalItems = resource.page.totalElements;
                    return resource.$get('experiment2Nodes');
                })
                .then(function (experiment2Nodes) {
                    $scope.nodesGridOptions.data = experiment2Nodes;
                });
        };

        var getNodeConnectionsData =  function (experiment) {
            experiment.$get('filteredNodeConnections', {
                'page': nodeConnnectionsPagingOptions.pageNumber - 1,
                'size': nodeConnnectionsPagingOptions.pageSize,
                'sort': nodeConnnectionsPagingOptions.sort,
                'firstNodeName': nodeConnectionsSearchOptions.firstNodeName,
                'secondNodeName': nodeConnectionsSearchOptions.secondNodeName,
                'firstNodeDescription': nodeConnectionsSearchOptions.firstNodeDescription,
                'secondNodeDescription': nodeConnectionsSearchOptions.secondNodeDescription,
                'gtConnectionTime': nodeConnectionsSearchOptions.gtConnectionTime,
                'ltConnectionTime': nodeConnectionsSearchOptions.ltConnectionTime,
                'gtDisconnectionTime': nodeConnectionsSearchOptions.gtDisconnectionTime,
                'ltDisconnectionTime': nodeConnectionsSearchOptions.ltDisconnectionTime
            })
                .then(function (resource) {
                    $scope.nodeConnectionsGridOptions.data = [];
                    $scope.nodeConnectionsGridOptions.totalItems = resource.page.totalElements;
                    return resource.$get('node2Nodes');
                })
                .then(function (node2Nodes) {
                    $scope.nodeConnectionsGridOptions.data = node2Nodes;
                });
        };

        getNodesData($scope.parentExperiment);
        getNodeConnectionsData($scope.parentExperiment);
    }]);