package be.kdg.prog6.boundedcontextA.adapters.in;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibraryRestController {


    @GetMapping("/hellob")
    public void sayHelloB(){
        System.out.println("Hello BoundedContext Bbbbb" +
                "sdfsdfsdfsf"
        );
    }

}
