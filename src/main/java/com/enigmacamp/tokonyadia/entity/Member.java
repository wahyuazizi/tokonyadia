package com.enigmacamp.tokonyadia.entity;

import com.enigmacamp.tokonyadia.dto.response.MemberResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "m_member")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Member {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(nullable = false, unique = true,  length = 20)
    private String username;
    @Size(min = 8, max = 20, message = "Password Min 8 characters")
    @Column(nullable = false)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).*$", message = "Password must be contained letter and number")
    private String password;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Customer customer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "m_member_role",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles =  new HashSet<>();

    public MemberResponse toResponse() {
        return MemberResponse.builder()
                .username(getUsername()).build();
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
