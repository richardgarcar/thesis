"use strict";

var config = angular.module('config', []);

config.constant('TRANSLATIONS_EN', {
    ABOUT: 'About',
    EXPERIMENTS: 'Experiments',
    EXPERIMENT: 'Experiment',
    NAME: 'Name',
    DESCRIPTION: 'Description',
    START_TIME: 'Start time',
    START_TIME_FROM: 'Start time from',
    START_TIME_TO: 'Start time to',
    END_TIME: 'End time',
    END_TIME_FROM: 'End time from',
    END_TIME_TO: 'End time to',
    ACTION: 'Action',
    NODES: 'Nodes',
    NODE: 'Node',
    NODE_CONNECTIONS: 'Node connections',
    NODE_CONNECTIONS_VISUALISATION: 'Node connections visualisation',
    NEXT: 'Next',
    PREVIOUS: 'Previous',
    ADDITION_TIME: 'Addition time',
    ADDITION_TIME_FROM: 'Addition time from',
    ADDITION_TIME_TO: 'Addition time to',
    REMOVAL_TIME: 'Removal time',
    REMOVAL_TIME_FROM: 'Removal time from',
    REMOVAL_TIME_TO: 'Removal time to',
    CONNECTION_TIME: 'Connection time',
    CONNECTION_TIME_FROM: 'Connection time from',
    CONNECTION_TIME_TO: 'Connection time to',
    DISCONNECTION_TIME: 'Disconnection time',
    DISCONNECTION_TIME_FROM: 'Disconnection time from',
    DISCONNECTION_TIME_TO: 'Disconnection time to',
    QUERIES: 'Queries',
    QUERY: 'Query',
    FIRST_NODE_ID: 'First node ID',
    SECOND_NODE_ID: 'Second node ID',
    FIRST_NODE_NAME: 'First node name',
    SECOND_NODE_NAME: 'First node name',
    DEPLOYMENT_TIME: 'Deployment time',
    DEPLOYMENT_TIME_FROM: 'Deployment time from',
    DEPLOYMENT_TIME_TO: 'Deployment time to',
    CONTENT: 'Content',
    ATTRIBUTES: 'Attributes',
    EXECUTIONS: 'Executions',
    DETAIL: 'Detail',
    EXECUTIONS_VISUALIZATION_HEADER: 'Visualisation of query executions',
    TOTAL_AMOUNT: 'Total amount',
    INTERVAL: 'Interval',
    DAY: 'Day',
    HOUR: 'Hour',
    MINUTE: 'Minute'
});
config.constant('TRANSLATIONS_CZ', {
    ABOUT: 'O aplikaci',
    EXPERIMENTS: 'Experimenty',
    EXPERIMENT: 'Experiment',
    NAME: 'Název',
    DESCRIPTION: 'Popis',
    START_TIME: 'Začátek',
    START_TIME_FROM: 'Začátek od',
    START_TIME_TO: 'Začátek do',
    END_TIME: 'Konec',
    END_TIME_FROM: 'Konec od',
    END_TIME_TO: 'Konec do',
    ACTION: 'Akce',
    NODES: 'Uzly',
    NODE: 'Uzel',
    NODE_CONNECTIONS: 'Přepojení uzlů',
    NODE_CONNECTIONS_VISUALISATION: 'Vizualizace přepojení uzlů',
    NEXT: 'Další',
    PREVIOUS: 'Předchozí',
    ADDITION_TIME: 'Čas přidání',
    ADDITION_TIME_FROM: 'Čas přidání od',
    ADDITION_TIME_TO: 'Čas přidání do',
    REMOVAL_TIME: 'Čas odebrání',
    REMOVAL_TIME_FROM: 'Čas odebrání od',
    REMOVAL_TIME_TO: 'Čas odebrání do',
    CONNECTION_TIME: 'Čas přepojení',
    CONNECTION_TIME_FROM: 'Čas přepojení od',
    CONNECTION_TIME_TO: 'Čas přepojení do',
    DISCONNECTION_TIME: 'Čas odpojení',
    DISCONNECTION_TIME_FROM: 'Čas odpojení od',
    DISCONNECTION_TIME_TO: 'Čas odpojení do',
    QUERIES: 'Dotazy',
    QUERY: 'Dotaz',
    FIRST_NODE_ID: 'ID prvního uzlu',
    SECOND_NODE_ID: 'ID druhého uzlu',
    FIRST_NODE_NAME: 'Název prvního uzlu',
    SECOND_NODE_NAME: 'Název druhého uzlu',
    DEPLOYMENT_TIME: 'Čas nasazení',
    DEPLOYMENT_TIME_FROM: 'Čas nasazení od',
    DEPLOYMENT_TIME_TO: 'Čas nasazení do',
    CONTENT: 'Obsah',
    ATTRIBUTES: 'Atributy',
    EXECUTIONS: 'Spuštění',
    DETAIL: 'Detail',
    EXECUTIONS_VISUALIZATION_HEADER: 'Vizualizace spuštění dotazu',
    TOTAL_AMOUNT: 'Celkový počet',
    INTERVAL: 'Interval',
    DAY: 'Den',
    HOUR: 'Hodina',
    MINUTE: 'Minuta'
});
config.constant('DATE_FORMAT', 'dd/MM/yyyy HH:mm:ss.sss');
