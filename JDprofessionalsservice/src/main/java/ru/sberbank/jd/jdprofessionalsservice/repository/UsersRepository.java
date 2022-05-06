package ru.sberbank.jd.jdprofessionalsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sberbank.jd.jdprofessionalsservice.model.UsersEntity;

public interface UsersRepository extends JpaRepository<UsersEntity, String> {
}
