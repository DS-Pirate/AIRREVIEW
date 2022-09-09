package ds.pirate.backend.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ds.pirate.backend.entity.ArticlesList;
import ds.pirate.backend.entity.acomments;
import ds.pirate.backend.entity.airUser;

public interface CommentRepository extends JpaRepository<acomments, Long>{
    

    @Query("SELECT ct FROM acomments ct WHERE articles_aid=:aid ORDER BY comment_group asc, comment_sorts asc, commnet_depth desc ")
    Optional<List<acomments>> getListByAid(Long aid);

    @Query("SELECT ct FROM acomments ct WHERE ct.cid=:cid and airuser_userid=:userid ")
    Optional<acomments> getCommentByCidAndUserid(Long cid, Long userid);

    @Query("SELECT AVG(articleRate) FROM acomments ct WHERE articles_aid=:aid and articleRate>0")
    Double getAvgByAid(Long aid);

    Optional<acomments> findByAiruser(airUser airuser);

    @Query("SELECT ct FROM acomments ct WHERE articles_aid=:aid and airuser_userid=:userid ")
    Optional<acomments> getCommentByAidAndUserid(ArticlesList aid, airUser userid);


    @Query(value = "SELECT ct FROM acomments ct WHERE articles_aid=:aid ORDER BY comment_group asc, comment_sorts asc, commnet_depth desc ", 
    countQuery = "select count(ct) from acomments ct WHERE articles_aid=:aid ORDER BY comment_group asc, comment_sorts asc, commnet_depth desc ")
    Page<acomments> getPageList(Pageable pageable, Long aid);
}
