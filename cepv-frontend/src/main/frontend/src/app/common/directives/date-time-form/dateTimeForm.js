"use strict";

app.directive('cepvDateTimeForm', function () {
    return {
        restrict: "E",
        templateUrl: 'app/common/directives/date-time-form/dateTimeForm.html',
        scope: {
            title: '@',
            searchFilterField: '='
        },
        link: function (scope, element, attrs) {
            scope.opened = false;

            scope.open = function($event) {
                $event.preventDefault();
                $event.stopPropagation();

                scope.opened= true;
            };

            scope.dateTime = {
                date: null,
                time: ''
            };

            scope.mergeValidInputs = function(dateTimeForm) {
                if (dateTimeForm.$valid) {
                    if (scope.dateTime.date == null) {
                        scope.searchFilterField = null;
                    } else {
                        var dateDigits = scope.dateTime.date.getFullYear().toString()
                                            + formatAsTwoDigits((scope.dateTime.date.getMonth() + 1).toString())
                                            + formatAsTwoDigits(scope.dateTime.date.getDate().toString());

                        var timeDigits;

                        if (scope.dateTime.time.length > 1) {
                            var timeDigitsArray = scope.dateTime.time.split(/:|\./);
                            timeDigits = timeDigitsArray[0] + timeDigitsArray[1]
                                        + timeDigitsArray[2] + timeDigitsArray[3];
                        } else {
                            timeDigits = '0000000000'
                        }

                        scope.searchFilterField = dateDigits + timeDigits;
                    }
                }
            };

            function formatAsTwoDigits(expression) {
                return expression.length == 1 ? '0' + expression : expression;
            }

            scope.cepvDateTimePatterns = {
                date: /^(0[1-9]|[12]\d|3[01])\/(0?[1-9]|1[012])\/((19|20)\d{2})$/,
                time: /^([01]\d|2[0-3]):([0-5]\d):([0-5]\d)\.(\d{3})$/
            };
        }
    }
});