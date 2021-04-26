package sit.int221.cars.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Brand {
  @Id
  private long brandid;
  private String brandname;
  @JsonBackReference
  @OneToMany(mappedBy = "brand",fetch = FetchType.LAZY)
  private List<Product> productList;


  public long getBrandid() {
    return brandid;
  }

  public void setBrandid(long brandid) {
    this.brandid = brandid;
  }


  public String getBrandname() {
    return brandname;
  }

  public void setBrandname(String brandname) {
    this.brandname = brandname;
  }

  public List<Product> getProductList() { return productList; }

  public void setProductList(List<Product> productList) { this.productList = productList; }
}
