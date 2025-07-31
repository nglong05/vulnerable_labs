package deser_java_lab.server.controller;

import deser_java_lab.data.productcatalog.ProductTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

@RestController
public class ProductController {

    @PostMapping("/product/deser")
    public Object deser(@RequestParam(required = false) String template, @RequestParam(required = false) String id) throws Exception {
        if (id != null && id.matches("p([1-9]|1[0-9])")) { // no sqli hehe
            var baos = new ByteArrayOutputStream();
            try (var oos = new ObjectOutputStream(baos)) {
                oos.writeObject(new ProductTemplate(id));
            }
            template = Base64.getEncoder().encodeToString(baos.toByteArray());
        }

        byte[] data = Base64.getDecoder().decode(template);
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(data));
        Object o = in.readObject();
        
        if (o instanceof ProductTemplate pt && pt.getProduct() != null) {
            return pt.getProduct();
        }

        // fallback
        return o.toString();
    }
}
