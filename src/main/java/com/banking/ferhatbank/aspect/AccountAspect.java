package com.banking.ferhatbank.aspect;

import com.banking.ferhatbank.service.AccountService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AccountAspect {

    private final Logger logger = LoggerFactory.getLogger(AccountAspect.class);

    @After("execution(* com.banking.ferhatbank.service.AccountService.depositMoneyByAccountNumber(..))")
    public void afterDepositMoneyByAccountNumber(JoinPoint joinPoint){
        AccountService accountService = (AccountService) joinPoint.getTarget();
        String accountNumber = (String) joinPoint.getArgs()[0];
        Long amount = (Long) joinPoint.getArgs()[1];
        logger.info(accountNumber + " hesabına " + amount + " TL yatırıldı." );
    }
}
