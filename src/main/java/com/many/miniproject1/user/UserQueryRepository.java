package com.many.miniproject1.user;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserQueryRepository {
    private final EntityManager em;

    public User findById(int id) {
        Query query = em.createNativeQuery("select * from user_tb where id=?", User.class);
        query.setParameter(1, id);

        try {
            User user = (User) query.getSingleResult();
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public void companyInfoUpdate(UserRequest.CompanyInfoUpdateDTO requestDTO, int id, String profileFileName) {
        String updateQuery = "update user_tb set profile=?, address=?, tel=?, email=?";
        // Check if newPassword is not empty, then update the password
        if (StringUtils.isNotEmpty(requestDTO.getNewPassword())) {
            updateQuery += ", password=?";
        }
        updateQuery += " where id = ?";

        Query query = em.createNativeQuery(updateQuery);
        query.setParameter(1, profileFileName);
        query.setParameter(2, requestDTO.getAddress());
        query.setParameter(3, requestDTO.getTel());
        query.setParameter(4, requestDTO.getEmail());

        // If newPassword is not empty, set it; otherwise, set the existing password
        if (StringUtils.isNotEmpty(requestDTO.getNewPassword())) {
            query.setParameter(5, requestDTO.getNewPassword());
            query.setParameter(6, id);
        } else {
            query.setParameter(5, requestDTO.getPassword());
            query.setParameter(6, id);
        }

        query.executeUpdate();
    }
}
