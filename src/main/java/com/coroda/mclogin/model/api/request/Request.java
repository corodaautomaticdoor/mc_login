package com.coroda.mclogin.model.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("Model Request")
public class Request {

    @ApiModelProperty(value = "loginId", position = 1)
    private Long loginId;

    @ApiModelProperty(value = "email", required = true , position = 2)
    private String email;

    @ApiModelProperty(value = "userName", required = true , position = 3)
    private String userName;

    @ApiModelProperty(value = "password", required = true , position = 4)
    private String password;

}
