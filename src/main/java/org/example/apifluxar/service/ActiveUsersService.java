package org.example.apifluxar.service;

import org.example.apifluxar.repository.ActiveUsersRepository;
import org.springframework.stereotype.Service;

@Service
public class ActiveUsersService {

    final ActiveUsersRepository ActiveUsersRepository;

    public ActiveUsersService(ActiveUsersRepository activeUsersRepository) {
        this.ActiveUsersRepository = activeUsersRepository;
    }

    public void insertAccess(Long userId, String origin) {
        ActiveUsersRepository.insertAccess(userId, origin);
    }
}
