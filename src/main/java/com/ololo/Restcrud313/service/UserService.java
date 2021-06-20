package com.ololo.Restcrud313.service;

import com.ololo.Restcrud313.model.Role;
import com.ololo.Restcrud313.model.User;
import com.ololo.Restcrud313.repositories.RoleRepository;
import com.ololo.Restcrud313.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository,
                           EntityManager entityManager, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.entityManager = entityManager;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public void updateUser(User user) {
        if (!userRepository.getById(user.getId()).getPassword().equals(user.getPassword())) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        userRepository.saveAndFlush(user);
    }

    @Transactional
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }


    @Transactional
    public void addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.saveAndFlush(user);
    }


    @Transactional
    public User findById(long id) {
        return userRepository.findById(id).get();
    }

    @Transactional
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }


    @Transactional
    public User findByUsername(String username) {
        return userRepository.findByEmail(username);
    }

    @Transactional
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Transactional
    public Role getRoleById(Long id) {
        return roleRepository.getById(id);
    }

    @Transactional
    public List<Role>setRolesToUser(String[] arr) {
        List<Integer> listOfId = Arrays.stream(arr).mapToInt(Integer::parseInt)
                .boxed().collect(Collectors.toList());

        return entityManager.createQuery("SELECT a FROM Role a WHERE a.id IN (:id)", Role.class)
                .setParameter("id",listOfId)
                .getResultList();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new User(
                user.getUsername(), user.getPassword(), user.getRoles());
    }
}
