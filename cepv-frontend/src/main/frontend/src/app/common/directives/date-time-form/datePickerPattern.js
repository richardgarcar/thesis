"use strict";

app.directive('cepvDatepickerPattern',function() {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function(scope,elem,attrs,ngModelCtrl) {
            var dRegex = new RegExp(attrs.cepvDatepickerPattern);

            ngModelCtrl.$parsers.unshift(function(value) {
                ngModelCtrl.$setValidity('datep',true);

                if (typeof value === 'string' && value.length > 0) {
                    var isValid = dRegex.test(value);
                    ngModelCtrl.$setValidity('datep',isValid);
                    if (!isValid) {
                        return undefined;
                    }
                }

                return value;
            });

        }
    };
});