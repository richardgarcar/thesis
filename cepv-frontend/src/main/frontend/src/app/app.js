'use strict';

var app = angular.module('cepVisualiser', ['visualiserServices', 'ui.bootstrap', 'ui.router', 'pascalprecht.translate',
    'config']);

app.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
    $stateProvider
        .state('experiments', {
            url: '/',
            controller: 'ExperimentController',
            templateUrl: 'app/experiments/experiments.html'
        })
        .state('nodes', {
            url:'/nodes',
            controller: 'NodeController',
            templateUrl: 'app/nodes/nodes.html'
        })
        .state('queries', {
            url:'/queries',
            controller: 'QueryController',
            templateUrl: 'app/queries/queries.html'
        })
        .state('queryDetail', {
            url:'/queryDetail',
            controller: 'QueryDetailController',
            templateUrl: 'app/queries/queryDetail.html'
        })
        .state('about', {
            url: '/about',
            templateUrl: 'app/about/about.html'
        });
    $urlRouterProvider.otherwise('/');
}]);

app.config(['$translateProvider', 'TRANSLATIONS_EN', 'TRANSLATIONS_CZ',
    function ($translateProvider, TRANSLATIONS_EN, TRANSLATIONS_CZ) {
    $translateProvider.translations('en', TRANSLATIONS_EN);
    $translateProvider.translations('cz', TRANSLATIONS_CZ);
    $translateProvider.preferredLanguage('en');
}]);