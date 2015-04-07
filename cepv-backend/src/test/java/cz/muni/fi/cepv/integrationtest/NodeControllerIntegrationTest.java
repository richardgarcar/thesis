package cz.muni.fi.cepv.integrationtest;

import cz.muni.fi.cepv.web.LinkUtil;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.is;
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
public class NodeControllerIntegrationTest extends BaseIntegrationTest{

    @Test
    public void test01_findNodeResources() throws Exception {
        mockMvc.perform(get(LinkUtil.NODES)
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
    public void test03_findOneNodeResource() throws Exception {
        mockMvc.perform(get(LinkUtil.NODE, "PC001")
                .contentType(MediaType.APPLICATION_JSON)
                .with(httpBasic(environment.getProperty("spring.security.user"),
                        environment.getProperty("spring.security.password"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("N001")))
                .andExpect(jsonPath("$.description", is("Node description 1")));
    }

    @Test
    public void test04_createNode() throws Exception {
        mockMvc.perform(post(LinkUtil.NODES)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"externalId\": \"PC100\", \"name\": \"Node from test\"," +
                        "\"description\": \"Node from test description\"}")
                .with(httpBasic(environment.getProperty("spring.security.user"),
                        environment.getProperty("spring.security.password"))))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", is(LinkUtil.getNodeResourceLink("PC100"))));
    }

    @Test
    public void test05_updateNodeWithPatch() throws Exception {
        mockMvc.perform(patch(LinkUtil.NODE, "PC100")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"description\": \"Updated node from test description\"}")
                .with(httpBasic(environment.getProperty("spring.security.user"),
                        environment.getProperty("spring.security.password"))))
                .andExpect(status().isNoContent());
    }

    @Test
    public void test06_createNodeWithInvalidCredentials() throws Exception {
        mockMvc.perform(post(LinkUtil.NODES)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"externalId\": \"PC100\", \"name\": \"Node from test\"," +
                        "\"description\": \"Node from test description\"}")
                .with(httpBasic(environment.getProperty("spring.security.user"),
                        environment.getProperty("spring.security.password") + "1")))
                .andExpect(status().isUnauthorized());
    }
}
