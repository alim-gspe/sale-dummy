package com.gspe.sale.po;

import com.gspe.sale.bill.BillContact;
import com.gspe.sale.bill.BillTo;
import com.gspe.sale.customer.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String salesName;
    private String site;
    private String materialDocumentNumber;
    private String currency;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "bill_to_id", referencedColumnName = "id")
    private BillTo billTo;

    @ManyToOne
    @JoinColumn(name = "bill_contact_id", referencedColumnName = "id")
    private BillContact billContact;

    @OneToMany(mappedBy = "purchaseOrder")
    private List<PurchaseOrderProduct> products;
}
