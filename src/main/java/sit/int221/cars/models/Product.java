package sit.int221.cars.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Product {
  @Id
  private long productid;
  private String productname;
  private long power;
  private long torque;
  private double weight;
  private String transmission;
  private java.sql.Date yom;
  private String description;
  private String img;
  @ManyToOne
  private Brand brand;
  @ManyToMany
  @JoinTable(
          name = "productcolor",
          joinColumns = @JoinColumn(name = "productid"),
          inverseJoinColumns = @JoinColumn(name = "colorid"))
  private List<Color> colorList;


  public long getProductid() {
    return productid;
  }

  public void setProductid(long productid) {
    this.productid = productid;
  }


  public String getProductname() {
    return productname;
  }

  public void setProductname(String productname) {
    this.productname = productname;
  }


  public long getPower() {
    return power;
  }

  public void setPower(long power) {
    this.power = power;
  }


  public long getTorque() {
    return torque;
  }

  public void setTorque(long torque) {
    this.torque = torque;
  }


  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }


  public String getTransmission() {
    return transmission;
  }

  public void setTransmission(String transmission) {
    this.transmission = transmission;
  }


  public java.sql.Date getYom() {
    return yom;
  }

  public void setYom(java.sql.Date yom) {
    this.yom = yom;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public String getImg() { return img; }

  public void setImg(String img) { this.img = img; }


  public Brand getBrand() { return brand; }

  public void setBrand(Brand brand) { this.brand = brand; }


  public List<Color> getColorList() { return colorList; }

  public void setColorList(List<Color> colorList) { this.colorList = colorList; }
}
