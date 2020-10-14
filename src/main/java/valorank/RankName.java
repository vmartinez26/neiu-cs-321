package valorank;

import lombok.Data;
import valorank.Rank;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
//should have no args constructor
public class RankName {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime createdAt;

    @NotNull(message = "Name is required")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    //OnetoOne????? One IGN can only have rank
    @ManyToMany(targetEntity = Rank.class)
    @NotEmpty(message = "You must choose one rank")
    private List<Rank> ranks;

    @PrePersist
    void createdAt(){
        this.createdAt = LocalDateTime.now();
    }
}
