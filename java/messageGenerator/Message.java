package messageGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class Message {

    private MessageType messageType;
    private String context;
    private LocalDateTime localDateTime;

    public Message(MessageType messageType, String context, LocalDateTime localDateTime) {
        this.messageType = messageType;
        this.context = context;
        this.localDateTime = localDateTime;
    }
}
