app.controller('ExperimentController', ['$scope', 'RestApiService', 'DataService',
    function ($scope, RestApiService, DataService) {

        var dateFilter = 'date:\'dd.MM.yyyy HH:mm:ss\'';

        var pagingOptions = {
            pageNumber: 1,
            pageSize: 25,
            sort: 'start,desc'
        };

        var searchOptions = {
            name: null,
            description: null,
            gtStart: null,
            ltStart: null,
            gtEnd: null,
            ltEnd: null
        };

        $scope.myViewModel = {
            setParentExperiment: function (parentExperiment) {
                DataService.setParentExperiment(parentExperiment);
            }
        };

        $scope.experimentGridOptions = {
            pagingPageSizes: [25, 50, 75, 100],
            enableFiltering: true,
            useExternalFiltering: true,
            useExternalPaging: true,
            useExternalSorting: true,
            columnDefs: [
                {field: 'name', enableHiding: false},
                {field: 'description', enableHiding: false},
                {
                    field: 'start', displayName: 'Start time', enableHiding: false, cellFilter: dateFilter,
                    filters: [
                        {placeholder: 'greater than'},
                        {placeholder: 'less than'}
                    ]
                },
                {
                    field: 'end', displayName: 'End time', enableHiding: false, cellFilter: dateFilter,
                    filters: [
                        {placeholder: 'greater than'},
                        {placeholder: 'less than'}
                    ]
                },
                {
                    name: 'action',
                    displayName: 'Action',
                    enableSorting: false,
                    enableColumnMenu: false,
                    enableFiltering: false,
                    cellTemplate: '<a ui-sref="nodes"><button class="btn btn-info" ng-click="getExternalScopes().setParentExperiment(row.entity)">Nodes</button></a>'
                }

            ],
            onRegisterApi: function (gridApi) {
                $scope.gridApi = gridApi;

                $scope.gridApi.core.on.filterChanged( $scope, function() {
                    var grid = this.grid;
                    searchOptions.name = grid.columns[0].filters[0].term;
                    searchOptions.description = grid.columns[1].filters[0].term;
                    searchOptions.gtStart = grid.columns[2].filters[0].term;
                    searchOptions.ltStart = grid.columns[2].filters[1].term;
                    searchOptions.gtEnd = grid.columns[3].filters[0].term;
                    searchOptions.ltEnd = grid.columns[3].filters[1].term;

                    getExperimentData();
                });

                $scope.gridApi.core.on.sortChanged($scope, function (grid, sortColumns) {
                    if (sortColumns.length == 0) {
                        pagingOptions.sort = null;
                    } else {
                        pagingOptions.sort = sortColumns[0].field + "," + sortColumns[0].sort.direction;
                    }
                    getExperimentData();
                });

                gridApi.paging.on.pagingChanged($scope, function (newPage, pageSize) {
                    pagingOptions.pageNumber = newPage;
                    pagingOptions.pageSize = pageSize;
                    getExperimentData();
                });
            }
        };

        var getExperimentData = function () {
            RestApiService.getRoot()
                .then(function (resource) {
                    return resource.$get('experiments', {
                        'page': pagingOptions.pageNumber - 1,
                        'size': pagingOptions.pageSize,
                        'sort': pagingOptions.sort
                    });
                })
                .then(function (resource) {
                    $scope.experimentGridOptions.totalItems = resource.page.totalElements;
                    return resource.$get('experiments');
                })
                .then(function (experiments) {
                    $scope.experimentGridOptions.data = experiments;
                });
        };

        getExperimentData();

    }]);