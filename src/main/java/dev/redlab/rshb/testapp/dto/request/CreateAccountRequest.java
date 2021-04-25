package dev.redlab.rshb.testapp.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class CreateAccountRequest {

    @NotNull(message = "account name must be not null")
    private String name;

}
