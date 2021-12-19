package com.example.week4tr1.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @SequenceGenerator(name = "contact_sequence", sequenceName = "contact_sequence", allocationSize = 1)
    @GeneratedValue(strategy  = SEQUENCE, generator = "contact_sequence")
    @Column(name = "contactId")
    private Long contactId;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(50)")
    private String name;

    @Column(name = "phone", nullable = false, columnDefinition = "VARCHAR(50)")
    private String phone;

    @Column(name = "email", nullable = false, columnDefinition = "VARCHAR(50)")
    private String email;

    @Column(name = "address", nullable = false, columnDefinition = "VARCHAR(150)")
    private String address;

    @Column(name = "remark", nullable = false, columnDefinition = "VARCHAR(150)")
    private String remark;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private UserInfo userInfo;

}
