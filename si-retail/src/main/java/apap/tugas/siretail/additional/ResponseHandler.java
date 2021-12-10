package apap.tugas.siretail.additional;

import apap.tugas.siretail.model.ItemCabangModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    int status;
    String message;
    ItemCabangModel result;

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object result) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("result", result);

        return new ResponseEntity<Object>(map, status);
    }
}
