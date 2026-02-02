package com.enigmacamp.tokonyadia.dto.request;

import com.enigmacamp.tokonyadia.entity.Member;
import com.enigmacamp.tokonyadia.utils.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSearch {
    private String fullname;
    private String email;
    private String address;
    private Gender gender;
    private Member member;
}
