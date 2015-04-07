package cz.muni.fi.cepv.integrationtest;

import cz.muni.fi.cepv.web.LinkUtil;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author xgarcar
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Experiment2NodeControllerIntegrationTest extends BaseIntegrationTest {

    @Test
    public void test01_findExperiment2NodeResources() throws Exception {
        mockMvc.perform(get(LinkUtil.EXPERIMENT2NODES)
                .contentType(MediaType.APPLICATION_JSON)
                .with(httpBasic(environment.getProperty("spring.security.user"),
                        environment.getProperty("spring.security.password"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.page.size", is(20)))
                .andExpect(jsonPath("$.page.totalElements", is(15)))
                .andExpect(jsonPath("$.page.totalPages", is(1)))
                .andExpect(jsonPath("$.page.number", is(0)));
    }

    @Test
    public void test02_findOneExperiment2NodeResource() throws Exception {
        mockMvc.perform(get(LinkUtil.EXPERIMENT2NODE, 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .with(httpBasic(environment.getProperty("spring.security.user"),
                        environment.getProperty("spring.security.password"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.embeddedNode.externalId", is("PC001")))
                .andExpect(jsonPath("$.embeddedNode.name", is("N001")))
                .andExpect(jsonPath("$.embeddedNode.description", is("Node description 1")))
                .andExpect(jsonPath("$.additionTime", is("2014-10-10T08:00:01.070")))
                .andExpect(jsonPath("$.removalTime", is(nullValue())));
    }

    @Test
    public void test03_findExperiment2NodeResourcesByExperiment() throws Exception {
        mockMvc.perform(get(LinkUtil.EXPERIMENT_EXPERIMENT2NODES, 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .with(httpBasic(environment.getProperty("spring.security.user"),
                        environment.getProperty("spring.security.password"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.page.size", is(20)))
                .andExpect(jsonPath("$.page.totalElements", is(10)))
                .andExpect(jsonPath("$.page.totalPages", is(1)))
                .andExpect(jsonPath("$.page.number", is(0)));
    }

    @Test
    public void test04_findExperiment2NodeResourcesByExperimentWithEmptyResult() throws Exception {
        mockMvc.perform(get(LinkUtil.EXPERIMENT_EXPERIMENT2NODES, 3L)
                .contentType(MediaType.APPLICATION_JSON)
                .with(httpBasic(environment.getProperty("spring.security.user"),
                        environment.getProperty("spring.security.password"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.page.size", is(20)))
                .andExpect(jsonPath("$.page.totalElements", is(0)))
                .andExpect(jsonPath("$.page.totalPages", is(0)))
                .andExpect(jsonPath("$.page.number", is(0)));
    }

    @Test
    public void test05_findFilteredExperiment2NodeResourcesByExperiment() throws Exception {
        mockMvc.perform(get(LinkUtil.EXPERIMENT_EXPERIMENT2NODES_FILTER, 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .param("nodeExternalId", "PC002")
                .param("nodeName", "N002")
                .param("nodeDescription", "Node description 2")
                .param("gtAdditionTime", "20141010080014069")
                .param("ltAdditionTime", "20141010080014071")
                .param("gtRemovalTime", "20141010080200069")
                .param("ltRemovalTime", "20141010080200071")
                .with(httpBasic(environment.getProperty("spring.security.user"),
                        environment.getProperty("spring.security.password"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.page.size", is(20)))
                .andExpect(jsonPath("$.page.totalElements", is(1)))
                .andExpect(jsonPath("$.page.totalPages", is(1)))
                .andExpect(jsonPath("$.page.number", is(0)));
    }

    @Test
    public void test06_createExperiment2Node() throws Exception {
        mockMvc.perform(post(LinkUtil.EXPERIMENT_EXPERIMENT2NODES, 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"node\": \"PC002\"," +
                        "\"additionTime\": \"2014-10-10T09:02:00.070\", \"removalTime\": null}")
                .with(httpBasic(environment.getProperty("spring.security.user"),
                        environment.getProperty("spring.security.password"))))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", is(LinkUtil.getExperiment2NodeResourceLink(16L))));
    }

    @Test
    public void test07_updateExperiment2Node() throws Exception {
        mockMvc.perform(patch(LinkUtil.EXPERIMENT_EXPERIMENT2NODE, 1L, 16L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"removalTime\": \"2014-10-10T09:24:00.070\"}")
                .with(httpBasic(environment.getProperty("spring.security.user"),
                        environment.getProperty("spring.security.password"))))
                .andExpect(status().isNoContent());
    }

    @Test
    public void test08_createExperiment2NodeWithInvalidCredentials() throws Exception {
        mockMvc.perform(post(LinkUtil.EXPERIMENT_EXPERIMENT2NODES, 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"node\": \"PC002\"," +
                        "\"additionTime\": \"2014-10-10T09:02:00.070\", \"removalTime\": null}")
                .with(httpBasic(environment.getProperty("spring.security.user"),
                        environment.getProperty("spring.security.password") + "1")))
                .andExpect(status().isUnauthorized());
    }
}
