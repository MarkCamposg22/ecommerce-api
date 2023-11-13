package com.invest.ecommerceapi.application.services.user;

import com.invest.ecommerceapi.application.cryptography.Encrypter;
import com.invest.ecommerceapi.application.cryptography.Hasher;
import com.invest.ecommerceapi.domain.schemas.UserRegisterRequestSchema;
import com.invest.ecommerceapi.domain.schemas.UserResponseSchema;
import com.invest.ecommerceapi.domain.schemas.UserSchema;
import com.invest.ecommerceapi.infra.entities.User;
import com.invest.ecommerceapi.infra.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Hasher hasher;
    @Autowired
    private Encrypter encrypter;

    public UserResponseSchema register(UserRegisterRequestSchema requestModel) {
        var userAlreadyExists = this.userRepository.findByEmail(requestModel.getEmail());
        if (userAlreadyExists != null) return null;
        requestModel.setPassword(this.hasher.hash(requestModel.getPassword()));
        User user = new User(requestModel);
        User userCreated = this.userRepository.save(user);
        String accessToken = this.encrypter.encrypt(userCreated.getId());
        UserSchema userSchema = new UserSchema(
                userCreated.getEmail(),
                userCreated.getName(),
                userCreated.isOwner(),
                userCreated.getCep(),
                userCreated.getAddress()
        );
        return new UserResponseSchema(userSchema, accessToken);
    }
}
