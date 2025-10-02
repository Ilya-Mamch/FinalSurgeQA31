package dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Workout {

    @Builder.Default
    String date = "";
    @Builder.Default
    String workoutName = "";
    @Builder.Default
    String description = "";
    @Builder.Default
    String distance = "";
    @Builder.Default
    boolean selectFartlek = false;
}

