package com.cydeo.dto;

import com.cydeo.enums.EducationLevel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeacherDTO {

    @JsonIgnore
    private Long id;

    @NotBlank(message = "Firstname is a required field!")
    @Size(min = 2, max = 50, message = "Firstname must be between 2 and 50 characters long!")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Firstname must contain only letters")
    private String firstName;


    @NotBlank(message = "Lastname is a required field!")
    @Size(min = 2, max = 50, message = "Lastname must be between 2 and 50 characters long!")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Lastname must contain only letters")
    private String lastName;



    @NotBlank(message = "Phone number is a required field!")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number must be between 10 and 15 digits")
    private String phoneNumber;

    @NotBlank(message = "Email is a required field!")
    @Email(message = "Email should be valid!")
    private String email;

    @NotBlank(message = "username is a required field!")
    @Size(min = 4, max = 20, message = "username must be between 4 and 20 characters long!")
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "password is a required field!")
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}", message = "password must be at least 8 chars and min 1 uppercase, 1 lowercase and 1 number")
    private String password;

    @NotNull(message = "Birthday is required field")
    @Past(message = "Birthday must be a past date")
    private LocalDate birthday;

    @NotNull(message = "Education level is required field")
    private EducationLevel educationLevel;

    @NotBlank(message = "Address no is a required field")
    private String addressNo;

}
