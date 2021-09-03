package com.coroda.mclogin.model.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private Long loginId;
    private String email;
    private String userName;
    private String password;

}
