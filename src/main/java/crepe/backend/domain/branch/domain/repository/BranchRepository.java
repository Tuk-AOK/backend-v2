package crepe.backend.domain.branch.domain.repository;


import crepe.backend.domain.branch.domain.entity.Branch;
import crepe.backend.domain.project.domain.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
    List<Branch> findAllByProjectAndIsActiveTrue(Project projectByUuid);

    Optional<Branch> findBranchByIdAndIsActiveTrue(Long branchId);

    Optional<Branch> findBranchByUuidAndIsActiveTrue(UUID uuid);

    Optional<Branch> findBranchByProjectAndIsActiveTrueAndName(Project project, String name);
  
    Page<Branch> findAllByProjectAndIsActiveTrueOrderByIdDesc(Project project, Pageable pageable);

    List<Branch> findAllByProjectIdAndIsActiveTrueOrderByCreatedAt(Long projectId);
}
