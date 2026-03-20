package com.ezyreach.multiservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ezyreach.multiservice.config.Props;
import com.ezyreach.multiservice.dto.ResponseDTO;
import com.ezyreach.multiservice.dto.finpay.KycStatus;
import com.ezyreach.multiservice.dto.finpay.ResendOTP;
import com.ezyreach.multiservice.dto.finpay.SendOtp;
import com.ezyreach.multiservice.dto.finpay.SuperMerchant;
import com.ezyreach.multiservice.dto.finpay.ValidateOTP;
import com.ezyreach.multiservice.dto.finpay.biometric.Biometric;
import com.ezyreach.multiservice.exception.MultiserviceException;
import com.ezyreach.multiservice.service.security.SecurityService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MerchantService {
    @Autowired
    private SecurityService securityService;
	@Autowired
    private Props props;
	@Autowired
	private WebClient onboardingWebClient;
	@Autowired
	private WebClient masterWebClient;
	@Autowired
	private WebClient kycWebClient;
    // creating a logger
	Logger logger = LoggerFactory.getLogger(MerchantService.class);

	public ResponseDTO<String> merchantOnboard(SuperMerchant superMerchant) {
		ResponseDTO<String> response = new ResponseDTO<>();
		try {
			String uri=props.getMerchant().getOnboarding();
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(superMerchant);
			String jsonResponse=securityService.restCall(jsonString, uri,onboardingWebClient);
			response.setData(jsonResponse);
			response.setCode(200);
			response.setMessage("Success");
            
			logger.info("Merchant onboarding response - " + response);

		} catch (Exception ex) {
			throw new MultiserviceException(ex.getMessage());
		}

		return response;
	}


	public ResponseDTO<String> fetchStates() {
		ResponseDTO<String> response = new ResponseDTO<>();
		try {
			String uri=props.getMaster().getState();
          
			String jsonResponse=securityService.restGetCallWithoutBody(uri,masterWebClient);
			response.setData(jsonResponse);
			response.setCode(200);
			response.setMessage("Success");
            
			logger.info(" State response - " + response);

		} catch (Exception ex) {
			throw new MultiserviceException(ex.getMessage());
		}

		return response;
	}

		public ResponseDTO<String> fetchCompanyTypes() {
		ResponseDTO<String> response = new ResponseDTO<>();
		try {
			String uri=props.getMaster().getCompanytype();
          
			String jsonResponse=securityService.restGetCallWithoutBody(uri,masterWebClient);
			response.setData(jsonResponse);
			response.setCode(200);
			response.setMessage("Success");
            
			logger.info(" Company Type Response - " + response);

		} catch (Exception ex) {
			throw new MultiserviceException(ex.getMessage());
		}

		return response;
	}

	public ResponseDTO<String> sendOtp(SendOtp sendOtp) {
		ResponseDTO<String> response = new ResponseDTO<>();
		try {
			String uri=props.getMerchant().getSendotp();
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(sendOtp);
			String jsonResponse=securityService.restCall(jsonString, uri,kycWebClient);
			response.setData(jsonResponse);
			response.setCode(200);
			response.setMessage("Success");
            
			logger.info("Merchant onboarding response - " + response);

		} catch (Exception ex) {
			throw new MultiserviceException(ex.getMessage());
		}

		return response;
	}

		public ResponseDTO<String> validateOtp(ValidateOTP validateOtp) {
		ResponseDTO<String> response = new ResponseDTO<>();
		try {
			String uri=props.getMerchant().getValidateotp();
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(validateOtp);
			String jsonResponse=securityService.restCall(jsonString, uri,kycWebClient);
			response.setData(jsonResponse);
			response.setCode(200);
			response.setMessage("Success");
            
			logger.info("Merchant onboarding response - " + response);

		} catch (Exception ex) {
			throw new MultiserviceException(ex.getMessage());
		}

		return response;
	}

	public ResponseDTO<String> resendOtp(ResendOTP resendOtp) {
		ResponseDTO<String> response = new ResponseDTO<>();
		try {
			String uri=props.getMerchant().getResendotp();
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(resendOtp);
			String jsonResponse=securityService.restCall(jsonString, uri,kycWebClient);
			response.setData(jsonResponse);
			response.setCode(200);
			response.setMessage("Success");
            
			logger.info("Merchant onboarding response - " + response);

		} catch (Exception ex) {
			throw new MultiserviceException(ex.getMessage());
		}

		return response;
	}


	public ResponseDTO<String> biometric(Biometric biometric) {
		ResponseDTO<String> response = new ResponseDTO<>();
		try {
			String uri=props.getMerchant().getBiometric();
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(biometric);
			String jsonResponse=securityService.restCall(jsonString, uri,kycWebClient);
			response.setData(jsonResponse);
			response.setCode(200);
			response.setMessage("Success");
            
			logger.info("Merchant onboarding response - " + response);

		} catch (Exception ex) {
			throw new MultiserviceException(ex.getMessage());
		}

		return response;
	}


	public ResponseDTO<String> kycStatus(KycStatus kycStatus) {
		ResponseDTO<String> response = new ResponseDTO<>();
		try {
			String uri=props.getMerchant().getStatuscheck();
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(kycStatus);
			String jsonResponse=securityService.restCall(jsonString, uri,kycWebClient);
			response.setData(jsonResponse);
			response.setCode(200);
			response.setMessage("Success");
            
			logger.info("Merchant onboarding response - " + response);

		} catch (Exception ex) {
			throw new MultiserviceException(ex.getMessage());
		}

		return response;
	}

}
