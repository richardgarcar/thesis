'use strict';

var NodesPage = require('./nodesPageObj.js');

var nodesPage = new NodesPage();

var QueriesPage = function() {
    this.deploymentTimeDateFromFilter = element(by.css('[title="DEPLOYMENT_TIME_FROM"]')).element(by.model('dateTime.date'));
    this.deploymentTimeTimeFromFilter = element(by.css('[title="DEPLOYMENT_TIME_FROM"]')).element(by.model('dateTime.time'));
    this.deploymentTimeDateToFilter = element(by.css('[title="DEPLOYMENT_TIME_TO"]')).element(by.model('dateTime.date'));
    this.deploymentTimeTimeToFilter = element(by.css('[title="DEPLOYMENT_TIME_TO"]')).element(by.model('dateTime.time'));
    this.filteredQueries = element.all(by.repeater('query in queries'));
    this.attributesPanelOfFirstFilteredQuery = element.all(by.repeater('query in queries')).get(0).
                                                        element(by.cssContainingText('.ng-binding', 'Attributes'));
    this.attributesOfFirstFilteredQuery = element.all(by.repeater('query in queries')).get(0).
                                                        all(by.repeater('queryAttribute in query.queryAttributes'));

    this.getPageWithQueriesOfParentNode = function() {
        nodesPage.getPageWithNodesOfParentExperiment();
        nodesPage.clickOnQueriesButton();
    };

    this.setDateAndTimeToDeploymentTimeFromFilter = function(date, time) {
        this.deploymentTimeDateFromFilter.sendKeys(date);
        this.deploymentTimeTimeFromFilter.sendKeys(time);
    };

    this.setDateAndTimeToDeploymentTimeToFilter = function(date, time) {
        this.deploymentTimeDateToFilter.sendKeys(date);
        this.deploymentTimeTimeToFilter.sendKeys(time);
    };

    this.setDeploymentIntervalToFilter = function(dateFrom, timeFrom, dateTo, timeTo) {
        this.setDateAndTimeToDeploymentTimeFromFilter(dateFrom, timeFrom);
        this.setDateAndTimeToDeploymentTimeToFilter(dateTo, timeTo);
    };

    this.getFilteredQueries = function() {
        return this.filteredQueries;
    };

    this.openAttributesListOfFirstFilteredQuery = function() {
        return this.attributesPanelOfFirstFilteredQuery.click();
    };

    this.getQueryAttributesOfFirstFilteredQuery = function() {
        return this.attributesOfFirstFilteredQuery;
    };
};

module.exports = QueriesPage;