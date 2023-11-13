package com.invest.ecommerceapi.application.services.user;

import com.invest.ecommerceapi.application.cryptography.Encrypter;
import com.invest.ecommerceapi.application.cryptography.Hasher;
import com.invest.ecommerceapi.domain.schemas.UserAuthenticationRequestSchema;
import com.invest.ecommerceapi.domain.schemas.UserResponseSchema;
import com.invest.ecommerceapi.domain.schemas.UserSchema;
import com.invest.ecommerceapi.infra.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Hasher hasher;
    @Autowired
    private Encrypter encrypter;

    public UserResponseSchema auth(UserAuthenticationRequestSchema requestModel) {
        var userExists = this.userRepository.findByEmail(requestModel.getEmail());
        if (userExists == null) return null;
        boolean passwordIsValid = this.hasher.compare(requestModel.getPassword(), userExists.getPassword());
        if (!passwordIsValid) return null;
        String accessToken = this.encrypter.encrypt(userExists.getId());
        UserSchema userSchema = new UserSchema(
                userExists.getEmail(),
                userExists.getName(),
                userExists.isOwner(),
                userExists.getCep(),
                userExists.getAddress()
        );
        return new UserResponseSchema(userSchema, accessToken);
    }
}
