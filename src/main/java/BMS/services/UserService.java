package BMS.services;

import BMS.models.Ticket;
import BMS.models.User;
import BMS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public User singup(String name, String emailId, String password) throws Exception {
        User savedUser = userRepository.findUserByEmail(emailId);
        if(savedUser != null){
            throw new Exception("User with same email Id is exist");
        }
        User user = new User();
        user.setName(name);
        user.setEmail(emailId);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(password));
        //user.setTickets(new ArrayList<>());
        return userRepository.save(user);
    }

    public User login(String email, String password) throws Exception {
        User savedUser = userRepository.findUserByEmail(email);
        if(savedUser == null){
            throw new Exception("User with this email Id does not exist");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(encoder.matches(savedUser.getPassword(), password)){
            return savedUser;
        }
        throw new Exception("Invalid password");
    }

}
