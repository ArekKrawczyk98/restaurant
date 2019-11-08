package com.example.easy.domain.supply;

public interface DeliveryRepository {

    Delivery save();
    Delivery load(String id);



}
