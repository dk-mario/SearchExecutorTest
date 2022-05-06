package ru.sberbank.jd.jdprofessionalsservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sberbank.jd.jdprofessionalsservice.model.Order;
import ru.sberbank.jd.jdprofessionalsservice.repository.OrderRepository;


import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order findById(Long id){
        return orderRepository.getById(id);
    }

    public List<Order> findByUser(String user){
        return orderRepository.findByLoginOrderByIdAsc(user);
    }


    public List<Order> findAll(){
        return orderRepository.findByOrderByIdAsc();
    }

    public Order saveOrder(Order order){
        return orderRepository.save(order);
    }
    public Order acceptOrder(Long id, Order oldOrder){
        oldOrder.setStatus("Заказ принят исполнителем");
        return orderRepository.save(oldOrder);
    }

    public Order finishOrder(Long id, Order oldOrder){
        oldOrder.setStatus("Заказ выполнен");
        return orderRepository.save(oldOrder);
    }
    public Order returnOrder(Long id, Order oldOrder){
        oldOrder.setStatus("Возвращен в работу");
        return orderRepository.save(oldOrder);
    }

    public void deleteById(Long id){
        orderRepository.deleteById(id);
    }
}
