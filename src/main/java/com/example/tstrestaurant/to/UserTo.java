package com.example.tstrestaurant.to;

import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value
@EqualsAndHashCode(callSuper = true)
public class UserTo extends BaseTo {

    @NotBlank
    @Size(min = 2, max = 128)
    String name;

    @Email
    @NotBlank
    @Size(max = 128)
    String email;

    @NotBlank
    @Size(min = 5, max = 32)
    String password;

    public UserTo(Long id, String name, String email, String password) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserTo:" + id + '[' + email + ']';
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
