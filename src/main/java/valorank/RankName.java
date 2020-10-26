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

    @NotNull(message = "In-Game-Name is required")
    @Size(min = 2, message = "Name must be at least 2 characters long")
    private String name;

    @NotNull( message = "Please enter rough estimate of ranked games played")
    private int gamesPlayed;

    @NotNull(message = "Please enter number of wins in the last ten ranked games played")
    private int lastTenGameW;

    //could add a method to count num of days in specific rank

    //OnetoOne????? One IGN can only have rank
    @ManyToOne(targetEntity = Rank.class)

    private Rank ranks;


    @PrePersist
    void createdAt(){
        this.createdAt = LocalDateTime.now();
    }
}