package ds.pirate.backend.service.SettingService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ds.pirate.backend.dto.ArticleDTO;
import ds.pirate.backend.dto.airUserDTO;
import ds.pirate.backend.entity.ArticlesList;
import ds.pirate.backend.entity.airUser;
import ds.pirate.backend.repository.ArticleReportRepository;
import ds.pirate.backend.repository.ArticleRepository;
import ds.pirate.backend.repository.ArticleRepository.getMySettingArticleList;
import ds.pirate.backend.repository.LikeUnlikeRepository;
import ds.pirate.backend.repository.UserRepository;
import ds.pirate.backend.repository.ArticleReportRepository.getMySettingReportList;
import ds.pirate.backend.service.ArticleService.ArticleService;
import ds.pirate.backend.service.UserService.UserService;
import ds.pirate.backend.vo.userid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class SettingServiceImpl implements SettingService {
  private final UserRepository urepo;
  private final UserService uService;
  private final PasswordEncoder encoder;
  private final ArticleRepository arepo;
  private final ArticleService aService;
  private final LikeUnlikeRepository lrepo;
  private final ArticleReportRepository rrepo;

  @Override
  public airUserDTO getByUserId(Long userid) {
    airUserDTO result = uService.entityToDTO(urepo.findByUserId(userid).get());
    return result;
  }

  @Override
  public String changePasswd(userid vo) {
    Optional<airUser> result = urepo.findByUserId(vo.getUserid());
    if (encoder.matches(vo.getCpasswd(), result.get().getPasswd())) {
      urepo.changePasswdbyIdAndcpass(vo.getUserid(),
          encoder.encode(vo.getUpasswd()),
          vo.getName(), vo.getEmail(), vo.getUserintro(), vo.getBirthDay());
      return "설정을 변경했습니다";
    } else {
      return "다시 설정해주세요";
    }
  }

  @Override
  public HashMap<String, Object> settingArticleList(Long userid) {
    List<Integer> countlist = new ArrayList<>();
    List<ArticleDTO> result = arepo.getListbyuserId2(userid).get().stream()
        .map((Function<ArticlesList, ArticleDTO>) v -> {
          aService.EntityToDTO(v);
          Optional<Integer> tmp = lrepo.checkFavoLogByUserIdAndArticleId2(userid, v.getAid());
          if (!tmp.isPresent()) {
            countlist.add(tmp.get());
          } else {
            countlist.add(0);
          }

          return aService.EntityToDTO(v);
        }).collect(Collectors.toList());
    HashMap<String, Object> hash = new HashMap<>();
    hash.put("article", result);
    hash.put("count", countlist);
    return hash;
  }

  @Override
  public Page<getMySettingArticleList> articleListByUserid(Long userid, Integer pageNum) {
    Pageable pageable = PageRequest.of(pageNum, 10);
    log.info("page:::::::::::::" + pageNum);
    return arepo.getSettingArticleListByUserIdWithPageable(userid, pageable);
  }

  @Override
  public Page<getMySettingReportList> reportListByUserid(Long userid, Integer pageNum) {
    Pageable pageable = PageRequest.of(pageNum, 10);
    log.info("page:::::::::::::" + pageNum);
    return rrepo.getSettingReportListByUserIdWithPageable(userid, pageable);
  }
}
