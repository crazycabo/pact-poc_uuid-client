package com.ingendevelopment;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by BRIAN.SMITH on 08/09/2020
 * Project: uuid-client
 */
@Controller
public class IndexController
{
    private final UUIDGenerateClient uuidGenerateClient;

    public IndexController(UUIDGenerateClient uuidGenerateClient) {
        this.uuidGenerateClient = uuidGenerateClient;
    }

    @RequestMapping({"/", ""})
    public String displayIndex() {
        return "index";
    }

    @RequestMapping("uuid/generate")
    public String generateUUID(Model model) {
        model.addAttribute("uuid", uuidGenerateClient.getUUID().uuid);
        return "index";
    }
}
