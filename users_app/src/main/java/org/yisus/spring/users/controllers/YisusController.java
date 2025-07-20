package org.yisus.spring.users.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Stereotype
@RequestMapping("/yisus") //Me ayuda a definir un recurso base para todos los endpoints de este controlador
public class YisusController {

    @GetMapping() //Define un endpoint que responde a solicitudes GET en /yisus/hello
    public String helloWorld() {
        return "Hello World from YisusController!";
    }

}
