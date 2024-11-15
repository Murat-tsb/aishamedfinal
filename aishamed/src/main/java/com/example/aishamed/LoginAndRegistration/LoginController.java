package com.example.aishamed.LoginAndRegistration;

import com.example.aishamed.repository.DoctorRepository;
import com.example.aishamed.repository.PatientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public LoginController(PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }
    @GetMapping("/")
    public ResponseEntity<Boolean> whoIsThis(@RequestParam String email){
        Boolean amIPatient = null;
        if (Objects.nonNull(patientRepository.getByEmail(email))) {amIPatient = true;}
        else
        if (Objects.nonNull(doctorRepository.getByEmail(email))){
            amIPatient = false;
        }
        return new ResponseEntity<>(amIPatient, HttpStatus.OK);
    }

}
