import org.springframework.stereotype.Service;

import com.viasoft.desafioBackEnd.model.EmailData;

@Service
public class ConvertDataService {
    public String convertData(EmailData emailData) {

        return "Converted Data: " + serializeData(emailData);
    }

    private String serializeData(EmailData emailData) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = objectMapper.writeValueAsString(emailData);
        } catch (JsonProcessingException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return jsonString;
    }
}
