package sit.int221.cars.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Color {
  @Id
  private long colorid;
  private String colorname;
  private String colorcode;
  @ManyToMany(mappedBy = "productcolor", fetch = FetchType.LAZY)
  private List<Product> productList;


  public long getColorid() {
    return colorid;
  }

  public void setColorid(long colorid) {
    this.colorid = colorid;
  }


  public String getColorname() {
    return colorname;
  }

  public void setColorname(String colorname) {
    this.colorname = colorname;
  }


  public String getColorcode() {
    return colorcode;
  }

  public void setColorcode(String colorcode) {
    this.colorcode = colorcode;
  }

}
