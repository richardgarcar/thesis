'use strict';

var ExperimentsPage = require('./page-objects/experimentsPageObj.js');

var experimentsPage = new ExperimentsPage();

describe('experiments page with filter', function() {
    it('should find single result filtering by name', function() {
        experimentsPage.getPageWithExperiments();
        experimentsPage.setToNameFilter('experiment 30');
        expect(experimentsPage.getFilteredExperiments().count()).toEqual(1);
        expect(experimentsPage.getNameOfFirstFilteredExperiment()).toEqual('Test experiment 30');
    });

    it('should find single result filtering by description', function() {
        experimentsPage.getPageWithExperiments();
        experimentsPage.setToDescriptionFilter('description 30');
        expect(experimentsPage.getFilteredExperiments().count()).toEqual(1);
        expect(experimentsPage.getNameOfFirstFilteredExperiment()).toEqual('Test experiment 30');
    });

    it('should find single result filtering by name and description', function() {
        experimentsPage.getPageWithExperiments();
        experimentsPage.setToNameFilter('experiment');
        experimentsPage.setToDescriptionFilter('30');
        expect(experimentsPage.getFilteredExperiments().count()).toEqual(1);
        expect(experimentsPage.getNameOfFirstFilteredExperiment()).toEqual('Test experiment 30');
    });

    it('should find single result filtering by start time interval', function() {
        experimentsPage.getPageWithExperiments();
        experimentsPage.setStartTimeIntervalToFilter('10/10/2008', '14:20:00.077', '10/10/2008', '14:20:00.079');
        expect(experimentsPage.getFilteredExperiments().count()).toEqual(1);
        expect(experimentsPage.getNameOfFirstFilteredExperiment()).toEqual('Test experiment 30');
    });

    it('should find single result filtering by end time interval', function() {
        experimentsPage.getPageWithExperiments();
        experimentsPage.setEndTimeIntervalToFilter('10/10/2015', '08:20:00.122', '10/10/2015', '08:20:00.124');
        expect(experimentsPage.getFilteredExperiments().count()).toEqual(1);
        expect(experimentsPage.getNameOfFirstFilteredExperiment()).toEqual('Test experiment 1');
    });

    it('should sort filtered experiments by name in descending and ascending order', function() {
        experimentsPage.getPageWithExperiments();
        experimentsPage.sortExperimentsByName();
        expect(experimentsPage.getNameOfFirstFilteredExperiment()).toEqual('Test experiment 9');
        experimentsPage.sortExperimentsByName();
        expect(experimentsPage.getNameOfFirstFilteredExperiment()).toEqual('Test experiment 1');
    });

    it('should sort filtered experiments by description in descending and ascending order', function() {
        experimentsPage.getPageWithExperiments();
        experimentsPage.sortExperimentsByDescription();
        expect(experimentsPage.getNameOfFirstFilteredExperiment()).toEqual('Test experiment 9');
        experimentsPage.sortExperimentsByDescription();
        expect(experimentsPage.getNameOfFirstFilteredExperiment()).toEqual('Test experiment 1');
    });

    it('should sort filtered experiments by start in ascending and descending order', function() {
        experimentsPage.getPageWithExperiments();
        experimentsPage.sortExperimentsByStart();
        expect(experimentsPage.getNameOfFirstFilteredExperiment()).toEqual('Test experiment 30');
        experimentsPage.sortExperimentsByStart();
        expect(experimentsPage.getNameOfFirstFilteredExperiment()).toEqual('Test experiment 1');
    });

    it('should sort filtered experiments by end in descending and ascending order', function() {
        experimentsPage.getPageWithExperiments();
        experimentsPage.sortExperimentsByEnd();
        expect(experimentsPage.getNameOfFirstFilteredExperiment()).toEqual('Test experiment 1');
        experimentsPage.sortExperimentsByEnd();
        expect(experimentsPage.getNameOfFirstFilteredExperiment()).toEqual('Test experiment 30');
    });
});