package deser_java_lab.data.productcatalog;

import java.sql.ResultSet;
import java.sql.SQLException;

public record Product(String id, String name, String description, int price) {
    public static Product from(ResultSet rs) throws SQLException {
    return new Product(
        rs.getString("id"),
        rs.getString("name"),
        rs.getString("description"),
        rs.getInt("price")
    );
}
}
