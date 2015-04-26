'use strict';

var ExperimentsPage = require('./experimentsPageObj.js');

var experimentsPage = new ExperimentsPage();

var NodesPage = function() {
    this.idFilter = element(by.model('nodesSearchFilter.externalId'));
    this.nameFilter = element(by.model('nodesSearchFilter.name'));
    this.descriptionFilter = element(by.model('nodesSearchFilter.description'));
    this.additionTimeDateFromFilter = element(by.css('[title="ADDITION_TIME_FROM"]')).element(by.model('dateTime.date'));
    this.additionTimeTimeFromFilter = element(by.css('[title="ADDITION_TIME_FROM"]')).element(by.model('dateTime.time'));
    this.additionTimeDateToFilter = element(by.css('[title="ADDITION_TIME_TO"]')).element(by.model('dateTime.date'));
    this.additionTimeTimeToFilter = element(by.css('[title="ADDITION_TIME_TO"]')).element(by.model('dateTime.time'));
    this.removalTimeDateFromFilter = element(by.css('[title="REMOVAL_TIME_FROM"]')).element(by.model('dateTime.date'));
    this.removalTimeTimeFromFilter = element(by.css('[title="REMOVAL_TIME_FROM"]')).element(by.model('dateTime.time'));
    this.removalTimeDateToFilter = element(by.css('[title="REMOVAL_TIME_TO"]')).element(by.model('dateTime.date'));
    this.removalTimeTimeToFilter = element(by.css('[title="REMOVAL_TIME_TO"]')).element(by.model('dateTime.time'));
    this.idSortingHeader = element(by.css('[header="nodeExternalId"]')).element(by.tagName('a'));
    this.nameSortingHeader = element(by.css('[header="nodeName"]')).element(by.tagName('a'));
    this.descriptionSortingHeader = element(by.css('[header="nodeDescription"]')).element(by.tagName('a'));
    this.additionTimeSortingHeader = element(by.css('[header="additionTime"]')).element(by.tagName('a'));
    this.filteredNodes = element.all(by.repeater('experiment2Node in experiment2Nodes'));
    this.idOfFirstFilteredNode = element(by.repeater('experiment2Node in experiment2Nodes').row(0).column('experiment2Node.embeddedNode.externalId'));
    this.additionTimeOfFirstFilteredNode = element(by.repeater('experiment2Node in experiment2Nodes').row(0).column('experiment2Node.additionTime'));

    this.getPageWithNodesOfParentExperiment = function() {
        experimentsPage.getPageWithExperiments();
        experimentsPage.clickOnNodesButton();
    };

    this.setToIdFilter = function(value) {
        this.idFilter.sendKeys(value);
    };

    this.setToNameFilter = function(value) {
        this.nameFilter.sendKeys(value);
    };

    this.setToDescriptionFilter = function(value) {
        this.descriptionFilter.sendKeys(value);
    };

    this.setDateAndTimeToAdditionTimeFromFilter = function(date, time) {
        this.additionTimeDateFromFilter.sendKeys(date);
        this.additionTimeTimeFromFilter.sendKeys(time);
    };

    this.setDateAndTimeToAdditionTimeToFilter = function(date, time) {
        this.additionTimeDateToFilter.sendKeys(date);
        this.additionTimeTimeToFilter.sendKeys(time);
    };

    this.setAdditionTimeIntervalToFilter = function(dateFrom, timeFrom, dateTo, timeTo) {
        this.setDateAndTimeToAdditionTimeFromFilter(dateFrom, timeFrom);
        this.setDateAndTimeToAdditionTimeToFilter(dateTo, timeTo);
    };

    this.setDateAndTimeToRemovalTimeFromFilter = function(date, time) {
        this.removalTimeDateFromFilter.sendKeys(date);
        this.removalTimeTimeFromFilter.sendKeys(time);
    };

    this.setDateAndTimeToRemovalTimeToFilter = function(date, time) {
        this.removalTimeDateToFilter.sendKeys(date);
        this.removalTimeTimeToFilter.sendKeys(time);
    };

    this.setRemovalTimeIntervalToFilter = function(dateFrom, timeFrom, dateTo, timeTo) {
        this.setDateAndTimeToRemovalTimeFromFilter(dateFrom, timeFrom);
        this.setDateAndTimeToRemovalTimeToFilter(dateTo, timeTo);
    };

    this.getFilteredNodes = function() {
        return this.filteredNodes;
    };

    this.getIdOfFirstFilteredNode = function() {
        return this.idOfFirstFilteredNode.getText();
    };

    this.getAdditionTimeOfFirstFilteredNode = function() {
        return this.additionTimeOfFirstFilteredNode.getText();
    };

    this.sortNodesById = function() {
        this.idSortingHeader.click();
    };

    this.sortNodesByName = function() {
        this.nameSortingHeader.click();
    };

    this.sortNodesByDescription = function() {
        this.descriptionSortingHeader.click();
    };

    this.sortNodesByAdditionTime = function() {
        this.additionTimeSortingHeader.click();
    };
};

module.exports = NodesPage;