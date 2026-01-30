package com.enigmacamp.tokonyadia.entity;

import com.enigmacamp.tokonyadia.utils.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "m_customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;
    private String fullname;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;
    @NotNull
    private Gender gender;
    private String address;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL,  fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private List<Transaction> transactions;

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                ", member=" + member +
                '}';
    }
}
