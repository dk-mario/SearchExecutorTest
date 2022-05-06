package ru.sberbank.jd.jdprofessionalsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.sberbank.jd.jdprofessionalsservice.model.Order;
import ru.sberbank.jd.jdprofessionalsservice.service.CustomUserDetailedService;
import ru.sberbank.jd.jdprofessionalsservice.service.OrderService;
import ru.sberbank.jd.jdprofessionalsservice.service.TelegramNotificationService;

import java.util.List;

@Controller
public class OrderController {

    private final OrderService orderService;

    @Autowired
    private CustomUserDetailedService customUserDetailedService;

    @Autowired
    private TelegramNotificationService tlgService;


    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public String findAll(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // если роль Администратор или исполнитель, то показываем всё
        // для заказчика только его заказы
        List<Order> orders;
        if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CUST"))) {
            String loginUser = auth.getName();
            String nameUser = customUserDetailedService.getInitials(loginUser);
            orders = orderService.findByUser(nameUser);
            model.addAttribute("whoseOrders", "Заказы пользователя " + loginUser + " (" + nameUser + ")" );
        } else {
            orders = orderService.findAll();
            model.addAttribute("whoseOrders", "Все заказы");
        }
        model.addAttribute("orders", orders);
        return "order-list";
    }

    @GetMapping("/order-create")
    public String createOrderForm(Order order){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        order.setLogin(customUserDetailedService.getInitials(currentPrincipalName));
        return "order-create";
    }

    @PostMapping("/order-create")
    public String createOrder(Order order){
        orderService.saveOrder(order);
        tlgService.sendMessage("Создан новый заказ! \nСпециальность: " + order.getSpeciality() +
                " \nОписание: " + order.getDiscription() + "\nЦена " + order.getMaxPrice());
        return "redirect:/orders";
    }

    @GetMapping("order-delete/{id}")
    public String deleteOrder(@PathVariable("id") Long id){
        orderService.deleteById(id);
        return "redirect:/orders";
    }

    @GetMapping("/order-update/{id}")
    public String updateOrderForm(@PathVariable("id") Long id, Model model){
        Order order = orderService.findById(id);
        model.addAttribute("order", order);
        return "order-update";
    }
    @GetMapping("/order-accept/{id}")
    public String acceptOrderForm(@PathVariable("id") Long id, Model model){
        Order order = orderService.acceptOrder(id, orderService.findById(id));
        model.addAttribute("order", order);
        return "redirect:/orders";
    }
    @GetMapping("/order-finish/{id}")
    public String finishOrderForm(@PathVariable("id") Long id, Model model){
        Order order = orderService.finishOrder(id, orderService.findById(id));
        model.addAttribute("order", order);
        return "redirect:/orders";
    }
    @GetMapping("/order-return/{id}")
    public String returnOrderForm(@PathVariable("id") Long id, Model model){
        Order order = orderService.returnOrder(id, orderService.findById(id));
        model.addAttribute("order", order);
        return "redirect:/orders";
    }


    @PostMapping("/order-update")
    public String updateOrder(Order order){
        orderService.saveOrder(order);
        return "redirect:/orders";
    }
}
