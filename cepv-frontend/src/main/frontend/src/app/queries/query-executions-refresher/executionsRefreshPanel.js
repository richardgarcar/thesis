'use strict';

app.directive('cepvExecutionsRefreshPanel', ['$interval', 'Queries', function ($interval, Queries) {
    return {
        restrict: "E",
        templateUrl: 'app/queries/query-executions-refresher/executionsRefreshPanel.html',
        scope: {
            query: '='
        },
        link: function (scope, element, attrs) {

            var queryExecRefresher;

            scope.isCollapsed = true;

            scope.onClick = function () {
                scope.isCollapsed = !scope.isCollapsed;
                if (!scope.isCollapsed) {
                    getQueryExecutions();
                    queryExecRefresher = $interval(function () {
                        getQueryExecutions()
                    }, 2000);
                } else {
                    closeInterval();
                }
            };

            function getQueryExecutions() {

                var resource = Queries.getQueryExecutions(scope.query, {currentPage: 1, pageSize: 10});

                resource.get(function (result) {
                    scope.executions = (result._embedded === undefined ? result._embedded : result._embedded.queryExecutions);
                })
            }

            function closeInterval() {
                if (angular.isDefined(queryExecRefresher)) {
                    $interval.cancel(queryExecRefresher);
                    queryExecRefresher = undefined;
                }
            }

            scope.$on('$destroy', function () {
                closeInterval();
            });
        }
    }
}]);