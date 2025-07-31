package deser_java_lab.data.productcatalog;

import deser_java_lab.db.JdbcConnectionBuilder;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.io.Serializable;
import java.sql.*;

public class ProductTemplate implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String id;
    private transient Product product;

    public ProductTemplate(String id) {
        this.id = id;
    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        JdbcConnectionBuilder cb = JdbcConnectionBuilder.from(
            "org.postgresql.Driver",
            "postgresql",
            "db",
            5432,
            "postgres",
            "postgres",
            "password").withAutoCommit();

        try (Connection c = cb.connect(30);
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(String.format("SELECT * FROM products WHERE id = '%s' LIMIT 1", id))
        ) {
            if (rs.next()) product = Product.from(rs);
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }

    public String getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return product == null ? "not loaded" : product.toString();
    }
}
