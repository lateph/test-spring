package com.mgg.springboot.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

@Service
public class CommonUtil {

    public static List<Order> processSort(int $skip, int $limit, Map<String, String> requestParams) {

        String sortColumn = "createdAt";
        String sortDirection = "-1";

        // Process $sort
        for (Map.Entry<String, String> entry : requestParams.entrySet()) {
            System.out.println("entry.getKey()" + entry.getKey());
            if (entry.getKey().length() > 4 && entry.getKey().substring(0, 5).equals("$sort")) {
                sortColumn = entry.getKey().substring(6, entry.getKey().length() - 1);

                sortDirection = entry.getValue();
                break;
            }
        }

        List<Order> orders = new ArrayList<Order>();

        Sort.Direction dire = sortDirection.equals("-1") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Order order = new Order(dire, sortColumn);
        orders.add(order);

        return orders;
    }

}
