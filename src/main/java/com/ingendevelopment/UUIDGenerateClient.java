package com.ingendevelopment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "uuidGenerate", url = "http://localhost:8221/api/v0")
public interface UUIDGenerateClient {

    @RequestMapping(method = RequestMethod.GET, value = "/uuid")
    UUIDResult getUUID();
}
