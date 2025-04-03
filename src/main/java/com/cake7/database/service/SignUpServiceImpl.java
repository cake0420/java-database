package com.cake7.database.service;

import com.cake7.database.domain.Users;
import com.cake7.database.model.dto.SignUpRequestDTO;
import com.cake7.database.model.repository.UserRepository;
import com.cake7.database.util.Encrypt;
import com.cake7.database.util.UuidToBinary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class SignUpServiceImpl implements SignUpService {
    Logger logger = LoggerFactory.getLogger(SignUpServiceImpl.class.getName());

    private final UserRepository userRepository;
    private final Encrypt encrypt;
    private final UuidToBinary uuidToBinary;

    @Autowired
    public SignUpServiceImpl(UserRepository userRepository, Encrypt encrypt, UuidToBinary uuidToBinary) {
        this.userRepository = userRepository;
        this.encrypt = encrypt;
        this.uuidToBinary = uuidToBinary;
    }

    @Override
    public Users signUp(SignUpRequestDTO signUpRequestDTO) throws Exception {
        if (userRepository.existByEmail(signUpRequestDTO.email())) {
            throw new DuplicateKeyException("이미 존재하는 이메일입니다.");
            }
        try {
                String salt = encrypt.getSalt();
                logger.info("salt: {}", salt);
                String encryptPassword = encrypt.getEncrypt(signUpRequestDTO.password());
                UUID uuid = UUID.randomUUID();
                byte[] binary_uuid = uuidToBinary.uuidToBytes(uuid);
                String newPassword = encryptPassword + salt;
                // UUID를 포함한 Users 객체 생성
                Users newUser = new Users(
                        binary_uuid,
                        signUpRequestDTO.name(),
                        signUpRequestDTO.email(),
                        newPassword,
                        salt
                );


                userRepository.save(newUser);
                return newUser;

        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception("회원 가입 중 오류 발생", e);
        }
    }
}
