package ds.pirate.backend.service.EmbedService;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ds.pirate.backend.repository.ArticleRepository;
import ds.pirate.backend.vo.Embed;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class EmbedServiceImpl implements EmbedService{
    private final ArticleRepository arepo;

    @Override
    public Embed getEmbedInfo(Long article) {
        List<String> basic = new ArrayList<>();
        basic.add("basic.png'");
        ArticleRepository.getEmbedInformation geted = arepo.getEmbedInfoByAid(article).get();
        Embed result = new Embed(geted);
        log.info(result);
        if(result.getImageList() == null){
            result.setImageList(basic);
        }


        return result;
    }

}
