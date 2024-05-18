package com.banking.ferhatbank.request;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class MoneyTransferMessageRequest implements Serializable {
    String sourceAccNo;
    String destAccNo;
    Long amount;
}
