package cz.muni.fi.cepv.integrationtest;

import cz.muni.fi.cepv.web.LinkUtil;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author xgarcar
 */
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Node2NodeControllerIntegrationTest extends BaseIntegrationTest {

    @Test
    public void test01_findNode2NodeResources() throws Exception {
        mockMvc.perform(get(LinkUtil.NODE_CONNECTIONS)
                .contentType(MediaType.APPLICATION_JSON)
                .with(httpBasic(environment.getProperty("spring.security.user"),
                        environment.getProperty("spring.security.password"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.page.size", is(20)))
                .andExpect(jsonPath("$.page.totalElements", is(23)))
                .andExpect(jsonPath("$.page.totalPages", is(2)))
                .andExpect(jsonPath("$.page.number", is(0)));
    }

    @Test
    public void test02_findOneNode2NodeResource() throws Exception {
        mockMvc.perform(get(LinkUtil.NODE_CONNECTION, 2L)
                .contentType(MediaType.APPLICATION_JSON)
                .with(httpBasic(environment.getProperty("spring.security.user"),
                        environment.getProperty("spring.security.password"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.embeddedFirstNode.name", is("N002")))
                .andExpect(jsonPath("$.embeddedFirstNode.description", is("Node description 2")))
                .andExpect(jsonPath("$.embeddedSecondNode.name", is("N003")))
                .andExpect(jsonPath("$.embeddedSecondNode.description", is("Node description 3")))
                .andExpect(jsonPath("$.connectionTime", is("2014-10-10 08:00:13.123")))
                .andExpect(jsonPath("$.disconnectionTime", isEmptyOrNullString()));
    }

    @Test
    public void test03_findNode2NodeResourcesByExperiments() throws Exception {
        mockMvc.perform(get(LinkUtil.EXPERIMENT_NODE_CONNECTIONS, 2L)
                .contentType(MediaType.APPLICATION_JSON)
                .with(httpBasic(environment.getProperty("spring.security.user"),
                        environment.getProperty("spring.security.password"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.page.size", is(20)))
                .andExpect(jsonPath("$.page.totalElements", is(5)))
                .andExpect(jsonPath("$.page.totalPages", is(1)))
                .andExpect(jsonPath("$.page.number", is(0)));
    }

    @Test
    public void test04_findNode2NodeResourcesByExperimentsWithEmptyResult() throws Exception {
        mockMvc.perform(get(LinkUtil.EXPERIMENT_NODE_CONNECTIONS, 3L)
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
    public void test06_findFilteredNode2NodeResourcesByExperiments() throws Exception {
        mockMvc.perform(get(LinkUtil.EXPERIMENT_NODE_CONNECTIONS_FILTER, 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .with(httpBasic(environment.getProperty("spring.security.user"),
                        environment.getProperty("spring.security.password")))
                .param("firstNodeName", "N001")
                .param("secondNodeName", "N002")
                .param("gtConnectionTime", "20141010080001122")
                .param("ltConnectionTime", "20141010080001124")
                .param("gtDisconnectionTime", "20141010080012122")
                .param("ltDisconnectionTime", "20141010080012124"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.page.size", is(20)))
                .andExpect(jsonPath("$.page.totalElements", is(1)))
                .andExpect(jsonPath("$.page.totalPages", is(1)))
                .andExpect(jsonPath("$.page.number", is(0)));
    }

    @Test
    public void test07_createNode2NodeConnection() throws Exception {
        mockMvc.perform(post(LinkUtil.EXPERIMENT_NODE_CONNECTIONS, 2L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstNode\": \"PC011\", \"secondNode\": \"PC014\",\"connectionTime\": 1412920801123, \"disconnectionTime\": null}")
                .with(httpBasic(environment.getProperty("spring.security.user"),
                        environment.getProperty("spring.security.password"))))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", is(LinkUtil.getNode2NodeResourceLink(24L))));
    }

    @Test
    public void test08_updateNode2NodeConnection() throws Exception {
        mockMvc.perform(patch(LinkUtil.EXPERIMENT_NODE_CONNECTION, 2L, 19L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"disconnectionTime\": 1412920801123}")
                .with(httpBasic(environment.getProperty("spring.security.user"),
                        environment.getProperty("spring.security.password"))))
                .andExpect(status().isNoContent());
    }

    @Test
    public void test09_createNode2NodeConnectionWithInvalidCredentials() throws Exception {
        mockMvc.perform(post(LinkUtil.EXPERIMENT_NODE_CONNECTIONS, 2L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstNode\": \"PC011\", \"secondNode\": \"PC014\",\"connectionTime\": 1412920801123, \"disconnectionTime\": null}")
                .with(httpBasic(environment.getProperty("spring.security.user"),
                        environment.getProperty("spring.security.password") + "1")))
                .andExpect(status().isUnauthorized());
    }
}
