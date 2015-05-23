'use strict';

var QueriesPage = require('./page-objects/queriesPageObj.js');

var queriesPage = new QueriesPage();

describe('Queries page', function() {
    beforeEach(function() {
        queriesPage.getPageWithQueriesOfParentNode();
    });

    it('should display single result when filtering by deployment time interval.', function() {
        queriesPage.setDeploymentIntervalToFilter('10/10/2014', '08:00:10.069', '10/10/2014', '08:00:10.071');
        expect(queriesPage.getFilteredQueries().count()).toEqual(1);
    });

    it('should display list of four attributes when clicking on attribute panel of filtered query.', function() {
        queriesPage.openAttributesListOfFirstFilteredQuery();
        expect(queriesPage.getQueryAttributesOfFirstFilteredQuery().count()).toEqual(4);
    });
});