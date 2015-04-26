'use strict';

var NodeConnectionsPage = require('./page-objects/nodeConnectionsObj.js');

var nodeConnectionsPage = new NodeConnectionsPage();

describe('Tab \'Connections between nodes of experiment\'', function() {
    beforeEach(function() {
        nodeConnectionsPage.getPageWithNodeConnectionsOfParentExperiment();
    });

    it('should display three result elements when filtering by first node ID and second node ID', function() {
        nodeConnectionsPage.setToFirstNodeIdFilter('PC001');
        nodeConnectionsPage.setToSecondNodeIdFilter('PC002');
        expect(nodeConnectionsPage.getFilteredNodeConnections().count()).toEqual(3);
    });

    it('should display three result elements filtering by first node name and Second node name', function() {
        nodeConnectionsPage.setToFirstNodeNameFilter('N001');
        nodeConnectionsPage.setToSecondNodeNameFilter('N002');
        expect(nodeConnectionsPage.getFilteredNodeConnections().count()).toEqual(3);
    });

    it('should display single result filtering by first node id and Second node name', function() {
        nodeConnectionsPage.setToFirstNodeIdFilter('PC001');
        nodeConnectionsPage.setToSecondNodeNameFilter('N004');
        expect(nodeConnectionsPage.getFilteredNodeConnections().count()).toEqual(1);
        expect(nodeConnectionsPage.getFirstNodeIdOfFirstFilteredNode()).toEqual('PC001');
        expect(nodeConnectionsPage.getSecondNodeIdOfFirstFilteredNode()).toEqual('PC004');
    });

    it('should display single result filtering by connection time interval', function() {
        nodeConnectionsPage.setConnectionTimeIntervalToFilter('10/10/2014', '08:00:01.122', '10/10/2014', '08:00:01.124');
        expect(nodeConnectionsPage.getFilteredNodeConnections().count()).toEqual(1);
        expect(nodeConnectionsPage.getFirstNodeIdOfFirstFilteredNode()).toEqual('PC001');
        expect(nodeConnectionsPage.getSecondNodeIdOfFirstFilteredNode()).toEqual('PC002');
    });

    it('should display single result filtering by disconnection time interval', function() {
        nodeConnectionsPage.setDisconnectionTimeIntervalToFilter('10/10/2014', '08:00:59.122', '10/10/2014', '08:00:59.124');
        expect(nodeConnectionsPage.getFilteredNodeConnections().count()).toEqual(1);
        expect(nodeConnectionsPage.getFirstNodeIdOfFirstFilteredNode()).toEqual('PC004');
        expect(nodeConnectionsPage.getSecondNodeIdOfFirstFilteredNode()).toEqual('PC006');
    });

    it('should sort filtered node connections by first node id in descending and ascending order', function() {
        nodeConnectionsPage.sortNodesByFirstNodeId();
        expect(nodeConnectionsPage.getFirstNodeIdOfFirstFilteredNode()).toEqual('PC009');
        nodeConnectionsPage.sortNodesByFirstNodeId();
        expect(nodeConnectionsPage.getFirstNodeIdOfFirstFilteredNode()).toEqual('PC001');
    });

    it('should sort filtered node connections by second node id in descending and ascending order', function() {
        nodeConnectionsPage.sortNodesBySecondNodeId();
        expect(nodeConnectionsPage.getSecondNodeIdOfFirstFilteredNode()).toEqual('PC010');
        nodeConnectionsPage.sortNodesBySecondNodeId();
        expect(nodeConnectionsPage.getSecondNodeIdOfFirstFilteredNode()).toEqual('PC002');
    });

    it('should sort filtered node connections by first node name in descending and ascending order', function() {
        nodeConnectionsPage.sortNodesByFirstNodeName();
        expect(nodeConnectionsPage.getFirstNodeIdOfFirstFilteredNode()).toEqual('PC009');
        nodeConnectionsPage.sortNodesByFirstNodeName();
        expect(nodeConnectionsPage.getFirstNodeIdOfFirstFilteredNode()).toEqual('PC001');
    });

    it('should sort filtered node connections by second node name in descending and ascending order', function() {
        nodeConnectionsPage.sortNodesBySecondNodeName();
        expect(nodeConnectionsPage.getSecondNodeIdOfFirstFilteredNode()).toEqual('PC010');
        nodeConnectionsPage.sortNodesBySecondNodeName();
        expect(nodeConnectionsPage.getSecondNodeIdOfFirstFilteredNode()).toEqual('PC002');
    });

    it('should sort filtered node connections by connection time in ascending and descending order', function() {
        nodeConnectionsPage.sortNodesByConnectionTime();
        expect(nodeConnectionsPage.getConnectionTimeOfFirstFilteredNode()).toEqual('10/10/2014 08:00:01.123');
        nodeConnectionsPage.sortNodesByConnectionTime();
        expect(nodeConnectionsPage.getConnectionTimeOfFirstFilteredNode()).toEqual('10/10/2014 08:02:17.123');
    });
});