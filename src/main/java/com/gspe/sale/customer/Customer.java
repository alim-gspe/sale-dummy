package com.gspe.sale.customer;

import com.gspe.sale.bill.BillContact;
import com.gspe.sale.bill.BillTo;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private String npwp;
    private String nik;
    @Column(columnDefinition = "TEXT")
    private String addressNpwp;
    private boolean wapu;

    @OneToMany(mappedBy = "customer")
    private List<BillTo> billToList;

    @OneToMany(mappedBy = "customer")
    private List<BillContact> billContactList;
}
