package cz.muni.fi.cepv.integrationtest;

import cz.muni.fi.cepv.web.LinkUtil;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
* @author xgarcar
*/
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExperimentControllerIntegrationTest extends BaseIntegrationTest {

    @Test
    @WithMockUser()
    public void test01_findExperimentResources() throws Exception {
        mockMvc.perform(get(LinkUtil.EXPERIMENTS)
                .contentType(MediaType.APPLICATION_JSON)
                .with(httpBasic(environment.getProperty("spring.security.user"),
                        environment.getProperty("spring.security.password"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.page.size", is(20)))
                .andExpect(jsonPath("$.page.totalElements", is(30)))
                .andExpect(jsonPath("$.page.totalPages", is(2)))
                .andExpect(jsonPath("$.page.number", is(0)));
    }



    @Test
    public void test02_findFilteredExperimentResources() throws Exception {
        mockMvc.perform(get(LinkUtil.EXPERIMENTS_FILTER)
                .contentType(MediaType.APPLICATION_JSON)
                .with(httpBasic(environment.getProperty("spring.security.user"),
                        environment.getProperty("spring.security.password")))
                .param("name", "Test experiment 1")
                .param("description", "Test experiment description 1")
                .param("gtStart", "201510100800000069")
                .param("ltStart", "201510100800000071")
                .param("gtEnd", "20151010082000122")
                .param("ltEnd", "201510100820000124"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.page.size", is(20)))
                .andExpect(jsonPath("$.page.totalElements", is(1)))
                .andExpect(jsonPath("$.page.totalPages", is(1)))
                .andExpect(jsonPath("$.page.number", is(0)));
    }

    @Test
    public void test03_findOneExperimentResource() throws Exception {
        mockMvc.perform(get(LinkUtil.EXPERIMENT, 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .with(httpBasic(environment.getProperty("spring.security.user"),
                        environment.getProperty("spring.security.password"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Test experiment 1")))
                .andExpect(jsonPath("$.description", is("Test experiment description 1")))
                .andExpect(jsonPath("$.start", is("2015-10-10T08:00:00.070")))
                .andExpect(jsonPath("$.end", is("2015-10-10T08:20:00.123")));
    }

    @Test
    public void test04_createExperiment() throws Exception {
        mockMvc.perform(post(LinkUtil.EXPERIMENTS)
                .contentType(MediaType.APPLICATION_JSON)
                .with(httpBasic(environment.getProperty("spring.security.user"),
                        environment.getProperty("spring.security.password")))
                .content("{\"name\": \"Experiment from test\", \"description\": \"Description from test\"," +
                        "\"start\": 1444456800070, \"end\": \"2015-10-10T08:12:12.078\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", is(LinkUtil.getExperimentResourceLink(31L))));
    }

    @Test
    public void test05_updateExperimentWithPut() throws Exception {
        mockMvc.perform(put(LinkUtil.EXPERIMENT, 31L)
                .contentType(MediaType.APPLICATION_JSON)
                .with(httpBasic(environment.getProperty("spring.security.user"),
                        environment.getProperty("spring.security.password")))
                .content("{\"name\": \"Updated experiment from test\", \"description\": \"Description from test\"," +
                        "\"start\": 1444456800070, \"end\": 1444458000123}"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void test06_updateExperimentWithPatch() throws Exception {
        mockMvc.perform(patch(LinkUtil.EXPERIMENT, 31L)
                .contentType(MediaType.APPLICATION_JSON)
                .with(httpBasic(environment.getProperty("spring.security.user"),
                        environment.getProperty("spring.security.password")))
                .content("{\"start\": 1444456800071}"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void test07_createExperimentWithInvalidCredentials() throws Exception {
        mockMvc.perform(post(LinkUtil.EXPERIMENTS)
                .contentType(MediaType.APPLICATION_JSON)
                .with(httpBasic(environment.getProperty("spring.security.user"),
                        environment.getProperty("spring.security.password") + "1"))
                .content("{\"name\": \"Experiment from test\", \"description\": \"Description from test\"," +
                        "\"start\": 1444456800070, \"end\": \"2015-10-10T08:12:12.078\"}"))
                .andExpect(status().isUnauthorized());
    }
}
