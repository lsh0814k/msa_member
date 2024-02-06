package fem.member.domain.model.event;


import fem.member.domain.model.vo.IDName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
@Builder(access = PROTECTED)
public class ItemRented implements Serializable {
    private IDName idName;
    private Item item;
    private long point;

    public static ItemRented create(IDName idName, Item item, long point) {
        return ItemRented
                .builder()
                .idName(idName)
                .item(item)
                .point(point)
                .build();
    }
}
