package com.gspe.sale;

import com.gspe.sale.customer.Customer;
import com.gspe.sale.customer.CustomerRepository;
import com.gspe.sale.delivery.DeliveryOrderRepository;
import com.gspe.sale.dto.*;
import com.gspe.sale.po.PurchaseOrderRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MainService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    DeliveryOrderRepository deliveryOrderRepository;

    @Autowired
    DataMapper dataMapper;

    public List<CustomerDto> customers() {
        val customers = customerRepository.findAll();
        val result = dataMapper.customerMap(customers);
        return result;
    }

    public List<PurchaseOrderDto> purchaseOrders(Long customerId) {
        val purchaseOrders = purchaseOrderRepository.findByCustomer_Id(customerId);
        val result = dataMapper.purchaseOrderMap(purchaseOrders);
        return result;
    }

    public List<DeliveryDto> deliveryOrders(Long customerId) {
        val deliverOrder = deliveryOrderRepository.findByCustomer_Id(customerId);
        val result = dataMapper.deliveryMap(deliverOrder);
        return  result;
    }

    public List<DropDown> dropDownList(Long customerId, String type) {
        val result = dataMapper.dropDownMap(customerId, type);
        return  result;
    }

    public List<NpwpDto> listNpwp() {
        val result = dataMapper.listNpwp(customerRepository.findAll());
        return result;
    }

    public void updateNpwp(NpwpDto npwpDto) {
        val customer = dataMapper.updateNpwp(npwpDto);
        customerRepository.save(customer);
    }

    public NpwpDto npwpById(Long id) {
        val npwp = dataMapper.npwpById(customerRepository.findById(id).get());
        return npwp;
    }
}
