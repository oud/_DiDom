package com.didom.myapp.web.rest;

import com.didom.myapp.DiDomApp;

import com.didom.myapp.domain.Freelancer;
import com.didom.myapp.repository.FreelancerRepository;
import com.didom.myapp.service.FreelancerService;
import com.didom.myapp.service.dto.FreelancerDTO;
import com.didom.myapp.service.mapper.FreelancerMapper;
import com.didom.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the FreelancerResource REST controller.
 *
 * @see FreelancerResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DiDomApp.class)
public class FreelancerResourceIntTest {

    private static final byte[] DEFAULT_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_CONTENT_TYPE = "image/png";

    @Autowired
    private FreelancerRepository freelancerRepository;

    @Autowired
    private FreelancerMapper freelancerMapper;

    @Autowired
    private FreelancerService freelancerService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFreelancerMockMvc;

    private Freelancer freelancer;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        FreelancerResource freelancerResource = new FreelancerResource(freelancerService);
        this.restFreelancerMockMvc = MockMvcBuilders.standaloneSetup(freelancerResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Freelancer createEntity(EntityManager em) {
        Freelancer freelancer = new Freelancer()
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE);
        return freelancer;
    }

    @Before
    public void initTest() {
        freelancer = createEntity(em);
    }

    @Test
    @Transactional
    public void createFreelancer() throws Exception {
        int databaseSizeBeforeCreate = freelancerRepository.findAll().size();

        // Create the Freelancer
        FreelancerDTO freelancerDTO = freelancerMapper.freelancerToFreelancerDTO(freelancer);
        restFreelancerMockMvc.perform(post("/api/freelancers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freelancerDTO)))
            .andExpect(status().isCreated());

        // Validate the Freelancer in the database
        List<Freelancer> freelancerList = freelancerRepository.findAll();
        assertThat(freelancerList).hasSize(databaseSizeBeforeCreate + 1);
        Freelancer testFreelancer = freelancerList.get(freelancerList.size() - 1);
        assertThat(testFreelancer.getImage()).isEqualTo(DEFAULT_IMAGE);
        assertThat(testFreelancer.getImageContentType()).isEqualTo(DEFAULT_IMAGE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createFreelancerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = freelancerRepository.findAll().size();

        // Create the Freelancer with an existing ID
        freelancer.setId(1L);
        FreelancerDTO freelancerDTO = freelancerMapper.freelancerToFreelancerDTO(freelancer);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFreelancerMockMvc.perform(post("/api/freelancers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freelancerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Freelancer> freelancerList = freelancerRepository.findAll();
        assertThat(freelancerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFreelancers() throws Exception {
        // Initialize the database
        freelancerRepository.saveAndFlush(freelancer);

        // Get all the freelancerList
        restFreelancerMockMvc.perform(get("/api/freelancers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(freelancer.getId().intValue())))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE))));
    }

    @Test
    @Transactional
    public void getFreelancer() throws Exception {
        // Initialize the database
        freelancerRepository.saveAndFlush(freelancer);

        // Get the freelancer
        restFreelancerMockMvc.perform(get("/api/freelancers/{id}", freelancer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(freelancer.getId().intValue()))
            .andExpect(jsonPath("$.imageContentType").value(DEFAULT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.image").value(Base64Utils.encodeToString(DEFAULT_IMAGE)));
    }

    @Test
    @Transactional
    public void getNonExistingFreelancer() throws Exception {
        // Get the freelancer
        restFreelancerMockMvc.perform(get("/api/freelancers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFreelancer() throws Exception {
        // Initialize the database
        freelancerRepository.saveAndFlush(freelancer);
        int databaseSizeBeforeUpdate = freelancerRepository.findAll().size();

        // Update the freelancer
        Freelancer updatedFreelancer = freelancerRepository.findOne(freelancer.getId());
        updatedFreelancer
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE);
        FreelancerDTO freelancerDTO = freelancerMapper.freelancerToFreelancerDTO(updatedFreelancer);

        restFreelancerMockMvc.perform(put("/api/freelancers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freelancerDTO)))
            .andExpect(status().isOk());

        // Validate the Freelancer in the database
        List<Freelancer> freelancerList = freelancerRepository.findAll();
        assertThat(freelancerList).hasSize(databaseSizeBeforeUpdate);
        Freelancer testFreelancer = freelancerList.get(freelancerList.size() - 1);
        assertThat(testFreelancer.getImage()).isEqualTo(UPDATED_IMAGE);
        assertThat(testFreelancer.getImageContentType()).isEqualTo(UPDATED_IMAGE_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingFreelancer() throws Exception {
        int databaseSizeBeforeUpdate = freelancerRepository.findAll().size();

        // Create the Freelancer
        FreelancerDTO freelancerDTO = freelancerMapper.freelancerToFreelancerDTO(freelancer);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restFreelancerMockMvc.perform(put("/api/freelancers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freelancerDTO)))
            .andExpect(status().isCreated());

        // Validate the Freelancer in the database
        List<Freelancer> freelancerList = freelancerRepository.findAll();
        assertThat(freelancerList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteFreelancer() throws Exception {
        // Initialize the database
        freelancerRepository.saveAndFlush(freelancer);
        int databaseSizeBeforeDelete = freelancerRepository.findAll().size();

        // Get the freelancer
        restFreelancerMockMvc.perform(delete("/api/freelancers/{id}", freelancer.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Freelancer> freelancerList = freelancerRepository.findAll();
        assertThat(freelancerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Freelancer.class);
    }
}
