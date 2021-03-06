package com.jobvacancy.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.jobvacancy.domain.JobOffer;
import com.jobvacancy.domain.util.EmailAddressValidator;
import com.jobvacancy.domain.util.UrlValidator;
import com.jobvacancy.repository.JobOfferRepository;
import com.jobvacancy.service.MailService;
import com.jobvacancy.web.rest.dto.JobApplicationDTO;
import com.jobvacancy.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;

import java.net.URISyntaxException;


@RestController
@RequestMapping("/api")
public class JobApplicationResource {

    private final Logger log = LoggerFactory.getLogger(JobOfferResource.class);

    @Inject
    private JobOfferRepository jobOfferRepository;

    @Inject
    private MailService mailService;

    /**
     * POST  /Application -> Create a new jobOffer.
     */
    @RequestMapping(value = "/Application",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<JobOffer> createJobApplication(@Valid @RequestBody JobApplicationDTO jobApplication) throws URISyntaxException {
        
    	ResponseEntity<JobOffer> responseJobApplication;
    	JobOffer jobOffer=null;
    	boolean validJobApp = true;
    	
    	log.debug("REST request to save JobApplication : {}", jobApplication);
        
        EmailAddressValidator emailValidator = new EmailAddressValidator();
          UrlValidator urlValidator=new UrlValidator();
        
        if (urlValidator.validateUrl(jobApplication.getUrl())) {
            
            jobOffer = jobOfferRepository.findOne(jobApplication.getOfferId());
            this.mailService.sendApplication(jobApplication.getUrl(), jobOffer);
            validJobApp = true;
            responseJobApplication=ResponseEntity.accepted().headers(HeaderUtil.createAlert("Application created and sent offer's owner", "")).body(null);
        } else {
            responseJobApplication=ResponseEntity.badRequest().headers(HeaderUtil.
                    createAlert("Wrong URL address, application was not proccesed", "")).body(null);
            jobApplication.setUrl("");
            validJobApp = false;
        }
        
        if (emailValidator.validateEmail(jobApplication.getEmail())) {
        	
        	jobOffer = jobOfferRepository.findOne(jobApplication.getOfferId());
            this.mailService.sendApplication(jobApplication.getEmail(), jobOffer);
            validJobApp = true;
            responseJobApplication=ResponseEntity.accepted().headers(HeaderUtil.createAlert("Application created and sent offer's owner", "")).body(null);
        } else {
        	responseJobApplication=ResponseEntity.badRequest().headers(HeaderUtil.
        			createAlert("Wrong email address, application was not proccesed", "")).body(null);
        	jobApplication.setEmail("");
        	validJobApp = false;
        }
        
        if (validJobApp) {
        	jobOffer.setApplied();
        	jobOfferRepository.save(jobOffer);
        }
        return responseJobApplication;
    }
}
