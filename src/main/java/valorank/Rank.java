package valorank;

import lombok.Data;

@Data
public class Rank {
    private final  String id;
    private final  String name;
    private final Type type;

    public static enum Type{
        UNRATED,IRON,BRONZE,SILVER,GOLD,PLATINUM,DIAMOND,IMMORTAL,RADIANT;
    }
}
