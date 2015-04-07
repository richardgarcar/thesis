package cz.muni.fi.cepv.web;

import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.mvc.BasicLinkBuilder;

/**
 * @author xgarcar
 */
public class LinkUtil {

    public static final String BASE = BasicLinkBuilder.linkToCurrentMapping().toString();

    public static final String FILTER = "/searchByFilter";
    public static final String EXPERIMENTS = "/experiments";
    public static final String EXPERIMENT = EXPERIMENTS + "/{experimentId}";
    public static final String EXPERIMENTS_FILTER = EXPERIMENTS + FILTER;

    public static final String EXPERIMENT_EXPERIMENT2NODES = EXPERIMENT + "/experiment2NodeRelations";
    public static final String EXPERIMENT_EXPERIMENT2NODE = EXPERIMENT_EXPERIMENT2NODES + "/{experiment2NodeId}";
    public static final String EXPERIMENT_EXPERIMENT2NODES_FILTER = EXPERIMENT_EXPERIMENT2NODES + FILTER;

    public static final String EXPERIMENT2NODES = "/experiment2NodeRelations";
    public static final String EXPERIMENT2NODE = EXPERIMENT2NODES + "/{experiment2NodeId}";

    public static final String NODES = "/nodes";
    public static final String NODE = NODES + "/{externalNodeId}";

    public static final String NODE_CONNECTIONS = "/nodeConnections";
    public static final String NODE_CONNECTION = NODE_CONNECTIONS + "/{node2NodeId}";
    public static final String EXPERIMENT_NODE_CONNECTIONS = EXPERIMENT + NODE_CONNECTIONS;
    public static final String EXPERIMENT_NODE_CONNECTIONS_FILTER = EXPERIMENT + NODE_CONNECTIONS + FILTER;
    public static final String EXPERIMENT_NODE_CONNECTION = EXPERIMENT + NODE_CONNECTION;

    public static final String QUERIES = "/queries";
    public static final String QUERY = QUERIES + "/{queryId}";
    public static final String EXPERIMENT_QUERIES = EXPERIMENT + QUERIES;
    public static final String NODE_QUERIES = NODE + QUERIES;
    public static final String EXPERIMENT_NODE_QUERIES = EXPERIMENT + NODE + QUERIES;
    public static final String EXPERIMENT_NODE_QUERIES_FILTER = EXPERIMENT + NODE + QUERIES + FILTER;
    public static final String QUERY_ATTRIBUTE = "/queryAttributes/{queryAttributeId}";
    public static final String QUERY_QUERY_ATTRIBUTES = QUERY + "/queryAttributes";
    public static final String QUERY_QUERY_EXECUTIONS = QUERY + "/queryExecutions";
    public static final String QUERY_EXECUTION = "/queryExecutions/{queryExecutionId}";



    private LinkUtil() {
    }

    public static String getExperimentResourceLink(final Long experimentId) {
        return (BASE + EXPERIMENT).replace("{experimentId}", experimentId.toString());
    }

    public static String getExperimentResourceExperiment2NodeRelationsLink(final Long experimentId) {
        return (BASE + EXPERIMENT_EXPERIMENT2NODES).replace("{experimentId}", experimentId.toString());
    }

    public static String getExperimentResourceFilteredExperiment2NodeRelationsLink(final Long experimentId) {
        return (BASE + EXPERIMENT_EXPERIMENT2NODES_FILTER).replace("{experimentId}", experimentId.toString());
    }

    public static String getExperiment2NodeResourceLink(final Long experiment2nodeId) {
        return (BASE + EXPERIMENT2NODE).replace("{experiment2NodeId}", experiment2nodeId.toString());
    }

    public static String getExperimentResourceNodeConnectionsLink(final Long experimentId) {
        return (BASE + EXPERIMENT_NODE_CONNECTIONS).replace("{experimentId}", experimentId.toString());
    }

    public static String getExperimentResourceFilteredNodeConnectionsLink(final Long experimentId) {
        return (BASE + EXPERIMENT_NODE_CONNECTIONS_FILTER).replace("{experimentId}", experimentId.toString());
    }

    public static String getExperimentResourceQueriesLink(final Long experimentId) {
        return (BASE + EXPERIMENT_QUERIES).replace("{experimentId}", experimentId.toString());
    }

    public static String getNodeResourceLink(final String externalNodeId) {
        return (BASE + NODE).replace("{externalNodeId}", externalNodeId);
    }

    public static String getNodeResourceQueriesLink(final String externalNodeId) {
        return (BASE + NODE_QUERIES).replace("{externalNodeId}", externalNodeId);
    }

    public static String getNode2NodeResourceLink(final Long node2NodeId) {
        return (BASE + NODE_CONNECTION).replace("{node2NodeId}", node2NodeId.toString());
    }

    public static String getQueryResourceLink(final Long queryId) {
        return (BASE + QUERY).replace("{queryId}", queryId.toString());
    }

    public static String getQueryAttributeResourceLink(final Long queryAttributeId) {
        return (BASE + QUERY_ATTRIBUTE).replace("{queryAttributeId}", queryAttributeId.toString());
    }

    public static String getQueryResourceQueryExecutionsLink(final Long queryId) {
        return (BASE + QUERY_QUERY_EXECUTIONS).replace("{queryId}", queryId.toString());
    }

    public static String getQueryExecutionResourceLink(final Long queryExecutionId) {
        return (BASE + QUERY_EXECUTION).replace("{queryExecutionId}", queryExecutionId.toString());
    }

    public static TemplateVariables getFilteredExperiment2NodeRelationsTemplateVariables() {
        return new TemplateVariables(
                new TemplateVariable("nodeExternalId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("nodeName", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("nodeDescription", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("gtAdditionTime", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("ltAdditionTime", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("gtRemovalTime", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("ltRemovalTime", TemplateVariable.VariableType.REQUEST_PARAM)
        );
    }


    public static TemplateVariables getFilteredNodeConnectionsTemplateVariables() {
        return new TemplateVariables(
                new TemplateVariable("firstNodeExternalId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("secondNodeExternalId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("firstNodeName", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("secondNodeName", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("gtConnectionTime", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("ltConnectionTime", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("gtDisconnectionTime", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("ltDisconnectionTime", TemplateVariable.VariableType.REQUEST_PARAM)
        );
    }

    public static TemplateVariables getFilteredExperimentsTemplateVariables() {
        return new TemplateVariables(
                new TemplateVariable("name", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("description", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("gtStart", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("ltStart", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("gtEnd", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("ltEnd", TemplateVariable.VariableType.REQUEST_PARAM));
    }

    public static TemplateVariables getFilteredQueriesTemplateVariables() {
        return new TemplateVariables(
                new TemplateVariable("gtDeploymentTime", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("ltDeploymentTime", TemplateVariable.VariableType.REQUEST_PARAM));
    }

    public static TemplateVariables getPageTemplateVariables() {
        return new TemplateVariables(
                new TemplateVariable("page", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("size", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("sort", TemplateVariable.VariableType.REQUEST_PARAM));
    }
}
