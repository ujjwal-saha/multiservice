package com.ezyreach.multiservice.dto.aeps;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class BalanceDetails implements Serializable {

	private static final long serialVersionUID = -3067434720423369442L;
	private String externalRef;
    private String bankName;
    private String accountNumber;
    private String ipayId;
    private String transactionMode;
    private String payableValue;
    private String transactionValue;
    private String openingBalance;
    private String closingBalance;
    private String operatorId;
    private String walletIpayId;
    private String bankAccountBalance;
    private List<MiniStatement> miniStatement;
}
