'use strict';

app.directive('cepvQueryExecutionsGraph', function () {
    return {
        restrict: 'E',
        replace: false,
        scope: {
            data: '=',
            interval: '@',
            intervalTooltipText: '@',
            executionsTooltipText: '@'
        },
        link: function (scope, element, attrs) {

            var width = 800;
            var height = 600;
            var margin = {
                top: 20,
                right: 20,
                bottom: 60,
                left: 40
            };

            var getTimeInterval = function() {
                if (scope.interval === 'MINUTE'){
                    return d3.time.second;
                }

                if (scope.interval === 'HOUR'){
                    return d3.time.minute;
                }
            };

            var getMinDate = function(date){
                if (scope.interval === 'MINUTE'){
                    return getTimeInterval().offset(date, -60);
                }

                if (scope.interval === 'HOUR'){
                    return getTimeInterval().offset(date, -60);
                }
            };

            var getTimeRange = function(){
                if (scope.interval === 'MINUTE'){
                    return d3.time.seconds;
                }

                if (scope.interval === 'HOUR'){
                    return d3.time.minutes;
                }
            };

            var getTimeFormat = function(){
                if (scope.interval === 'MINUTE'){
                    return '%H:%M:%S';
                }

                if (scope.interval === 'HOUR'){
                    return '%H:%M';
                }
            };

            var svg = d3.select(element[0]).append('svg')
                .attr('width', '100%')
                .attr('height', '100%')
                .attr('viewBox', '0 0 ' + width + ' ' + height)
                .attr('preserveAspectRatio', 'xMidYMid meet')
                .append('g')
                .attr('transform', 'translate(' + margin.left + ', ' + margin.top + ')');

            scope.$watch('data', function (newValue, oldValue) {

                svg.selectAll('*').remove(newValue);

                if (angular.isDefined(newValue)) {

                    var maxDate = getTimeInterval().offset(moment(newValue[0].intervalEndpoint), 1);
                    var minDate = getMinDate(moment(newValue[0].intervalEndpoint));

                    var tip = d3.tip()
                        .attr('class', 'd3-tip')
                        .offset([-10, 0])
                        .html(function(d) {
                            var dateFormat = d3.time.format(getTimeFormat());
                            var a = dateFormat(moment(d.intervalEndpoint).toDate());
                            var b = dateFormat(getTimeInterval().offset(moment(d.intervalEndpoint).toDate(), 1));
                            return '<strong>' + scope.intervalTooltipText + ' : </strong>' + a + ' - ' + b + '<br/>' +
                                   '<strong>' + scope.executionsTooltipText + ' : </strong>' + d.amount;
                        });

                    svg.call(tip);

                    var x = d3.time.scale()
                        .domain([minDate, maxDate])
                        .range([0, width - margin.left - margin.right]);

                    var y = d3.scale.linear()
                        .domain([0, d3.max(newValue, function (d) {
                            return d.amount > 10 ? d.amount : 10;
                        })])
                        .range([height - margin.top - margin.bottom, 0]);

                    var xAxis = d3.svg.axis()
                        .scale(x)
                        .orient('bottom')
                        .ticks(getTimeRange(), 5)
                        .tickFormat(d3.time.format(getTimeFormat()))
                        .tickSize(1)
                        .tickPadding(10);

                    var yAxis = d3.svg.axis()
                        .scale(y)
                        .orient('left')
                        .tickSize(1)
                        .tickPadding(10);

                    svg.selectAll('.chart')
                        .data(newValue)
                        .enter().append('rect')
                            .attr('class', 'bar')
                            .attr('x', function (d) {
                                return x(moment(d.intervalEndpoint));
                            })
                            .attr('y', function (d) {
                                return height - margin.top - margin.bottom -
                                    (height - margin.top - margin.bottom - y(d.amount))
                            })
                            .attr('width', 8)
                            .attr('height', function (d) {
                                return height - margin.top - margin.bottom - y(d.amount)
                            })
                            .on('mouseover', tip.show)
                            .on('mouseout', tip.hide);

                    svg.append('g')
                        .attr('class', 'xAxis')
                        .attr('transform', 'translate(0, ' + (height - margin.top - margin.bottom) + ')')
                        .call(xAxis);

                    svg.append('g')
                        .attr('class', 'yAxis')
                        .call(yAxis);

                    svg.selectAll('.xAxis text')
                        .attr('transform', function () {
                            return 'translate(' + this.getBBox().height * -2 + ',' + this.getBBox().height + ')rotate(-45)';
                        });
                }
            });
        }
    }
});