"use strict";

app.directive('cepvSortingHeader', function () {
    return {
        restrict: "E",
        templateUrl: 'app/common/directives/sorting-header/sortingHeader.html',
        scope: {
            title: '@',
            header: '@',
            column: '=',
            isDesc: '='
        },
        link: function (scope) {

            scope.setSortingHeaderAndDirection = function () {
                if (scope.column != scope.header) {
                    scope.column = scope.header;
                    scope.isDesc = true;
                } else {
                    scope.isDesc = !scope.isDesc;
                }
            };

            scope.canDisplayCaretUp = function () {
                return (scope.column == scope.header && !scope.isDesc);
            };

            scope.canDisplayCaretDown = function () {
                return (scope.column == scope.header && scope.isDesc);
            };
        }
    }
});