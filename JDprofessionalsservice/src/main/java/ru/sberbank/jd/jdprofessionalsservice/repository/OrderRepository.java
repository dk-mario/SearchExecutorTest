package ru.sberbank.jd.jdprofessionalsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sberbank.jd.jdprofessionalsservice.model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByOrderByIdAsc();

    List<Order> findByLoginOrderByIdAsc(String login);


}

