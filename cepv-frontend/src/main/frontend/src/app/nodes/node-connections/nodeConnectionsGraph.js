'use strict';

app.directive('cepvNodeConnectionsGraph', function () {
    return {
        restrict: "E",
        replace: false,
        scope: {
            data: '='
        },
        link: function (scope, element, attrs) {

            var width = 640,
                height = 480;

            var svg = d3.select(element[0]).append("svg")
                .attr("width", '100%')
                .attr("height", '100%')
                .attr("viewBox", "0 0 " + width + " " + height)
                .attr("preserveAspectRatio", "xMidYMid meet");

            scope.$watch('data', function (newValue, oldValue) {

                svg.selectAll('*').remove();

                if (newValue !== undefined && newValue !== null) {

                    var nodes = [],
                        links = [];

                    newValue.forEach(function (node2node) {
                        var link = {
                            source: null,
                            target: null,
                            disconnected: false
                        };

                        link.source = nodes[node2node.embeddedFirstNode.externalId] || (nodes[node2node.embeddedFirstNode.externalId] = {name: node2node.embeddedFirstNode.externalId});
                        link.target = nodes[node2node.embeddedSecondNode.externalId] || (nodes[node2node.embeddedSecondNode.externalId] = {name: node2node.embeddedSecondNode.externalId});
                        link.disconnected = node2node.disconnectionTime != null;

                        if (!containsLinkBetweenSameNodes(links, link)) {
                            links.push(link);
                        }
                    });

                    var force = d3.layout.force()
                        .charge(-120)
                        .linkDistance(120)
                        .size([width, height])
                        .nodes(d3.values(nodes))
                        .links(links)
                        .on("tick", function () {
                            link.attr("x1", function (d) {
                                return d.source.x;
                            })
                                .attr("y1", function (d) {
                                    return d.source.y;
                                })
                                .attr("x2", function (d) {
                                    return d.target.x;
                                })
                                .attr("y2", function (d) {
                                    return d.target.y;
                                });

                            node.attr("transform", function (d) {
                                return "translate(" + d.x + "," + d.y + ")";
                            });
                        })
                        .start();

                    var link = svg.selectAll(".link")
                        .data(force.links())
                        .enter().append("line")
                        .attr("class", "link")
                        .style("stroke", function (d) {
                            return d.disconnected ? "#a94442" : "#3c763d"
                        });

                    var node = svg.selectAll(".node")
                        .data(force.nodes())
                        .enter().append("g")
                        .attr("class", "node")
                        .on("mouseover", function () {
                            d3.select(this).select("circle").transition()
                                .duration(750)
                                .attr("r", 12);
                        })
                        .on("mouseout", function () {
                            d3.select(this).select("circle").transition()
                                .duration(750)
                                .attr("r", 8);
                        })
                        .call(force.drag);

                    node.append("circle")
                        .attr("r", 8);

                    node.append("text")
                        .attr("x", 12)
                        .attr("dy", ".35em")
                        .text(function (d) {
                            return d.name;
                        });
                }
            });

            function containsLinkBetweenSameNodes(linksArray, link) {
                if (linksArray.length == 0) {
                    return false;
                } else {
                    for (var i = 0; i < linksArray.length; i++) {
                        if ((linksArray[i].source.name === link.source.name && linksArray[i].target.name === link.target.name)
                            || (linksArray[i].target.name === link.source.name && linksArray[i].source.name === link.target.name)) {
                            return true;
                        }
                    }
                }
                return false;
            }
        }
    }
});