package com.coroda.mclogin.model.api.response;

import com.coroda.mclogin.model.entity.RolLogin;
import com.coroda.mclogin.model.thirtparthy.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private Long loginId;
    private String email;
    private RolLogin rol;
    private Person information;

}
