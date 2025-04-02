package com.prototype.triptop.identityprovider;

import com.prototype.triptop.identityprovider.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class AuthRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveUser(UserInfo userInfo, String accessToken) {
        String sql = "INSERT INTO TriptopGebruiker (voornaam, achternaam, tussenvoegsels, email, access_token) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, userInfo.getFirstName(), userInfo.getLastName(), userInfo.getPrefix(), userInfo.getEmail(), accessToken);
    }

    public Map<String, Object> getUser(String email) {
        try {
            return jdbcTemplate.queryForMap("SELECT * FROM TriptopGebruiker WHERE email = ?", email);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public boolean userExists(String email) {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TriptopGebruiker WHERE email = ?", Integer.class, email) > 0;
    }

    public void updateUser(
            UserInfo userInfo,
            String accessToken
    ) {
        String sql = "UPDATE TriptopGebruiker SET voornaam = ?, achternaam = ?, tussenvoegsels = ?, email = ?, access_token = ? WHERE email = ?";
        jdbcTemplate.update(sql, userInfo.getFirstName(), userInfo.getLastName(), userInfo.getPrefix(), userInfo.getEmail(), accessToken, userInfo.getEmail());
    }


}
