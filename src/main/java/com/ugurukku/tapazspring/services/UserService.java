package com.ugurukku.tapazspring.services;

import com.ugurukku.tapazspring.dto.user.*;
import com.ugurukku.tapazspring.entities.User;
import com.ugurukku.tapazspring.exceptions.user.IncorrectVerificationException;
import com.ugurukku.tapazspring.exceptions.user.AuthenticationFailedException;
import com.ugurukku.tapazspring.exceptions.user.UserAlreadyExistsException;
import com.ugurukku.tapazspring.exceptions.user.UserNotFoundException;
import com.ugurukku.tapazspring.repositories.UserRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    private final UserMapper userMapper;

    private final JavaMailSender mailSender;

    private final PasswordEncoder encoder;

    @Value("${spring.mail.username}")
    String fromAddress;

    public UserService(UserRepository repository, UserMapper userMapper, JavaMailSender mailSender, PasswordEncoder encoder) {
        this.repository = repository;
        this.userMapper = userMapper;
        this.mailSender = mailSender;
        this.encoder = encoder;
    }

    public List<UserDto> getAll() {
        return userMapper.toUserDtoList(repository.findAll());
    }

    public User getUserByEmail(String email) {
        return repository.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found for email : " + email));
    }

    public UserDto getUserById(String id) {
        return userMapper.toUserDto(findUserById(id));
    }

    public Long getCount() {
        return repository.count();
    }

    public void addUser(CreateUserRequest userRequest) throws MessagingException, UnsupportedEncodingException {

        if (userExistsByEmail(userRequest.email()))
            throw new UserAlreadyExistsException(String.format("e poçt ünvanı(%s) götürülmüşdür!", userRequest.email()));

        User user = userMapper.toUser(userRequest);
        user.setPassword(encoder.encode(userRequest.password()));

        String randomCode = RandomString.make(64);
        user.setVerificationCode(randomCode);
        user.setEnabled(false);

        User savedUser = repository.save(user);
        savedUser.setPassword(userRequest.password());


        sendVerificationEmail(user);

    }

    private void sendVerificationEmail(User user)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String senderName = "UKKU AZ";
        String subject = "Please verify your registration";
        String content = "Əziz istifadəçimiz [[name]],<br>"
                + "Təhlükəsizlik kodunuz:<br>"
                + "<h3>[[URL]]</h3>"
                + "Təşşəkür edirik,<br>"
                + senderName;

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getUsername());
        String verificationCode = user.getVerificationCode();

        content = content.replace("[[URL]]", verificationCode);

        helper.setText(content, true);

        mailSender.send(message);

    }



    public void updateUser(String id, UpdateUserRequest userRequest) {
        User user = findUserById(id);
        user.setUsername(userRequest.username() != null ? userRequest.username() : user.getUsername());
        user.setPassword(userRequest.password() != null ? encoder.encode(userRequest.password()) : user.getPassword());
        repository.save(user);
    }

    private User findUserById(String id) {
        return repository
                .findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(String
                                .format("User with id:%s is unavailable", id)));
    }

    private boolean userExistsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    public User authenticate(UserLoginDto userLoginDto) {

        User user = getUserByEmail(userLoginDto.email());

        if (!(encoder.matches(userLoginDto.password(), user.getPassword()))) {
            throw new AuthenticationFailedException("E poçt və ya şifrə yanlışdır!");
        }
        user.setPassword(userLoginDto.password());
        return user;
    }

    public Boolean verify(String code) {
        User user = repository.findUserByVerificationCode(code).orElseThrow(() -> new IncorrectVerificationException("Təhlükəsizlik kodu yanlışdır!"));
        user.setEnabled(true);
        user.setVerificationCode("");

        return true;
    }
}
