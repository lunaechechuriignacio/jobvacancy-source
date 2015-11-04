package com.jobvacancy.web.rest;


import com.jobvacancy.Application;
import com.jobvacancy.domain.JobOffer;
import com.jobvacancy.domain.User;
import com.jobvacancy.repository.JobOfferRepository;
import com.jobvacancy.repository.UserRepository;
import com.jobvacancy.service.MailService;
import com.jobvacancy.web.rest.dto.JobApplicationDTO;
import com.jobvacancy.web.rest.util.EmailAddressValidator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class EmailValidatorTest {
	
  	private static final String APPLICANT_FULLNAME = "THE APPLICANT";
  	private static final String APPLICANT_VALID_EMAIL_1 = "martin@test.com";
  	private static final String APPLICANT_VALID_EMAIL_2 = "martin+1@test.com";
  	private static final String APPLICANT_VALID_EMAIL_3 = "martin@test.com.ar";
    
   	private static final String APPLICANT_INVALID_EMAIL_1 = "zaraza";
   	private static final String APPLICANT_INVALID_EMAIL_2 = "@zaraza";	
    private static final String APPLICANT_INVALID_EMAIL_3 = "zaraza@";
    private static final String APPLICANT_INVALID_EMAIL_4 = "zaraza@test";
    private static final String APPLICANT_INVALID_EMAIL_5 = "   @test.com";
    
    private MockMvc restMockMvc;

    private static final long OFFER_ID = 1;
    private static final String OFFER_TITLE = "SAMPLE_TEXT";
    private static final String OFFER_URL = "https://www.dropbox.com/s/5cmg2gr84g4fmhz/cv.pdf?dl=0";

    @Mock
    private MailService mailService;

    @Mock
    private JobOfferRepository jobOfferRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    private JobOffer offer;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Optional<User> user = userRepository.findOneByLogin("user");
        offer = new JobOffer();
        offer.setTitle(OFFER_TITLE);
        offer.setId(OFFER_ID);
        offer.setOwner(user.get());
        when(jobOfferRepository.findOne(OFFER_ID)).thenReturn(offer);
        JobApplicationResource jobApplicationResource = new JobApplicationResource();
        ReflectionTestUtils.setField(jobApplicationResource, "jobOfferRepository", jobOfferRepository);
        ReflectionTestUtils.setField(jobApplicationResource, "mailService", mailService);

        this.restMockMvc = MockMvcBuilders.standaloneSetup(jobApplicationResource)
            .setMessageConverters(jacksonMessageConverter).build();
    }

	@Test
	public void EmailValidoHotmail() {
		String emailAddress="inle_one@hotmail.com";
		EmailAddressValidator email=new EmailAddressValidator();
		Assert.assertTrue(email.validateEmail(emailAddress));
	}
	
	@Test
	public void EmailValidoGmail() {
		String emailAddress="lunaechechuri.ignacio@gmail.com";
		EmailAddressValidator email=new EmailAddressValidator();
		Assert.assertTrue(email.validateEmail(emailAddress));
	}
	@Test
	public void EmailInvalidoConParentesis() {
		String emailAddress="()()()@gmail.com";
		EmailAddressValidator email=new EmailAddressValidator();
		Assert.assertFalse(email.validateEmail(emailAddress));
	}
	
	@Test
	public void EmailInvalidoSinElPunto() {
		String emailAddress="inle@gmailcom";
		EmailAddressValidator email=new EmailAddressValidator();
		Assert.assertFalse(email.validateEmail(emailAddress));
	}
	
	@Test
	public void EmailInvalidoSinElCom() {
		String emailAddress="inle@gmail.";
		EmailAddressValidator email=new EmailAddressValidator();
		Assert.assertFalse(email.validateEmail(emailAddress));
	}
	
	@Test
	public void EmailInvalidoSinElArroba() {
		String emailAddress="inlegmail.com";
		EmailAddressValidator email=new EmailAddressValidator();
		Assert.assertFalse(email.validateEmail(emailAddress));
	}
	@Test
	public void EmailInvalidoSinLaTerminacionDelServidos() {
		String emailAddress="inle@";
		EmailAddressValidator email=new EmailAddressValidator();
		Assert.assertFalse(email.validateEmail(emailAddress));
	}
	
	 @Test
	    @Transactional
	    public void createJobApplicationWithValidMail1() throws Exception {
	        JobApplicationDTO dto = new JobApplicationDTO();
	        dto.setEmail(APPLICANT_VALID_EMAIL_1);
	        dto.setFullname(APPLICANT_FULLNAME);
	        dto.setOfferId(OFFER_ID);
	        dto.setUrl(OFFER_URL);

	        doNothing().when(mailService).sendApplication(APPLICANT_VALID_EMAIL_1, offer);

	        restMockMvc.perform(post("/api/Application")
	            .contentType(TestUtil.APPLICATION_JSON_UTF8)
	            .content(TestUtil.convertObjectToJsonBytes(dto)))
	                .andExpect(status().isAccepted());

	        Mockito.verify(mailService).sendApplication(APPLICANT_VALID_EMAIL_1, offer);
	    }
	 
	 @Test
	    @Transactional
	    public void createJobApplicationWithValidMail2() throws Exception {
	        JobApplicationDTO dto = new JobApplicationDTO();
	        dto.setEmail(APPLICANT_VALID_EMAIL_2);
	        dto.setFullname(APPLICANT_FULLNAME);
	        dto.setOfferId(OFFER_ID);
	        dto.setUrl(OFFER_URL);

	        doNothing().when(mailService).sendApplication(APPLICANT_VALID_EMAIL_2, offer);

	        restMockMvc.perform(post("/api/Application")
	            .contentType(TestUtil.APPLICATION_JSON_UTF8)
	            .content(TestUtil.convertObjectToJsonBytes(dto)))
	                .andExpect(status().isAccepted());

	        Mockito.verify(mailService).sendApplication(APPLICANT_VALID_EMAIL_2, offer);
	    }
	    
	    @Test
	    @Transactional
	    public void createJobApplicationWithValidMail3() throws Exception {
	        JobApplicationDTO dto = new JobApplicationDTO();
	        dto.setEmail(APPLICANT_VALID_EMAIL_3);
	        dto.setFullname(APPLICANT_FULLNAME);
	        dto.setOfferId(OFFER_ID);
	        dto.setUrl(OFFER_URL);

	        doNothing().when(mailService).sendApplication(APPLICANT_VALID_EMAIL_3, offer);

	        restMockMvc.perform(post("/api/Application")
	            .contentType(TestUtil.APPLICATION_JSON_UTF8)
	            .content(TestUtil.convertObjectToJsonBytes(dto)))
	                .andExpect(status().isAccepted());

	        Mockito.verify(mailService).sendApplication(APPLICANT_VALID_EMAIL_3, offer);
	    }
	    
	    @Test
	    @Transactional
	    public void createJobApplicationWithInvalidMail1() throws Exception {
	        JobApplicationDTO dto = new JobApplicationDTO();
	        dto.setEmail(APPLICANT_INVALID_EMAIL_1);
	        dto.setFullname(APPLICANT_FULLNAME);
	        dto.setOfferId(OFFER_ID);
	        dto.setUrl(OFFER_URL);

	        doNothing().when(mailService).sendApplication(APPLICANT_INVALID_EMAIL_1, offer);

	        restMockMvc.perform(post("/api/Application")
	            .contentType(TestUtil.APPLICATION_JSON_UTF8)
	            .content(TestUtil.convertObjectToJsonBytes(dto)))
	                .andExpect(status().isBadRequest());
	    }
	    
	    @Test
	    @Transactional
	    public void createJobApplicationWithInvalidMail2() throws Exception {
	        JobApplicationDTO dto = new JobApplicationDTO();
	        dto.setEmail(APPLICANT_INVALID_EMAIL_2);
	        dto.setFullname(APPLICANT_FULLNAME);
	        dto.setOfferId(OFFER_ID);
	        dto.setUrl(OFFER_URL);

	        doNothing().when(mailService).sendApplication(APPLICANT_INVALID_EMAIL_2, offer);

	        restMockMvc.perform(post("/api/Application")
	            .contentType(TestUtil.APPLICATION_JSON_UTF8)
	            .content(TestUtil.convertObjectToJsonBytes(dto)))
	                .andExpect(status().isBadRequest());
	    }
	    
	    @Test
	    @Transactional
	    public void createJobApplicationWithInvalidMail3() throws Exception {
	        JobApplicationDTO dto = new JobApplicationDTO();
	        dto.setEmail(APPLICANT_INVALID_EMAIL_3);
	        dto.setFullname(APPLICANT_FULLNAME);
	        dto.setOfferId(OFFER_ID);
	        dto.setUrl(OFFER_URL);

	        doNothing().when(mailService).sendApplication(APPLICANT_INVALID_EMAIL_3, offer);

	        restMockMvc.perform(post("/api/Application")
	            .contentType(TestUtil.APPLICATION_JSON_UTF8)
	            .content(TestUtil.convertObjectToJsonBytes(dto)))
	                .andExpect(status().isBadRequest());
	    }
	    
	    @Test
	    @Transactional
	    public void createJobApplicationWithInvalidMail4() throws Exception {
	        JobApplicationDTO dto = new JobApplicationDTO();
	        dto.setEmail(APPLICANT_INVALID_EMAIL_4);
	        dto.setFullname(APPLICANT_FULLNAME);
	        dto.setOfferId(OFFER_ID);
	        dto.setUrl(OFFER_URL);

	        doNothing().when(mailService).sendApplication(APPLICANT_INVALID_EMAIL_4, offer);

	        restMockMvc.perform(post("/api/Application")
	            .contentType(TestUtil.APPLICATION_JSON_UTF8)
	            .content(TestUtil.convertObjectToJsonBytes(dto)))
	                .andExpect(status().isBadRequest());
	    }
	    
	    @Test
	    @Transactional
	    public void createJobApplicationWithInvalidMail5() throws Exception {
	        JobApplicationDTO dto = new JobApplicationDTO();
	        dto.setEmail(APPLICANT_INVALID_EMAIL_5);
	        dto.setFullname(APPLICANT_FULLNAME);
	        dto.setOfferId(OFFER_ID);
	        dto.setUrl(OFFER_URL);

	        doNothing().when(mailService).sendApplication(APPLICANT_INVALID_EMAIL_5, offer);

	        restMockMvc.perform(post("/api/Application")
	            .contentType(TestUtil.APPLICATION_JSON_UTF8)
	            .content(TestUtil.convertObjectToJsonBytes(dto)))
	                .andExpect(status().isBadRequest());
	    }

}
