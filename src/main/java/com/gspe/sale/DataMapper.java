package com.gspe.sale;

import com.gspe.sale.bill.BillContact;
import com.gspe.sale.bill.BillTo;
import com.gspe.sale.customer.Customer;
import com.gspe.sale.customer.CustomerRepository;
import com.gspe.sale.delivery.DeliveryOrder;
import com.gspe.sale.delivery.DeliveryOrderProduct;
import com.gspe.sale.dto.*;
import com.gspe.sale.po.PurchaseOrder;
import com.gspe.sale.po.PurchaseOrderProduct;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataMapper {

    @Autowired
    CustomerRepository customerRepository;

    public List<CustomerDto> customerMap(List<Customer> customers) {
        List<CustomerDto> customerDtos = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerDto customerDto = new CustomerDto();
            customerDto.setId(customer.getId());
            customerDto.setName(customer.getName());
            customerDto.setCode(customer.getCode());
            customerDtos.add(customerDto);
        }
        return customerDtos;
    }

    public List<PurchaseOrderDto> purchaseOrderMap(List<PurchaseOrder> purchaseOrders) {
        List<PurchaseOrderDto> purchaseOrderDtos = new ArrayList<>();
        for (PurchaseOrder purchaseOrder : purchaseOrders) {
            PurchaseOrderDto purchaseOrderDto = new PurchaseOrderDto();
            purchaseOrderDto.setId(purchaseOrder.getId());
            purchaseOrderDto.setName(purchaseOrder.getName());
            purchaseOrderDto.setAddress(purchaseOrder.getAddress());
            purchaseOrderDto.setSalesName(purchaseOrder.getSalesName());
            purchaseOrderDto.setCurrency(purchaseOrder.getCurrency());
            purchaseOrderDto.setMaterialDocumentNumber(purchaseOrder.getMaterialDocumentNumber());
            purchaseOrderDto.setSite(purchaseOrder.getSite());
            purchaseOrderDto.setBillTo(purchaseOrder.getBillTo().getName());
            purchaseOrderDto.setBillContact(purchaseOrder.getBillContact().getName());
            purchaseOrderDto.setTotal(totalPo(purchaseOrder.getProducts()));
            purchaseOrderDto.setProducts(
                    productPurchaseMap(purchaseOrder.getProducts())
            );
            purchaseOrderDtos.add(purchaseOrderDto);
        }
        return purchaseOrderDtos;
    }

    public List<DeliveryDto> deliveryMap(List<DeliveryOrder> deliveryOrders) {
        List<DeliveryDto> deliveryDtos = new ArrayList<>();
        for (DeliveryOrder deliveryOrder : deliveryOrders) {
            DeliveryDto deliveryDto = new DeliveryDto();
            deliveryDto.setId(deliveryOrder.getId());
            deliveryDto.setName(deliveryOrder.getName());
            deliveryDto.setBillTo(deliveryOrder.getBillTo().getName());
            deliveryDto.setBillContact(deliveryOrder.getBillContact().getName());
            deliveryDto.setPurchaseOrderDto(purchaseOrder(deliveryOrder.getPurchaseOrder()));
            deliveryDto.setProducts(productDeliveryMap(deliveryOrder.getProducts()));
            deliveryDtos.add(deliveryDto);
        }
        return deliveryDtos;
    }

    public BigDecimal totalPo(List<PurchaseOrderProduct> products) {
        BigDecimal total = BigDecimal.ZERO;
        for (PurchaseOrderProduct purchaseOrderProduct : products) {
            total = total.add(purchaseOrderProduct.getTotal());
        }
        return total;
    }


    public PurchaseOrderDto purchaseOrder(PurchaseOrder purchaseOrder) {
        PurchaseOrderDto purchaseOrderDto = new PurchaseOrderDto();
        purchaseOrderDto.setId(purchaseOrder.getId());
        purchaseOrderDto.setName(purchaseOrder.getName());
        purchaseOrderDto.setAddress(purchaseOrder.getAddress());
        purchaseOrderDto.setSalesName(purchaseOrder.getSalesName());
        purchaseOrderDto.setCurrency(purchaseOrder.getCurrency());
        purchaseOrderDto.setMaterialDocumentNumber(purchaseOrder.getMaterialDocumentNumber());
        purchaseOrderDto.setSite(purchaseOrder.getSite());
        purchaseOrderDto.setTotal(totalPo(purchaseOrder.getProducts()));
        return purchaseOrderDto;
    }

    public List<ProductDto> productPurchaseMap(List<PurchaseOrderProduct> products) {
        List<ProductDto> productDtos = new ArrayList<>();
        for (PurchaseOrderProduct purchaseOrderProduct : products) {
            ProductDto productDto = new ProductDto();
            productDto.setProductId(purchaseOrderProduct.getProduct().getId());
            productDto.setProductDetail(purchaseOrderProduct.getProduct().getProductDetail());
            productDto.setDiscount(purchaseOrderProduct.getDiscount());
            productDto.setType(purchaseOrderProduct.getProduct().getType());
            productDto.setDiscountAmount(purchaseOrderProduct.getDiscountAmount());
            productDto.setUnitMeasure(purchaseOrderProduct.getProduct().getUnitMeasure());
            productDto.setUnitPrice(purchaseOrderProduct.getProduct().getUnitPrice());
            productDto.setQuantity(purchaseOrderProduct.getQuantity());
            productDto.setTotal(purchaseOrderProduct.getTotal());
            productDto.setDoNumber("");
            productDto.setPoNumber(purchaseOrderProduct.getPurchaseOrder().getName());
            productDto.setLineItem(purchaseOrderProduct.getLineItem());
            productDtos.add(productDto);
        }
        return productDtos;
    }

    public List<ProductDto> productDeliveryMap(List<DeliveryOrderProduct> products) {
        List<ProductDto> productDtos = new ArrayList<>();
        for (DeliveryOrderProduct deliveryOrderProduct : products) {
            ProductDto productDto = new ProductDto();
            productDto.setProductId(deliveryOrderProduct.getProduct().getId());
            productDto.setProductDetail(deliveryOrderProduct.getProduct().getProductDetail());
            productDto.setType(deliveryOrderProduct.getProduct().getType());
            productDto.setDiscount(deliveryOrderProduct.getDiscount());
            productDto.setDiscountAmount(deliveryOrderProduct.getDiscountAmount());
            productDto.setUnitMeasure(deliveryOrderProduct.getProduct().getUnitMeasure());
            productDto.setUnitPrice(deliveryOrderProduct.getProduct().getUnitPrice());
            productDto.setQuantity(deliveryOrderProduct.getQuantity());
            productDto.setTotal(deliveryOrderProduct.getTotal());
            productDto.setDoNumber(deliveryOrderProduct.getDeliveryOrder().getName());
            productDto.setPoNumber(deliveryOrderProduct.getPurchaseOrder().getName());
            productDto.setLineItem(deliveryOrderProduct.getLineItem());
            productDtos.add(productDto);
        }
        return productDtos;
    }

    public List<DropDown> dropDownMap(Long customerId, String type) {
        List<DropDown> dropDowns = new ArrayList<>();
        val customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            if (type.equals("bill")) {
                val billToList = customer.get().getBillToList();
                for (BillTo billTo : billToList) {
                    DropDown dropDown = new DropDown();
                    dropDown.setId(billTo.getId());
                    dropDown.setName(billTo.getName());
                    dropDowns.add(dropDown);
                }
            } else {
                val billToContactList = customer.get().getBillContactList();
                for (BillContact billContact : billToContactList) {
                    DropDown dropDown = new DropDown();
                    dropDown.setId(billContact.getId());
                    dropDown.setName(billContact.getName());
                    dropDowns.add(dropDown);
                }
            }
        }
        return dropDowns;
    }

    public List<NpwpDto> listNpwp(List<Customer> customers) {
        List<NpwpDto> npwpDtoList = new ArrayList<>();
        for (Customer customer : customers) {
            NpwpDto npwpDto = new NpwpDto();
            npwpDto.setId(customer.getId());
            npwpDto.setName(customer.getName());
            npwpDto.setCode(customer.getCode());
            npwpDto.setNpwp(customer.getNpwp());
            npwpDto.setNik(customer.getNik());
            npwpDto.setWapu(customer.isWapu());
            npwpDto.setAddressNpwp(customer.getAddressNpwp());
            npwpDtoList.add(npwpDto);
        }
        return npwpDtoList;
    }

    public Customer updateNpwp(NpwpDto npwpDto) {
        val customer = customerRepository.findById(npwpDto.getId());
        if (customer.isPresent()) {
            customer.get().setNpwp(npwpDto.getNpwp());
            customer.get().setNik(npwpDto.getNik());
            customer.get().setAddressNpwp(npwpDto.getAddressNpwp());
            customer.get().setWapu(npwpDto.isWapu());
        }
        return customer.get();
    }

    public NpwpDto npwpById(Customer customer) {
        NpwpDto npwpDto = new NpwpDto();
        npwpDto.setId(customer.getId());
        npwpDto.setName(customer.getName());
        npwpDto.setCode(customer.getCode());
        npwpDto.setNpwp(customer.getNpwp());
        npwpDto.setNik(customer.getNik());
        npwpDto.setWapu(customer.isWapu());
        npwpDto.setAddressNpwp(customer.getAddressNpwp());
        return npwpDto;
    }
}
