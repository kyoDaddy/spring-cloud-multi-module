package com.mall.userservice.process.security.service;

import com.mall.userservice.process.security.service.impl.DefaultUserDetails;

import java.util.Map;

public interface DefaultUserDetailsJwtClaimsConverter {

    DefaultUserDetails convert(final Map<String, Object> claims);

    Map<String, Object> convert(final DefaultUserDetails userDetails);

}
