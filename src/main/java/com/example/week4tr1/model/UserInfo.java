package com.example.week4tr1.model;

import lombok.*;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name = "userInfo",
        uniqueConstraints = {
                @UniqueConstraint(name = "user_email_constraint", columnNames = "email"),
        }
)
public class UserInfo {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy  = SEQUENCE, generator = "user_sequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(50)")
    private String name;

    @Column(name = "phone", nullable = false, columnDefinition = "VARCHAR(50)")
    private String phone;

    @Column(name = "email", nullable = false, columnDefinition = "VARCHAR(50)")
    private String email;

    @Column(name = "address", nullable = false, columnDefinition = "VARCHAR(150)")
    private String address;

    @Column(name = "login_name", nullable = false, columnDefinition = "VARCHAR(20)")
    private String loginName;

    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR(50)")
    private String password;

    @Column(name = "role", nullable = false, columnDefinition = "INT")
    private int role;

    @Column(name = "login_status", nullable = false, columnDefinition = "INT")
    private int loginStatus;

    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.REMOVE)
    private List<Contact> contact;

    public UserInfo(Long id, String name, String phone, String email, String address, String loginName, String password, int role, int loginStatus) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.loginName = loginName;
        this.password = password;
        this.role = role;
        this.loginStatus = loginStatus;
    }
}
