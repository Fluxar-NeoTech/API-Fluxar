package org.example.apifluxar.security;

import org.example.apifluxar.model.Employee;
import org.example.apifluxar.repository.EmployeeRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomDetailsService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    public CustomDetailsService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + employee.getRole());

        System.out.println("Usuário autenticado: " + email);
        System.out.println("Authorities: " + authority);

        return new org.springframework.security.core.userdetails.User(
                employee.getEmail(),
                employee.getPassword(),
                Set.of(authority)
        );
    }
}
