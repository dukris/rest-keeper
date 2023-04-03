package by.bsuir.restkeeper.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Artifact {

    private String filename;
    private byte[] bytes;

}
