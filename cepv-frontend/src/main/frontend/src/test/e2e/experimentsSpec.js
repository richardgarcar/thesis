'use strict';

var ExperimentsPage = require('./page-objects/experimentsPageObj.js');

var experimentsPage = new ExperimentsPage();

describe('Experiments page', function() {
    beforeEach(function() {
        experimentsPage.getPageWithExperiments();
    });

    it('should find single result when filtering by name', function() {
        experimentsPage.setToNameFilter('experiment 30');
        expect(experimentsPage.getFilteredExperiments().count()).toEqual(1);
        expect(experimentsPage.getNameOfFirstFilteredExperiment()).toEqual('Test experiment 30');
    });

    it('should display single result when filtering by description', function() {
        experimentsPage.setToDescriptionFilter('description 30');
        expect(experimentsPage.getFilteredExperiments().count()).toEqual(1);
        expect(experimentsPage.getNameOfFirstFilteredExperiment()).toEqual('Test experiment 30');
    });

    it('should display single result when filtering by name and description', function() {
        experimentsPage.setToNameFilter('experiment');
        experimentsPage.setToDescriptionFilter('30');
        expect(experimentsPage.getFilteredExperiments().count()).toEqual(1);
        expect(experimentsPage.getNameOfFirstFilteredExperiment()).toEqual('Test experiment 30');
    });

    it('should display single result when filtering by start time interval', function() {
        experimentsPage.setStartTimeIntervalToFilter('10/10/2008', '14:20:00.077', '10/10/2008', '14:20:00.079');
        expect(experimentsPage.getFilteredExperiments().count()).toEqual(1);
        expect(experimentsPage.getNameOfFirstFilteredExperiment()).toEqual('Test experiment 30');
    });

    it('should display single result when filtering by end time interval', function() {
        experimentsPage.setEndTimeIntervalToFilter('10/10/2015', '09:20:00.122', '10/10/2015', '09:20:00.124');
        expect(experimentsPage.getFilteredExperiments().count()).toEqual(1);
        expect(experimentsPage.getNameOfFirstFilteredExperiment()).toEqual('Test experiment 1');
    });

    it('should sort filtered experiments by name in descending and ascending order', function() {
        experimentsPage.sortExperimentsByName();
        expect(experimentsPage.getNameOfFirstFilteredExperiment()).toEqual('Test experiment 9');
        experimentsPage.sortExperimentsByName();
        expect(experimentsPage.getNameOfFirstFilteredExperiment()).toEqual('Test experiment 1');
    });

    it('should sort filtered experiments by description in descending and ascending order', function() {
        experimentsPage.sortExperimentsByDescription();
        expect(experimentsPage.getNameOfFirstFilteredExperiment()).toEqual('Test experiment 9');
        experimentsPage.sortExperimentsByDescription();
        expect(experimentsPage.getNameOfFirstFilteredExperiment()).toEqual('Test experiment 1');
    });

    it('should sort filtered experiments by start in ascending and descending order', function() {
        experimentsPage.sortExperimentsByStart();
        expect(experimentsPage.getNameOfFirstFilteredExperiment()).toEqual('Test experiment 30');
        experimentsPage.sortExperimentsByStart();
        expect(experimentsPage.getNameOfFirstFilteredExperiment()).toEqual('Test experiment 1');
    });

    it('should sort filtered experiments by end in descending and ascending order', function() {
        experimentsPage.sortExperimentsByEnd();
        expect(experimentsPage.getNameOfFirstFilteredExperiment()).toEqual('Test experiment 1');
        experimentsPage.sortExperimentsByEnd();
        expect(experimentsPage.getNameOfFirstFilteredExperiment()).toEqual('Test experiment 30');
    });
});