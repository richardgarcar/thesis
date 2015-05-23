'use strict';

var NodesPage = require('./nodesPageObj.js');

var nodesPage = new NodesPage();

var NodeConnectionsPage = function() {
    this.nodeConnectionsTab = element(by.css('[heading="Node connections"]')).element(by.tagName('a'));
    this.firstNodeIdFilter = element(by.model('nodeConnectionsSearchFilter.firstNodeExternalId'));
    this.secondNodeIdFilter = element(by.model('nodeConnectionsSearchFilter.secondNodeExternalId'));
    this.firstNodeNameFilter = element(by.model('nodeConnectionsSearchFilter.firstNodeName'));
    this.secondNodeNameFilter = element(by.model('nodeConnectionsSearchFilter.secondNodeName'));
    this.connectionTimeDateFromFilter = element(by.css('[search-filter-field="nodeConnectionsSearchFilter.gtConnectionTime"]')).
                                                    element(by.model('dateTime.date'));
    this.connectionTimeTimeFromFilter = element(by.css('[search-filter-field="nodeConnectionsSearchFilter.gtConnectionTime"]')).
                                                    element(by.model('dateTime.time'));
    this.connectionTimeDateToFilter = element(by.css('[search-filter-field="nodeConnectionsSearchFilter.ltConnectionTime"]')).
                                                    element(by.model('dateTime.date'));
    this.connectionTimeTimeToFilter = element(by.css('[search-filter-field="nodeConnectionsSearchFilter.ltConnectionTime"]')).
                                                    element(by.model('dateTime.time'));
    this.disconnectionTimeDateFromFilter = element(by.css('[search-filter-field="nodeConnectionsSearchFilter.gtDisconnectionTime"]')).
                                                    element(by.model('dateTime.date'));
    this.disconnectionTimeTimeFromFilter = element(by.css('[search-filter-field="nodeConnectionsSearchFilter.gtDisconnectionTime"]')).
                                                    element(by.model('dateTime.time'));
    this.disconnectionTimeDateToFilter = element(by.css('[search-filter-field="nodeConnectionsSearchFilter.ltDisconnectionTime"]')).
                                                    element(by.model('dateTime.date'));
    this.disconnectionTimeTimeToFilter = element(by.css('[search-filter-field="nodeConnectionsSearchFilter.ltDisconnectionTime"]')).
                                                    element(by.model('dateTime.time'));
    this.firstNodeIdSortingHeader = element(by.css('[header="firstNodeExternalId"]')).element(by.tagName('a'));
    this.secondNodeIdSortingHeader = element(by.css('[header="secondNodeExternalId"]')).element(by.tagName('a'));
    this.firstNodeNameSortingHeader = element(by.css('[header="firstNodeName"]')).element(by.tagName('a'));
    this.secondNodeNameSortingHeader = element(by.css('[header="secondNodeName"]')).element(by.tagName('a'));
    this.connectionTimeSortingHeader = element(by.css('[header="connectionTime"]')).element(by.tagName('a'));
    this.filteredNodeConnections = element.all(by.repeater('node2Node in node2Nodes'));
    this.firstNodeIdOfFirstFilteredNodeConnection = element(by.repeater('node2Node in node2Nodes').row(0).column('node2Node.embeddedFirstNode.externalId'));
    this.secondNodeIdOfFirstFilteredNodeConnection = element(by.repeater('node2Node in node2Nodes').row(0).column('node2Node.embeddedSecondNode.externalId'));
    this.connectionTimeOfFirstFilteredNodeConnection = element(by.repeater('node2Node in node2Nodes').row(0).column('node2Node.connectionTime'));

    this.getPageWithNodeConnectionsOfParentExperiment = function() {
        nodesPage.getPageWithNodesOfParentExperiment();
        this.clickOnNodeConnectionsTab();
    };

    this.setToFirstNodeIdFilter = function(value) {
        this.firstNodeIdFilter.sendKeys(value);
    };

    this.setToSecondNodeIdFilter = function(value) {
        this.secondNodeIdFilter.sendKeys(value);
    };

    this.setToFirstNodeNameFilter = function(value) {
        this.firstNodeNameFilter.sendKeys(value);
    };

    this.setToSecondNodeNameFilter = function(value) {
        this.secondNodeNameFilter.sendKeys(value);
    };

    this.setDateAndTimeToConnectionTimeFromFilter = function(date, time) {
        this.connectionTimeDateFromFilter.sendKeys(date);
        this.connectionTimeTimeFromFilter.sendKeys(time);
    };

    this.setDateAndTimeToConnectionTimeToFilter = function(date, time) {
        this.connectionTimeDateToFilter.sendKeys(date);
        this.connectionTimeTimeToFilter.sendKeys(time);
    };

    this.setConnectionTimeIntervalToFilter = function(dateFrom, timeFrom, dateTo, timeTo) {
        this.setDateAndTimeToConnectionTimeFromFilter(dateFrom, timeFrom);
        this.setDateAndTimeToConnectionTimeToFilter(dateTo, timeTo);
    };

    this.setDateAndTimeToDisconnectionTimeFromFilter = function(date, time) {
        this.disconnectionTimeDateFromFilter.sendKeys(date);
        this.disconnectionTimeTimeFromFilter.sendKeys(time);
    };

    this.setDateAndTimeToDisconnectionTimeToFilter = function(date, time) {
        this.disconnectionTimeDateToFilter.sendKeys(date);
        this.disconnectionTimeTimeToFilter.sendKeys(time);
    };

    this.setDisconnectionTimeIntervalToFilter = function(dateFrom, timeFrom, dateTo, timeTo) {
        this.setDateAndTimeToDisconnectionTimeFromFilter(dateFrom, timeFrom);
        this.setDateAndTimeToDisconnectionTimeToFilter(dateTo, timeTo);
    };

    this.getFilteredNodeConnections = function() {
        return this.filteredNodeConnections;
    };

    this.getFirstNodeIdOfFirstFilteredNode = function() {
        return this.firstNodeIdOfFirstFilteredNodeConnection.getText();
    };

    this.getSecondNodeIdOfFirstFilteredNode = function() {
        return this.secondNodeIdOfFirstFilteredNodeConnection.getText();
    };

    this.getConnectionTimeOfFirstFilteredNode = function() {
        return this.connectionTimeOfFirstFilteredNodeConnection.getText();
    };

    this.sortNodesByFirstNodeId = function() {
        this.firstNodeIdSortingHeader.click();
    };

    this.sortNodesBySecondNodeId = function() {
        this.secondNodeIdSortingHeader.click();
    };

    this.sortNodesByFirstNodeName = function() {
        this.firstNodeNameSortingHeader.click();
    };

    this.sortNodesBySecondNodeName = function() {
        this.secondNodeNameSortingHeader.click();
    };

    this.sortNodesByConnectionTime = function() {
        this.connectionTimeSortingHeader.click();
    };

    this.clickOnNodeConnectionsTab = function() {
        this.nodeConnectionsTab.click();
    }
};

module.exports = NodeConnectionsPage;