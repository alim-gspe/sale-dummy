package com.gspe.sale.delivery;

import com.gspe.sale.bill.BillContact;
import com.gspe.sale.bill.BillTo;
import com.gspe.sale.customer.Customer;
import com.gspe.sale.po.PurchaseOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class DeliveryOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "bill_to_id", referencedColumnName = "id")
    private BillTo billTo;

    @ManyToOne
    @JoinColumn(name = "bill_contact_id", referencedColumnName = "id")
    private BillContact billContact;

    @OneToOne
    @JoinColumn(name = "purchase_order_id", referencedColumnName = "id")
    private PurchaseOrder purchaseOrder;

    @OneToMany(mappedBy = "deliveryOrder")
    private List<DeliveryOrderProduct> products;
}
