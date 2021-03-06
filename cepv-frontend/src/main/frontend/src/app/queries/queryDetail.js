app.controller('QueryDetailController', ['$scope', '$interval', 'DataService', 'Queries', 'DATE_FORMAT',
    function ($scope, $interval, DataService, Queries, DATE_FORMAT) {

        $scope.dateFormat = DATE_FORMAT;

        $scope.queryExecutionPagination = {
            currentPage: 1,
            pageSize: 15,
            totalItems: 0
        };

        $scope.intervals = [
            'HOUR',
            'MINUTE'
        ];

        $scope.selectedInterval = $scope.intervals[0];

        $scope.query = DataService.getParentQuery();

        $scope.getQueryExecutions = function () {

            var resource = Queries.getQueryExecutions($scope.query, $scope.queryExecutionPagination);

            resource.get(function (result) {
                $scope.queryExecutionPagination.currentPage = result.page.number + 1;
                $scope.queryExecutionPagination.totalItems = result.page.totalElements;
                $scope.queryExecutions = (result._embedded === undefined ? result._embedded : result._embedded.queryExecutions);
            })
        };

        $scope.getQueryExecutionsStatistics = function () {

            var resource = Queries.getQueryExecutionsStatistics($scope.query, $scope.selectedInterval);

            resource.get(function (result) {
                $scope.queryExecutionsGraphData = (result._embedded === undefined ? result._embedded : result._embedded.queryExecutionsIntervalList);
            })
        };

        $scope.getQueryExecutions();
        $scope.getQueryExecutionsStatistics();

        var queryExecRefresh = $interval(function () {
            $scope.getQueryExecutions()
        }, 2000);

        $scope.$on('$destroy', function () {
            $interval.cancel(queryExecRefresh);
        });
    }]);