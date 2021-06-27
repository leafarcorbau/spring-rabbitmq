package com.dh.sp.rabbit.msg;

import com.dh.sp.rabbit.util.TestUtil;

import java.util.UUID;

public class TestGdprMessage extends GdprMessage.GdprMessageBuilder{

    public static GdprMessage.GdprMessageBuilder getInstance(final UUID seed){
        return GdprMessage.builder()
                .email(TestUtil.genEmail(seed))
                .id(seed)
                .userId(UUID.randomUUID())
                .username(TestUtil.genField("username", seed));
    }
}
