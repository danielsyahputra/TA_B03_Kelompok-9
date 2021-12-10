package apap.tugas.siretail.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ItemModel {
    String uuid;
    String nama;
    int harga;
    int stok;
    String kategori;
}
