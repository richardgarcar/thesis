"use strict";

app.controller('TranslationController', ['$scope', '$translate',
    function ($scope, $translate) {

        $scope.changeLanguage = function (langKey) {
            $translate.use(langKey);
        };

    }]);