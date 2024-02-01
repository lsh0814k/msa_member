package fem.member.domain.model.vo;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Embeddable
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PRIVATE)
@EqualsAndHashCode(of = "point")
public class Point {
    private long point;

    public Point addPoint(long point) {
        if (point <= 0) {
            throw new IllegalArgumentException("적립 포인트는 0 이거나 0 보다 작을 수 없습니다.");
        }
        return new Point(this.point + point);
    }

    public Point removePoint(long point) {
        if (point > this.point) {
            throw new IllegalArgumentException("기존 보유 Point보다 적어 차감할 수 없습니다.");
        }

        return new Point(this.point - point);
    }

    public static Point create(long point) {
        return new Point(point);
    }
}
