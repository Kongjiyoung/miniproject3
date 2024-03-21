package com.many.miniproject1.backup.offer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OfferRepository {
    private final EntityManager em;


    public List<Offer> findAll() {
        Query query = em.createNativeQuery("SELECT * FROM offer_tb", Offer.class);

        return query.getResultList();
    }

    public OfferResponse.OfferResumeDetailDTO companyOfferResumeDetail(int cid, int rid){
        String q = """
                SELECT      r.title, u.username, u.birth, u.tel, u.address, u.email, r.career, r.simple_introduce, r.portfolio, r.introduce, r.profile 
                FROM        offer_tb o
                INNER JOIN  resume_tb r
                ON          o.resume_id = r.id
                INNER JOIN  user_tb u
                ON          r.person_id = u.id
                WHERE       o.company_id = ? AND o.resume_id = ?;
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1,cid);
        query.setParameter(2,rid);

        Object[] row = (Object[]) query.getSingleResult();

        String title = (String) row[0];
        String username = (String) row[1];
        String birth = (String) row[2];
        String tel = (String) row[3];
        String address = (String) row[4];
        String email = (String) row[5];
        String career = (String) row[6];
        String simpleIntroduce = (String) row[7];
        String portfolio = (String) row[8];
        String introduce = (String) row[9];
        String profile = (String) row[10];

        OfferResponse.OfferResumeDetailDTO responseDTO = new OfferResponse.OfferResumeDetailDTO();

        responseDTO.setTitle(title);
        responseDTO.setUsername(username);
        responseDTO.setBirth(birth);
        responseDTO.setTel(tel);
        responseDTO.setAddress(address);
        responseDTO.setEmail(email);
        responseDTO.setCareer(career);
        responseDTO.setSimpleIntroduce(simpleIntroduce);
        responseDTO.setPortfolio(portfolio);
        responseDTO.setIntroduce(introduce);
        responseDTO.setProfile(profile);

        return responseDTO;
        }

    public List<OfferResponse.OfferResumeDTO> personFindAllOffer(int cid) {
        String q ="""
                SELECT      r.id, r.person_id, r.title, r.profile, r.portfolio, r.introduce, r.career, r.simple_introduce, r.created_at, u.username
                FROM        offer_tb o
                INNER JOIN  resume_tb r
                ON          o.resume_id = r.id
                INNER JOIN  user_tb u
                ON          r.person_id = u.id
                WHERE       o.company_id = ?
                """;
        Query query = em.createNativeQuery(q);

        query.setParameter(1,cid);

        List<Object[]> resultList = query.getResultList();

        List<OfferResponse.OfferResumeDTO> responseList = new ArrayList<>();
        for (Object[] row : resultList){
            Integer id = (Integer) row[0];
            Integer personId = (Integer) row[1];
            String title = (String) row[2];
            String profile = (String) row[3];
            String portfolio = (String) row[4];
            String introduce = (String) row[5];
            String career = (String) row[6];
            String simpleIntroduce = (String) row[7];
            Timestamp createdAt = (Timestamp) row[8];
            String username = (String) row[9];

            OfferResponse.OfferResumeDTO responseDTO = new OfferResponse.OfferResumeDTO();
            responseDTO.setId(id);
            responseDTO.setPersonId(personId);
            responseDTO.setTitle(title);
            responseDTO.setProfile(profile);
            responseDTO.setPortfolio(portfolio);
            responseDTO.setIntroduce(introduce);
            responseDTO.setCareer(career);
            responseDTO.setSimpleIntroduce(simpleIntroduce);
            responseDTO.setCreatedAt(createdAt);
            responseDTO.setUsername(username);

            responseList.add(responseDTO);
        }


        return responseList;
    }

    public List<OfferResponse.companyFindAllOfferDTO> companyFindAllOffer() {
        String q = """
               SELECT *
               FROM         offer_tb o
               INNER JOIN   resume_tb
               ON           o.resume_id = r.id
               """;

        Query query = em.createNativeQuery(q,OfferResponse.companyFindAllOfferDTO.class);
        try {
            Offer offer = (Offer) query.getSingleResult();
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public Offer findById(int id) {
        Query query = em.createNativeQuery("SELECT * FROM offer_tb WHERE id=?", Offer.class);
        query.setParameter(1, id);

        try {
            Offer offer = (Offer) query.getSingleResult();
            return offer;
        } catch (Exception e) {
            return null;
        }

    }

    public List<OfferResponse.OfferBoardDTO> findCompanyOffersWithId(int oid) {
        String q = """
                SELECT      ot.id, ut.company_name, ot.post_id, pt.title, ot.created_at , pt.profile, 
                FROM        offer_tb ot 
                INNER JOIN  user_tb ut ON ot.company_id = ut.id 
                INNER JOIN  post_tb pt ON ot.post_id = pt.id 
                WHERE       ot.person_id= ?
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, oid);

        List<Object[]> resultList = query.getResultList();

        List<OfferResponse.OfferBoardDTO> responseList = new ArrayList<>();
        for (Object[] row : resultList) {
            Integer id = (Integer) row[0];
            String companyName = (String) row[1];
            Integer postId = (Integer) row[2];
            String title = (String) row[3];
            Timestamp createdAt = (Timestamp) row[4];
            String profile = (String) row[5];

            OfferResponse.OfferBoardDTO responseDTO1 = new OfferResponse.OfferBoardDTO();

            responseDTO1.setId(id);
            responseDTO1.setCompanyName(companyName);
            responseDTO1.setPostId(postId);
            responseDTO1.setTitle(title);
            responseDTO1.setCreatedAt(createdAt);
            responseDTO1.setProfile(profile);

            responseList.add(responseDTO1);
        }
        return responseList;
    }
//    public Offer List<Offer> fintAllSelect(int id) {
//        Query query = em.createNativeQuery("SELECT * FROM offer_tb WHERE id=?", Of);
//        query.setParameter(1, id);
//
//        Offer offer = (Offer) query.getSingleResult();
//
//        return offer;
//    }


    //공지영 작성 지우지말기 offer_tb 저장
    @Transactional
    public void save(OfferRequest.SaveDTO requestDTO) {
        Query query = em.createNativeQuery("INSERT INTO offer_tb(resume_id, post_id, company_id, person_id, created_at) VALUES (?,?,?,?,now())");
        query.setParameter(1, requestDTO.getResumeId());
        query.setParameter(2, requestDTO.getPostId());
        query.setParameter(3, requestDTO.getCompanyId());
        query.setParameter(4, requestDTO.getPersonId());


        query.executeUpdate();
    }

    @Transactional
    public void update(OfferRequest.UpdateDTO requestDTO, int id) {
        Query query = em.createNativeQuery("UPDATE offer_tb SET where id = ?");
        query.setParameter(1, id);

        query.executeUpdate();
    }

    @Transactional
    public void delete(int id) {
        Query query = em.createNativeQuery("DELETE FROM offer_tb WHERE id = ?");
        query.setParameter(1, id);

        query.executeUpdate();
    }

    @Transactional
    public void offerDelete(int rid, int cid) {
        Query query = em.createNativeQuery("DELETE FROM offer_tb WHERE resume_id = ? AND company_id = ? ");
        query.setParameter(1, rid);
        query.setParameter(2, cid);

        query.executeUpdate();
    }
}
