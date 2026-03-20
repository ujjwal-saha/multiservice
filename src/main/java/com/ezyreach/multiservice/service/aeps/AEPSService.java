package com.ezyreach.multiservice.service.aeps;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import com.ezyreach.multiservice.config.AEPSProps;
import com.ezyreach.multiservice.dto.ResponseDTO;
import com.ezyreach.multiservice.dto.aeps.AEPSRequest;
import com.ezyreach.multiservice.dto.aeps.AEPSResponse;
import com.ezyreach.multiservice.dto.aeps.BankDTO;
import com.ezyreach.multiservice.dto.aeps.BankResponse;
import com.ezyreach.multiservice.dto.aeps.LoginStatusResponse;
import com.ezyreach.multiservice.exception.MultiserviceException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class AEPSService {
	private final RestClient restClient;
	// creating a logger
	Logger logger = LoggerFactory.getLogger(AEPSService.class);	
	private final AEPSProps aepsProps;


	public AEPSService(RestClient.Builder restClientBuilder,AEPSProps aepsProps) {
		this.aepsProps=aepsProps;
		this.restClient = restClientBuilder.baseUrl(this.aepsProps.getEndpoint().getBaseUrl()).build();
	}


	public ResponseDTO<List<BankDTO>> fetchBankDetails() {
		ResponseDTO<List<BankDTO>> banks=new ResponseDTO<>();
		try{
		    String uri=this.aepsProps.getEndpoint().getBanks();
			BankResponse bankResponse = restClient.get()
					.uri(uri)
					.headers(header->{ header.addAll(getHeaders());})
					.exchange((request, response) -> {
						BankResponse apiResponse = null;
						if (response.getStatusCode().is4xxClientError()) {
							logger.error("fetchBankDetails - 4XX Issues = "+ response.getStatusText());
							throw new MultiserviceException(response.getStatusText());
						}else if (response.getStatusCode().is5xxServerError()) {
							logger.error("fetchBankDetails - 5XX Issues = "+ response.getStatusText());
							throw new MultiserviceException(response.getStatusText());
						} else {
							ObjectMapper mapper = new ObjectMapper();
							apiResponse = mapper.readValue(response.getBody(), BankResponse.class);
						}
						return apiResponse;
					});

			logger.info("URI = "+uri+" and Response = "+bankResponse);

			if(bankResponse!=null){
				banks.setData(bankResponse.getData());
				banks.setCode(200);
			}
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			throw new MultiserviceException(e.getMessage());
		}
		return banks;
	}

	public ResponseDTO<LoginStatusResponse> outletLoginStatus(AEPSRequest request) {

		ResponseDTO<LoginStatusResponse> loginResponse=new ResponseDTO<>();
		try{
			String response=this.aepsRequest(request,this.aepsProps.getEndpoint().getOutletLoginStatus());
			if(response!=null){
				ObjectMapper mapper = new ObjectMapper();
				LoginStatusResponse loginStatusResponse = mapper.readValue(response, LoginStatusResponse.class);
				loginResponse.setData(loginStatusResponse);
				loginResponse.setCode(200);
			}
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			throw new MultiserviceException(e.getMessage());
		}
		return loginResponse;
	}

	public ResponseDTO<AEPSResponse> outletLogin(AEPSRequest request) {

		ResponseDTO<AEPSResponse> aepsResponse=new ResponseDTO<>();
		try{
			String response=this.aepsRequest(request,this.aepsProps.getEndpoint().getOutletLogin());
			if(response!=null){
				ObjectMapper mapper = new ObjectMapper();
				aepsResponse.setData(mapper.readValue(response, AEPSResponse.class));
				aepsResponse.setCode(200);
			}
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			throw new MultiserviceException(e.getMessage());
		}
		return aepsResponse;
	}


	public ResponseDTO<AEPSResponse> cashWithdrawal(AEPSRequest request){

		ResponseDTO<AEPSResponse> aepsResponse=new ResponseDTO<>();
		try{
			String response=this.aepsRequest(request,this.aepsProps.getEndpoint().getCashWithdrawal());
			if(response!=null){
				ObjectMapper mapper = new ObjectMapper();
				aepsResponse.setData(mapper.readValue(response, AEPSResponse.class));
				aepsResponse.setCode(200);
			}
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			throw new MultiserviceException(e.getMessage());
		}
		return aepsResponse;
	}

	public ResponseDTO<AEPSResponse> cashDeposit(AEPSRequest request){

		ResponseDTO<AEPSResponse> aepsResponse=new ResponseDTO<>();
		try{
			String response=this.aepsRequest(request,this.aepsProps.getEndpoint().getCashDeposit());
			if(response!=null){
				ObjectMapper mapper = new ObjectMapper();
				aepsResponse.setData(mapper.readValue(response, AEPSResponse.class));
				aepsResponse.setCode(200);
			}
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			throw new MultiserviceException(e.getMessage());
		}

		return aepsResponse;
	}

	public ResponseDTO<AEPSResponse> balanceEnquiry(AEPSRequest request)  {

		ResponseDTO<AEPSResponse> aepsResponse=new ResponseDTO<>();
		try{
			String response=this.aepsRequest(request,this.aepsProps.getEndpoint().getBalanceInquiry());
			if(response!=null){
				ObjectMapper mapper = new ObjectMapper();
				aepsResponse.setData(mapper.readValue(response, AEPSResponse.class));
				aepsResponse.setCode(200);
			}
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			throw new MultiserviceException(e.getMessage());
		}

		return aepsResponse;
	}

	public ResponseDTO<AEPSResponse> miniStatement(AEPSRequest request){

		ResponseDTO<AEPSResponse> aepsResponse=new ResponseDTO<>();
		try {
			String response = this.aepsRequest(request, this.aepsProps.getEndpoint().getMiniStatement());
			if (response != null) {
				ObjectMapper mapper = new ObjectMapper();
				aepsResponse.setData(mapper.readValue(response, AEPSResponse.class));
				aepsResponse.setCode(200);
			}
		}catch(Exception e){
			logger.error(e.getMessage(),e);
			throw new MultiserviceException(e.getMessage());
		}
		return aepsResponse;
	}

	public String aepsRequest(AEPSRequest aepsRequest, String uri) {

		String aepsResponse = restClient.post()
				.uri(uri)
				.headers(header->{ header.addAll(getHeaders());})
				.body(aepsRequest)
				.exchange((request, response) -> {
					String  apiResponse = null;
					if (response.getStatusCode().is4xxClientError()) {
						logger.error(uri+" - 4XX Issues = "+ response.getStatusText());
						throw new MultiserviceException(response.getStatusText());
					}else if (response.getStatusCode().is5xxServerError()) {
						logger.error(uri+" - 5XX Issues = "+ response.getStatusText());
						throw new MultiserviceException(response.getStatusText());
					} else {
						ObjectMapper mapper = new ObjectMapper();
						apiResponse = mapper.readValue(response.getBody(), String.class);
					}
					return apiResponse;
				});

		logger.info("URI = "+uri+" and Request ==== "+aepsRequest+" and Response = "+aepsResponse);

		return aepsResponse;
	}



	private MultiValueMap<String, String> getHeaders(){
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Accept","application/json");
		headers.add("Content-Type","application/json");
		headers.add("X-Ipay-Auth-Code",this.aepsProps.getHeader().getAuthCode());
		headers.add("X-Ipay-Client-Id",this.aepsProps.getHeader().getClientId());
		headers.add("X-Ipay-Client-Secret",this.aepsProps.getHeader().getClientSecret());
		headers.add("X-Ipay-Outlet-Id",this.aepsProps.getHeader().getOutletId());

		return headers;
	}

}
