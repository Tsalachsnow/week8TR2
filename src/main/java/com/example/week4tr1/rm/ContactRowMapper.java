package com.example.week4tr1.rm;

import com.example.week4tr1.model.Contact;
import com.example.week4tr1.model.UserInfo;
import com.example.week4tr1.services.serviceImplementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactRowMapper implements RowMapper<Contact> {
    private UserServiceImpl userServiceImpl;

    @Autowired
    public ContactRowMapper(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    public ContactRowMapper() {

    }

    @Override
    public Contact mapRow(ResultSet rs, int i) throws SQLException {
        Contact c=new Contact();
        UserInfo user = userServiceImpl.getUserById(rs.getLong("userId"));
        c.setContactId(rs.getLong("contactId"));
        c.setUserInfo(user);
        c.setName(rs.getString("name"));
        c.setEmail(rs.getString("email"));
        c.setAddress(rs.getString("address"));
        c.setPhone(rs.getString("phone"));
        c.setRemark(rs.getString("remark"));
        return c;
    }
}
