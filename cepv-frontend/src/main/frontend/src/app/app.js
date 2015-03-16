'use strict';

var app = angular.module('visualiserApp', ['visualiserServices', 'ui.bootstrap', 'ui.router', 'ui.grid', 'ui.grid.paging', 'ui.grid.expandable', 'ui.grid.selection']);

app.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
    $stateProvider
        .state('home', {
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
        .state('about', {
            url: '/about',
            templateUrl: 'app/about.html'
        });
    $urlRouterProvider.otherwise('/');
}]);