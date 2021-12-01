package com.coroda.mclogin.model.api.request;

import com.coroda.mclogin.model.entity.RolLogin;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
@ApiModel("Model Request")
public class Request {

    @ApiModelProperty(value = "loginId", position = 1)
    private Long loginId;

    @ApiModelProperty(value = "email", required = true , position = 2)
    @Email(message = "Ingresar un correo valido")
    private String email;

    @ApiModelProperty(value = "password", required = true , position = 3)
    private String password;

    @ApiModelProperty(value = "rolId", required = true , position = 4)
    private RolLogin rolId;


}
