public class ItemDTO {
   private String id;
   private String desc;
   private double price;
   private double qty;

    public ItemDTO() {

    }

    public ItemDTO(String id, String desc, double price, double qty) {
        this.id = id;
        this.desc = desc;
        this.price = price;
        this.qty = qty;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "id=" + id +
                ", desc='" + desc + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                '}';
    }
}
