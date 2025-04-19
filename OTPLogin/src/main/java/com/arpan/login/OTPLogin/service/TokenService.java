package com.arpan.login.OTPLogin.service;

import com.arpan.login.OTPLogin.DTO.TokenDTO;
import com.arpan.login.OTPLogin.models.OneTimeToken;
import com.arpan.login.OTPLogin.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenService {

    BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

    @Autowired
    TokenRepository tokenRepository;

    public String generateToken(TokenDTO tokenDTO){
        try{
            OneTimeToken oneTimeToken=tokenRepository.findOneTimeTokenByPhoneNumber(tokenDTO.getPhoneNumber());
            if(oneTimeToken!=null) {
                String token=UUID.randomUUID().toString();
                String hashedToken=convertTokenToHashData(token);
                oneTimeToken.setToken(hashedToken);
                oneTimeToken.setUsed(false);
                tokenRepository.save(oneTimeToken);
                return oneTimeToken.getPhoneNumber()+" : "+token;
            }
                oneTimeToken=new OneTimeToken();
            String token= UUID.randomUUID().toString();
            String hashedToken=convertTokenToHashData(token);
            oneTimeToken.setToken(hashedToken);
            oneTimeToken.setPhoneNumber(tokenDTO.getPhoneNumber());
            oneTimeToken.setUsed(false);
            tokenRepository.save(oneTimeToken);
            return tokenDTO.getPhoneNumber()+" : "+token;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean verifyToken(TokenDTO tokenDTO){
        String phoneNumber=tokenDTO.getPhoneNumber();
        String token=tokenDTO.getToken();
        OneTimeToken oneTimeToken= tokenRepository.findOneTimeTokenByPhoneNumber(phoneNumber);
        String hashedToken=oneTimeToken.getToken();
        if(checkEquality(token,hashedToken)) {
            tokenRepository.delete(oneTimeToken);
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
