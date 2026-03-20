package com.ezyreach.multiservice.service.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ezyreach.multiservice.config.Props;
import com.ezyreach.multiservice.dto.ResponseDTO;
import com.ezyreach.multiservice.dto.finpay.aadhaar.AadhaarDeposit;
import com.ezyreach.multiservice.dto.finpay.aadhaar.AadhaarDepositAck;
import com.ezyreach.multiservice.dto.finpay.aadhaar.AadhaarDepositStatus;
import com.ezyreach.multiservice.dto.finpay.aadhaar.AadhaarPayStatus;
import com.ezyreach.multiservice.dto.finpay.aadhaar.AddaarPay;
import com.ezyreach.multiservice.dto.finpay.deposit.CashDepositStatus;
import com.ezyreach.multiservice.dto.finpay.deposit.CashDepositTransaction;
import com.ezyreach.multiservice.dto.finpay.deposit.CashDepositWithOTP;
import com.ezyreach.multiservice.dto.finpay.deposit.CashDepositWithoutAck;
import com.ezyreach.multiservice.dto.finpay.deposit.ValidateOTPAndFetchBene;
import com.ezyreach.multiservice.dto.finpay.transaction.Transaction;
import com.ezyreach.multiservice.dto.finpay.transaction.WithdrawalStatusCheck;
import com.ezyreach.multiservice.exception.MultiserviceException;
import com.ezyreach.multiservice.service.security.SecurityService;
import com.fasterxml.jackson.databind.ObjectMapper;



@Service
public class TransactionService {
  // creating a logger
	Logger logger = LoggerFactory.getLogger(TransactionService.class);
    @Autowired
    private SecurityService securityService;
	@Autowired
    private Props props;
    @Autowired
	private WebClient transactionWebClient;



    public ResponseDTO<String> cashWithdrawal(Transaction transaction) {
		ResponseDTO<String> response = new ResponseDTO<>();
		try {
			String uri=props.getTransaction().getWithdrawal().getWithdrawal();
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(transaction);
			String jsonResponse=securityService.restCall(jsonString, uri,transactionWebClient);
			response.setData(jsonResponse);
			response.setCode(200);
			response.setMessage("Success");
            
			logger.info("Cash Withdrawal response - " + response);

		} catch (Exception ex) {
			throw new MultiserviceException(ex.getMessage());
		}

		return response;
	}

    public ResponseDTO<String> withdrawalStatusCheck(WithdrawalStatusCheck withdrawalStatusCheck) {
		ResponseDTO<String> response = new ResponseDTO<>();
		try {
			String uri=props.getTransaction().getWithdrawal().getStatus();
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(withdrawalStatusCheck);
			String jsonResponse=securityService.restCall(jsonString, uri,transactionWebClient);
			response.setData(jsonResponse);
			response.setCode(200);
			response.setMessage("Success");
            
			logger.info("Cash Withdrawal response - " + response);

		} catch (Exception ex) {
			throw new MultiserviceException(ex.getMessage());
		}

		return response;
	}


    public ResponseDTO<String> balanceInquiry(Transaction transaction) {
		ResponseDTO<String> response = new ResponseDTO<>();
		try {
			String uri=props.getTransaction().getBalance().getBalancecheck();
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(transaction);
			String jsonResponse=securityService.restCall(jsonString, uri,transactionWebClient);
			response.setData(jsonResponse);
			response.setCode(200);
			response.setMessage("Success");
            
			logger.info("Balance Inquiry response - " + response);

		} catch (Exception ex) {
			throw new MultiserviceException(ex.getMessage());
		}

		return response;
	}

    public ResponseDTO<String> miniStatements(Transaction transaction) {
		ResponseDTO<String> response = new ResponseDTO<>();
		try {
			String uri=props.getTransaction().getStatement().getMinistatement();
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(transaction);
			String jsonResponse=securityService.restCall(jsonString, uri,transactionWebClient);
			response.setData(jsonResponse);
			response.setCode(200);
			response.setMessage("Success");
            
			logger.info("Balance Inquiry response - " + response);

		} catch (Exception ex) {
			throw new MultiserviceException(ex.getMessage());
		}

		return response;
	}


    public ResponseDTO<String> aadhaarPay(AddaarPay transaction) {
		ResponseDTO<String> response = new ResponseDTO<>();
		try {
			String uri=props.getTransaction().getAadhaar().getPayment();
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(transaction);
			String jsonResponse=securityService.restCall(jsonString, uri,transactionWebClient);
			response.setData(jsonResponse);
			response.setCode(200);
			response.setMessage("Success");
            
			logger.info("Aadhaar Payment response - " + response);

		} catch (Exception ex) {
			throw new MultiserviceException(ex.getMessage());
		}

		return response;
	}

	  public ResponseDTO<String> aadhaarPayStatus(AadhaarPayStatus status) {
		ResponseDTO<String> response = new ResponseDTO<>();
		try {
			String uri=props.getTransaction().getAadhaar().getPaymentstatus();
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(status);
			String jsonResponse=securityService.restCall(jsonString, uri,transactionWebClient);
			response.setData(jsonResponse);
			response.setCode(200);
			response.setMessage("Success");
            
			logger.info("Aadhaar Payment Status response - " + response);

		} catch (Exception ex) {
			throw new MultiserviceException(ex.getMessage());
		}

		return response;
	}


	public ResponseDTO<String> aadhaarDeposit(AadhaarDeposit aadhaarDeposit) {
		ResponseDTO<String> response = new ResponseDTO<>();
		try {
			String uri=props.getTransaction().getAadhaar().getDeposit();
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(aadhaarDeposit);
			String jsonResponse=securityService.restCall(jsonString, uri,transactionWebClient);
			response.setData(jsonResponse);
			response.setCode(200);
			response.setMessage("Success");
            
			logger.info("Aadhaar Payment Status response - " + response);

		} catch (Exception ex) {
			throw new MultiserviceException(ex.getMessage());
		}

		return response;
	}

	public ResponseDTO<String> aadhaarDepositAck(AadhaarDepositAck aadhaarDepositAck) {
		ResponseDTO<String> response = new ResponseDTO<>();
		try {
			String uri=props.getTransaction().getAadhaar().getDepositacknowledgement();
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(aadhaarDepositAck);
			String jsonResponse=securityService.restCall(jsonString, uri,transactionWebClient);
			response.setData(jsonResponse);
			response.setCode(200);
			response.setMessage("Success");
            
			logger.info("Aadhaar Payment Status response - " + response);

		} catch (Exception ex) {
			throw new MultiserviceException(ex.getMessage());
		}

		return response;
	}

	public ResponseDTO<String> aadhaarDepositStatus(AadhaarDepositStatus aadhaarDepositStatus) {
		ResponseDTO<String> response = new ResponseDTO<>();
		try {
			String uri=props.getTransaction().getAadhaar().getDepositstatus();
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(aadhaarDepositStatus);
			String jsonResponse=securityService.restCall(jsonString, uri,transactionWebClient);
			response.setData(jsonResponse);
			response.setCode(200);
			response.setMessage("Success");
            
			logger.info("Aadhaar Payment Status response - " + response);

		} catch (Exception ex) {
			throw new MultiserviceException(ex.getMessage());
		}

		return response;
	}


	public ResponseDTO<String> cashDepositWithOTP(CashDepositWithOTP cashDepositWithOTP) {
		ResponseDTO<String> response = new ResponseDTO<>();
		try {
			String uri=props.getTransaction().getDeposit().getDepositwithotp();
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(cashDepositWithOTP);
			String jsonResponse=securityService.restCall(jsonString, uri,transactionWebClient);
			response.setData(jsonResponse);
			response.setCode(200);
			response.setMessage("Success");
            
			logger.info("Aadhaar Payment Status response - " + response);

		} catch (Exception ex) {
			throw new MultiserviceException(ex.getMessage());
		}

		return response;
	}

	public ResponseDTO<String> validateOTPAndFetchBene(ValidateOTPAndFetchBene validateOTPAndFetchBene) {
		ResponseDTO<String> response = new ResponseDTO<>();
		try {
			String uri=props.getTransaction().getDeposit().getValidateotp();
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(validateOTPAndFetchBene);
			String jsonResponse=securityService.restCall(jsonString, uri,transactionWebClient);
			response.setData(jsonResponse);
			response.setCode(200);
			response.setMessage("Success");
            
			logger.info("Aadhaar Payment Status response - " + response);

		} catch (Exception ex) {
			throw new MultiserviceException(ex.getMessage());
		}

		return response;
	}

	public ResponseDTO<String> cashDepositTransaction(CashDepositTransaction cashDepositTransaction) {
		ResponseDTO<String> response = new ResponseDTO<>();
		try {
			String uri=props.getTransaction().getDeposit().getTransaction();
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(cashDepositTransaction);
			String jsonResponse=securityService.restCall(jsonString, uri,transactionWebClient);
			response.setData(jsonResponse);
			response.setCode(200);
			response.setMessage("Success");
            
			logger.info("Aadhaar Payment Status response - " + response);

		} catch (Exception ex) {
			throw new MultiserviceException(ex.getMessage());
		}

		return response;
	}


	public ResponseDTO<String> cashDepositWithoutAck(CashDepositWithoutAck cashDepositWithoutAck) {
		ResponseDTO<String> response = new ResponseDTO<>();
		try {
			String uri=props.getTransaction().getDeposit().getAcknowledgementwithoutotp();
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(cashDepositWithoutAck);
			String jsonResponse=securityService.restCall(jsonString, uri,transactionWebClient);
			response.setData(jsonResponse);
			response.setCode(200);
			response.setMessage("Success");
            
			logger.info("Aadhaar Payment Status response - " + response);

		} catch (Exception ex) {
			throw new MultiserviceException(ex.getMessage());
		}

		return response;
	}


	public ResponseDTO<String> cashDepositStatus(CashDepositStatus cashDepositStatus) {
		ResponseDTO<String> response = new ResponseDTO<>();
		try {
			String uri=props.getTransaction().getDeposit().getStatuscheck();
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(cashDepositStatus);
			String jsonResponse=securityService.restCall(jsonString, uri,transactionWebClient);
			response.setData(jsonResponse);
			response.setCode(200);
			response.setMessage("Success");
            
			logger.info("Aadhaar Payment Status response - " + response);

		} catch (Exception ex) {
			throw new MultiserviceException(ex.getMessage());
		}

		return response;
	}
	
}
