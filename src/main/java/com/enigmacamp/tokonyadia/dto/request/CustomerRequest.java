package com.enigmacamp.tokonyadia.dto.request;

import com.enigmacamp.tokonyadia.entity.Member;
import com.enigmacamp.tokonyadia.utils.enums.Gender;

public record CustomerRequest(
        String fullname,
        String email,
        String address,
        Gender gender,
        Member memberId
) {
}
