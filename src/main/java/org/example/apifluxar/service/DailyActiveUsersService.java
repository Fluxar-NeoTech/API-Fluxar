package org.example.apifluxar.service;

import org.example.apifluxar.model.DailyActiveUsers;
import org.example.apifluxar.repository.DailyActiveUsersRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class DailyActiveUsersService {

    final DailyActiveUsersRepository dailyActiveUsersRepository;

    public DailyActiveUsersService(DailyActiveUsersRepository dailyActiveUsersRepository) {
        this.dailyActiveUsersRepository = dailyActiveUsersRepository;
    }

    public void insertAccess(Long userId, String origin) {
        dailyActiveUsersRepository.insertAccess(userId, origin);
    }
}
