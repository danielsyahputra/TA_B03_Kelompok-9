package apap.tugas.siretail.additional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CabangDetail {
    private Integer id;
    private String nama;
    private String noTelepon;
    private Integer ukuran;
    private Integer jumlahItem;
    private Integer status;
}
