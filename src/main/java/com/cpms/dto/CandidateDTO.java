package com.cpms.dto;

import com.cpms.customAnnotation.NullOrNotBlank;
import com.cpms.customAnnotation.ValidEmail;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CandidateDTO {

    private Long id;
    @NullOrNotBlank(min=1,max=50,isMandatory="yes", message = "Name is required and  must be between {min} and {max} characters" )
    private String name;

    @NullOrNotBlank
    @ValidEmail
    private String email;

    @NotNull(message = "dob is required")
    @Past
    private LocalDate dob;

    @NotEmpty(message = "positionIds are required")
    private List<Long> positionIds;

}
