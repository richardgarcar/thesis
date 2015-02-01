app.controller('QueryController', ['$scope', 'RestApiService', 'DataService',
    function ($scope, RestApiService, DataService) {

        var dateFilter = 'date:\'dd.MM.yyyy HH:mm:ss\'';
        $scope.parentNode = DataService.getParentNode();

        $scope.queryGridOptions = {
            expandableRowTemplate: '<div ui-grid="row.entity.subGridOptions"></div>',
            expandableRowHeight: 150,
            pagingPageSizes: [25, 50, 75, 100],
            pagingPageSize: 25,
            rowHeight: 90,
            columnDefs: [
                {field: 'content', displayName: 'Content', width: '80%', cellClass: 'break-word', enableHiding: false},
                {
                    field: 'executionDate',
                    displayName: 'Execution date',
                    width: '20%',
                    enableHiding: false,
                    cellFilter: dateFilter
                }
            ]
        };

        var getQueriesData = function (parentNode) {
            parentNode.$get('node')
                .then(function (a) {
                    return a.$get('queryList', {'projection': 'with_attributes'});
                })
                .then(function (resource) {
                    return resource.$get('queries');
                })
                .then(function (queries) {
                    for (var i = 0; i < queries.length; i++) {
                        queries[i].subGridOptions = {
                            columnDefs: [
                                {field: 'key', displayName: 'Attribute'},
                                {field: 'value', displayName: 'Attribute value'}],
                            data: queries[i].queryAttributes
                        }
                    }
                    $scope.queryGridOptions.data = queries;
                });
        };

        getQueriesData($scope.parentNode);

    }]);