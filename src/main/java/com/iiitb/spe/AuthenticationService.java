package com.iiitb.spe;

import com.iiitb.spe.JwtUtil.TokenManager;
import com.iiitb.spe.models.User_Login;
import com.iiitb.spe.repositories.UserLoginRepository;
import com.iiitb.spe.service.MovieDetailsService;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private static final String ACCOUNT_SID = "AC00d6db9084eb417e0d35f8e8082638a3"; //System.getenv("ACbc91ba0b1ce5b5020130385133c2ae46");
    private static final String AUTH_TOKEN = "ceb481e3624e3a93ef880865420b08b9"; //System.getenv("aa2cab81ac3f795ce2280dbe04659ef1");

    public void create_service() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        com.twilio.rest.verify.v2.Service service = com.twilio.rest.verify.v2.Service.creator("My First Verify Service").create();
        System.out.println("Verification Service Created!");
        System.out.println(service.getSid());
        String service_sid = service.getSid();
    }
    public String send_otp(String mobile_number) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Verification verification = Verification.creator(
                        "VAee7296006168aef06bef5ffa1148a553",
                        "+91"+mobile_number,
                        "sms")
                .create();
        System.out.println(verification.getStatus());
        return verification.getStatus();
    }
    public String verify_otp(String otp, String mobile_number) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        VerificationCheck verificationCheck = VerificationCheck.creator(
                        "VAee7296006168aef06bef5ffa1148a553")
                .setTo("+91"+mobile_number)
                .setCode(otp)
                .create();
        System.out.println(verificationCheck.getStatus());
        return verificationCheck.getStatus();
    }
}
