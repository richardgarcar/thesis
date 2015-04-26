'use strict';

var NodesPage = require('./page-objects/nodesPageObj.js');

var nodesPage = new NodesPage();

describe('nodes of experiment tab with filter', function() {
    beforeEach(function() {
        nodesPage.getPageWithNodesOfParentExperiment();
    });

    it('should display single result when filtering by ID', function() {
        nodesPage.setToIdFilter('PC010');
        expect(nodesPage.getFilteredNodes().count()).toEqual(1);
        expect(nodesPage.getIdOfFirstFilteredNode()).toEqual('PC010');
    });

    it('should display single result when filtering by ID', function() {
        nodesPage.setToNameFilter('N010');
        expect(nodesPage.getFilteredNodes().count()).toEqual(1);
        expect(nodesPage.getIdOfFirstFilteredNode()).toEqual('PC010');
    });

    it('should display single result when filtering by description', function() {
        nodesPage.setToDescriptionFilter('description 9');
        expect(nodesPage.getFilteredNodes().count()).toEqual(1);
        expect(nodesPage.getIdOfFirstFilteredNode()).toEqual('PC009');
    });

    it('should display single result when filtering by name and description', function() {
        nodesPage.setToNameFilter('N009');
        nodesPage.setToDescriptionFilter('Node description 9');
        expect(nodesPage.getFilteredNodes().count()).toEqual(1);
        expect(nodesPage.getIdOfFirstFilteredNode()).toEqual('PC009');
    });

    it('should display single result when filtering by addition time interval', function() {
        nodesPage.setAdditionTimeIntervalToFilter('10/10/2014', '08:00:15.071', '10/10/2014', '08:00:15.073');
        expect(nodesPage.getFilteredNodes().count()).toEqual(1);
        expect(nodesPage.getIdOfFirstFilteredNode()).toEqual('PC005');
    });

    it('should display single result when filtering by removal time interval', function() {
        nodesPage.setRemovalTimeIntervalToFilter('10/10/2014', '08:02:01.069', '10/10/2014', '08:02:01.071');
        expect(nodesPage.getFilteredNodes().count()).toEqual(1);
        expect(nodesPage.getIdOfFirstFilteredNode()).toEqual('PC005');
    });

    it('should sort filtered nodes by id in descending and ascending order', function() {
        nodesPage.sortNodesById();
        expect(nodesPage.getIdOfFirstFilteredNode()).toEqual('PC010');
        nodesPage.sortNodesById();
        expect(nodesPage.getIdOfFirstFilteredNode()).toEqual('PC001');
    });

    it('should sort filtered nodes by name in descending and ascending order', function() {
        nodesPage.sortNodesByName();
        expect(nodesPage.getIdOfFirstFilteredNode()).toEqual('PC010');
        nodesPage.sortNodesByName();
        expect(nodesPage.getIdOfFirstFilteredNode()).toEqual('PC001');
    });

    it('should sort filtered nodes by description in descending and ascending order', function() {
        nodesPage.sortNodesByDescription();
        expect(nodesPage.getIdOfFirstFilteredNode()).toEqual('PC009');
        nodesPage.sortNodesByDescription();
        expect(nodesPage.getIdOfFirstFilteredNode()).toEqual('PC001');
    });

    it('should sort filtered nodes by addition time in ascending and descending order', function() {
        nodesPage.sortNodesByAdditionTime();
        expect(nodesPage.getAdditionTimeOfFirstFilteredNode()).toEqual('10/10/2014 08:00:01.070');
        nodesPage.sortNodesByAdditionTime();
        expect(nodesPage.getAdditionTimeOfFirstFilteredNode()).toEqual('10/10/2014 08:19:00.070');
    });
});