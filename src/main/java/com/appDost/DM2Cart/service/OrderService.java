//package com.appDost.DM2Cart.service;
//
//import com.appDost.DM2Cart.model.AppUser;
//import com.appDost.DM2Cart.model.CartItem;
//import com.appDost.DM2Cart.model.Order;
//import com.appDost.DM2Cart.repository.OrderRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//@Service
//public class OrderService {
//
//    @Autowired
//    private InventoryService inventoryService;
//
//    @Autowired
//    private OrderRepository orderRepo;
//
//    public Order placeOrder(AppUser user, List<CartItem> items) {
//
//        Order order = new Order();
//        order.setUser(user);
//        order.setTenant(TenantContext.getTenant());
//        order.setStatus("CREATED");
//        order.setOrderNumber("ORD-" + System.currentTimeMillis());
//
//        BigDecimal total = BigDecimal.ZERO;
//
//        for (CartItem item : items) {
//            inventoryService.reserveStock(
//                    item.getProductId(),
//                    item.getQuantity(),
//                    order.getId()
//            );
//            total = total.add(item.getPriceAtAdd()
//                    .multiply(BigDecimal.valueOf(item.getQuantity())));
//        }
//
//        order.setTotalAmount(total);
//        return orderRepo.save(order);
//    }
//}
//
