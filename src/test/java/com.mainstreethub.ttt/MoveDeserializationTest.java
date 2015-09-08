import com.mainstreethub.ttt.game.Move;

import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MoveDeserializationTest{

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void deserializesFromJSON() throws Exception {
        final Move move = new Move(1,2,"foo");
            assertThat(MAPPER.readValue(fixture("fixtures/move.json"), Move.class))
                .isEqualTo(move);
    }
}