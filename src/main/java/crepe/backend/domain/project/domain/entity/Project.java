package crepe.backend.domain.project.domain.entity;

import crepe.backend.domain.branch.domain.entity.Branch;
import crepe.backend.domain.project.dto.ProjectCreateRequest;
import crepe.backend.global.domain.BaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE project SET is_active = false WHERE id=?")
@Table(name = "project")
public class Project extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid", columnDefinition = "BINARY(16)", nullable = false, unique = true)
    private UUID uuid;

    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @Column(name = "intro", length = 500)
    private String intro;

    @Column(name = "preview", length = 200)
    private String preview;

    @OneToMany(mappedBy = "project")
    private List<UserProject> userProjects = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    private List<Branch> branches = new ArrayList<>();

    @Builder
    public Project(String name, String intro, String preview){
        this.name = name;
        this.intro = intro;
        this.preview = preview;
        super.isActive = true;
        this.uuid = UUID.randomUUID();

    }

    public interface ProjectInfoMapping { // 프로젝트 아이디만 가져오기 위한 인터페이스 셍성
        Long getId();
    }

    public void updatePreview(String preview) {
        this.preview = preview;
    }
}
