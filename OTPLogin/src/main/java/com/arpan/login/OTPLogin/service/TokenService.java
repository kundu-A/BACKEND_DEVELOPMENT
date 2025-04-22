package com.arpan.login.OTPLogin.service;

import com.arpan.login.OTPLogin.DTO.OneTimeTokenDTO;
import com.arpan.login.OTPLogin.models.OneTimeToken;
import com.arpan.login.OTPLogin.repository.OneTimeTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenService {

    BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

    @Autowired
    OneTimeTokenRepository oneTimeTokenRepository;

    public String generateToken(OneTimeTokenDTO oneTimeTokenDTO){
        try{
            OneTimeToken oneTimeToken= oneTimeTokenRepository.findOneTimeTokenByPhoneNumber(oneTimeTokenDTO.getPhoneNumber());
            if(oneTimeToken!=null) {
                String token=UUID.randomUUID().toString();
                String hashedToken=convertTokenToHashData(token);
                oneTimeToken.setToken(hashedToken);
                oneTimeToken.setUsed(false);
                oneTimeTokenRepository.save(oneTimeToken);
                return oneTimeToken.getPhoneNumber()+" : "+token;
            }
                oneTimeToken=new OneTimeToken();
            String token= UUID.randomUUID().toString();
            String hashedToken=convertTokenToHashData(token);
            oneTimeToken.setToken(hashedToken);
            oneTimeToken.setPhoneNumber(oneTimeTokenDTO.getPhoneNumber());
            oneTimeToken.setUsed(false);
            oneTimeTokenRepository.save(oneTimeToken);
            return oneTimeTokenDTO.getPhoneNumber()+" : "+token;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean verifyToken(OneTimeTokenDTO oneTimeTokenDTO){
        String phoneNumber= oneTimeTokenDTO.getPhoneNumber();
        String token= oneTimeTokenDTO.getToken();
        OneTimeToken oneTimeToken= oneTimeTokenRepository.findOneTimeTokenByPhoneNumber(phoneNumber);
        String hashedToken=oneTimeToken.getToken();
        if(checkEquality(token,hashedToken)) {
            oneTimeTokenRepository.delete(oneTimeToken);
            return true;
        }
        return false;
    }

    private String convertTokenToHashData(String token){
        return bCryptPasswordEncoder.encode(token);
    }

    private boolean checkEquality(String token , String hashedToken){
        return bCryptPasswordEncoder.matches(token,hashedToken);
    }
}
