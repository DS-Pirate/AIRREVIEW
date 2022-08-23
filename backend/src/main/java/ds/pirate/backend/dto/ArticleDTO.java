package ds.pirate.backend.dto;

import java.util.List;

import ds.pirate.backend.entity.HashTags;
import ds.pirate.backend.entity.ImagesList;
import ds.pirate.backend.entity.acomments;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleDTO {

    private Long aid;
    private String atitle;
    private String context;
    private boolean opened;
    private boolean shareable;
    private List<String> tags;
    private List<ImagesList> images;
    private List<acomments> cgroup;
}
