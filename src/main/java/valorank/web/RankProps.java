package valorank.web;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "valorank.rankname")
@Data
public class RankProps {

    private int pageSize = 10;
}
