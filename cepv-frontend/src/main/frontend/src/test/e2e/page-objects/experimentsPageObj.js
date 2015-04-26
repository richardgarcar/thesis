'use strict';

var ExperimentsPage = function() {
    this.nameFilter = element(by.model('searchFilter.name'));
    this.descriptionFilter = element(by.model('searchFilter.description'));
    this.startTimeDateFromFilter = element(by.css('[title="START_TIME_FROM"]')).element(by.model('dateTime.date'));
    this.startTimeTimeFromFilter = element(by.css('[title="START_TIME_FROM"]')).element(by.model('dateTime.time'));
    this.startTimeDateToFilter = element(by.css('[title="START_TIME_TO"]')).element(by.model('dateTime.date'));
    this.startTimeTimeToFilter = element(by.css('[title="START_TIME_TO"]')).element(by.model('dateTime.time'));
    this.endTimeDateFromFilter = element(by.css('[title="END_TIME_FROM"]')).element(by.model('dateTime.date'));
    this.endTimeTimeFromFilter = element(by.css('[title="END_TIME_FROM"]')).element(by.model('dateTime.time'));
    this.endTimeDateToFilter = element(by.css('[title="END_TIME_TO"]')).element(by.model('dateTime.date'));
    this.endTimeTimeToFilter = element(by.css('[title="END_TIME_TO"]')).element(by.model('dateTime.time'));
    this.nameSortingHeader = element(by.css('[header="name"]')).element(by.tagName('a'));
    this.descriptionSortingHeader = element(by.css('[header="description"]')).element(by.tagName('a'));
    this.startSortingHeader = element(by.css('[header="start"]')).element(by.tagName('a'));
    this.endSortingHeader = element(by.css('[header="end"]')).element(by.tagName('a'));
    this.filteredExperiments = element.all(by.repeater('experiment in experiments'));
    this.nameOfFirstFilteredExperiment = element(by.repeater('experiment in experiments').row(0).column('experiment.name'));
    this.nodesButtonOfFirstFitleredExperiment = element(by.repeater('experiment in experiments').row(0)).element(by.tagName('button'));

    this.getPageWithExperiments = function() {
        browser.get('http://user:password@localhost:8080/cepv/');
    };

    this.setToNameFilter = function(value) {
        this.nameFilter.sendKeys(value);
    };

    this.setToDescriptionFilter = function(value) {
        this.descriptionFilter.sendKeys(value);
    };

    this.setDateAndTimeToStartTimeFromFilter = function(date, time) {
        this.startTimeDateFromFilter.sendKeys(date);
        this.startTimeTimeFromFilter.sendKeys(time);
    };

    this.setDateAndTimeToStartTimeToFilter = function(date, time) {
        this.startTimeDateToFilter.sendKeys(date);
        this.startTimeTimeToFilter.sendKeys(time);
    };

    this.setStartTimeIntervalToFilter = function(dateFrom, timeFrom, dateTo, timeTo) {
        this.setDateAndTimeToStartTimeFromFilter(dateFrom, timeFrom);
        this.setDateAndTimeToStartTimeToFilter(dateTo, timeTo);
    };

    this.setDateAndTimeToEndTimeFromFilter = function(date, time) {
        this.endTimeDateFromFilter.sendKeys(date);
        this.endTimeTimeFromFilter.sendKeys(time);
    };

    this.setDateAndTimeToEndTimeToFilter = function(date, time) {
        this.endTimeDateToFilter.sendKeys(date);
        this.endTimeTimeToFilter.sendKeys(time);
    };

    this.setEndTimeIntervalToFilter = function(dateFrom, timeFrom, dateTo, timeTo) {
        this.setDateAndTimeToEndTimeFromFilter(dateFrom, timeFrom);
        this.setDateAndTimeToEndTimeToFilter(dateTo, timeTo);
    };

    this.getFilteredExperiments = function() {
        return this.filteredExperiments;
    };

    this.getNameOfFirstFilteredExperiment = function() {
        return this.nameOfFirstFilteredExperiment.getText();
    };

    this.sortExperimentsByName = function() {
        this.nameSortingHeader.click();
    };

    this.sortExperimentsByDescription = function() {
        this.descriptionSortingHeader.click();
    };

    this.sortExperimentsByStart = function() {
        this.startSortingHeader.click();
    };

    this.sortExperimentsByEnd = function() {
        this.endSortingHeader.click();
    };

    this.clickOnNodesButton = function(){
        this.nodesButtonOfFirstFitleredExperiment.click();
    }
};

module.exports = ExperimentsPage;