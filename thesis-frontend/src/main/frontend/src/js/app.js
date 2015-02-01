'use strict';

var app = angular.module('visualiserApp', ['visualiserServices', 'ui.router', 'ui.grid', 'ui.grid.paging', 'ui.grid.expandable', 'ui.grid.selection']);

app.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
    $stateProvider
        .state('home', {
            url: '/',
            controller: 'ExperimentController',
            templateUrl: 'experiments.html'
        })
        .state('nodes', {
            controller: 'NodeController',
            templateUrl: 'nodes.html'
        }).state('queries', {
            controller: 'QueryController',
            templateUrl: 'queries.html'
        }).state('about', {
            url: '/about',
            templateUrl: 'about.html'
        });

    $urlRouterProvider.otherwise('/');
}]);