package com.enigmacamp.tokonyadia.dto.response;

import com.enigmacamp.tokonyadia.entity.Member;
import com.enigmacamp.tokonyadia.utils.enums.Gender;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {

    private UUID id;
    private String fullname;
    private String email;
    private String address;
    private Gender gender;
    private MemberResponse member;
}
