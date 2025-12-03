package com.diego.babycare.domain.dto;

import com.diego.babycare.domain.model.Baby;
import com.diego.babycare.domain.model.User;
import lombok.Data;

@Data
public class BabyCareRequest {
    private User user;
    private Baby baby;
}
