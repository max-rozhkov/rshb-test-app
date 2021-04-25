package dev.redlab.rshb.testapp.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class DepositRequest {

    @NotNull(message = "transaction id must be not null")
    private Long transactionId;

    @NotNull(message = "account id must be not null")
    private Long accountId;

    @NotNull(message = "deposit must be not null")
    private BigDecimal deposit;

}
