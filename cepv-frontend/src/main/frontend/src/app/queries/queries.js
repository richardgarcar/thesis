app.controller('QueryController', ['$scope', 'DataService', 'Queries', 'SpringDataRestAdapter', 'DATE_FORMAT',
    function ($scope, DataService, Queries, SpringDataRestAdapter, DATE_FORMAT) {

        $scope.dateFormat = DATE_FORMAT;

        $scope.searchFilter = {
            gtDeployment: null,
            ltDeployment: null
        };

        $scope.pagination = {
            currentPage: 1,
            pageSize: 5,
            totalItems: 0
        };

        $scope.isAttrPanelCollapsed = true;
        $scope.isExecPanelCollapsed = true;

        $scope.parentExperiment = DataService.getParentExperiment();
        $scope.parentNode = DataService.getParentNode();

        $scope.$watchCollection('searchFilter', function (newValue, oldValue) {
            $scope.getFilteredQueries();
        });

        $scope.getFilteredQueries = function () {

            var promise = Queries.getFilteredQueries($scope.parentExperiment.id, $scope.parentNode.embeddedNode.externalId,
                $scope.searchFilter, $scope.pagination);

            SpringDataRestAdapter.process(promise).then(function (result) {
                $scope.pagination.totalItems = result.page.totalElements;
                $scope.pagination.currentPage = result.page.number + 1;
                $scope.queries = result._embeddedItems;
            })
        };

        $scope.setParentQuery = function (query) {
            DataService.setParentQuery(query);
        };

        $scope.getFilteredQueries();
    }]);