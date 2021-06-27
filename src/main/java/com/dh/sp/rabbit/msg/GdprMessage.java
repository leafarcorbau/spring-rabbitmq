package com.dh.sp.rabbit.msg;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GdprMessage {

    private UUID id;
    private UUID userId;
    private String email;
    private String username;
}
