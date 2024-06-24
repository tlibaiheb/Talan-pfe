// package com.example.bpiappapi.auth.controller;


// import com.example.bpiappapi.auth.model.RegistrationRequest;
// import com.example.bpiappapi.auth.service.RegistrationService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.Map;

// @RestController
// //@CrossOrigin(origins = "http://localhost:4200")

// @RequestMapping(path = "/api/v1/registration")
// public class RegistrationController {
//     private final RegistrationService registrationService;
//     @Autowired
//     public RegistrationController(RegistrationService registrationService) {
//         this.registrationService = registrationService;
//     }
//     @PostMapping
//     public ResponseEntity<?> register(@RequestBody RegistrationRequest request) {
//         try {
//             String token = registrationService.register(request);
//             return ResponseEntity.ok().body(Map.of("token", token));
//         } catch (IllegalStateException e) {
//             return ResponseEntity.badRequest().body(e.getMessage());
//         }
//     }


//     @GetMapping(path = "confirm")
//     public String confirm(@RequestParam("token") String token) {
//         return registrationService.confirmToken(token);
//     }
// }

package com.example.bpiappapi.auth.controller;

import com.example.bpiappapi.auth.model.RegistrationRequest;
import com.example.bpiappapi.auth.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody RegistrationRequest request) {
        try {
            String token = registrationService.register(request);
            return ResponseEntity.ok().body(Map.of("token", token));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}

