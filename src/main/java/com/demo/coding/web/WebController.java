package com.demo.coding.web;

import com.demo.coding.application.configuration.LogInfo;
import com.demo.coding.domain.model.UserDO;
import com.demo.coding.domain.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Controller
@RequestMapping("base")
public class WebController {

    private Log LOG = LogFactory.getLog(WebController.class);

    @Autowired
    UserService userService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/test")
    @LogInfo
    public ResponseEntity<String> helloWorld(){
        LOG.info("run hello world ...");
        return ResponseEntity.ok("Hello World!");
    }

    @PostMapping("/user")
    public ResponseEntity<Void> createUser(@RequestBody UserDO user){
        LOG.info("create User: name:"+user.getName()+", email:"+user.getEmail());
        Long id = userService.createUser(user);
        URI location= ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/base/user/"+id.toString())
                .build().toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/mq")
    public ResponseEntity<Void> sendMessage(@RequestBody String message){
        LOG.info("Send message: "+message);
        rabbitTemplate.convertAndSend("amq.direct", "#", message);
        return ResponseEntity.ok().build();
    }
}
