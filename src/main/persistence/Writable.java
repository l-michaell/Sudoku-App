package persistence;

import org.json.JSONObject;

public interface Writable {
    // EFFECTS: returns this as JSON object
    // Based on JsonSerializationDemo shown in the Workroom example for CPSC 210
    JSONObject toJson();
}
