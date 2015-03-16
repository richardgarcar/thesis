app.controller('QueryController', ['$scope', 'RestApiService', 'DataService',
    function ($scope, RestApiService, DataService) {

        $scope.isAttrPanelCollapsed = true;
        $scope.isExecPanelCollapsed = true;
        $scope.parentNode = DataService.getParentNode();

        var getQueriesData = function (parentNode) {
            parentNode.$get('node')
                .then(function (node) {
                    return node.$get('queries');
                })
                .then(function (resource) {
                    return resource.$get('queries');
                })
                .then(function (queries) {
                    $scope.queries = queries;
                });
        };

        getQueriesData($scope.parentNode);

    }]);