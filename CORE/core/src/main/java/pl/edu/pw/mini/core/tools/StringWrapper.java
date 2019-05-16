package pl.edu.pw.mini.core.tools;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StringWrapper {
    private String value;

    public static StringWrapper fromValue(String value) {
        return new StringWrapper(value);
    }
}
