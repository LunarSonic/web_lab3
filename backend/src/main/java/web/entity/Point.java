package web.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Point {
    private Long id;
    private float x;
    private float y;
    private float r;
    private boolean hit;
    private String serverTime;
    private long scriptTime;
    @JsonIgnore
    private User user;
}
