package com.example.bootdata.domain.dto;

import com.example.bootdata.resource.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    @NotNull
    @Min(1)
    @Max(1000)
    private Long id;

    @JsonProperty("fn")
    @NotNull
    @Size(min = 2, message = "user name should have at least 2 characters")
    private String firstName;

    @JsonProperty("ln")
    private String lastName;

    @Email
    private String email;

    @Pattern(regexp = "(\\+38|0)[0-9]{9}")
    @NotBlank
    private String phoneNumber;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "dd-MM-yyyy hh:mm:ss")
    private Date hireDate;

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date creationDate;


//    private Job jobs;

    private BigDecimal salary;

    @JsonIgnore
    private BigDecimal commissionPct;

    private String managerFirstName;

    private String departmentName;

//    private Set<JobHistory> jobHistories = new LinkedHashSet<>();


    @Override
    public String toString() {
        return "EmployeeDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", hireDate=" + hireDate +
                ", creationDate=" + creationDate +
//                ", jobs=" + jobs +
                ", salary=" + salary +
                ", commissionPct=" + commissionPct +
                ", managerFirstName='" + managerFirstName + '\'' +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
